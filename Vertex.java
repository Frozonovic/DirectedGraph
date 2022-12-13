public class Vertex<V>
{
    private final V _label;


    public Vertex(V label)
    {
        if (label == null)
        {
            throw new IllegalArgumentException("Error: Null is considered an invalid value");
        }
        _label = label;
    }


    public V getLabel()
    {
        return _label;
    }


    public boolean equals(Vertex<V> o)
    {
        return getLabel() == o.getLabel();
    }
}
