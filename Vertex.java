/**
 * Vertices for plotting on graph
 *
 * @param <V> Type of vertices
 */
public class Vertex<V>
{
    // Internal State
    private final V _label;


    // Constructor
    /**
     * Creates a new instance of Vertex object
     *
     * @param label Vertex label
     */
    public Vertex(V label) {
        if (label == null) {
            throw new IllegalArgumentException("Error: Null is considered an invalid value");
        }
        _label = label;
    }


    // Methods
    /**
     * Fetches the label of the current vertex
     *
     * @return Vertex's label
     */
    public V getLabel() {
        return _label;
    }


    /**
     * Compares the current vertex object to another existing vertex object
     * @param o Another vertex object
     * @return True if labels are the same, else false
     */
    public boolean equals(Vertex<V> o) {
        if (o == null) {
            throw new IllegalArgumentException("Error: Null is considered an invalid value");
        }

        return getLabel() == o.getLabel();
    }
}
