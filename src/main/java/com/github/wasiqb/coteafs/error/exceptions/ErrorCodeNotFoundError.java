package com.github.wasiqb.coteafs.error.exceptions;

import java.io.Serial;

/**
 * @author Wasiq Bhamla
 * @since 27-Dec-2020
 */
public class ErrorCodeNotFoundError extends Error {
    @Serial
    private static final long serialVersionUID = 6502006342710444775L;

    /**
     * @param message Message
     *
     * @since 27-Dec-2020
     */
    public ErrorCodeNotFoundError (final String message) {
        super (message);
    }
}