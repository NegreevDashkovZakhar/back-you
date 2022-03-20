package it.me.backyou.query.exception;

public class EmptyResultSetException extends Exception {
    public EmptyResultSetException() {
        super();
    }

    public EmptyResultSetException(final String message) {
        super(message);
    }

    public EmptyResultSetException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public EmptyResultSetException(final Throwable cause) {
        super(cause);
    }

    protected EmptyResultSetException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
