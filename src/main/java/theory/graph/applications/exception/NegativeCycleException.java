package theory.graph.applications.exception;

public class NegativeCycleException extends RuntimeException {
    public NegativeCycleException(String message) {
        super(message);
    }
}
