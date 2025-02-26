/**
 * Thrown by various accessor methods to indicate that the edge being requested already exists
 *
 * @author blee20@georgefox.edu
 */
public class DuplicateEdgeException extends RuntimeException {
    /**
     * Constructs a {@code DuplicateEdgeException} with {@code null}
     * as its error message string
     */
    public DuplicateEdgeException() {
        super();
    }


    /**
     * Constructs a {@code DuplicateEdgeException}, saving a reference
     * to the error message string {@code msg}
     *
     * @param msg The detail message
     */
    public DuplicateEdgeException(String msg) {
        super(msg);
    }
}
