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
import com.github.wasiqb.coteafs.error.OperationNotSupportedError;

/**
 * @author Wasiq Bhamla
 * @since Jul 9, 2017 6:34:49 PM
 */
public class ErrorUtil {
	/**
	 * @author Wasiq Bhamla
	 * @since Jul 9, 2017 6:44:34 PM
	 * @param cls
	 * @param args
	 */
	@SuppressWarnings ("unchecked")
	public static <T extends CoteafsError, E> void fail (Class <T> cls, E... args) {
		final Class <?> [] list = new Class [args.length];
		for (int i = 0; i < args.length; i++)
			list [i] = args [i].getClass ();
		try {
			final Constructor <T> ctor = cls.getDeclaredConstructor (list);
			throw ctor.newInstance (args);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			throw new CoteafsError ("Error occurred while throwing custom error.", e);
		}
	}

	private ErrorUtil () {
		fail (OperationNotSupportedError.class);
	}
}