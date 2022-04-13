package it.me.backyou.repository.query.exception;

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
     */
    public EmptyResultSetException(final String message) {
        super(message);
    }

    /**
     * Delegated constructor
     */
    public EmptyResultSetException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Delegated constructor
     */
    public EmptyResultSetException(final Throwable cause) {
        super(cause);
    }

    protected EmptyResultSetException(final String message, final Throwable cause, final boolean enableSuppression,
                                      final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
