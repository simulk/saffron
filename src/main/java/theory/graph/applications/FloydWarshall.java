package theory.graph.applications;

import theory.graph.ds.Graph;
import theory.graph.ds.Vertex;
import theory.graph.ds.WeightedEdge;
import theory.graph.applications.exception.NegativeCycleException;

import java.util.*;

public class FloydWarshall<T> {

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

        Map<T, Map<T, Double>> distances = new HashMap<>();
        Map<T, Map<T, T>> next = new HashMap<>();
        initialize(graph, distances, next);

        for (T k: graph.getVertices()) {
            for (T i: graph.getVertices()) {
                for (T j: graph.getVertices()) {
                    double ikDist = distances.get(i).get(k);
                    double kjDist = distances.get(k).get(j);
                    if (ikDist == Double.POSITIVE_INFINITY
                            || kjDist == Double.POSITIVE_INFINITY) {
                        continue;
                    }
                    double ijDist = distances.get(i).get(j);
                    if (ijDist > kjDist + ikDist) {
                        System.out.println("here i " + i + " j " + j + " k " + k);
                        distances.get(i).put(j, kjDist + ikDist);
                        next.get(i).put(j, next.get(i).get(k));
                    }
                }
            }
        }

        for (T label: graph.getVertices()) {
            if (distances.get(label).get(label) < 0) {
                throw new NegativeCycleException("Graph contains negative cycle");
            }
        }

        if (next.get(source).get(destination) == null) {
            return Collections.emptySet();
        }

        return reconstructPath(source, destination, next);
    }

    private Set<T> reconstructPath(T source, T destination, Map<T, Map<T, T>> next) {
        Set<T> paths = new LinkedHashSet<>();
        while(!source.equals(destination)) {
            paths.add(source);
            source = next.get(source).get(destination);
        }
        paths.add(destination);
        return paths;
    }

    private void initialize(Graph<T> graph, Map<T, Map<T, Double>> distances, Map<T, Map<T, T>> next) {
        for (T label: graph.getVertices()) {
            if(!distances.containsKey(label)) {
                distances.put(label, new HashMap<>());
                next.put(label, new HashMap<>());
            }
            Vertex<T> curr = graph.getVertex(label);
            Map<T, Double> adjacencies = distances.get(label);
            Map<T, T> nxt = next.get(label);
            graph.getVertices().forEach(vertex -> {
                if (vertex.equals(label)) {
                    adjacencies.put(vertex, 0D);
                } else {
                    adjacencies.put(vertex, Double.POSITIVE_INFINITY);
                }
            });
            curr.getEdges().forEach(edge -> {
                adjacencies.put(edge.getDestination().getName(), ((WeightedEdge<T>)edge).getWeight());
                nxt.put(edge.getDestination().getName(), edge.getDestination().getName());
            });
        }
    }
}
