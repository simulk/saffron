package theory.graph.ds;

public class UndirectedGraph<T> extends Graph<T> {

    @Override
    public void addEdge(T source, T destination) {
        Vertex<T> sourceVertex = addVertex(source);
        Vertex<T> destinationVertex = addVertex(destination);

        Edge<T> sourceToDest = new Edge<>(destinationVertex);
        Edge<T> destToSource = new Edge<>(sourceVertex);

        if (!sourceVertex.containsEdge(destination)) {
            sourceVertex.addEdge(sourceToDest);
        }
        if (!destinationVertex.containsEdge(source)) {
            destinationVertex.addEdge(destToSource);
        }
    }

    @Override
    public void addEdge(T source, T destination, double weight) {
        Vertex<T> sourceVertex = addComparableVertex(source);
        Vertex<T> destinationVertex = addComparableVertex(destination);

        Edge<T> sourceToDest = new WeightedEdge<>(destinationVertex, weight);
        Edge<T> destToSource = new WeightedEdge<>(sourceVertex, weight);

        if (!sourceVertex.containsEdge(destination)) {
            sourceVertex.addEdge(sourceToDest);
        }
        if (!destinationVertex.containsEdge(source)) {
            destinationVertex.addEdge(destToSource);
        }
    }

    @Override
    public boolean removeEdge(T source, T destination) {
        if (!this.adjacencyMap.containsKey(source) || !this.adjacencyMap.containsKey(destination)) {
            throw new IllegalArgumentException("Source or destination cannot be null");
        }

        boolean removeDest = this.adjacencyMap.get(source).removeEdge(destination);
        boolean removeSrc = this.adjacencyMap.get(destination).removeEdge(source);
        return removeSrc || removeDest;
    }
}
