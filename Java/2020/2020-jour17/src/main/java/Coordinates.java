import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(staticName = "of")
public class Coordinates {

    public static final Coordinates ORIGIN = new Coordinates(0, 0, 0, 0);
    public int x, y, z, w;

    /**
     * Parses a coordinate in the format "x,y,z,w".
     *
     * @param coord The input string of which to parse a coordinate.
     * @return A new {@link Coordinates} object.
     */
    public static Coordinates parse(String coord) {
        String[] arr = coord.split(",");
        int x = Integer.parseInt(arr[0]);
        int y = Integer.parseInt(arr[1]);
        int z = Integer.parseInt(arr[2]);
        int w = Integer.parseInt(arr[3]);
        return new Coordinates(x, y, z, w);
    }

    public Coordinates resolve(Coordinates other) {
        return resolve(other.x, other.y, other.z, other.w);
    }

    /**
     * Creates a new {@link Coordinates} based on the offset of x, y, z, and w given in parameters added to the current
     * coordinate.
     *
     * @param x The offset-x to add to the coordinate x.
     * @param y The offset-y to add to the coordinate y.
     * @param z The offset-z to add to the coordinate z.
     * @param w The offset-w to add to the coordinate w.
     * @return A new {@link Coordinates} created from the sum of the current coordinates and offset values provided.
     */
    public Coordinates resolve(int x, int y, int z, int w) {
        return new Coordinates(this.x + x, this.y + y, this.z + z, this.w + w);
    }

    /**
     * Finds the Manhattan distance from this coordinate to the origin at (0, 0, 0, 0).
     *
     * @return The Manhattan distance from this coordinate to the origin at (0, 0, 0, 0).
     */
    public int distanceToOrigin() {
        return distance(0, 0, 0, 0);
    }

    /**
     * Finds the Manhattan distance from this coordinate to (x2, y2, z2, w2).
     *
     * @param x2 The x of the other coordinate.
     * @param y2 The y of the other coordinate.
     * @param z2 The z of the other coordinate.
     * @param w2 The w of the other coordinate.
     * @return The Manhattan distance from this coordinate to (x2, y2, z2, w2).
     */
    public int distance(int x2, int y2, int z2, int w2) {
        return Math.abs(x - x2) + Math.abs(y - y2) + Math.abs(z - z2) + Math.abs(w - w2);
    }

    /**
     * Finds the Manhattan distance from this coordinate to the other coordinate specified.
     *
     * @param other The coordinate object to compare with.
     * @return The Manhattan distance from this coordinate to the other coordinate specified.
     */
    public int distance(Coordinates other) {
        return distance(other.x, other.y, other.z, other.w);
    }
}
