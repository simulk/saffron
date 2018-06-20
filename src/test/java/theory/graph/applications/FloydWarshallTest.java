package theory.graph.applications;

import org.junit.jupiter.api.*;
import theory.graph.applications.FloydWarshall;
import theory.graph.ds.DirectedGraph;
import theory.graph.ds.Graph;
import theory.graph.ds.UndirectedGraph;
import theory.graph.applications.exception.NegativeCycleException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class FloydWarshallTest {
    private static FloydWarshall<String> floydWarshall;
    private Graph<String> diGraph;

    @BeforeAll
    public static void init() {
        floydWarshall = new FloydWarshall<>();
    }

    @BeforeEach
    public void setup() {
        diGraph = new DirectedGraph<>();
        diGraph.addEdge("A", "B", 2);
        diGraph.addEdge("A", "C", 1);
        diGraph.addEdge("B", "C", -2);
        diGraph.addEdge("D", "E", 2);
        diGraph.addEdge("D", "F", 4);
        diGraph.addEdge("E", "F", 1);
        diGraph.addEdge("E", "H", 2);
        diGraph.addEdge("F", "H", 3);
    }

    @Nested
    @DisplayName("valid paths")
    class ValidPathTest {
        @Test
        public void floydWarshall_shortestPath_validPath_withNegativeWeight() {
            Set<String> aToC = floydWarshall.findShortestPath(diGraph, "A", "C");
            assertFalse(aToC.isEmpty());
            assertEquals(3, aToC.size());
            assertEquals(new HashSet(Arrays.asList("A", "B", "C")), aToC);
        }

        @Test
        public void floydWarshall_shortestPath_validPath_withoutNegativeWeight() {
            Set<String> dToF = floydWarshall.findShortestPath(diGraph, "D", "F");
            assertFalse(dToF.isEmpty());
            assertEquals(3, dToF.size());
            assertEquals(new HashSet(Arrays.asList("D", "E", "F")), dToF);

            Set<String> eToH = floydWarshall.findShortestPath(diGraph, "E", "H");
            assertFalse(eToH.isEmpty());
            assertEquals(2, eToH.size());
            assertEquals(new HashSet(Arrays.asList("E", "H")), eToH);
        }
    }

    @Nested
    @DisplayName("invalid paths")
    class InvalidPathTest {
        @Test
        public void floydWarshall_shortestPath_invalidPath() {
            Set<String> cToA = floydWarshall.findShortestPath(diGraph, "C", "A");
            assertTrue(cToA.isEmpty());
        }

        @Test
        public void floydWarshall_shortestPath_invalidPath_sameSrcDest() {
            Set<String> noPath = floydWarshall.findShortestPath(diGraph, "A", "A");
            assertTrue(noPath.isEmpty());
        }

        @Test
        public void floydWarshall_shortestPath_missingSrc() {
            Set<String> noPath = floydWarshall.findShortestPath(diGraph, "X", "A");
            assertTrue(noPath.isEmpty());
        }

        @Test
        public void floydWarshall_shortestPath_missingDest() {
            Set<String> noPath = floydWarshall.findShortestPath(diGraph, "A", "X");
            assertTrue(noPath.isEmpty());
        }

        @Test
        public void floydWarshall_shortestPath_nullDest() {
            try {
                floydWarshall.findShortestPath(diGraph, "A", null);
                fail("should have thrown IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }
        }

        @Test
        public void floydWarshall_shortestPath_nullGraph() {
            try {
                floydWarshall.findShortestPath(null, "A", "B");
                fail("should have thrown IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }
        }

        @Test
        public void floydWarshall_shortestPath_negativeCycles() {
            Graph<String> unDirGraph = new UndirectedGraph<>();
            unDirGraph.addEdge("A", "B", -2);
            unDirGraph.addEdge("B", "C", 2);
            unDirGraph.addEdge("A", "C", 1);
            unDirGraph.addEdge("D", "E", 1);
            try {
                floydWarshall.findShortestPath(unDirGraph, "A", "C");
                fail("should have thrown NegativeCycleException");
            } catch (Exception e) {
                assertTrue(e instanceof NegativeCycleException);
            }
        }
    }
}
