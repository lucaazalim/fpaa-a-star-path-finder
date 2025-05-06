package br.pucminas.fpaa.pathfinder;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MazeTest {

    @Test
    void testEasyMaze() {

        testMaze("""
                        S 0 0 0 0
                        9 9 1 9 0
                        0 1 1 1 0
                        0 9 1 9 0
                        0 0 0 0 E
                        """,
                """
                        S * * * 0
                        9 9 1 9 *
                        0 1 1 1 *
                        0 9 1 9 *
                        0 0 0 0 E
                        """
        );

    }

    @Test
    void testMediumMaze() {

        testMaze("""
                        S 0 0 9 0 0 0
                        9 9 0 9 0 9 0
                        0 2 0 1 0 9 0
                        0 9 9 9 0 3 0
                        0 0 0 9 0 9 0
                        0 9 0 0 0 9 E
                        0 0 0 9 0 0 0
                        """,
                """
                        S * 0 9 0 0 0
                        9 9 * 9 0 9 0
                        0 2 * * 0 9 0
                        0 9 9 9 * 3 0
                        0 0 0 9 * 9 0
                        0 9 0 0 * 9 E
                        0 0 0 9 0 * 0
                        """
        );

    }

    @Test
    void testHardMaze() {

        testMaze("""
                        S 0 9 0 0 0 0 9 0 0
                        9 1 9 1 1 9 0 9 9 0
                        0 1 0 0 1 0 0 1 9 0
                        0 9 0 9 9 9 1 9 0 0
                        0 2 0 1 0 0 1 1 1 0
                        9 9 9 1 9 1 9 9 9 0
                        0 0 1 1 0 1 0 0 0 0
                        0 1 9 9 9 1 9 9 1 0
                        0 0 0 1 0 1 0 1 1 0
                        0 9 0 1 0 0 0 0 9 E
                        """,
                """
                        S * 9 0 0 0 0 9 0 0
                        9 * 9 1 1 9 0 9 9 0
                        0 1 * 0 1 0 0 1 9 0
                        0 9 * 9 9 9 1 9 0 0
                        0 2 * 1 0 0 1 1 1 0
                        9 9 9 * 9 1 9 9 9 0
                        0 0 1 1 * 1 0 0 0 0
                        0 1 9 9 9 * 9 9 1 0
                        0 0 0 1 0 1 * 1 * 0
                        0 9 0 1 0 0 0 * 9 E
                        """
        );

    }

    @Test
    void testImpossibleMaze() {

        testMaze("""
                S 9 9 9 9 9 9 0
                0 2 2 2 2 2 9 0
                0 2 9 9 9 2 9 0
                0 2 9 4 9 2 9 0
                0 2 9 4 9 2 9 0
                0 2 9 9 9 2 9 0
                0 2 2 2 2 2 9 0
                0 9 9 9 9 9 9 E
                """, null);

    }

    @Test
    void testMixedTerrainMaze() {

        testMaze("""
                S 1 9 0 0 0
                0 2 9 3 4 0
                0 0 9 0 5 0
                9 9 9 0 6 0
                0 0 0 0 7 0
                0 8 9 9 9 E
                """, null);

    }

    void testMaze(String maze, String expectedSolution) {

        Maze m = Maze.fromString(maze);

        List<Node> path = AStar.aStar(m);

        if (expectedSolution == null) {
            assertNull(path, "Path should be null.");
        } else {
            assertNotNull(path, "Path should not be null.");
            assertEquals(expectedSolution.trim(), m.toStringWithPath(path).trim());
        }

    }

}
