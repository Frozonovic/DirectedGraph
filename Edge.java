/**
 * Edges for connecting vertices on the graph
 *
 * @param <V> Type of vertices
 * @param <E> Type of edges
 */
public class Edge<V, E>
{
    // Internal State
    private final V _u;
    private final V _v;
    private E _label;


    // Constructor
    /**
     * Creates new instance of class Edge object
     *
     * @param u Source vertex
     * @param v Destination vertex
     * @param label Edge label
     */
    public Edge(V u, V v, E label) {
        checkNullVertex(u);
        checkNullVertex(v);

        if (label == null) {
            throw new IllegalArgumentException("Error: Null is considered an invalid value");
        }

        _u = u;
        _v = v;
        _label = label;
    }


    /**
     * Fetches the source vertex of the current Edge object
     *
     * @return Source vertex
     */
    public V getU() {
        return _u;
    }


    /**
     * Fetches the destination vertex of the current Edge object
     *
     * @return Destination vertex
     */
    public V getV() {
        return _v;
    }


    /**
     * Fetches the label of the current Edge object
     *
     * @return Edge's label
     */
    public E getLabel() {
        return _label;
    }


    /**
     * Reassigns the current Edge object's label
     *
     * @param label New label
     */
    public void setLabel(E label) {
        if (label == null) {
            throw new IllegalArgumentException("Error: Null is considered an invalid value");
        }

        _label = label;
    }


    /**
     * Compares the current Edge object to another existing Edge object
     *
     * @param o Another Edge
     * @return True if source vertex and destination vertex are the same, else false
     */
    public boolean equals(Edge<V, E> o) {
        if (o == null) {
            throw new IllegalArgumentException("Error: Null is considered an invalid value");
        }

        return getU().equals(o.getU()) && getV().equals(o.getV());
    }


    // Helper Method
    /**
     * Exception handler for null-labeled vertices
     *
     * @param u Source vertex label
     */
    private void checkNullVertex(V u) {
        if (u == null) {
            throw new IllegalArgumentException("Error: Null is considered an invalid value");
        }
    }
}
