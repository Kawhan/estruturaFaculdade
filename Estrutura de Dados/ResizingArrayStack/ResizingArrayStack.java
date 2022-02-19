
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ResizingArrayStack<Item> implements Iterable<Item>{
    private Item[] a;
    private int N;

    

    public ResizingArrayStack(){
        a = (Item[]) new Object[2];
        N = 0;
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    private void resize(int capacity){
        assert capacity >= N;
        Item[] temp = (Item[]) new Object[capacity];
        for(int i=0; i< N;i++){
            temp[i] = a[i];
        }
        a = temp;
    }

    public void push(Item item){
        if(N == a.length) resize(2*a.length);
        a[N] = item;
        N++;
    }

    public Item pop(){
        if(isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = a[N-1];
        a[N-1] = null;
        N--;
        if(N > 0 && N == a.length/4) resize(a.length/2);
        return item;
    }

    public Item peek(){
        if(isEmpty()) throw new NoSuchElementException("Stack underflow");
        return a[N-1];
    }

    public Iterator<Item> iterator(){
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item>{
        private int i;

        public ReverseArrayIterator(){
            i= N-1;
        }

        public boolean hasNext(){
            return i >= 0;
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }

        public Item next(){
            if(!hasNext()) throw new NoSuchElementException();
            return a[i--];
        }  
    }
    public static void main(String[] args){
        ResizingArrayStack<String> s = new ResizingArrayStack<>();

        Scanner myScan = new Scanner(System.in);
        String line, tmp;
        StringTokenizer mytk;
        while(myScan.hasNext()){
            line = myScan.next();
            mytk = new StringTokenizer(line);

            tmp = mytk.nextToken();
            if(tmp.equals("-")){
                if(!s.isEmpty()) System.out.print(s.pop()+" ");
            }
            else{
                s.push(tmp);
            }
        }
        System.out.println("Sobraram "+s.size()+" Elementos na Pilha");
    }
}
