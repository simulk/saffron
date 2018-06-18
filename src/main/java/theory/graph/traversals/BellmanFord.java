package theory.graph.traversals;

import theory.graph.ds.*;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Stack;

public class BellmanFord<T> {

    public Set<T> findShortestPath(Graph<T> graph, T source, T destination) {
        if (graph == null || source == null || destination == null) {
            throw new IllegalArgumentException("Arguments cannot be null!");
        }
        if (source.equals(destination)) {
            return Collections.emptySet();
        }

        Vertex<T> srcVertex = graph.getVertex(source);
        Vertex<T> destVertex = graph.getVertex(destination);
        if (srcVertex == null || destVertex == null) { return Collections.emptySet(); }

        ((ComparableVertex<T>)srcVertex).setDistance(0);

        for (T vertex: graph.getVertices()) {
            ComparableVertex<T> prev = (ComparableVertex<T>)graph.getVertex(vertex);

            prev.getEdges().forEach(edge -> {
                ComparableVertex<T> curr = (ComparableVertex<T>) edge.getDestination();
                double prevDist = ((WeightedEdge<T>)edge).getWeight() + prev.getDistance();
                if (curr.getDistance() > prevDist) {
                    curr.setDistance(prevDist);
                    curr.setParent(prev);
                }
            });
        }

        for (T vertex: graph.getVertices()) {
            ComparableVertex<T> prev = (ComparableVertex<T>)graph.getVertex(vertex);

            prev.getEdges().forEach(edge -> {
                ComparableVertex<T> curr = (ComparableVertex<T>) edge.getDestination();
                double prevDist = ((WeightedEdge<T>)edge).getWeight() + prev.getDistance();
                if (curr.getDistance() > prevDist) {
                    throw new BellmanFordNegativeCycleException("Graph contains negative cycle");
                }
            });
        }

        Stack<T> stack = new Stack<>();
        while(!destVertex.getName().equals(source)) {
            stack.push(destVertex.getName());
            destVertex = destVertex.getParent();
            if (destVertex == null) {
                //no path exists
                return Collections.emptySet();
            }
        }
        stack.push(source);

        Set<T> path = new LinkedHashSet<>();
        while(!stack.isEmpty()) {
            path.add(stack.pop());
        }
        return path;
    }

    public static class BellmanFordNegativeCycleException extends RuntimeException {
        public BellmanFordNegativeCycleException(String message) {
            super(message);
        }
    }
}
