/**
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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.github.wasiqb.coteafs.error.CoteafsError;
import com.github.wasiqb.coteafs.error.enums.Category;
import com.github.wasiqb.coteafs.error.enums.Reason;
import com.github.wasiqb.coteafs.error.enums.Severity;

/**
 * @author Wasiq Bhamla
 * @since Jul 9, 2017 6:34:49 PM
 */
public class ErrorUtil {
	/**
	 * @author Wasiq Bhamla
	 * @since Jul 31, 2017 5:10:39 PM
	 * @param cls
	 * @param message
	 */
	public static <T extends CoteafsError> void fail (final Class <T> cls, final String message) {
		final Class <?> [] clsArray = new Class [] { String.class };
		final Object [] args = new Object [] { message };
		fail (cls, clsArray, args);
	}

	/**
	 * @author Wasiq Bhamla
	 * @since Jul 31, 2017 5:02:40 PM
	 * @param cls
	 * @param message
	 * @param reason
	 * @param category
	 * @param severity
	 */
	public static <T extends CoteafsError> void fail (final Class <T> cls, final String message, final Reason reason,
			final Category category, final Severity severity) {
		final Class <?> [] clsArray = new Class [] { String.class, Reason.class, Category.class, Severity.class };
		final Object [] args = new Object [] { message, reason, category, severity };
		fail (cls, clsArray, args);
	}

	/**
	 * @author Wasiq Bhamla
	 * @since Jul 31, 2017 5:09:00 PM
	 * @param cls
	 * @param message
	 * @param cause
	 */
	public static <T extends CoteafsError> void fail (final Class <T> cls, final String message,
			final Throwable cause) {
		final Class <?> [] clsArray = new Class [] { String.class, Throwable.class };
		final Object [] args = new Object [] { message, cause };
		fail (cls, clsArray, args);
	}

	/**
	 * @author Wasiq Bhamla
	 * @since Jul 31, 2017 5:09:48 PM
	 * @param cls
	 * @param message
	 * @param cause
	 * @param reason
	 * @param category
	 * @param severity
	 */
	public static <T extends CoteafsError> void fail (final Class <T> cls, final String message, final Throwable cause,
			final Reason reason, final Category category, final Severity severity) {
		final Class <?> [] clsArray = new Class [] { String.class, Throwable.class, Reason.class, Category.class,
				Severity.class };
		final Object [] args = new Object [] { message, cause, reason, category, severity };
		fail (cls, clsArray, args);
	}

	/**
	 * @author Wasiq Bhamla
	 * @since Jul 31, 2017 5:03:52 PM
	 * @param cls
	 * @param clsArray
	 * @param args
	 */
	private static <T extends CoteafsError> void fail (final Class <T> cls, final Class <?> [] clsArray,
			final Object [] args) {
		try {
			final Constructor <T> ctor = cls.getDeclaredConstructor (clsArray);
			throw ctor.newInstance (args);
		}
		catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			throw new CoteafsError ("Error occurred while throwing custom error.", e.getCause ());
		}
	}

	private ErrorUtil () {
		// Utility class.
	}
}