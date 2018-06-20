package theory.graph.applications;

import theory.graph.ds.Vertex;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Stack;

public abstract class SingleSourceShortestPath<T> {

    protected Set<T> constructPath(T source, Vertex<T> destVertex) {
        Stack<T> stack = new Stack<>();
        while(!destVertex.getName().equals(source)) {
            stack.push(destVertex.getName());
            destVertex = destVertex.getParent();
            if (destVertex == null) {
                //no path exists
                return Collections.emptySet();
            }
        }
        stack.push(source);

        Set<T> path = new LinkedHashSet<>();
        while(!stack.isEmpty()) {
            path.add(stack.pop());
        }
        return path;
    }
}
