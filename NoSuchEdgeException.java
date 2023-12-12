import java.util.NoSuchElementException;


/**
 * Thrown by various accessor methods to indicate that the edge being requested does not exist
 *
 * @author blee20@georgefox.edu
 */
public class NoSuchEdgeException extends NoSuchElementException {
    /**
     * Constructs a {@code NoSuchEdgeException} with {@code null}
     * as its error message string
     */
    public NoSuchEdgeException() {
        super();
    }


    /**
     * Constructs a {@code NoSuchEdgeException}, saving a reference
     * to the error message string {@code msg}
     *
     * @param msg The detail message
     */
    public NoSuchEdgeException(String msg) {
        super(msg);
    }
}
