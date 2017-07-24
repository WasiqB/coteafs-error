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

import static com.github.wasiqb.coteafs.error.util.ErrorUtil.fail;
import static com.google.common.truth.Truth.assertThat;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import org.testng.annotations.Test;

import com.github.wasiqb.coteafs.error.CoteafsError;
import com.github.wasiqb.coteafs.error.NotImplementedError;
import com.github.wasiqb.coteafs.error.OperationNotSupportedError;
import com.github.wasiqb.coteafs.error.enums.Category;
import com.github.wasiqb.coteafs.error.enums.Reason;
import com.github.wasiqb.coteafs.error.enums.Severity;

/**
 * @author Wasiq Bhamla
 * @since Jul 23, 2017 2:51:13 PM
 */
public class ErrorUtilTest {
	/**
	 * @author Wasiq Bhamla
	 * @since Jul 24, 2017 5:12:55 PM
	 */
	@SuppressWarnings ("unchecked")
	@Test (expectedExceptions = CoteafsError.class, expectedExceptionsMessageRegExp = "Test Error!")
	public void testCoteafsErrorWithFourArg () {
		fail (CoteafsError.class, "Test Error!", Reason.R2, Category.C1, Severity.CRITICAL);
	}

	/**
	 * @author Wasiq Bhamla
	 * @since Jul 24, 2017 4:34:18 PM
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Test
	public void testInitializationFailure () throws InstantiationException, IllegalAccessException,
			NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		final Class <ErrorUtil> clazz = ErrorUtil.class;
		final Constructor <ErrorUtil> constructor = clazz.getDeclaredConstructor ();
		assertThat (Modifier.isPrivate (constructor.getModifiers ())).named ("ctor modifier")
			.isTrue ();
		try {
			constructor.setAccessible (true);
			constructor.newInstance ();
		}
		finally {
			constructor.setAccessible (false);
		}
	}

	/**
	 * @author Wasiq Bhamla
	 * @since Jul 24, 2017 5:12:48 PM
	 */
	@Test (expectedExceptions = NotImplementedError.class, expectedExceptionsMessageRegExp = "Test Error!")
	public void testNotImplementedErrorWithOneArg () {
		fail (NotImplementedError.class, "Test Error!");
	}

	/**
	 * @author Wasiq Bhamla
	 * @since Jul 24, 2017 5:15:19 PM
	 */
	@Test (expectedExceptions = NotImplementedError.class, expectedExceptionsMessageRegExp = "Test Error!")
	public void testNotImplementedErrorWithTwoArg () {
		fail (NotImplementedError.class, "Test Error!", new Throwable ());
	}

	/**
	 * @author Wasiq Bhamla
	 * @since Jul 24, 2017 4:58:29 PM
	 */
	@Test
	public void testOperationNotSupportedErrorTypes () {
		try {
			fail (OperationNotSupportedError.class, "Error");
		}
		catch (final OperationNotSupportedError e) {
			assertThat (e.getReason ()
				.reason ()).isEqualTo (Reason.R2.reason ());
			assertThat (e.getCategory ()
				.category ()).isEqualTo (Category.C1.category ());
			assertThat (e.getSeverity ()).isEqualTo (Severity.CRITICAL);
		}
	}

	/**
	 * @author Wasiq Bhamla
	 * @since Jul 23, 2017 2:58:39 PM
	 */
	@Test (expectedExceptions = CoteafsError.class)
	public void testOperationNotSupportedErrorWithInvalidParams () {
		fail (OperationNotSupportedError.class, 10);
	}

	/**
	 * @author Wasiq Bhamla
	 * @since Jul 23, 2017 2:55:32 PM
	 */
	@Test (expectedExceptions = OperationNotSupportedError.class, expectedExceptionsMessageRegExp = "Test Error!")
	public void testOperationNotSupportedErrorWithOneArg () {
		fail (OperationNotSupportedError.class, "Test Error!");
	}

	/**
	 * @author Wasiq Bhamla
	 * @since Jul 24, 2017 5:11:36 PM
	 */
	@Test (expectedExceptions = OperationNotSupportedError.class, expectedExceptionsMessageRegExp = "Test Error!")
	public void testOperationNotSupportedErrorWithTwoArg () {
		fail (OperationNotSupportedError.class, "Test Error!", new Throwable ());
	}
}