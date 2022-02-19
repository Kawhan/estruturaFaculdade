import java.util.NoSuchElementException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Graph {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;
    private int E;
    private Queue<Integer>[] adj;

    public Graph(int V){
        if(V<0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
        this.V = V;
        this.E = 0;
        adj = (Queue<Integer>[]) new Queue[V];
        for(int v = 0; v < V; v++){
            adj[v] = new Queue<Integer>();
        }
    }

    public Graph(String arg){
        try{
            File myinput = new File(arg);
            Scanner myReader = new Scanner(myinput);
            String data;
            StringTokenizer st;

            data = myReader.nextLine();
            this.V = Integer.parseInt(data);
            if(V < 0) throw new IllegalArgumentException("Number of vertices in a Graph must be nonnegative");
            adj = (Queue<Integer>[]) new Queue[V];
            for(int v=0; v < V; v++){
                adj[v] = new Queue<Integer>();
            }
            data = myReader.nextLine();
            int E = Integer.parseInt(data);
            if(E < 0) throw new IllegalArgumentException("Number of edges in a Graph must be nonnegative");
            for(int i=0; i<E; i++){
                data = myReader.nextLine();
                st = new StringTokenizer(data);
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                validateVertex(v);
                validateVertex(w);
                addEdge(v, w);
            }
        }catch(Exception e){
            throw new IllegalArgumentException("invalid input format in Graph constructor", e);
        }
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    private void validateVertex(int v){
        if(v < 0 || v >= V) throw new IllegalArgumentException("vertex "+v+" is not between 0 and "+(V-1));
    }

    public void addEdge(int v, int w){
        validateVertex(v);
        validateVertex(w);
        E++;
        adj[v].enqueue(w);
        adj[w].enqueue(v);
    }

    public Iterable<Integer> adj(int v){
        validateVertex(v);
        return adj[v];
    }

    public int degree(int v){
        validateVertex(v);
        return adj[v].size();
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append(V+ " vertices, "+ E +" arestas "+ NEWLINE);
        for(int v=0; v<V; v++){
            s.append(v + ": ");
            for(int w : adj[v]){
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    public static void main(String[] args){
        
        Graph G = new Graph(args[0]);
        System.out.println(G);
    }

}
