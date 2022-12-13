// Imports
import java.util.Iterator;
import java.util.ArrayList;


/**
 * Custom class utilizing multidimensional arrays for vertex/edge relationships
 *
 * @author blee20@georgefox.edu
 * @param <V> Label type for vertices
 * @param <E> Label type for edges
 */
public class MatrixGraph<V, E> extends DirectedGraph<V, E>
{
    // Constants
    private static final int DEFAULT_CAPACITY = 10;
    private static final int NOT_FOUND = -1;


    // Instance Variables
    private Vertex<V>[] _vertices;
    private Edge<V, E>[][] _adjacencyMatrix;
    private int lastIndex;
    private int _size;
    private int _edgeCount;


    /**
     * Creates an instance of class MatrixGraph object
     */
    public MatrixGraph()
    {
        new MatrixGraph<>(DEFAULT_CAPACITY);
    }


    /**
     * Creates an instance of class MatrixGraph object
     *
     * @param initialCapacity Starting capacity of the graph
     */
    @SuppressWarnings({"rawtype","unchecked"})
    public MatrixGraph(int initialCapacity)
    {
        if (initialCapacity < 0)
        {
            throw new IllegalArgumentException("Error: Capacity cannot be negative");
        }
        else if (initialCapacity == 0)
        {
            _vertices = (Vertex<V>[]) new Vertex[DEFAULT_CAPACITY];
            _adjacencyMatrix = (Edge<V, E>[][]) new Edge[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        }
        else
        {
            _vertices = (Vertex<V>[]) new Vertex[initialCapacity];
            _adjacencyMatrix = (Edge<V, E>[][]) new Edge[initialCapacity][initialCapacity];
        }

        lastIndex = 0;
        _edgeCount = 0;

        clear();
    }


    /**
     * Creates a new vertex and adds it to the graph
     *
     * @param u Vertex label
     */
    public void add(V u)
    {
        checkNullVertex(u);
        // DuplicateVertexException (if contains(v), throw it)
        if (contains(u))
        {
            throw new DuplicateVertexException();
        }

        Vertex<V> newVertex = new Vertex<>(u);

        _vertices[lastIndex] = newVertex;
        lastIndex++;
        _size++;

        growVertexArray(_vertices.length);
    }


    /**
     * Determines if the given label is assigned to a vertex
     *
     * @param u Vertex label
     * @return True if vertex with label exists, else false
     */
    public boolean contains(V u)
    {
        checkNullVertex(u);

        int index = NOT_FOUND;

        for (int i = 0; i < _vertices.length && index == NOT_FOUND; i++)
        {
            if (_vertices[i].getLabel().equals(u))
            {
                index = i;
            }
        }

        return index != NOT_FOUND;
    }


    /**
     * Fetches the vertex with designated label
     *
     * @param u Vertex label
     * @return Vertex of type V with corresponding label
     */
    public Vertex<V> get(V u)
    {
        checkNullVertex(u);
        // NoSuchVertexException (if !contains(v), throw it)
        int index = NOT_FOUND;

        for (int i = 0; i < _vertices.length && index == NOT_FOUND; i++)
        {
            if (_vertices[i].getLabel().equals(u))
            {
                index = i;
            }
        }

        if (index == NOT_FOUND)
        {
            throw new NoSuchVertexException();
        }

         return _vertices[index];
    }


    /**
     * Deletes a Vertex object
     *
     * @param u Vertex label
     * @return Deleted vertex's label
     */
    public V remove (V u)
    {
        checkNullVertex(u);

        V returnValue = _vertices[vertexIndex(u)].getLabel();

        _vertices[vertexIndex(u)] = null;
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
    public void addEdge(V u, V v, E label)
    {
        checkNullVertex(u);
        checkNullVertex(v);
        checkNullEdge(label);
        // DuplicateEdgeException (if containsEdge, throw it); NoSuchVertexException (if
        // !contains(u) || !contains(v), throw it)
        int uIndex = vertexIndex(u);
        int vIndex = vertexIndex(v);

        if (containsEdge(u, v))
        {
            throw new DuplicateEdgeException();
        }

        if (uIndex == NOT_FOUND || vIndex == NOT_FOUND)
        {
            throw new NoSuchVertexException();
        }

        growEdgeArray(_adjacencyMatrix.length);
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
    public boolean containsEdge(V u, V v)
    {
        checkNullVertex(u);
        checkNullVertex(v);
        // NoSuchVertexException (if !contains(u) || !contains(v), throw it)
        int uIndex = vertexIndex(u);
        int vIndex = vertexIndex(v);

        if (uIndex == NOT_FOUND || vIndex == NOT_FOUND)
        {
            throw new NoSuchVertexException();
        }

        return _adjacencyMatrix[uIndex][vIndex] != null;
    }


    /**
     * Fetches an edge if it exists from u to v
     *
     * @param u Source vertex label
     * @param v Destination vertex label
     * @return Edge of type E from u to v
     */
    public Edge<V, E> getEdge(V u, V v)
    {
        checkNullVertex(u);
        checkNullVertex(v);
        // NoSuchVertexException (containsEdge throws already); NoSuchEdgeException (if
        // !containsEdge(u, v), throw it)
        int uIndex = vertexIndex(u);
        int vIndex = vertexIndex(v);

        if (uIndex == NOT_FOUND || vIndex == NOT_FOUND)
        {
            throw new NoSuchVertexException();
        }

        if (!containsEdge(u, v))
        {
            throw new NoSuchEdgeException();
        }

        return _adjacencyMatrix[uIndex][vIndex];
    }


    /**
     * Deletes an Edge object
     *
     * @param u Source vertex label
     * @param v Destination vertex label
     * @return Deleted edge's label
     */
    public E removeEdge(V u, V v)
    {
        checkNullVertex(u);
        checkNullVertex(v);
        // NoSuchVertexException (containsEdge throws already); NoSuchEdgeException (if
        // !containsEdge(u, v), throw it)
        int uIndex = vertexIndex(u);
        int vIndex = vertexIndex(v);

        if (!containsEdge(u, v))
        {
            throw new NoSuchEdgeException();
        }

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
    public int size()
    {
        return _size;
    }


    /**
     * Fetches the number of edges emanating from the given vertex
     *
     * @param u Source vertex label
     * @return Number of edges from u to another vertex
     */
    public int degree(V u)
    {
        checkNullVertex(u);
        // Number of outgoing edges emanating from that vertex
        // NoSuchVertexException (if !contains(u), throw it)
        if (!contains(u))
        {
            throw new NoSuchVertexException();
        }

        int degree = 0;

        for (int i = 0; i < _adjacencyMatrix.length; i++)
        {
            for (int j = 0; j < _adjacencyMatrix[i].length; j++)
            {
                if (_adjacencyMatrix[i][j].getU().equals(u))
                {
                    degree++;
                }
            }
        }

        return degree;
    }


    /**
     * Fetches the number of edges in the entire graph
     *
     * @return Number of edges in graph
     */
    public int edgeCount()
    {
        return _edgeCount;
    }


    /**
     * Creates an iterator set for iterating through all the vertices
     *
     * @return A new vertex iterator object
     */
    public Iterator<Vertex<V>> vertices()
    {
        ArrayList<Vertex<V>> arr = new ArrayList<>(_vertices.length);

        for (Vertex<V> v : _vertices)
        {
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
    public Iterator<Vertex<V>> adjacent(V u)
    {
        return null;
    }


    /**
     * Creates an iterator set for iterating through all the edges
     *
     * @return A new iterator object
     */
    public Iterator<Edge<V, E>> edges()
    {
        ArrayList<Edge<V, E>> arr = new ArrayList<>();

        for (Edge<V, E>[] adjacencyMatrix : _adjacencyMatrix)
        {
            for (Edge<V, E> edge : adjacencyMatrix)
            {
                if (edge != null)
                {
                    arr.add(edge);
                }
            }
        }

        return arr.iterator();
    }


    /**
     * Clears the existing graph
     */
    public void clear()
    {
        _size = 0;
        _edgeCount = 0;

        for (int i = 0; i < _adjacencyMatrix.length; i++)
        {
            _vertices[i] = null;

            for (int j = 0; j < _adjacencyMatrix[i].length; j++)
            {
                _adjacencyMatrix[i][j] = null;
            }
        }
    }


    /**
     * Determines if the current graph contains any vertices
     *
     * @return True if size is 0, else false
     */
    public boolean isEmpty()
    {
        return _size == 0;
    }


    // Helper Methods
    /**
     * Exception handler for null-labeled vertices
     *
     * @param u Source vertex label
     */
    private void checkNullVertex(V u)
    {
        if (u == null)
        {
            throw new IllegalArgumentException("Error: Null is considered an invalid value");
        }
    }


    /**
     * Exception handler for null-labeled edges
     *
     * @param e Edge label
     */
    private void checkNullEdge(E e)
    {
        if (e == null)
        {
            throw new IllegalArgumentException("Error: Null is considered an invalid value");
        }
    }


    /**
     * Fetches the index of a given vertex
     *
     * @param u Vertex label
     * @return Index of vertex
     */
    private int vertexIndex(V u)
    {
        int index = NOT_FOUND;

        for (int i = 0; i < _vertices.length && index == NOT_FOUND; i++)
        {
            if (_vertices[i].getLabel() == u)
            {
                index = i;
            }
        }

        return index;
    }


    /**
     * Expands the vertex array
     *
     * @param capacity Current capacity of vertex array
     */
    @SuppressWarnings({"rawtype", "unchecked"})
    private void growVertexArray(int capacity)
    {
        if (lastIndex >= _vertices.length)
        {
            Vertex<V>[] vArray = (Vertex<V>[]) new Vertex[capacity * 2];

            for (int i = 0; i < _vertices.length; i++)
            {
                vArray[i] = _vertices[i];
            }

            _vertices = vArray;

            growEdgeArray(capacity);
        }
    }


    /**
     * Expands the adjacency matrix array
     *
     * @param capacity Current capacity of array
     */
    @SuppressWarnings({"rawtype", "unchecked"})
    private void growEdgeArray(int capacity)
    {
        Edge<V, E>[][] eArray = (Edge<V, E>[][]) new Edge[capacity * 2][capacity * 2];

        for (int i = 0; i < _adjacencyMatrix.length; i++)
        {

            for (int j = 0; j < _adjacencyMatrix[i].length; j++)
            {
                eArray[i][j] = _adjacencyMatrix[i][j];
            }
        }

        _adjacencyMatrix = eArray;
    }
}
