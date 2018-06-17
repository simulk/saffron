package theory.graph.ds;

public class WeightedEdge<T> extends Edge<T> {
    private double weight;

    public WeightedEdge(Vertex destination, double weight) {
        super(destination);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }


    @Override
    public String toString() {
        return String.format("Edge[destination: %s weight: %f]", super.getDestination().getName(), weight);
    }
}
