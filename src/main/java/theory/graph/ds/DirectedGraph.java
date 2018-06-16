package theory.graph.ds;

public class DirectedGraph<T> extends Graph<T> {

    @Override
    public void addEdge(T source, T destination) {
        Vertex<T> sourceVertex = addVertex(source);
        Vertex<T> destinationVertex = addVertex(destination);

        Edge<T> sourceToDest = new Edge<>(destinationVertex);

        if (!sourceVertex.containsEdge(destination)) {
            sourceVertex.addEdge(sourceToDest);
        }
    }

    @Override
    public void addEdge(T source, T destination, int weight) {
        Vertex<T> sourceVertex = addVertex(source);
        Vertex<T> destinationVertex = addVertex(destination);

        Edge<T> sourceToDest = new WeightedEdge<>(destinationVertex, weight);

        if (!sourceVertex.containsEdge(destination)) {
            sourceVertex.addEdge(sourceToDest);
        }
    }

    @Override
    public boolean removeEdge(T source, T destination) {
        if (!this.adjacencyMap.containsKey(source) || !this.adjacencyMap.containsKey(destination)) {
            throw new IllegalArgumentException("Source or destination cannot be null");
        }
        return this.adjacencyMap.get(source).removeEdge(destination);
    }
}
