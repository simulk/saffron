package theory.graph.ds;

public class Edge<T> {
    private Vertex<T> destination;

    public Edge(Vertex<T> destination) {
        this.destination = destination;
    }

    public Vertex<T> getDestination() {
        return destination;
    }

    public void setDestination(Vertex<T> destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return String.format("Edge[destination: %s]", destination.getName());
    }
}
