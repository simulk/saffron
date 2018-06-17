package theory.graph.ds;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Graph<T> {
    protected Map<T, Vertex<T>> adjacencyMap;

    public Graph() {
        this.adjacencyMap = new HashMap<>();
    }

    public abstract void addEdge(T source, T destination);
    public abstract boolean removeEdge(T source, T destination);
    public abstract void addEdge(T source, T destination, double weight);

    public Vertex<T> addVertex(T name) {
        if (adjacencyMap.containsKey(name)) {
            return adjacencyMap.get(name);
        }
        Vertex<T> vertex = new Vertex<>(name);
        this.adjacencyMap.put(vertex.getName(), vertex);
        return vertex;
    }

    public Vertex<T> addComparableVertex(T name) {
        if (adjacencyMap.containsKey(name)) {
            return adjacencyMap.get(name);
        }
        Vertex<T> vertex = new ComparableVertex<>(name);
        this.adjacencyMap.put(vertex.getName(), vertex);
        return vertex;
    }

    public Vertex<T> getVertex(T name) {
        return this.adjacencyMap.get(name);
    }

    public Set<Edge<T>> getVertices(T name) {
        Vertex<T> vertex = this.adjacencyMap.getOrDefault(name, null);
        if (null != vertex) {
            return this.adjacencyMap.get(name).getEdges();
        }
        return Collections.emptySet();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        this.adjacencyMap.forEach((k, v) -> {
            sb.append(k).append(":");
            sb.append(v.getEdges().stream()
                    .map(edge -> edge.getDestination().getName())
                    .collect(Collectors.toList()).toString());
            sb.append(System.lineSeparator());
        });
        return sb.toString();
    }
}
