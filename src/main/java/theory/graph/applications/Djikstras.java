package theory.graph.applications;

import theory.graph.ds.ComparableVertex;
import theory.graph.ds.Graph;
import theory.graph.ds.Vertex;
import theory.graph.ds.WeightedEdge;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Djikstras<T> extends SingleSourceShortestPath<T> {

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

        Queue<Vertex<T>> queue = new PriorityQueue<>();
        queue.add(srcVertex);
        ((ComparableVertex<T>)srcVertex).setDistance(0D);

        while(!queue.isEmpty()) {
            ComparableVertex<T> prev = (ComparableVertex<T>)queue.remove();

            prev.getEdges().forEach(edge -> {
                ComparableVertex<T> curr = (ComparableVertex<T>)edge.getDestination();
                if (!curr.isVisited()) {
                    double minDistance = Math.min(curr.getDistance(),
                            ((WeightedEdge<T>)edge).getWeight() + prev.getDistance());
                    if (minDistance <= curr.getDistance()) {
                        curr.setDistance(minDistance);
                        curr.setParent(prev);
                    }
                    queue.add(curr);
                }
            });

            prev.setVisited(true);
        }

        return constructPath(source, destVertex);
    }
}
