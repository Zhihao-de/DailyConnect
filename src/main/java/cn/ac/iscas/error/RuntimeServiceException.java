package cn.ac.iscas.error;

/**
 * {@code RuntimeServiceException} is the class wrapping any
 * exception that can be thrown during the normal operation.
 *
 * @author Naiming
 */
public
class RuntimeServiceException extends RuntimeException {

    private int code;

    /**
     * Constructs an RuntimeServiceException with no detail message.
     * A detail message is a String that describes this particular exception.
     */
    public RuntimeServiceException() {
        super();
    }

    /**
     * Constructs an RuntimeServiceException with the specified detail
     * message.  A detail message is a String that describes this particular
     * exception.
     *
     * @param s the String that contains a detailed message
     */
    public RuntimeServiceException(int code, String s) {
        super(s);
        this.code = code;
    }

    /**
     * Constructs a new exception with the specified detail message and
     * cause.
     *
     * <p>Note that the detail message associated with <code>cause</code> is
     * <i>not</i> automatically incorporated in this exception's detail
     * message.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link Throwable#getMessage()} method).
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link Throwable#getCause()} method).  (A <tt>null</tt> value
     *                is permitted, and indicates that the cause is nonexistent or
     *                unknown.)
     * @since 1.5
     */
    public RuntimeServiceException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    /**
     * Constructs a new exception with the specified cause and a detail
     * message of <tt>(cause==null ? null : cause.toString())</tt> (which
     * typically contains the class and detail message of <tt>cause</tt>).
     * This constructor is useful for exceptions that are little more than
     * wrappers for other throwables (for example, {@link
     * java.security.PrivilegedActionException}).
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link Throwable#getCause()} method).  (A <tt>null</tt> value is
     *              permitted, and indicates that the cause is nonexistent or
     *              unknown.)
     * @since 1.5
     */
    public RuntimeServiceException(int code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    /**
     * Returns the exception code of this throwable.
     *
     * @return the exception code of this {@code Throwable} instance.
     */
    public int getCode() {
        return this.code;
    }

    static final long serialVersionUID = -3693855725545878508L;
}
