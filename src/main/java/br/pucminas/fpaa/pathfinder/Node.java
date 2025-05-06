package br.pucminas.fpaa.pathfinder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Represents a node in the A* pathfinding algorithm.
 */
public record Node(
        Vector2D position,
        double gCost,
        double hCost,
        Node parent) implements Comparable<Node> {

    /**
     * Calculates the total estimated cost (f = g + h).
     *
     * @return The total estimated cost.
     */
    public double fCost() {
        return this.gCost + this.hCost;
    }

    /**
     * Reconstructs the path from the start node to this node.
     *
     * @return A list of nodes representing the path.
     */
    public List<Node> reconstructPath() {
        List<Node> path = new ArrayList<>();
        Node node = this;
        while (node != null) {
            path.add(node);
            node = node.parent();
        }
        Collections.reverse(path);
        return path;
    }

    @Override
    public int compareTo(Node other) {
        return Double.compare(fCost(), other.fCost());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Node o) {
            return this.position.equals(o.position);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.position);
    }
}
