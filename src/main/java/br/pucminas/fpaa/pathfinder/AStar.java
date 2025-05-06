package br.pucminas.fpaa.pathfinder;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Implements the A* pathfinding algorithm.
 */
public class AStar {

    /**
     * Directions for movement in the maze (orthogonal and diagonal).
     */
    private static final Vector2D[] DIRECTIONS = {
            new Vector2D(-1, 0), new Vector2D(1, 0), new Vector2D(0, -1), new Vector2D(0, 1), // orthogonal
            new Vector2D(-1, -1), new Vector2D(-1, 1), new Vector2D(1, -1), new Vector2D(1, 1) // diagonal
    };

    /**
     * Costs associated with each movement direction.
     */
    private static final double[] MOVE_COSTS = {
            1, 1, 1, 1, // orthogonal
            Math.sqrt(2), Math.sqrt(2), Math.sqrt(2), Math.sqrt(2) // diagonal
    };

    /**
     * Value representing an obstacle in the maze.
     */
    private static final int OBSTACLE = 9;

    /**
     * Executes the A* algorithm to find the shortest path in a maze.
     *
     * @param maze The maze to solve.
     * @return A list of nodes representing the path from start to end, or null if
     *         no path exists.
     */
    public static List<Node> aStar(Maze maze) {

        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Set<Node> closedSet = new HashSet<>();

        Node startNode = new Node(
                maze.start(),
                0,
                AStar.manhattan(maze.start(), maze.end()),
                null);

        openSet.add(startNode);

        while (!openSet.isEmpty()) {

            Node current = openSet.poll();

            // Check if the goal has been reached
            if (current.position().equals(maze.end())) {
                return current.reconstructPath();
            }

            closedSet.add(current);

            for (int i = 0; i < DIRECTIONS.length; i++) {

                Vector2D neighbor = current.position().add(DIRECTIONS[i]);

                // Skip if the neighbor is out of bounds
                if (!maze.isInBounds(neighbor))
                    continue;

                int neighborValue = maze.get(neighbor);

                // Skip if the neighbor is an obstacle
                if (neighborValue >= OBSTACLE)
                    continue;

                double tentativeG = current.gCost() + (MOVE_COSTS[i] * neighborValue);

                Node neighborNode = new Node(
                        neighbor,
                        tentativeG,
                        manhattan(neighbor, maze.end()),
                        current);

                // Skip if the neighbor is already in the closed set
                if (closedSet.contains(neighborNode))
                    continue;

                Optional<Node> existing = openSet.stream()
                        .filter(n -> n.equals(neighborNode))
                        .findFirst();

                if (existing.isEmpty()) {
                    openSet.add(neighborNode);
                    continue;
                }

                // Update the node in the open set if a better path is found
                if (tentativeG < existing.get().gCost()) {
                    openSet.remove(existing.get());
                    openSet.add(neighborNode);
                }
            }
        }

        return null; // No path found
    }

    /**
     * Calculates the Manhattan distance between two positions.
     *
     * @param position1 The first position.
     * @param position2 The second position.
     * @return The Manhattan distance between the two positions.
     */
    private static double manhattan(Vector2D position1, Vector2D position2) {
        return Math.abs(position1.x() - position2.x()) + Math.abs(position1.y() - position2.y());
    }

}
