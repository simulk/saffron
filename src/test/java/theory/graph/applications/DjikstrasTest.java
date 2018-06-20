package theory.graph.applications;

import org.junit.jupiter.api.*;
import theory.graph.applications.Djikstras;
import theory.graph.ds.DirectedGraph;
import theory.graph.ds.Graph;
import theory.graph.ds.UndirectedGraph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class DjikstrasTest {
    private static Djikstras<String> djikstras;

    @BeforeAll
    public static void init() {
        djikstras = new Djikstras<>();
    }

    @Nested
    @DisplayName("djikstras - undirected graph")
    class UndirectedGraphDjikstrasTest {
        private Graph<String> unDirGraph;

        @BeforeEach
        public void init() {
            unDirGraph = new UndirectedGraph<>();
            unDirGraph.addEdge("A", "B", 1);
            unDirGraph.addEdge("A", "C", 5);
            unDirGraph.addEdge("B", "C", 2);
            unDirGraph.addEdge("B", "F", 3);
            unDirGraph.addEdge("D", "E", 4);
            unDirGraph.addEdge("D", "F", 10);
            unDirGraph.addEdge("G", "H", 1);
        }

        @Nested
        @DisplayName("valid paths")
        class ValidPathTest {
            @Test
            public void djikstras_undirGraph_shortestPath_validPath() {
                Set<String> aToC = djikstras.findShortestPath(unDirGraph, "A", "C");
                assertFalse(aToC.isEmpty());
                assertEquals(3, aToC.size());
                assertEquals(new HashSet(Arrays.asList("A", "B", "C")), aToC);
            }
        }

        @Nested
        @DisplayName("invalid paths")
        class InvalidPathTest {
            @Test
            public void djikstras_undirGraph_shortestPath_invalidPath() {
                Set<String> aToH = djikstras.findShortestPath(unDirGraph, "A", "H");
                assertTrue(aToH.isEmpty());
            }

            @Test
            public void djikstras_undirGraph_shortestPath_invalidPath_sameSrcDest() {
                Set<String> noPath = djikstras.findShortestPath(unDirGraph, "A", "A");
                assertTrue(noPath.isEmpty());
            }

            @Test
            public void djikstras_unDirGraph_shortestPath_missingSrc() {
                Set<String> noPath = djikstras.findShortestPath(unDirGraph, "X", "A");
                assertTrue(noPath.isEmpty());
            }

            @Test
            public void djikstras_unDirGraph_shortestPath_missingDest() {
                Set<String> noPath = djikstras.findShortestPath(unDirGraph, "A", "X");
                assertTrue(noPath.isEmpty());
            }

            @Test
            public void djikstras_unDirGraph_shortestPath_nullDest() {
                try {
                    djikstras.findShortestPath(unDirGraph, "A", null);
                    fail("should have thrown IllegalArgumentException");
                } catch (Exception e) {
                    assertTrue(e instanceof IllegalArgumentException);
                }
            }
            @Test
            public void djikstras_unDirGraph_shortestPath_nullGraph() {
                try {
                    djikstras.findShortestPath(null, "A", "B");
                    fail("should have thrown IllegalArgumentException");
                } catch (Exception e) {
                    assertTrue(e instanceof IllegalArgumentException);
                }
            }
        }
    }

    @Nested
    @DisplayName("djikstras - directed graph")
    class DirectedGraphDjikstrasTest {
        private Graph<String> diGraph;
        @BeforeEach
        public void init() {
            diGraph = new DirectedGraph<>();
            diGraph.addEdge("A", "B", 3);
            diGraph.addEdge("A", "C", 2);
            diGraph.addEdge("B", "C", 1);
            diGraph.addEdge("D", "E", 2);
            diGraph.addEdge("D", "F", 4);
        }

        @Nested
        @DisplayName("valid paths")
        class ValidPathTest {
            @Test
            public void djikstras_diGraph_shortestPath_validPath() {
                Set<String> aToC = djikstras.findShortestPath(diGraph, "A", "C");
                assertFalse(aToC.isEmpty());
                assertEquals(2, aToC.size());
                assertEquals(new HashSet(Arrays.asList("A", "C")), aToC);
            }
        }

        @Nested
        @DisplayName("invalid paths")
        class InvalidPathTest {
            @Test
            public void djikstras_diGraph_shortestPath_invalidPath() {
                Set<String> cToA = djikstras.findShortestPath(diGraph, "C", "A");
                assertTrue(cToA.isEmpty());
            }

            @Test
            public void djikstras_diGraph_shortestPath_invalidPath_sameSrcDest() {
                Set<String> noPath = djikstras.findShortestPath(diGraph, "A", "A");
                assertTrue(noPath.isEmpty());
            }

            @Test
            public void djikstras_diGraph_shortestPath_missingSrc() {
                Set<String> noPath = djikstras.findShortestPath(diGraph, "X", "A");
                assertTrue(noPath.isEmpty());
            }

            @Test
            public void djikstras_diGraph_shortestPath_missingDest() {
                Set<String> noPath = djikstras.findShortestPath(diGraph, "A", "X");
                assertTrue(noPath.isEmpty());
            }

            @Test
            public void djikstras_diGraph_shortestPath_nullDest() {
                try {
                    djikstras.findShortestPath(diGraph, "A", null);
                    fail("should have thrown IllegalArgumentException");
                } catch (Exception e) {
                    assertTrue(e instanceof IllegalArgumentException);
                }
            }

            @Test
            public void djikstras_diGraph_shortestPath_nullGraph() {
                try {
                    djikstras.findShortestPath(null, "A", "B");
                    fail("should have thrown IllegalArgumentException");
                } catch (Exception e) {
                    assertTrue(e instanceof IllegalArgumentException);
                }
            }
        }
    }

    @Nested
    @DisplayName("djikstras - negative cycles")
    class UndirectedGraphNegativeCyclesTest {
        private Graph<String> unDirGraph;

        @BeforeEach
        public void init() {
            unDirGraph = new UndirectedGraph<>();
            unDirGraph.addEdge("A", "B", 2);
            unDirGraph.addEdge("A", "C", 1);
            unDirGraph.addEdge("B", "C", -2);
            unDirGraph.addEdge("B", "F", -3);
            unDirGraph.addEdge("D", "E", -4);
            unDirGraph.addEdge("D", "F", -10);
            unDirGraph.addEdge("G", "H", -1);
        }

        // negative weights, should've been A->B->C, total weight = 0
        @Test
        public void djikstras_undirGraph_shortestPath_validPath() {
            Set<String> aToC = djikstras.findShortestPath(unDirGraph, "A", "C");
            assertFalse(aToC.isEmpty());
            assertNotEquals(3, aToC.size());
            assertNotEquals(new HashSet(Arrays.asList("A", "B", "C")), aToC);
        }

    }
}
