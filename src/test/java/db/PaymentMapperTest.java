/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import business.Payment;
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
public class PaymentMapperTest {
    private static int id;

    @BeforeClass
    public static void setUpDatabase() throws Exception {
        final TestConnectionManager manager = new TestConnectionManager();
        Statement statement = null;
        try (Connection connection = manager.getConnection("testdb")) {
            final String query = "INSERT into Payments(Name) VALUES ('By cash')";
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
            final String query = "DELETE from Payments";
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
            final Mapper<Payment> mapper = new PaymentMapper(connection);

            final Payment payment = mapper.find(id);
            assertEquals("Incorrect name", "By cash", payment.getName());
        }
    }

    @Test
    public void deleteTest() throws Exception {
        final TestConnectionManager manager = new TestConnectionManager();
        try (Connection connection = manager.getConnection("testdb")) {
            final Mapper<Payment> mapper = new PaymentMapper(connection);

            final Payment pay = new Payment(-1, "spam");
            final int id = mapper.insert(pay);
            mapper.delete(id + 1);
            assertNotNull("Payment was deleted", mapper.find(id));
            mapper.delete(id);
            assertNull("Payment was not deleted", mapper.find(id));
        }
    }
}
