import java.util.Scanner;
public class Main {

    public static void ShortestPath(){
        SSSPGraph graph1 = new SSSPGraph();
        Scanner input = new Scanner(System.in);
        int n;
        int m;
        int ids;
        int idd;
        int w;
        System.out.println("Enter the number of nodes:");
        n = input.nextInt();
        System.out.println("Enter the number of edges:");
        m = input.nextInt();
        System.out.println("Enter the values of the nodes:");

        for(int i = 0; i < n; i++){

            ids = input.nextInt();
            graph1.addNode(ids);

        }

        System.out.println("Enter the edges and their weights:");

        for(int j = 0; j < m; j++){

            System.out.println("Enter the source node:");
            ids = input.nextInt();
            System.out.println("Enter the destination node:");
            idd = input.nextInt();
            System.out.println("Enter the weight:");
            w = input.nextInt();
            graph1.addEdge(ids, idd, w);

        }

        System.out.println("Enter the source node for Dijkstra's approach:");
        ids = input.nextInt();
        graph1.DijkstrasAlg(ids);

        /** graph1.addNode(1);
         graph1.addNode(2);
         graph1.addNode(3);
         graph1.addNode(4);
         graph1.addNode(5);
         graph1.addEdge(1, 2, 2);
         graph1.addEdge(1, 3, 12);
         graph1.addEdge(4, 1, 10);
         graph1.addEdge(2, 3, 8);
         graph1.addEdge(3, 4, 6);
         graph1.addEdge(3, 5, 3);
         graph1.addEdge(5, 2, 4);
         graph1.addEdge(2, 5, 9);
         graph1.addEdge(5, 4, 2);
         graph1.addEdge(4, 5, 4);
         graph1.DijkstrasAlg(1); */
        input.close();

    }
    public static void main(String[] args) {



        Scanner in = new Scanner(System.in);
        int menu = 0;
        while(menu!=3) {

            do{
                System.out.println("1-Dijkstra\'s +Shortest Path \n2-Prim\'s MST\n3-Exit");
                menu = in.nextInt();
            }while( menu<1  ||menu>3);
            if(menu ==2){
                PrimGraph g = PrimGraph.InputGraph();
                PrimGraph.MinimumCostSpanningTree(g);
                PrimGraph.printPrimTree(g);
            }else if(menu == 1){
                ShortestPath();
            }

        }
    }
}
