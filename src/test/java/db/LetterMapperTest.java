/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import business.Category;
import business.Delivery;
import business.Letter;
import business.Lot;
import business.Payment;
import business.User;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.List;
import static org.hamcrest.CoreMatchers.instanceOf;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Олеся
 */
public class LetterMapperTest {
    private static int userId;
    private static int deliveryId;
    private static int paymentId;

    @BeforeClass
    public static void setUpDatabase() throws Exception {
    final TestConnectionManager manager = new TestConnectionManager();
        Statement statement = null;
        try (Connection connection = manager.getConnection("testdb")) {
            String query = "INSERT into Deliveries(Name) VALUES ('By car')";
            statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

            deliveryId = Mapper.getId(statement);
            
            query = "INSERT into Payments(Name) VALUES ('By cash')";
            statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

            paymentId = Mapper.getId(statement);
            
            query = "INSERT into Users(Type, Login, Password, Name, SecondName, Email)" +
                    "VALUES (2, 'seller', 0, 'seller', 'seller', 'seller@seller.ru')";
            statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            userId = Mapper.getId(statement);

            query = "INSERT into Letters(FROMNAME,TOID,SUBJECT,DELIVERYID,PAYMENTID,DESCRIPTION)" +
                    "VALUES ('Administration', "+userId+", 'Reply', "+deliveryId+", "+paymentId+", 'Incorrect data format') ";
            statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
        } finally {
            if (statement != null) statement.close();
        }
    }
    
    @AfterClass
    public static void clearDatabase() throws SQLException {
        final TestConnectionManager manager = new TestConnectionManager();
        Statement statement = null;
        try (Connection connection = manager.getConnection("testdb")) {
            String query = "DELETE from Letters";
            statement = connection.createStatement();
            statement.executeUpdate(query);
            query = "DELETE from Users";
            statement = connection.createStatement();
            statement.executeUpdate(query);
            query = "DELETE from Deliveries";
            statement = connection.createStatement();
            statement.executeUpdate(query);
            query = "DELETE from Payments";
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
            final UserMapper userMapper = new UserMapper(connection);
            final User user = userMapper.find(userId);
            assertNotNull("User not found", user);
            assertThat("Retrieved wrong user type", user, instanceOf(User.class));
            
            
            final LetterMapper mapper = new LetterMapper(connection);
            final List<Letter> letters = mapper.getUserLetters(user);
            for(Letter letter:letters){
                assertEquals("Wrong from", "Administration", letter.getFrom());
                assertEquals("Wrong description", "Incorrect data format", letter.getDescription());
                assertEquals("Wrong subject", "Reply", letter.getSubject());
                assertNotNull("To not null", letter.getTo());
                assertNotNull("Delivery not null", letter.getDelivery());
                assertNotNull("Payment not null", letter.getPayment());                         
            }
        }
    }

    @Test
    public void insertTest() throws Exception {
        final TestConnectionManager manager = new TestConnectionManager();
        try (Connection connection = manager.getConnection("testdb")) {
            final Mapper<Delivery> delMapper = new DeliveryMapper(connection);
            final Delivery delivery = delMapper.find(deliveryId);
            assertNotNull("Delivery not found", delivery);
            assertThat("Retrieved wrong delivery type", delivery, instanceOf(Delivery.class));
            
            final Mapper<Payment> catMapper = new PaymentMapper(connection);
            final Payment payment = catMapper.find(paymentId);
            assertNotNull("Payment not found", payment);
            assertThat("Retrieved wrong payment type", payment, instanceOf(Payment.class));
            
            final Mapper<User> userMapper = new UserMapper(connection);
            final User user = userMapper.find(userId);
            assertNotNull("User not found", user);
            assertThat("Retrieved wrong user type", user, instanceOf(User.class));

            final Letter letter = new Letter("Administration",user,"Reply2",delivery,payment,"test");
            final LetterMapper letterMapper = new LetterMapper(connection);
            final int id = letterMapper.insert(letter);
            assertNotNull("Inserted letter not found", id);
        }
    }
}
