/**
 * Copyright (C) 2015 Michael Schnell. All rights reserved. 
 * http://www.fuin.org/
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 3 of the License, or (at your option) any
 * later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library. If not, see http://www.gnu.org/licenses/.
 */
package org.fuin.units4j;

/**
 * A rule used in assertions.
 * 
 * @param <T>
 *            Type of object to validate.
 */
public interface AssertionRule<T> {

    /**
     * Verifies if the object observes the rule.
     * 
     * @param obj
     *            Object to test.
     * 
     * @return Result of the verification.
     */
    public AssertionResult verify(T obj);

}
