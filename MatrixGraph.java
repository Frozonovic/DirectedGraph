// Imports
import java.util.Iterator;
import java.util.ArrayList;


/**
 * Custom MatrixGraph class
 *
 * @author blee20@georgefox.edu
 * @param <V> Label type for vertices
 * @param <E> Label type for edges
 */
public class MatrixGraph<V, E> extends DirectedGraph<V, E> {
    // Constants
    private static final int DEFAULT_CAPACITY = 10;
    private static final int NOT_FOUND = -1;
    private static final int DOUBLE = 2;


    // Instance Variables
    private Vertex<V>[] _vertices;
    private Edge<V, E>[][] _adjacencyMatrix;
    private int _size;
    private int _edgeCount;
    private int _capacity;


    /**
     * Creates an instance of class MatrixGraph object; designates to second constructor
     */
    public MatrixGraph() {
        this(DEFAULT_CAPACITY);
    }


    /**
     * Creates an instance of class MatrixGraph object
     *
     * @param initialCapacity Starting capacity of the graph
     */
    @SuppressWarnings({"unchecked"})
    public MatrixGraph(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Error: Capacity cannot be negative");
        }

        if (initialCapacity == 0) {
            initialCapacity = DEFAULT_CAPACITY;
        }

        _vertices = (Vertex<V>[]) new Vertex[initialCapacity];
        _adjacencyMatrix = (Edge<V, E>[][]) new Edge[initialCapacity][initialCapacity];

        _capacity = initialCapacity;
        _edgeCount = 0;
        _size = 0;
    }


    /**
     * Creates a new vertex and adds it to the graph
     *
     * @param u Vertex label
     */
    public void add(V u) {
        // Null and duplicate vertices are handled within this call
        duplicateVertex(u);

        // Array growth handler
        growArrays();

        Vertex<V> newVertex = new Vertex<>(u);

        _vertices[_size] = newVertex;

        _size++;
    }


    /**
     * Determines if the given label is assigned to a vertex
     *
     * @param u Vertex label
     * @return True if vertex with label exists, else false
     */
    public boolean contains(V u) {
        // Null vertices are handled within this call
        int index = vertexIndex(u);

        return index != NOT_FOUND;
    }


    /**
     * Fetches the vertex with designated label
     *
     * @param u Vertex label
     * @return Vertex of type V with corresponding label
     */
    public Vertex<V> get(V u) {
        // Null vertices are handled with this call
        int index = vertexIndex(u);

        // Non-existent vertex handler
        noVertex(index);

         return _vertices[index];
    }


    /**
     * Deletes a Vertex object
     *
     * @param u Vertex label
     * @return Deleted vertex's label
     */
    public V remove (V u) {
        // Null vertices are handled within this call
        int index = vertexIndex(u);

        // Non-existent vertex handler
        noVertex(index);

        V returnValue = _vertices[index].getLabel();

        _vertices[index] = null;
        _size--;

        return returnValue;
    }


    /**
     * Creates a new edge and assigns it to two vertices on the graph
     *
     * @param u Source vertex label
     * @param v Destination vertex label
     * @param label Edge label
     */
    public void addEdge(V u, V v, E label) {
        // Null vertices are handled within the following calls
        int uIndex = vertexIndex(u);
        int vIndex = vertexIndex(v);

        // Non-existent vertices are handled here
        noVertex(uIndex);
        noVertex(vIndex);

        // Null edge handler
        nullEdge(label);

        // Duplicate edge handler
        duplicateEdge(uIndex, vIndex);

        // Array growth handler
        growArrays();

        _edgeCount++;
        _adjacencyMatrix[uIndex][vIndex] = new Edge<>(u, v, label);
    }


    /**
     * Determines if the given vertices share an edge
     *
     * @param u Source vertex label
     * @param v Destination vertex label
     * @return True if edge exists from u to v, else false
     */
    public boolean containsEdge(V u, V v) {
        // Null vertices are handled within the following calls
        int uIndex = vertexIndex(u);
        int vIndex = vertexIndex(v);

        // Non-existent vertices are handled here
        noVertex(uIndex);
        noVertex(vIndex);

        return _adjacencyMatrix[uIndex][vIndex] != null;
    }


    /**
     * Fetches an edge if it exists from u to v
     *
     * @param u Source vertex label
     * @param v Destination vertex label
     * @return Edge of type E from u to v
     */
    public Edge<V, E> getEdge(V u, V v) {
        // Null vertices are handled within the following calls
        int uIndex = vertexIndex(u);
        int vIndex = vertexIndex(v);

        // Non-existent vertices are handled here
        noVertex(uIndex);
        noVertex(vIndex);

        // Null edge handler
        noEdge(uIndex, vIndex);

        return _adjacencyMatrix[uIndex][vIndex];
    }


    /**
     * Deletes an Edge object
     *
     * @param u Source vertex label
     * @param v Destination vertex label
     * @return Deleted edge's label
     */
    public E removeEdge(V u, V v) {
        // Null vertices are handled within the following calls
        int uIndex = vertexIndex(u);
        int vIndex = vertexIndex(v);

        // Non-existent vertices are handled here
        noVertex(uIndex);
        noVertex(vIndex);

        // Null edge handler
        noEdge(uIndex, vIndex);

        E returnValue = _adjacencyMatrix[uIndex][vIndex].getLabel();

        _adjacencyMatrix[uIndex][vIndex] = null;
        _edgeCount--;

        return returnValue;
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
        // Null vertex is handled within this call
        int index = vertexIndex(u);

        int degree = 0;

        for (int i = 0; i < _size; i++) {
            if (_adjacencyMatrix[index][i] != null) {
                degree++;
            }
        }

        return degree;
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
     * @return A new Vertex iterator object
     */
    public Iterator<Vertex<V>> vertices() {
        ArrayList<Vertex<V>> arr = new ArrayList<>(_vertices.length);

        for (int i = 0; i < _size; i++) {
            if (_vertices[i] != null) {
                arr.add(_vertices[i]);
            }
        }

        return arr.iterator();
    }


    /**
     * Creates an iterator set for iterating through vertices adjacent to u
     *
     * @param u Source vertex
     * @return A new Vertex iterator object
     */
    public Iterator<Vertex<V>> adjacent(V u) {
        // Fetch position for vertex
        int adjCol = vertexIndex(u);

        // Non-existent vertex handler
        noVertex(adjCol);

        // Initialize an object that supports iteration because we don't do raw implementation for this program
        ArrayList<Vertex<V>> arr = new ArrayList<>(_size);

        // Although it's a 2D array, the column will not change since we're focused on a singular source vertex
        for (int j = 0; j < _size; j++) {
            /* If value is not null, get the vertex from the vertices array. The
            indices should be the same because the length and depth of the 2D
            array are the same (meaning equivalent indices) */
            if (_adjacencyMatrix[adjCol][j] != null) {
                arr.add(_vertices[j]);
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

        for (int i = 0; i < _size; i++) {
            for (int j = 0; j < _size; j++) {
                if (_adjacencyMatrix[i][j] != null) {
                    arr.add(_adjacencyMatrix[i][j]);
                }
            }
        }

        return arr.iterator();
    }


    /**
     * Clears the existing graph
     */
    public void clear() {
        for (int i = 0; i < _size; i++) {
            _vertices[i] = null;

            for (int j = 0; j < _size; j++ ) {
                _adjacencyMatrix[i][j] = null;
            }
        }

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
     * @param uIndex Source vertex index
     */
    private void noVertex(int uIndex) {
        if (uIndex == NOT_FOUND) {
            throw new NoSuchVertexException();
        }
    }


    /**
     * Exception handler for duplicate vertices
     *
     * @param u Source vertex label
     */
    private void duplicateVertex(V u) {
        /* A vertex is considered a duplicate based off of label
        and not off of index. Two vertices may not share the same
        label nor the same index */
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
     * @param uIndex Source vertex index
     * @param vIndex Destination vertex index
     */
    private void noEdge(int uIndex, int vIndex) {
        if (_adjacencyMatrix[uIndex][vIndex] == null) {
            throw new NoSuchEdgeException();
        }
    }


    /**
     * Exception handler for duplicate edges
     *
     * @param uIndex Source vertex index
     * @param vIndex Destination vertex index
     */
    private void duplicateEdge(int uIndex, int vIndex) {
        /* An edge is considered a duplicate based off of index
        and not off of label. Two edges may share the same label
        so long as they exist at separate indices in the matrix */
        if (_adjacencyMatrix[uIndex][vIndex] != null) {
            throw new DuplicateEdgeException();
        }
    }


    /**
     * Fetches the index of a given vertex
     *
     * @param u Vertex label
     * @return Index of vertex
     */
    private int vertexIndex(V u) {
        // Checks that provided vertex isn't null
        nullVertex(u);

        int index = NOT_FOUND;

        for (int i = 0; i < _size && index == NOT_FOUND; i++) {
            if (_vertices[i].getLabel() == u) {
                index = i;
            }
        }

        return index;
    }


    /**
     * If capacity is reached, increases the capacity of the MatrixGraph
     */
    @SuppressWarnings("unchecked")
    private void growArrays() {
        if (_capacity > Integer.MAX_VALUE / 2) {
            throw new OutOfMemoryError("Error: Integer limit reached");
        }

        if (_size == _capacity) {
            Vertex<V>[] vArray = (Vertex<V>[]) new Vertex[_capacity * DOUBLE];
            Edge<V, E>[][] eArray = (Edge<V, E>[][]) new Edge[_capacity * DOUBLE][_capacity * DOUBLE];

            for (int i = 0; i < _vertices.length; i++) {
                vArray[i] = _vertices[i];

                for (int j = 0; j < _adjacencyMatrix[i].length; j++) {
                    eArray[i][j] = _adjacencyMatrix[i][j];
                }
            }

            _capacity *= DOUBLE;
            _vertices = vArray;
            _adjacencyMatrix = eArray;
        }
    }
}
