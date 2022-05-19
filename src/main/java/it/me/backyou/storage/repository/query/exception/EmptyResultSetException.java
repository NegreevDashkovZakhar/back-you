package it.me.backyou.storage.repository.query.exception;

/**
 * Exception thrown when tried to parse empty result set
 */
public class EmptyResultSetException extends Exception {
    /**
     * Delegated constructor
     */
    public EmptyResultSetException() {
        super();
    }

    /**
     * Delegated constructor
     *
     * @param message passed message
     */
    public EmptyResultSetException(final String message) {
        super(message);
    }

    /**
     * Delegated constructor
     *
     * @param message passed message
     * @param cause passed cause
     */
    public EmptyResultSetException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Delegated constructor
     *
     * @param cause passes cause
     */
    public EmptyResultSetException(final Throwable cause) {
        super(cause);
    }

    protected EmptyResultSetException(final String message, final Throwable cause, final boolean enableSuppression,
                                      final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
