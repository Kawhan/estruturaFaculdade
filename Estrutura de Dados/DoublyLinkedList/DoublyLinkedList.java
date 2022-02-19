import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class DoublyLinkedList<Item> implements Iterable<Item> {
    private int n;
    private Node pre;
    private Node post;

    public DoublyLinkedList(){
        pre = new Node();
        post = new Node();
        pre.next = post;
        post.prev = pre;
    }

    private class Node{
        private Item item;
        private Node next;
        private Node prev;
    }

    public boolean isEmpty() {return n == 0;}
    public int size() {return n;}

    public void add(Item item){
        Node oldlast = post.prev;
        Node x = new Node();

        x.item =item;

        x.next = post;
        x.prev = oldlast;

        post.prev = x;
        oldlast.next = x;
        n++;
    }

    public ListIterator<Item> iterator(){ return new DoublyLinkedListIterator();}

    private class DoublyLinkedListIterator implements ListIterator<Item>{
        private Node current = pre.next;
        private Node lastAccessed = null;
        private int index = 0;

        public boolean hasNext(){ return index < n;}
        public boolean hasPrevious() {return index > 0;}
        public int previousIndex(){return index - 1;}
        public int nextIndex(){return index;}

        public Item next(){
            if(!hasNext()) throw new NoSuchElementException();
            lastAccessed = current;
            Item item = current.item;
            current = current.next;
            index++;
            return item;
        }

        public Item previous(){
            if(!hasPrevious()) throw new NoSuchElementException();
            current = current.prev;
            index--;
            lastAccessed = current;
            return current.item;
        }

        public void set(Item item){
            if(lastAccessed == null) throw new IllegalStateException();
            lastAccessed.item = item;
        }

        public void remove(){
            if(lastAccessed == null) throw new IllegalStateException();
            Node x = lastAccessed.prev;
            Node y = lastAccessed.next;
            x.next = y;
            y.prev = x;
            n--;
            if(current == lastAccessed)
                current = y;
            else
                index--;
            lastAccessed = null;
        }

        public void add(Item item){
            Node ant = current.prev;
            Node y = new Node();
            Node atual = current;

            y.item = item;
            ant.next = y;
            y.next = atual;
            atual.prev = y;
            y.prev = ant;

            n++;
            index++;
            lastAccessed = null;
        }
    }
    public String toString(){
        StringBuilder s = new StringBuilder();
        for(Item item: this)
            s.append(item+" ");
        return s.toString();
    }

    public static void main(String[] args){
        Random rand = new Random();
        int n = Integer.parseInt(args[0]);

        System.out.println(n+" inteiros menores que 100");
        DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();

        for(int i=0;i<n;i++)
            list.add(rand.nextInt(100));
        System.out.print("Lista Original: ");
        System.out.print(list);
        System.out.println();

        ListIterator<Integer> iterator = list.iterator();

        System.out.println("Some 1 usando next e set");
        while(iterator.hasNext()){
            int x = iterator.next();
            iterator.set(x+1);
            System.out.print("Inserindo ");
            System.out.println(x+1);
        }
        System.out.print("Lista: ");
        System.out.println(list);

        System.out.println("multiplicando por 3 usando previous e set");
        while(iterator.hasPrevious()){
            int x = iterator.previous();
            iterator.set(x+x+x);
            System.out.print("Inserindo ");
            System.out.println(x+x+x);
        }
        System.out.print("Lista: ");
        System.out.println(list);
        System.out.println();

        System.out.println("Remove os elementos que sao multiplos de 4 usando next e remove");
        while(iterator.hasNext()){
            int x = iterator.next();
            if(x%4 == 0) iterator.remove();
        }
        System.out.print("Lista: ");
        System.out.println(list);
        System.out.println();

        System.out.println("remove os pares usando previous e remove");
        while(iterator.hasPrevious()){
            int x = iterator.previous();
            if(x%2 == 0) iterator.remove();
        }
        System.out.print("Lista: ");
        System.out.println(list);
        System.out.println();
    }
}
