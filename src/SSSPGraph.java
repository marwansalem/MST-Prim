
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class SSSPGraph{

    private HashMap<Integer, Node> graphNodes = new HashMap<Integer, Node>();

    private class Node{

        private int id;
        private LinkedList<Edge> adjacent = new LinkedList<Edge>();
        private int distance;
        private Node parent;

        private Node(int id){

            this.id = id;

        }

    }

    private class Edge{

        private Node v;
        private int w;

        private Edge(Node v, int w){

            this.v = v;
            this.w = w;

        }

    }

    private class NodeComparator implements Comparator<Node>{

        public int compare(Node a, Node b) {

            if(a.distance < b.distance){
                return(-1);
            }
            else if(a.distance > b.distance){
                return(1);
            }
            return(0);
        }

    }

    public void addNode(int id){

        Node n = new Node(id);
        graphNodes.put(n.id, n);

    }

    private Node getNode(int id){

        return(this.graphNodes.get(id));

    }

    public void addEdge(int source, int destination, int weight){

        if(!graphNodes.containsKey(source) || !graphNodes.containsKey(destination)){

            System.out.println("Graph nodes missing");
            return;

        }

        graphNodes.get(source).adjacent.add(new Edge(graphNodes.get(destination), weight));

    }

    private void decreaseKey(PriorityQueue<Node> q, Node v){

        q.remove(v);
        q.add(v);

    }

    public void DijkstrasAlg(int source){

        if(!graphNodes.containsKey(source)){

            System.out.println("Source node missing");
            return;

        }

        for(Node n : graphNodes.values()){

            n.distance = Integer.MAX_VALUE;

        }

        this.getNode(source).distance = 0;
        PriorityQueue<Node> q = new PriorityQueue<Node>(new NodeComparator());

        for(Node n : graphNodes.values()){

            q.add(n);

        }

        Node u;

        while(!q.isEmpty()){

            u = q.remove();

            for(Edge e : u.adjacent){

                if((u.distance + e.w) < e.v.distance){

                    // Relax
                    e.v.distance = u.distance + e.w;
                    this.decreaseKey(q, e.v);
                    e.v.parent = u;

                }

            }

        }

        for(Node n : graphNodes.values()){

            System.out.print("ID: " + n.id + " Distance from source: " + n.distance);

            try {

                System.out.println(" Parent: " + n.parent.id);

            } catch (NullPointerException e) {

                System.out.println(" Parent: NIL");

            }

        }

    }

}