package theory.graph.traversals;

import theory.graph.ds.Graph;
import theory.graph.ds.Vertex;

import java.util.Stack;

public class DepthFirstSearch<T> {

    public boolean pathExists(Graph<T> graph, T source, T destination) {
        if (graph == null || source == null || destination == null) {
            throw new IllegalArgumentException("Arguments cannot be null!");
        }

        Vertex<T> srcVertex = graph.getVertex(source);
        if (srcVertex == null) { return false; }
        Stack<Vertex<T>> stack = new Stack<>();
        stack.add(srcVertex);

        while(!stack.isEmpty()) {
            Vertex<T> curr = stack.pop();

            if (curr.getName().equals(destination)) {
                return true;
            }
            curr.getEdges().forEach(edge -> {
                if (!edge.getDestination().isVisited()) {
                    edge.getDestination().setVisited(true);
                    edge.getDestination().setParent(curr);
                    stack.push(edge.getDestination());
                }
            });
            curr.setVisited(true);
        }
        return false;
    }
}
