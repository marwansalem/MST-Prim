
import java.util.ArrayList;
import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Comparator;

import javafx.util.Pair;

public class PrimGraph {

    public int [] Parent; // Index of parent Node
    public int [] Key;    // cost of joining each vertex to the Tree
    int Source;
    public ArrayList<Pair<Integer,Integer>> []graph;
    public PrimGraph(int n){
        graph = new ArrayList[n];
        for(int i = 0;i<n;i++){
            graph[i] = new ArrayList<>();
        }
        Parent = new int[n];
        Key = new int[n];

    }
    public int getVertices() {
        return graph.length;
    }
    public void setSource(int Source){
        this.Source = Source;
    }
    public int getSource(){
        return this.Source;
    }
    public void addEdge(int to,int from,int weight){
        graph[from].add(new Pair<>(weight,to));
        graph[to].add(new Pair<>(weight,from)); //Graph is unidirected so Relation is Symmetric
    }
    public static PrimGraph InputGraph(){
        Scanner sc = new Scanner(System.in);
        int n,m,s;
        System.out.println("Input Number of Vertices(N),Number of Edges(M) and Source vertex(S)");
        System.out.println("Then for the next M lines enter Edge :source,destination and weight");
        n = sc.nextInt() ;
        m = sc.nextInt();
        s = sc.nextInt();
        PrimGraph Graph = new PrimGraph(n);
        Graph.setSource(s);
        //System.out.println(n+" "+m+" "+s);
        for(int i =0;i<m;i++) {
            int from, to, weight;
            from = sc.nextInt();
            to = sc.nextInt();
            weight = sc.nextInt();


            Graph.graph[from].add(new Pair<>(weight,to));
            Graph.graph[to].add(new Pair<>(weight,from));

        }
        return Graph;
    }
    public static void MinimumCostSpanningTree(PrimGraph G){

        int vertices = G.getVertices();
        boolean []mst = new boolean[vertices];
        int source = G.getSource();
        for(int i = 0;i<vertices;i++){
            G.Key[i] = Integer.MAX_VALUE;

        }
        G.Key[source] = 0;

        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(vertices, new Comparator<Pair<Integer, Integer>>() {
            @Override

            public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) { //Key is Weight
                //Comparison according to weight which is the key
                int key1 = p1.getKey();
                int key2 = p2.getKey();
                return key1-key2;
            }
        });

        pq.offer(makePair(G.Key[source],source));
        G.Parent[source] = -1;  // can be used to know which vertex is the Root of the tree ( in traversals)

        while(!pq.isEmpty()){
            Pair<Integer,Integer> minPair = pq.poll();
            int minVertex = minPair.getValue();
            mst[minVertex] = true;

            // for each vertex adjacent to thi extracted vertex : relax and do stuff
            for(int j = 0 ;j<G.graph[minVertex].size();j++){
                Pair v = G.graph[minVertex].get(j);
                int dest = (int)v.getValue(); //dest is index of adjacent vertex
                int w    = (int)v.getKey();   // weight of the edge connecting extracted minimum vertex and its adjacent vertex
                if(mst[dest] == false &&G.Key[dest]>w){
                    G.Key[dest] = w;
                    pq.offer(makePair(w,dest));
                    G.Parent[dest] = minVertex;
                }
            }

        }
    }
    public static void printPrimTree(PrimGraph G){
        int minimumCost = 0;
        int s = G.getSource();
        System.out.printf("Root %d\n",G.getSource());
        for(int i =0;i<G.getVertices();i++){
            if(i == s)
                continue;
            System.out.printf("Vertex:%d - Parent:%d \n",i,G.Parent[i]);
            minimumCost+= G.Key[i];
        }
        System.out.println("Total Cost:"+minimumCost);
    }

    public static Pair makePair(int first,int second){ // first is
        return new Pair<Integer,Integer>(first,second);
    }

}
