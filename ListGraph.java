// Imports
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;


/**
 * Custom ListGraph class
 *
 * @author blee20@georgefox.edu
 * @param <V> Label type for vertices
 * @param <E> Label type for edges
 */
public class ListGraph<V, E> extends DirectedGraph<V, E> {
    // Internal State
    private final HashMap<V, Vertex<V>> _vertices;
    private final HashMap<V, HashMap<V, Edge<V, E>>> _adjacencies;
    private int _size;
    private int _edgeCount;


    // Constructor
    /**
     * Creates an instance of class ListGraph object
     */
    public ListGraph() {
        _vertices = new HashMap<>();
        _adjacencies = new HashMap<>();
    }


    // Methods
    /**
     * Creates a new vertex and adds it to the graph
     *
     * @param u Vertex label
     */
    public void add(V u) {
        // Null and duplicate vertex handler
        duplicateVertex(u);

        _vertices.put(u, new Vertex<>(u));
        _size++;
    }


    /**
     * Determines if the given label is assigned to a vertex
     *
     * @param u Vertex label
     * @return True if vertex with label exists, else false
     */
    public boolean contains(V u) {
        // Null vertex handler
        nullVertex(u);

        return _vertices.containsKey(u);
    }


    /**
     * Fetches the vertex with designated label
     *
     * @param u Vertex label
     * @return Vertex of type V with corresponding label
     */
    public Vertex<V> get(V u) {
        // Null and non-existent vertex handler
        noVertex(u);

        return _vertices.get(u);
    }


    /**
     * Deletes a Vertex object
     *
     * @param u Vertex label
     * @return Deleted vertex's label
     */
    public V remove(V u) {
        // Null and non-existent vertex handler
        noVertex(u);

        _size--;

        return _vertices.remove(u).getLabel();
    }


    /**
     * Creates a new edge and assigns it to two vertices on the graph
     *
     * @param u Source vertex label
     * @param v Destination vertex label
     * @param label Edge label
     */
    public void addEdge(V u, V v, E label) {
        // Duplicate edge, null vertices, and non-existent vertices are handled within
        duplicateEdge(u, v);

        if (!_adjacencies.containsKey(u)) {
            _adjacencies.put(u, new HashMap<>());
        }

        _adjacencies.get(u).put(v, new Edge<>(u, v, label));

        _edgeCount++;
    }


    /**
     * Determines if the given vertices share an edge
     *
     * @param u Source vertex label
     * @param v Destination vertex label
     * @return True if edge exists from u to v, else false
     */
    public boolean containsEdge(V u, V v) {
        // Null and non-existent vertices handled within
        noVertex(u);
        noVertex(v);

        return _adjacencies.containsKey(u) && _adjacencies.get(u).containsKey(v);
    }


    /**
     * Fetches an edge if it exists from u to v
     *
     * @param u Source vertex label
     * @param v Destination vertex label
     * @return Edge of type E from u to v
     */
    public Edge<V, E> getEdge(V u, V v) {
        // Null and non-existent vertices are handled within
        noVertex(u);
        noVertex(v);

        // Non-existent edge handler
        noEdge(u, v);

        return _adjacencies.get(u).get(v);
    }


    /**
     * Deletes an Edge object
     *
     * @param u Source vertex label
     * @param v Destination vertex label
     * @return Deleted edge's label
     */
    public E removeEdge(V u, V v) {
        // Null vertices, non-existent vertices, and non-existent edge are handled within
        noEdge(u, v);

        _edgeCount--;

        return _adjacencies.get(u).remove(v).getLabel();
    }


    /**
     * Fetches the size of the graph (number of vertices)
     *
     * @return Number of vertices in graph
     */
    public int size() {
        return _size;
    }


    /**
     * Fetches the number of edges emanating from the given vertex
     *
     * @param u Source vertex label
     * @return Number of edges from u to another vertex
     */
    public int degree(V u) {
        noVertex(u);

        return _adjacencies.get(u).size();
    }


    /**
     * Fetches the number of edges in the entire graph
     *
     * @return Number of edges in graph
     */
    public int edgeCount() {
        return _edgeCount;
    }


    /**
     * Creates an iterator set for iterating through all the vertices
     *
     * @return A new vertex iterator object
     */
    public Iterator<Vertex<V>> vertices() {
        ArrayList<Vertex<V>> arr = new ArrayList<>(_vertices.size());

        for (Vertex<V> v : _vertices.values()) {
            arr.add(v);
        }

        return arr.iterator();
    }


    /**
     * Creates an iterator set for iterating through vertices adjacent to u
     *
     * @param u Source vertex
     * @return A new iterator object
     */
    public Iterator<Vertex<V>> adjacent(V u) {
        ArrayList<Vertex<V>> arr = new ArrayList<>(_vertices.size());

        noVertex(u);

        for (Edge<V, E> edge : _adjacencies.get(u).values()) {
            if (edge != null) {
                arr.add(_vertices.get(edge.getV()));
            }
        }

        return arr.iterator();
    }


    /**
     * Creates an iterator set for iterating through all the edges
     *
     * @return A new iterator object
     */
    public Iterator<Edge<V, E>> edges() {
        ArrayList<Edge<V, E>> arr = new ArrayList<>();

        for (HashMap<V, Edge<V, E>> v : _adjacencies.values()) {
            for (Edge<V, E> edge : v.values()) {
                if (edge != null) {
                    arr.add(edge);
                }
            }
        }

        return arr.iterator();
    }


    /**
     * Clears the existing graph
     */
    public void clear() {
        _vertices.clear();
        _adjacencies.clear();

        _size = 0;
        _edgeCount = 0;
    }


    /**
     * Determines if the current graph contains any vertices
     *
     * @return True if size is 0, else false
     */
    public boolean isEmpty() {
        return _size == 0;
    }


    // Helper Methods
    /**
     * Exception handler for null-labeled vertices
     *
     * @param u Source vertex label
     */
    private void nullVertex(V u) {
        if (u == null) {
            throw new IllegalArgumentException("Error: Null is considered an invalid value");
        }
    }


    /**
     * Exception handler for non-existent vertices
     *
     * @param u Source vertex label
     */
    private void noVertex(V u) {
        if (!contains(u)) {
            throw new NoSuchVertexException();
        }
    }


    /**
     * Exception handler for duplicate vertices
     *
     * @param u Source vertex label
     */
    private void duplicateVertex(V u) {
        if (contains(u)) {
            throw new DuplicateVertexException();
        }
    }


    /**
     * Exception handler for null-labeled edges
     *
     * @param e Edge label
     */
    private void nullEdge(E e) {
        if (e == null) {
            throw new IllegalArgumentException("Error: Null is considered an invalid value");
        }
    }


    /**
     * Exception handler for non-existent edges
     *
     * @param u Source vertex label
     * @param v Destination vertex label
     */
    private void noEdge(V u, V v) {
        if (!containsEdge(u, v)) {
            throw new NoSuchEdgeException();
        }
    }


    /**
     * Exception handler for duplicate edges
     *
     * @param u Source vertex label
     * @param v Destination vertex label
     */
    private void duplicateEdge(V u, V v) {
        if (containsEdge(u, v)) {
            throw new DuplicateEdgeException();
        }
    }
}
