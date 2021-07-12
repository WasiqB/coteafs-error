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
package com.github.wasiqb.coteafs.error.util;

import static com.github.wasiqb.coteafs.error.ErrorHandler.fail;
import static com.github.wasiqb.coteafs.error.ErrorHandler.handleError;
import static com.google.common.truth.Truth.assertWithMessage;

import java.io.FileNotFoundException;
import java.lang.reflect.Modifier;

import com.github.wasiqb.coteafs.datasource.error.OperationNotSupportedError;
import com.github.wasiqb.coteafs.error.ErrorHandler;
import lombok.SneakyThrows;
import org.testng.annotations.Test;

/**
 * @author Wasiq Bhamla
 * @since 27-Dec-2020
 */
public class ErrorHandlerTest {
    /**
     * @author Wasiq Bhamla
     * @since 27-Dec-2020
     */
    @Test (expectedExceptions = OperationNotSupportedError.class, expectedExceptionsMessageRegExp = "Test Error!")
    public void testFailMethodWithThreeArgs () {
        fail (OperationNotSupportedError.class, "fnf", new FileNotFoundException ());
    }

    /**
     * @author Wasiq Bhamla
     * @since 27-Dec-2020
     */
    @Test (expectedExceptions = OperationNotSupportedError.class, expectedExceptionsMessageRegExp = "Test Error!")
    public void testFailMethodWithTwoArgs () {
        fail (OperationNotSupportedError.class, "fnf");
    }

    /**
     * @author Wasiq Bhamla
     * @since 27-Dec-2020
     */
    @Test
    public void testHandleErrorWithFilteredStackTrace () {
        try {
            fail (OperationNotSupportedError.class, "ge");
        } catch (final OperationNotSupportedError e) {
            handleError ("com.github.wasiqb", e).forEach (System.err::println);
        }
    }

    /**
     * @author Wasiq Bhamla
     * @since 27-Dec-2020
     */
    @Test
    public void testHandleErrorWithFullStackTrace () {
        try {
            fail (OperationNotSupportedError.class, "ge");
        } catch (final OperationNotSupportedError e) {
            handleError (e).forEach (System.err::println);
        }
    }

    /**
     * @author Wasiq Bhamla
     * @since 27-Dec-2020
     */
    @SneakyThrows
    @Test
    public void testInitializationFailure () {
        final var clazz = ErrorHandler.class;
        final var constructor = clazz.getDeclaredConstructor ();
        assertWithMessage ("ctor modifier").that (Modifier.isPrivate (constructor.getModifiers ()))
            .isTrue ();
        try {
            constructor.setAccessible (true);
            constructor.newInstance ();
        } finally {
            constructor.setAccessible (false);
        }
    }
}