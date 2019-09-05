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
package com.github.wasiqb.coteafs.error;

import static java.text.MessageFormat.format;

import com.github.wasiqb.coteafs.error.enums.Category;
import com.github.wasiqb.coteafs.error.enums.Reason;
import com.github.wasiqb.coteafs.error.enums.Severity;

/**
 * @author Wasiq Bhamla
 * @since Jul 8, 2017 8:44:50 PM
 */
public class CoteafsError extends AssertionError {
    private static final long serialVersionUID = -350027873597069923L;
    private final Category    errorCategory;
    private final Reason      errorReason;
    private final Severity    errorSeverity;

    /**
     * @author Wasiq Bhamla
     * @since Jul 8, 2017 8:47:31 PM
     * @param message
     */
    public CoteafsError (final String message) {
        this (message, null);
    }

    /**
     * @author Wasiq Bhamla
     * @since Jul 9, 2017 12:34:46 PM
     * @param message
     * @param reason
     * @param category
     * @param severity
     */
    public CoteafsError (final String message, final Reason reason, final Category category, final Severity severity) {
        this (message, null, reason, category, severity);
    }

    /**
     * @author Wasiq Bhamla
     * @since Jul 8, 2017 8:44:50 PM
     * @param message
     * @param cause
     */
    public CoteafsError (final String message, final Throwable cause) {
        this (message, cause, Reason.R2, Category.C1, Severity.CRITICAL);
    }

    /**
     * @author Wasiq Bhamla
     * @since Jul 9, 2017 12:31:32 PM
     * @param message
     * @param cause
     * @param reason
     * @param category
     * @param severity
     */
    public CoteafsError (final String message, final Throwable cause, final Reason reason, final Category category,
        final Severity severity) {
        super (message, cause);
        this.errorReason = reason;
        this.errorCategory = category;
        this.errorSeverity = severity;
    }

    /**
     * @author Wasiq Bhamla
     * @since Jul 9, 2017 12:32:14 PM
     * @return the category
     */
    public Category getCategory () {
        return this.errorCategory;
    }

    /**
     * @author Wasiq Bhamla
     * @since Jul 9, 2017 12:32:14 PM
     * @return the reason
     */
    public Reason getReason () {
        return this.errorReason;
    }

    /**
     * @author Wasiq Bhamla
     * @since Jul 9, 2017 12:32:14 PM
     * @return the severity
     */
    public Severity getSeverity () {
        return this.errorSeverity;
    }

    /*
     * (non-Javadoc)
     * @see @see java.lang.Throwable#toString()
     */
    @Override
    public String toString () {
        return format ("Error occurred: {0}\nReason: {1}\nCategory: {2}\nSeverity: {3}", getMessage (),
            this.errorReason.reason (), this.errorCategory.category (), this.errorSeverity.name ());
    }
}