package theory.graph.ds;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Vertex<T> {

    private T name;
    private Vertex<T> parent;
    private boolean visited;
    private Set<Edge<T>> edges;

    public Vertex(T name) {
        this(name, new HashSet<>());
    }

    public Vertex(T name, Set<Edge<T>> edges) {
        this.name = name;
        this.edges = edges;
    }

    public Edge addEdge(Edge<T> edge) {
        this.edges.add(edge);
        return edge;
    }

    public boolean containsEdge(T destination) {
        return this.getEdges().stream().anyMatch(edge -> edge.getDestination().getName().equals(destination));
    }

    public T getName() {
        return name;
    }

    public Set<Edge<T>> getEdges() {
        return edges;
    }

    public boolean removeEdge(T name) {
        return this.edges.removeIf(a -> a.getDestination().name.equals(name));
    }

    public Vertex<T> getParent() {
        return parent;
    }

    public void setParent(Vertex<T> parent) {
        this.parent = parent;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    @Override
    public String toString() {
        return String.format("%s, parent:%s [%s]]\n", name,
                Optional.ofNullable(parent).map(Vertex::getName).orElse(null), edges);
    }
}
