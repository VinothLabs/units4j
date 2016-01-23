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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 * Base class for lightweight unit tests with entity manager. The tests tries to
 * find a configuration properties file in the resource path:<br>
 * /org/fuin/units4j/AbstractPersistenceTest.properties<br>
 * You can use this file to configure the following properties:<br>
 * driver=org.hsqldb.jdbcDriver<br>
 * url=jdbc:hsqldb:mem:unit-testing-jpa<br>
 * user=sa<br>
 * pw=<br>
 * pu_name=testPU<br>
 * If the file is not found, the above default values are used.
 */
// CHECKSTYLE:OFF:JavaDoc
public abstract class AbstractPersistenceTest {

    private static EntityManagerFactory emf;

    private static EntityManager em;

    private static Connection connection;

    @BeforeClass
    public static void beforeClass() throws Exception {
        try {
            emf = Persistence.createEntityManagerFactory("testPU");
            em = emf.createEntityManager();
            final Map<String, Object> props = emf.getProperties();
            final boolean shutdown = Boolean.valueOf(""
                    + props.get("units4j.shutdown"));
            if (shutdown) {
                final String connUrl = "" + props.get("units4j.url");
                final String connUsername = "" + props.get("units4j.user");
                final String connPassword = "" + props.get("units4j.pw");
                connection = DriverManager.getConnection(connUrl, connUsername,
                        connPassword);
            }

        } catch (final SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @AfterClass
    public static void afterClass() {
        if (em != null) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
        try {
            if (connection != null) {
                connection.createStatement().execute("SHUTDOWN");
            }
        } catch (final SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Returns the entity manager.
     * 
     * @return Test entity manager.
     */
    protected static EntityManager getEm() {
        if (em == null) {
            throw new IllegalStateException(
                    "Entity manager not available - Something went wrong...");
        }
        return em;
    }

    /**
     * Starts a transaction.
     */
    protected static void beginTransaction() {
        getEm().getTransaction().begin();
    }

    /**
     * Commits the current transaction.
     */
    protected static void commitTransaction() {
        getEm().getTransaction().commit();
    }

    /**
     * Rolls back the current transaction (if active.
     */
    protected static void rollbackTransaction() {
        if (getEm().getTransaction().isActive()) {
            getEm().getTransaction().rollback();
        }
    }

}
// CHECKSTYLE:ON:JavaDoc
