package theory.graph.traversals;

import theory.graph.ds.Graph;
import theory.graph.ds.Vertex;

import java.util.*;

public class BreadthFirstSearch<T> {

    public Set<T> findShortestPath(Graph<T> graph, T source, T destination) {
        if (graph == null || source == null || destination == null) {
            throw new IllegalArgumentException("Arguments cannot be null!");
        }

        if (source.equals(destination)) {
            return Collections.emptySet();
        }

        Vertex<T> srcVertex = graph.getVertex(source);
        if (srcVertex == null) { return Collections.emptySet(); }
        Queue<Vertex<T>> queue = new LinkedList<>();
        queue.add(srcVertex);

        Vertex<T> curr = srcVertex;
        while(!queue.isEmpty()) {
            Vertex<T> temp = queue.remove();
            curr = temp;
            if (curr.getName().equals(destination)) {
                break;
            }
            graph.getVertex(curr.getName()).getEdges().forEach(edge -> {
                if (!edge.getDestination().isVisited()) {
                    edge.getDestination().setVisited(true);
                    edge.getDestination().setParent(temp);
                    queue.add(edge.getDestination());
                }
            });
            curr.setVisited(true);
        }

        if (!curr.getName().equals(destination)) { return Collections.emptySet(); }
        Stack<T> stack = new Stack<>();
        do {
            stack.push(curr.getName());
            curr = curr.getParent();
        } while(!curr.getName().equals(source));
        stack.push(source);

        Set<T> path = new LinkedHashSet<>();
        while(!stack.isEmpty()) {
            path.add(stack.pop());
        }
        return path;
    }

}
