import java.util.NoSuchElementException;


/**
 * Thrown by various accessor methods to indicate that the vertex being requested does not exist
 *
 * @author blee20@georgefox.edu
 */
public class NoSuchVertexException extends NoSuchElementException {
    /**
     * Constructs a {@code NoSuchVertexException} with {@code null}
     * as its error message string
     */
    public NoSuchVertexException() {
        super();
    }


    /**
     * Constructs a {@code NoSuchVertexException}, saving a reference
     * to the error message string {@code msg}
     *
     * @param msg The detail message
     */
    public NoSuchVertexException(String msg) {
        super(msg);
    }
}
