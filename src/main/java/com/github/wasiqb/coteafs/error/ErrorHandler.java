/*
 * Copyright (c) 2017, Wasiq Bhamla.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.wasiqb.coteafs.error;

import static com.github.wasiqb.coteafs.datasource.DataSource.parse;
import static java.text.MessageFormat.format;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.github.wasiqb.coteafs.error.exceptions.OperationNotSupportedError;
import lombok.SneakyThrows;

/**
 * @author Wasiq Bhamla
 * @since 27-Dec-2020
 */
public final class ErrorHandler {
    private static final ErrorCodes CODES = parse (ErrorCodes.class);

    /**
     * @param cls Error Class
     * @param messageId Message ID
     *
     * @author Wasiq Bhamla
     * @since 27-Dec-2020
     */
    public static <T extends Throwable> void fail (final Class<T> cls, final String messageId, final Object... args) {
        final Class<?>[] clsArray = new Class[] { String.class };
        final Object[] errorArgs = new Object[] { CODES.getMessage (messageId, args) };
        fail (cls, clsArray, errorArgs);
    }

    /**
     * @param cls Error Class
     * @param messageId Message ID
     * @param cause Cause
     *
     * @author Wasiq Bhamla
     * @since 27-Dec-2020
     */
    public static <T extends Throwable> void fail (final Class<T> cls, final Throwable cause, final String messageId,
        final Object... args) {
        final Class<?>[] clsArray = new Class[] { String.class, Throwable.class };
        final Object[] errorArgs = new Object[] { CODES.getMessage (messageId, args), cause };
        fail (cls, clsArray, errorArgs);
    }

    /**
     * @param filterPackage Filter package from stack trace
     * @param cause Cause
     *
     * @return filtered stack trace
     *
     * @author Wasiq Bhamla
     * @since 27-Dec-2020
     */
    public static List<String> handleError (final String filterPackage, final Throwable cause) {
        if (cause == null) {
            return Collections.emptyList ();
        }
        Throwable throwable = cause;
        final List<String> stack = new ArrayList<> ();
        boolean firstEntry = true;
        stack.add (format ("Error occurred: ({0})", throwable.getClass ()
            .getName ()));
        final String stackTrace = "\tat {0}: {1} ({2})";
        do {
            if (!firstEntry) {
                stack.add (format ("Caused by: ({0})", throwable.getClass ()));
            }
            stack.add (format ("Message: {0}", throwable.getMessage ()));
            for (final StackTraceElement trace : throwable.getStackTrace ()) {
                if (filterPackage == null || trace.getClassName ()
                    .startsWith (filterPackage)) {
                    stack.add (
                        format (stackTrace, trace.getClassName (), trace.getMethodName (), trace.getLineNumber ()));
                }
            }
            firstEntry = false;
            throwable = throwable.getCause ();
        } while (throwable != null);
        return stack;
    }

    /**
     * @param cause Cause
     *
     * @return filtered stack trace
     *
     * @author Wasiq Bhamla
     * @since 27-Dec-2020
     */
    public static List<String> handleError (final Throwable cause) {
        return handleError (null, cause);
    }

    @SneakyThrows
    private static <T extends Throwable> void fail (final Class<T> cls, final Class<?>[] clsArray,
        final Object[] args) {
        try {
            final Constructor<T> constructor = cls.getDeclaredConstructor (clsArray);
            throw constructor.newInstance (args);
        } catch (final NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new OperationNotSupportedError ("Error occurred while throwing custom error.", e.getCause ());
        }
    }

    private ErrorHandler () {
        // Utility class.
    }
}