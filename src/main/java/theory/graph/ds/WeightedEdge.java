package theory.graph.ds;

public class WeightedEdge<T> extends Edge<T> {
    private int weight;

    public WeightedEdge(Vertex destination, int weight) {
        super(destination);
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return String.format("Edge[destination: %s weight: %d]", super.getDestination(), weight);
    }
}
