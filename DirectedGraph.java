import java.util.Iterator;

public abstract class DirectedGraph<V, E>
{
    // TODO implement class
    public void add(V v)
    {
        // FIXME
    }


    public boolean contains(V v)
    {
        // FIXME
        return false;
    }


    public Vertex<V> get(V v)
    {
        // FIXME
        return null;
    }


    public V remove(V v)
    {
        // FIXME
        return null;
    }


    public void addEdge(V u, V v, E label)
    {
        // FIXME
    }


    public boolean containsEdge(V u, V v)
    {
        // FIXME
        return false;
    }


    public Edge<V, E> getEdge(V u, V v)
    {
        // FIXME
        return null;
    }


    public E removeEdge(V u, V v)
    {
        // FIXME
        return null;
    }


    public int size()
    {
        // FIXME
        return 0;
    }


    public int degree(V u)
    {
        // FIXME
        return 0;
    }


    public int edgeCount()
    {
        // FIXME
        return 0;
    }


    public Iterator<Vertex<V>> vertices()
    {
        // FIXME
        return null;
    }


    public Iterator<Vertex<V>> adjacent(V u)
    {
        // FIXME
        return null;
    }


    public Iterator<Edge<V, E>> edges()
    {
        // FIXME
        return null;
    }


    public void clear()
    {
        // FIXME
    }


    public boolean isEmpty()
    {
        return size() == 0;
    }
}

