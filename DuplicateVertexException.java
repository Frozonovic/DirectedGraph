/**
 * Thrown by various accessor methods to indicate that the vertex being requested already exists
 *
 * @author blee20@georgefox.edu
 */
public class DuplicateVertexException extends RuntimeException {
    /**
     * Constructs a {@code DuplicateVertexException} with {@code null}
     * as its error message string
     */
    public DuplicateVertexException() {
        super();
    }


    /**
     * Constructs a {@code DuplicateVertexException}, saving a reference
     * to the error message string {@code msg}
     *
     * @param msg The detail message
     */
    public DuplicateVertexException(String msg) {
        super(msg);
    }
}
