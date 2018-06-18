package theory.graph.traversals;

import org.junit.jupiter.api.*;
import theory.graph.ds.DirectedGraph;
import theory.graph.ds.Graph;
import theory.graph.ds.UndirectedGraph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class BellmanFordTest {
    private static BellmanFord<String> bellmanFord;
    private Graph<String> diGraph;

    @BeforeAll
    public static void init() {
        bellmanFord = new BellmanFord<>();
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
        public void bellmanFord_shortestPath_validPath_withNegativeWeight() {
            Set<String> aToC = bellmanFord.findShortestPath(diGraph, "A", "C");
            assertFalse(aToC.isEmpty());
            assertEquals(3, aToC.size());
            assertEquals(new HashSet(Arrays.asList("A", "B", "C")), aToC);
        }

        @Test
        public void bellmanFord_shortestPath_validPath_withoutNegativeWeight() {
            Set<String> dToF = bellmanFord.findShortestPath(diGraph, "D", "F");
            assertFalse(dToF.isEmpty());
            assertEquals(3, dToF.size());
            assertEquals(new HashSet(Arrays.asList("D", "E", "F")), dToF);

            Set<String> eToH = bellmanFord.findShortestPath(diGraph, "E", "H");
            assertFalse(eToH.isEmpty());
            assertEquals(2, eToH.size());
            assertEquals(new HashSet(Arrays.asList("E", "H")), eToH);
        }
    }

    @Nested
    @DisplayName("invalid paths")
    class InvalidPathTest {
        @Test
        public void bellmanFord_shortestPath_invalidPath() {
            Set<String> cToA = bellmanFord.findShortestPath(diGraph, "C", "A");
            assertTrue(cToA.isEmpty());
        }

        @Test
        public void bellmanFord_shortestPath_invalidPath_sameSrcDest() {
            Set<String> noPath = bellmanFord.findShortestPath(diGraph, "A", "A");
            assertTrue(noPath.isEmpty());
        }

        @Test
        public void bellmanFord_shortestPath_missingSrc() {
            Set<String> noPath = bellmanFord.findShortestPath(diGraph, "X", "A");
            assertTrue(noPath.isEmpty());
        }

        @Test
        public void bellmanFord_shortestPath_missingDest() {
            Set<String> noPath = bellmanFord.findShortestPath(diGraph, "A", "X");
            assertTrue(noPath.isEmpty());
        }

        @Test
        public void bellmanFord_shortestPath_nullDest() {
            try {
                bellmanFord.findShortestPath(diGraph, "A", null);
                fail("should have thrown IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }
        }

        @Test
        public void bellmanFord_shortestPath_nullGraph() {
            try {
                bellmanFord.findShortestPath(null, "A", "B");
                fail("should have thrown IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }
        }

        @Test
        public void bellmanFord_shortestPath_negativeCycles() {
            Graph<String> unDirGraph = new UndirectedGraph<>();
            unDirGraph.addEdge("A", "B", -2);
            unDirGraph.addEdge("B", "C", 2);
            unDirGraph.addEdge("A", "C", 1);
            unDirGraph.addEdge("D", "E", 1);
            try {
                bellmanFord.findShortestPath(unDirGraph, "A", "C");
                fail("should have thrown BellmanFordNegativeCycleException");
            } catch (Exception e) {
                assertTrue(e instanceof BellmanFord.BellmanFordNegativeCycleException);
            }
        }
    }
}
