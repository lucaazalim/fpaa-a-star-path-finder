package br.pucminas.fpaa.pathfinder;

import java.util.List;

/**
 * Entry point for the maze pathfinding application.
 */
public class Main {

    /**
     * Main method to execute the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {

        Maze maze = Maze.fromString("""
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
                """);

        List<Node> path = AStar.aStar(maze);

        if (path == null) {
            System.out.println("No solution!");
            return;
        }

        System.out.println("Path found:");

        for (Node n : path) {
            System.out.print("(" + n.position().x() + "," + n.position().y() + ") ");
        }

        System.out.println();
        System.out.println(maze.toStringWithPath(path));

    }
}
