import java.util.Iterator;


/**
 * Parent class for MatrixGraph and ListGraph
 *
 * @param <V> Label type for vertices
 * @param <E> Label type for edges
 */
public abstract class DirectedGraph<V, E>
{
    /**
     * Creates a new vertex and adds it to the graph
     *
     * @param u Vertex label
     */
    abstract void add(V u);


    /**
     * Determines if the given label is assigned to a vertex
     *
     * @param u Vertex label
     * @return True if vertex with label exists, else false
     */
    abstract boolean contains(V u);


    /**
     * Fetches the vertex with designated label
     *
     * @param u Vertex label
     * @return Vertex of type V with corresponding label
     */
    abstract Vertex<V> get(V u);


    /**
     * Deletes a Vertex object
     *
     * @param u Vertex label
     * @return Deleted vertex's label
     */
    abstract V remove(V u);


    /**
     * Creates a new edge and assigns it to two vertices on the graph
     *
     * @param u Source vertex label
     * @param v Destination vertex label
     * @param label Edge label
     */
    abstract void addEdge(V u, V v, E label);


    /**
     * Determines if the given vertices share an edge
     *
     * @param u Source vertex label
     * @param v Destination vertex label
     * @return True if edge exists from u to v, else false
     */
    abstract boolean containsEdge(V u, V v);


    /**
     * Fetches an edge if it exists from u to v
     *
     * @param u Source vertex label
     * @param v Destination vertex label
     * @return Edge of type E from u to v
     */
    abstract Edge<V, E> getEdge(V u, V v);


    /**
     * Deletes an Edge object
     *
     * @param u Source vertex label
     * @param v Destination vertex label
     * @return Deleted edge's label
     */
    abstract E removeEdge(V u, V v);


    /**
     * Fetches the size of the graph (number of vertices)
     *
     * @return Number of vertices in graph
     */
    abstract int size();


    /**
     * Fetches the number of edges emanating from the given vertex
     *
     * @param u Source vertex label
     * @return Number of edges from u to another vertex
     */
    abstract int degree(V u);


    /**
     * Fetches the number of edges in the entire graph
     *
     * @return Number of edges in graph
     */
    abstract int edgeCount();


    /**
     * Creates an iterator set for iterating through all the vertices
     *
     * @return A new vertex iterator object
     */
    abstract Iterator<Vertex<V>> vertices();


    /**
     * Creates an iterator set for iterating through vertices adjacent to u
     *
     * @param u Source vertex
     * @return A new iterator object
     */
    abstract Iterator<Vertex<V>> adjacent(V u);


    /**
     * Creates an iterator set for iterating through all the edges
     *
     * @return A new iterator object
     */
    abstract Iterator<Edge<V, E>> edges();


    /**
     * Clears the existing graph
     */
    abstract void clear();


    /**
     * Determines if the current graph contains any vertices
     *
     * @return True if size is 0, else false
     */
    abstract boolean isEmpty();
}

