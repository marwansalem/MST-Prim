public class Main {

    public static void main(String[] args) {

        PrimGraph g = PrimGraph.InputGraph();
        PrimGraph.MinimumCostSpanningTree(g);
        PrimGraph.printPrimTree(g);


    }
}
