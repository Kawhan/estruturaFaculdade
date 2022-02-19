public class BreadthFirstPaths {
    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;

    public BreadthFirstPaths(Graph G, int s){
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];
        validateVertex(s);
        bfs(G, s);

        assert check(G, s);
    }

    public BreadthFirstPaths(Graph G, Iterable<Integer> sources){
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];
        for(int v=0; v < G.V(); v++)
            distTo[v] = INFINITY;
        validateVertex(sources);
        bfs(G, sources);
    }

    private void bfs(Graph G, int s){
        Queue<Integer> q = new Queue<Integer>();
        for(int v = 0; v < G.V(); v++)
            distTo[v] = INFINITY;
        distTo[s] = 0;
        marked[s] = true;
        q.enqueue(s);

        while(!q.isEmpty()){
            int v = q.dequeue();
            for(int w : G.adj(v)){
                if(!marked[w]){
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
    }

    private void bfs(Graph G, Iterable<Integer> sources){
        Queue<Integer> q = new Queue<Integer>();
        for(int s : sources){
            marked[s] = true;
            distTo[s] = 0;
            q.enqueue(s);
        }
        while(!q.isEmpty()){
            int v = q.dequeue();
            for(int w : G.adj(v)){
                if(!marked[w]){
                    edgeTo[w] = v;
                    distTo[w] = distTo[v]+1;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v){
        return marked[v];
    }

    public int distTo(int v){
        validateVertex(v);
        return distTo[v];
    }

    private boolean check(Graph G, int s){
        if(distTo[s] != 0){
            System.out.println("Distancia da fonte "+ s + " para si mesma = "+ distTo[s]);
            return false;
        }
        for(int v=0; v < G.V(); v++){
            for(int w : G.adj(v)){
                if(hasPathTo(v) != hasPathTo(w)){
                    System.out.println("Aresta "+ v + "-" + w);
                    System.out.println("Existe caminho para("+ v + ") = "+hasPathTo(v));
                    System.out.println("Existe caminho para("+ w + ") = "+hasPathTo(w));
                    return false;
                }
                if(hasPathTo(v) && (distTo[w] > distTo[v]+1)){
                    System.out.println("Aresta "+ v + "-" + w);
                    System.out.println("Distancia["+ v + "] = "+distTo[v]);
                    System.out.println("Distancia["+ w + "] = "+distTo[w]);
                    return false;
                }
            }
        }
        for(int w = 0; w< G.V(); w++){
            if(!hasPathTo(w) || w == s) continue;
            int v = edgeTo[w];
            if(distTo[w] != distTo[v]+1){
                System.out.println("aresta de menor caminho "+ v + "-" + w);
                System.out.println("Distancia["+ v + "] = "+distTo[v]);
                System.out.println("Distancia["+ w + "] = "+distTo[w]);
                return false;
            }
        }
        return true;
    }

    private void validateVertex(int v){
        int V = marked.length;
        if(v < 0 || v >= V) throw new IllegalArgumentException("vertice "+ v + " nao esta entre 0 e "+ (V-1));
    }

    private void validateVertex(Iterable<Integer> vertices){
        if(vertices == null){
            throw new IllegalArgumentException("parametro nulo");
        }
        int V = marked.length;
        for(int v : vertices){
            if(v < 0 || v >= V){
                throw new IllegalArgumentException("vertices "+ v+" nao esta entre 0 "+ (V-1));
            }
        }
    }

    public void printPath(int s, int v){
        if(s == v){
            System.out.print(s);
        }else{
            if(!marked[v]) return;
            else{
                printPath(s, edgeTo[v]);
                System.out.print("-"+v);
            }
        }
    }

    public static void main(String[] args){
        Graph G = new Graph(args[0]);

        int s = Integer.parseInt(args[1]);
        BreadthFirstPaths bfs = new BreadthFirstPaths(G, s);

        for(int v = 0; v< G.V(); v++){
            if(bfs.hasPathTo(v)){
                System.out.print(s+" ate "+ v + " ("+bfs.distTo(v)+") - ");
                bfs.printPath(s, v);
                System.out.println();
            }else{
                System.out.print(s+ " e "+v+" nao estao ligados\n");
            }
        }
    }
}
