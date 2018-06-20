package theory.graph.applications;

import theory.graph.ds.ComparableVertex;
import theory.graph.ds.Graph;
import theory.graph.ds.Vertex;
import theory.graph.ds.WeightedEdge;
import theory.graph.applications.exception.NegativeCycleException;

import java.util.Collections;
import java.util.Set;

public class BellmanFord<T> extends SingleSourceShortestPath<T> {

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
                    throw new NegativeCycleException("Graph contains negative cycle");
                }
            });
        }

        return constructPath(source, destVertex);
    }
}
