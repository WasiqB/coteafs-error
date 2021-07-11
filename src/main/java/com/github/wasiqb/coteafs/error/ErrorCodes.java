package com.github.wasiqb.coteafs.error;

import static java.text.MessageFormat.format;

import java.util.Map;

import com.github.wasiqb.coteafs.datasource.annotation.DataFile;
import com.github.wasiqb.coteafs.error.exceptions.ErrorCodeNotFoundError;
import lombok.Data;

/**
 * @author Wasiq Bhamla
 * @since 27-Dec-2020
 */
@Data
@DataFile (folderPath = "src/main/resources")
public class ErrorCodes {
    private Map<String, String> errors;

    /**
     * @param code Message ID
     * @param args Message args
     *
     * @return Message
     *
     * @since 27-Dec-2020
     */
    public String getMessage (final String code, final Object... args) {
        if (this.errors.containsKey (code)) {
            return format (this.errors.get (code), args);
        }
        throw new ErrorCodeNotFoundError (code);
    }
}