package br.pucminas.fpaa.pathfinder;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Represents a maze with a start and end point.
 */
public class Maze {

    /**
     * The maze grid represented as a 2D array of integers.
     */
    private final int[][] maze;

    /**
     * The start position in the maze.
     */
    private Vector2D start;

    /**
     * The end position in the maze.
     */
    private Vector2D end;

    /**
     * Constructs a Maze object from a 2D array.
     *
     * @param maze The 2D array representing the maze.
     * @throws IllegalArgumentException If the maze is invalid.
     */
    public Maze(int[][] maze) {

        Objects.requireNonNull(maze);

        int rows = maze.length;

        if (rows == 0) {
            throw new IllegalArgumentException("Maze must have at least one row.");
        }

        int expectedColumns = maze[0].length;

        for (int i = 1; i < rows; i++) {
            if (maze[i].length != expectedColumns) {
                throw new IllegalArgumentException("All rows must have the same number of columns.");
            }
        }

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == 'S') {
                    start = new Vector2D(i, j);
                    maze[i][j] = 0;
                } else if (maze[i][j] == 'E') {
                    end = new Vector2D(i, j);
                    maze[i][j] = 0;
                }
            }
        }

        Objects.requireNonNull(start, "Start position 'S' not found in the maze.");
        Objects.requireNonNull(end, "End position 'E' not found in the maze.");

        this.maze = maze;
    }

    /**
     * Gets the value at a specific position in the maze.
     *
     * @param x The row index.
     * @param y The column index.
     * @return The value at the specified position.
     */
    public int get(int x, int y) {
        return maze[x][y];
    }

    /**
     * Gets the value at a specific position in the maze using a Vector2D.
     *
     * @param vector The position as a Vector2D.
     * @return The value at the specified position.
     */
    public int get(Vector2D vector) {
        return maze[vector.x()][vector.y()];
    }

    /**
     * Returns the number of rows in the maze.
     *
     * @return The number of rows.
     */
    public int rows() {
        return maze.length;
    }

    /**
     * Returns the number of columns in the maze.
     *
     * @return The number of columns.
     */
    public int cols() {
        return maze[0].length;
    }

    /**
     * Returns the start position of the maze.
     *
     * @return The start position as a Vector2D.
     */
    public Vector2D start() {
        return start;
    }

    /**
     * Returns the end position of the maze.
     *
     * @return The end position as a Vector2D.
     */
    public Vector2D end() {
        return end;
    }

    /**
     * Checks if a position is within the bounds of the maze.
     *
     * @param vector The position to check.
     * @return True if the position is within bounds, false otherwise.
     */
    public boolean isInBounds(Vector2D vector) {
        return vector.x() >= 0
                && vector.y() >= 0
                && vector.x() < rows()
                && vector.y() < cols();
    }

    /**
     * Converts the maze to a string representation with a path.
     *
     * @param path The path to display on the maze.
     * @return A string representation of the maze with the path.
     */
    public String toStringWithPath(List<Node> path) {

        char[][] display = new char[rows()][cols()];

        for (int i = 0; i < rows(); i++) {
            for (int j = 0; j < cols(); j++) {
                display[i][j] = (char) (get(i, j) + '0');
            }
        }

        for (Node node : path) {
            display[node.position().x()][node.position().y()] = '*';
        }

        display[start.x()][start.y()] = 'S';
        display[end.x()][end.y()] = 'E';

        return Arrays.stream(display)
                .map(row -> new String(row).chars()
                        .mapToObj(c -> String.valueOf((char) c))
                        .collect(Collectors.joining(" ")))
                .collect(Collectors.joining("\n"));
    }

    /**
     * Creates a Maze object from a string representation.
     *
     * @param mazeString The string representation of the maze.
     * @return A Maze object.
     */
    public static Maze fromString(String mazeString) {
        String[] rows = mazeString.strip().split("\n");

        int numRows = rows.length;
        int numCols = rows[0].trim().split("\\s+").length;

        int[][] maze = new int[numRows][numCols];

        for (int i = 0; i < numRows; i++) {

            String[] tokens = rows[i].trim().split("\\s+");

            for (int j = 0; j < numCols; j++) {
                String token = tokens[j];
                maze[i][j] = Character.isDigit(token.charAt(0)) ? Integer.parseInt(token) : token.charAt(0);
            }
        }

        return new Maze(maze);

    }

}
