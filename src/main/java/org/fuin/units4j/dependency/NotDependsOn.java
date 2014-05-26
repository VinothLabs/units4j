/**
 * Copyright (C) 2013 Future Invent Informationsmanagement GmbH. All rights
 * reserved. <http://www.fuin.org/>
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
 * along with this library. If not, see <http://www.gnu.org/licenses/>.
 */
package org.fuin.units4j.dependency;

/**
 * Forbidden dependency.
 */
public final class NotDependsOn extends Dependency {

    private final String comment;

    /**
     * Constructor with name. Sub package dependencies are included.
     * 
     * @param packageName
     *            Full qualified name of the package - Cannot be
     *            <code>null</code>.
     */
    public NotDependsOn(final String packageName) {
        super(packageName);
        this.comment = null;
    }

    /**
     * Constructor with package name and comment. Sub package dependencies are
     * included.
     * 
     * @param packageName
     *            Full qualified name of the package - Cannot be
     *            <code>null</code>.
     * @param comment
     *            Comment why the dependency is OK or not - May be null.
     */
    public NotDependsOn(final String packageName, final String comment) {
        super(packageName, true);
        this.comment = comment;
    }

    /**
     * Constructor with all possible arguments.
     * 
     * @param packageName
     *            Full qualified name of the package - Cannot be
     *            <code>null</code>.
     * @param includeSubPackages
     *            If sub package dependencies are included <code>true</code>
     *            else <code>false</code>.
     * @param comment
     *            Comment why the dependency is OK or not - May be null.
     */
    public NotDependsOn(final String packageName,
            final boolean includeSubPackages, final String comment) {
        super(packageName, includeSubPackages);
        this.comment = comment;
    }

    /**
     * Returns the comment.
     * 
     * @return Comment or <code>null</code>.
     */
    public final String getComment() {
        return comment;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String toString() {
        return getPackageName();
    }

    /**
     * {@inheritDoc}
     */
    public final String toDebugString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("packageName=" + getPackageName() + ", ");
        sb.append("includeSubPackages=" + isIncludeSubPackages() + ", ");
        sb.append("comment=" + comment);
        return sb.toString();
    }

}
