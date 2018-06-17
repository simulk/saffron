package theory.graph.traversals;

import org.junit.jupiter.api.*;
import theory.graph.ds.DirectedGraph;
import theory.graph.ds.Graph;
import theory.graph.ds.UndirectedGraph;

import static org.junit.jupiter.api.Assertions.*;

public class DepthFirstSearchTest {
    private static DepthFirstSearch<String> dfs;

    @BeforeAll
    public static void init() {
        dfs = new DepthFirstSearch<>();
    }

    @Nested
    @DisplayName("dfs - undirected graph")
    class UndirectedGraphDFSTest {
        private Graph<String> unDirGraph;

        @BeforeEach
        public void setup() {
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
            public void dfs_diGraph_pathExists_validPath() {
                assertTrue(dfs.pathExists(unDirGraph, "A", "C"));
            }

            @Test
            public void dfs_diGraph_pathExists_validPath_sameSrcDest() {
                assertTrue(dfs.pathExists(unDirGraph, "A", "A"));
            }
        }

        @Nested
        @DisplayName("invalid paths")
        class InvalidPathTest {
            @Test
            public void dfs_diGraph_pathExists_invalidPath() {
                assertFalse(dfs.pathExists(unDirGraph, "A", "H"));
            }

            @Test
            public void dfs_diGraph_pathExists_invalidDest() {
                assertFalse(dfs.pathExists(unDirGraph, "A", "invalid"));
            }

            @Test
            public void dfs_diGraph_pathExists_missingSrc() {
                assertFalse(dfs.pathExists(unDirGraph, "X", "A"));
            }

            @Test
            public void dfs_diGraph_pathExists_missingDest() {
                assertFalse(dfs.pathExists(unDirGraph, "A", "X"));
            }

            @Test
            public void dfs_diGraph_pathExists_nullDest() {
                try {
                    dfs.pathExists(unDirGraph, "A", null);
                    fail("should have thrown IllegalArgumentException");
                } catch (Exception e) {
                    assertTrue(e instanceof IllegalArgumentException);
                }
            }
            @Test
            public void dfs_diGraph_pathExists_nullGraph() {
                try {
                    dfs.pathExists(null, "A", "B");
                    fail("should have thrown IllegalArgumentException");
                } catch (Exception e) {
                    assertTrue(e instanceof IllegalArgumentException);
                }
            }
        }
    }

    @Nested
    @DisplayName("dfs - undirected graph")
    class DirectedGraphDFSTest {
        private Graph<String> diGraph;

        @BeforeEach
        public void setup() {
            diGraph = new DirectedGraph<>();
            diGraph.addEdge("A", "B");
            diGraph.addEdge("A", "C");
            diGraph.addEdge("B", "C");
            diGraph.addEdge("B", "F");
            diGraph.addEdge("D", "E");
            diGraph.addEdge("D", "F");
            diGraph.addEdge("G", "H");
        }

        @Nested
        @DisplayName("valid paths")
        class ValidPathTest {
            @Test
            public void dfs_diGraph_pathExists_validPath() {
                assertTrue(dfs.pathExists(diGraph, "A", "C"));
            }

            @Test
            public void dfs_diGraph_shortestPath_validPath_sameSrcDest() {
                assertTrue(dfs.pathExists(diGraph, "A", "A"));
            }
        }

        @Nested
        @DisplayName("invalid paths")
        class InvalidPathTest {
            @Test
            public void dfs_diGraph_pathExists_invalidPath() {
                assertFalse(dfs.pathExists(diGraph, "A", "D"));
            }

            @Test
            public void dfs_diGraph_pathExists_invalidDest() {
                assertFalse(dfs.pathExists(diGraph, "A", "invalid"));
            }

            @Test
            public void dfs_diGraph_pathExists_missingSrc() {
                assertFalse(dfs.pathExists(diGraph, "X", "A"));
            }

            @Test
            public void dfs_diGraph_pathExists_missingDest() {
                assertFalse(dfs.pathExists(diGraph, "A", "X"));
            }

            @Test
            public void dfs_diGraph_pathExists_nullDest() {
                try {
                    dfs.pathExists(diGraph, "A", null);
                    fail("should have thrown IllegalArgumentException");
                } catch (Exception e) {
                    assertTrue(e instanceof IllegalArgumentException);
                }
            }
            @Test
            public void dfs_diGraph_pathExists_nullGraph() {
                try {
                    dfs.pathExists(null, "A", "B");
                    fail("should have thrown IllegalArgumentException");
                } catch (Exception e) {
                    assertTrue(e instanceof IllegalArgumentException);
                }
            }
        }
    }
}
