/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import business.Administrator;
import business.Customer;
import business.Seller;
import business.User;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import static org.hamcrest.CoreMatchers.instanceOf;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Олеся
 */
public class UserMapperTest {
    private static int customerId;
    private static int sellerId;
    private static int administratorId;

    @BeforeClass
    public static void setUpDatabase() throws Exception {
        final TestConnectionManager manager = new TestConnectionManager();
        Statement statement = null;
        try (Connection connection = manager.getConnection("testdb")) {
            String query = "INSERT into Users(Type, Login, Password, Name, SecondName, Email)" +
                           "VALUES (0, 'admin', 0, 'admin', 'admin', 'admin@admin.com')";
            statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            administratorId = Mapper.getId(statement);

            query = "INSERT into Users(Type, Login, Password, Name, SecondName, Email)" +
                    "VALUES (1, 'customer', 0, 'customer', 'customer', 'customer@customer.ru')";
            statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            customerId = Mapper.getId(statement);
            
            query = "INSERT into Users(Type, Login, Password, Name, SecondName, Email)" +
                    "VALUES (2, 'seller', 0, 'seller', 'seller', 'seller@seller.ru')";
            statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            sellerId = Mapper.getId(statement);
        } finally {
            if (statement != null) statement.close();
        }
    }

    @AfterClass
    public static void clearDatabase() throws SQLException {
        final TestConnectionManager manager = new TestConnectionManager();
        Statement statement = null;
        try (Connection connection = manager.getConnection("testdb")) {
            final String query = "DELETE from Users";
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
            final UserMapper mapper = new UserMapper(connection);

            final User admin = mapper.find(administratorId);
            assertThat("Got incorrect type from the database", admin, instanceOf(Administrator.class));
            assertEquals("Incorrect name", "admin", admin.getLogin());

            final User custom = mapper.find(customerId);
            assertThat("Got incorrect type from the database", custom, instanceOf(Customer.class));
            assertEquals("Incorrect name", "customer", custom.getLogin());
            
            final User seller = mapper.find(sellerId);
            assertThat("Got incorrect type from the database", seller, instanceOf(Seller.class));
            assertEquals("Incorrect name", "seller", seller.getLogin());
        }
    }

    @Test
    public void updateTest() throws Exception {
        final TestConnectionManager manager = new TestConnectionManager();
        try (Connection connection = manager.getConnection("testdb")) {
            final UserMapper mapper = new UserMapper(connection);

            final User admin = new Administrator(-1, "spam", 0, "", "", "",0);
            final int id = mapper.insert(admin);
            final User tmp = new Administrator(id, "ham", 0, "", "", "",0);
            mapper.update(tmp);
            final User test = mapper.find(id);
            assertThat("Got incorrect type from the database", test, instanceOf(Administrator.class));
            assertEquals("Incorrect name", "ham", test.getLogin());
        }
    }

    @Test
    public void deleteTest() throws Exception {
        final TestConnectionManager manager = new TestConnectionManager();
        try (Connection connection = manager.getConnection("testdb")) {
            final UserMapper mapper = new UserMapper(connection);

            final User admin = new Administrator(-1, "spam", 0, "", "", "",0);
            final int id = mapper.insert(admin);
            mapper.delete(id + 1);
            assertNotNull("User was deleted", mapper.find(id));
            mapper.delete(id);
            assertNull("User was not deleted", mapper.find(id));
        }
    }
}
