package br.pucminas.fpaa.pathfinder;

/**
 * Represents a 2D vector with integer coordinates.
 */
public record Vector2D(int x, int y) {

    /**
     * Adds another vector to this vector.
     *
     * @param other The vector to add.
     * @return A new Vector2D representing the sum of the two vectors.
     */
    public Vector2D add(Vector2D other) {
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

}
