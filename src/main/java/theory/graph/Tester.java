package theory.graph;

import theory.graph.ds.DirectedGraph;
import theory.graph.ds.Graph;
import theory.graph.ds.UndirectedGraph;
import theory.graph.traversals.BreadthFirstSearch;
import theory.graph.traversals.DepthFirstSearch;

public class Tester {

    private static final DepthFirstSearch dfs = new DepthFirstSearch<>();

    public static void main(String[] args) {
        testUndirectedGraph();
        testDirectedGraph();
    }

    private static void testUndirectedGraph() {
        Graph<String> graph = new UndirectedGraph<>();
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "C");
        graph.addEdge("D", "E");
        graph.addEdge("D", "F");

        System.out.println("\n-----------UndirectedGraph-----------");
        System.out.println(graph);

        System.out.println("Does path exists between A-C: " + dfs.pathExists(graph,"A", "C"));
        System.out.println("Does path exists between A-D: " + dfs.pathExists(graph,"A", "D"));
        System.out.println("Does path exists between D-F: " + dfs.pathExists(graph,"D", "F"));
        System.out.println("Does path exists between A-F: " + dfs.pathExists(graph,"A", "F"));
        System.out.println("Does path exists between B-A: " + dfs.pathExists(graph,"B", "A"));
        System.out.println("Does path exists between C-A: " + dfs.pathExists(graph,"C", "A"));
        System.out.println("Does path exists between F-D: " + dfs.pathExists(graph,"F", "D"));

        System.out.println("removing edge D-F: " + graph.removeEdge("D", "F"));
        System.out.println("Does path exists between D-F: " + dfs.pathExists(graph,"D", "F"));
        System.out.println(dfs.pathExists(graph, "A", "C"));

        testBFS(graph);
    }

    private static void testDirectedGraph() {
        Graph<String> graph = new DirectedGraph<>();
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "C");
        graph.addEdge("D", "E");
        graph.addEdge("D", "F");

        System.out.println("\n-----------DirectedGraph-----------");
        System.out.println(graph);

        System.out.println("Does path exists between A-C: " + dfs.pathExists(graph, "A", "C"));
        System.out.println("Does path exists between A-D: " + dfs.pathExists(graph, "A", "D"));
        System.out.println("Does path exists between D-F: " + dfs.pathExists(graph, "D", "F"));
        System.out.println("Does path exists between A-F: " + dfs.pathExists(graph, "A", "F"));
        System.out.println("Does path exists between B-A: " + dfs.pathExists(graph, "B", "A"));
        System.out.println("Does path exists between C-A: " + dfs.pathExists(graph, "C", "A"));
        System.out.println("Does path exists between F-D: " + dfs.pathExists(graph, "F", "D"));

        graph.addEdge("B", "A");
        graph.addEdge("C", "A");
        graph.addEdge("F", "D");

        System.out.println("Does path exists between B-A: " + dfs.pathExists(graph,"B", "A"));
        System.out.println("Does path exists between C-A: " + dfs.pathExists(graph,"C", "A"));
        System.out.println("Does path exists between F-D: " + dfs.pathExists(graph,"F", "D"));

        System.out.println("removing edge D-E: " + graph.removeEdge("D", "E"));
        System.out.println("Does path exists between D-E: " + dfs.pathExists(graph,"D", "E"));

        testBFS(graph);
    }

    private static void testBFS(Graph graph) {
        System.out.println("-----BFS-----");
        System.out.print("Shortest path between A-C: ");
        BreadthFirstSearch<String> bfs = new BreadthFirstSearch<>();
        System.out.println(bfs.findShortestPath(graph, "A", "C"));
    }
}
