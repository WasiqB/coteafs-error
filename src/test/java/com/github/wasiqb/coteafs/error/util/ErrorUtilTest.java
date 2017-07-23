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

import org.testng.annotations.Test;

import com.github.wasiqb.coteafs.error.CoteafsError;
import com.github.wasiqb.coteafs.error.OperationNotSupportedError;

/**
 * @author Wasiq Bhamla
 * @since Jul 23, 2017 2:51:13 PM
 */
public class ErrorUtilTest {
	/**
	 * @author Wasiq Bhamla
	 * @since Jul 23, 2017 2:55:32 PM
	 */
	@Test (expectedExceptions = OperationNotSupportedError.class, expectedExceptionsMessageRegExp = "Test Error!")
	public void testFailHappyPath () {
		fail (OperationNotSupportedError.class, "Test Error!");
	}

	/**
	 * @author Wasiq Bhamla
	 * @since Jul 23, 2017 2:58:39 PM
	 */
	@Test (expectedExceptions = CoteafsError.class)
	public void testFailWithInvalidParams () {
		fail (OperationNotSupportedError.class, 10);
	}
}