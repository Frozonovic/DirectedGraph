public class Edge<V, E>
{
    private final V _u;
    private final V _v;
    private E _label;


    public Edge(V u, V v, E label)
    {
        if (label == null)
        {
            throw new IllegalArgumentException("Error: Null is considered an invalid value");
        }

        _u = u;
        _v = v;
        _label = label;
    }


    public V getU()
    {
        return _u;
    }


    public V getV()
    {
        return _v;
    }


    public E getLabel()
    {
        return _label;
    }


    public void setLabel(E label)
    {
        if (label == null)
        {
            throw new IllegalArgumentException("Error: Null is considered an invalid value");
        }

        _label = label;
    }


    public boolean equals(Edge<V, E> o)
    {
        return o.getU().equals(_u) && o.getV().equals(_v);
    }
}
