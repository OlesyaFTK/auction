/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import business.Delivery;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Олеся
 */
public class DeliveryMapperTest {
    private static int id;

    @BeforeClass
    public static void setUpDatabase() throws Exception {
        final TestConnectionManager manager = new TestConnectionManager();
        Statement statement = null;
        try (Connection connection = manager.getConnection("testdb")) {
            final String query = "INSERT into Deliveries(Name) VALUES ('By courier')";
            statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            id = Mapper.getId(statement);
        } finally {
            if (statement != null) statement.close();
        }
    }

    @AfterClass
    public static void clearDatabase() throws SQLException {
        final TestConnectionManager manager = new TestConnectionManager();
        Statement statement = null;
        try (Connection connection = manager.getConnection("testdb")) {
            final String query = "DELETE from Deliveries";
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } finally {
            if (statement != null) statement.close();
        }
    }

    @Test
    public void selectTest() throws Exception {
        final TestConnectionManager manager = new TestConnectionManager();
        try (Connection connection = manager.getConnection("testdb")) {
            final Mapper<Delivery> mapper = new DeliveryMapper(connection);

            final Delivery delivery = mapper.find(id);
            assertEquals("Incorrect name", "By courier", delivery.getName());
        }
    }

    @Test
    public void deleteTest() throws Exception {
        final TestConnectionManager manager = new TestConnectionManager();
        try (Connection connection = manager.getConnection("testdb")) {
            final Mapper<Delivery> mapper = new DeliveryMapper(connection);

            final Delivery del = new Delivery(-1, "spam");
            final int id = mapper.insert(del);
            mapper.delete(id + 1);
            assertNotNull("Delivery was deleted", mapper.find(id));
            mapper.delete(id);
            assertNull("Delivery was not deleted", mapper.find(id));
        }
    }
}
