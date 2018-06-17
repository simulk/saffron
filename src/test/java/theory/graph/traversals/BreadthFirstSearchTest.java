package theory.graph.traversals;

import org.junit.jupiter.api.*;
import theory.graph.ds.DirectedGraph;
import theory.graph.ds.Graph;
import theory.graph.ds.UndirectedGraph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class BreadthFirstSearchTest {
    private static BreadthFirstSearch<String> bfs;

    @BeforeAll
    public static void init() {
        bfs = new BreadthFirstSearch<>();
    }

    @Nested
    @DisplayName("bfs - undirected graph")
    class UndirectedGraphBFSTest {
        private Graph<String> unDirGraph;

        @BeforeEach
        public void init() {
            unDirGraph = new UndirectedGraph<>();
            unDirGraph.addEdge("A", "B");
            unDirGraph.addEdge("A", "C");
            unDirGraph.addEdge("B", "C");
            unDirGraph.addEdge("B", "F");
            unDirGraph.addEdge("D", "E");
            unDirGraph.addEdge("D", "F");
            unDirGraph.addEdge("G", "H");
        }

        @Nested
        @DisplayName("valid paths")
        class ValidPathTest {
            @Test
            public void bfs__undirGraph_shortestPath_validPath() {
                Set<String> aToD = bfs.findShortestPath(unDirGraph, "A", "D");
                assertFalse(aToD.isEmpty());
                assertEquals(4, aToD.size());
                assertEquals(new HashSet(Arrays.asList("A", "B", "F", "D")), aToD);
            }
        }

        @Nested
        @DisplayName("invalid paths")
        class InvalidPathTest {
            @Test
            public void bfs_undirGraph_shortestPath_invalidPath() {
                Set<String> aToH = bfs.findShortestPath(unDirGraph, "A", "H");
                assertTrue(aToH.isEmpty());
            }

            @Test
            public void bfs_undirGraph_shortestPath_invalidPath_sameSrcDest() {
                Set<String> noPath = bfs.findShortestPath(unDirGraph, "A", "A");
                assertTrue(noPath.isEmpty());
            }

            @Test
            public void bfs_unDirGraph_shortestPath_missingSrc() {
                Set<String> noPath = bfs.findShortestPath(unDirGraph, "X", "A");
                assertTrue(noPath.isEmpty());
            }

            @Test
            public void bfs_unDirGraph_shortestPath_missingDest() {
                Set<String> noPath = bfs.findShortestPath(unDirGraph, "A", "X");
                assertTrue(noPath.isEmpty());
            }

            @Test
            public void bfs_unDirGraph_shortestPath_nullDest() {
                try {
                    bfs.findShortestPath(unDirGraph, "A", null);
                    fail("should have thrown IllegalArgumentException");
                } catch (Exception e) {
                    assertTrue(e instanceof IllegalArgumentException);
                }
            }
            @Test
            public void bfs_unDirGraph_shortestPath_nullGraph() {
                try {
                    bfs.findShortestPath(null, "A", "B");
                    fail("should have thrown IllegalArgumentException");
                } catch (Exception e) {
                    assertTrue(e instanceof IllegalArgumentException);
                }
            }
        }
    }

    @Nested
    @DisplayName("bfs - directed graph")
    class DirectedGraphBFSTest {
        private Graph<String> diGraph;
        @BeforeEach
        public void init() {
            diGraph = new DirectedGraph<>();
            diGraph.addEdge("A", "B");
            diGraph.addEdge("A", "C");
            diGraph.addEdge("B", "C");
            diGraph.addEdge("D", "E");
            diGraph.addEdge("D", "F");
        }

        @Nested
        @DisplayName("valid paths")
        class ValidPathTest {
            @Test
            public void bfs_diGraph_shortestPath_validPath() {
                Set<String> aToC = bfs.findShortestPath(diGraph, "A", "C");
                assertFalse(aToC.isEmpty());
                assertEquals(2, aToC.size());
                assertEquals(new HashSet(Arrays.asList("A", "C")), aToC);
            }
        }

        @Nested
        @DisplayName("invalid paths")
        class InvalidPathTest {
            @Test
            public void bfs_diGraph_shortestPath_invalidPath() {
                Set<String> cToA = bfs.findShortestPath(diGraph, "C", "A");
                assertTrue(cToA.isEmpty());
            }

            @Test
            public void bfs_diGraph_shortestPath_invalidPath_sameSrcDest() {
                Set<String> noPath = bfs.findShortestPath(diGraph, "A", "A");
                assertTrue(noPath.isEmpty());
            }

            @Test
            public void bfs_diGraph_shortestPath_missingSrc() {
                Set<String> noPath = bfs.findShortestPath(diGraph, "X", "A");
                assertTrue(noPath.isEmpty());
            }

            @Test
            public void bfs_diGraph_shortestPath_missingDest() {
                Set<String> noPath = bfs.findShortestPath(diGraph, "A", "X");
                assertTrue(noPath.isEmpty());
            }

            @Test
            public void bfs_diGraph_shortestPath_nullDest() {
                try {
                    bfs.findShortestPath(diGraph, "A", null);
                    fail("should have thrown IllegalArgumentException");
                } catch (Exception e) {
                    assertTrue(e instanceof IllegalArgumentException);
                }
            }
            @Test
            public void bfs_diGraph_shortestPath_nullGraph() {
                try {
                    bfs.findShortestPath(null, "A", "B");
                    fail("should have thrown IllegalArgumentException");
                } catch (Exception e) {
                    assertTrue(e instanceof IllegalArgumentException);
                }
            }
        }
    }
}
