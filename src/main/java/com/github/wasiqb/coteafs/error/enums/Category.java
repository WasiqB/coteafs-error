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
package com.github.wasiqb.coteafs.error.enums;

/**
 * @author Wasiq Bhamla
 * @since Jul 8, 2017 8:55:18 PM
 */
public enum Category {
    /**
     * Internal error.
     */
    C1("Internal"),
    /**
     * Validation error.
     */
    C2("Validation"),
    /**
     * Reteival error.
     */
    C3("Retreival"),
    /**
     * UI error.
     */
    C4("UI"),
    /**
     * Message error.
     */
    C5("Message");

    private final String name;

    Category(final String name) {
        this.name = name;
    }

    /**
     * @return category
     * @author Wasiq Bhamla
     * @since Jul 8, 2017 8:57:50 PM
     */
    public String category() {
        return this.name;
    }
}