/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import business.Category;
import business.Lot;
import business.User;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import static org.hamcrest.CoreMatchers.instanceOf;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Олеся
 */
public class LotMapperTest {
    private static int userId;
    private static int categoryId;

    @BeforeClass
    public static void setUpDatabase() throws Exception {
        final TestConnectionManager manager = new TestConnectionManager();
        Statement statement = null;
        try (Connection connection = manager.getConnection("testdb")) {
            String query = "INSERT into Category(Name) VALUES ('Toys')";
            statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

            categoryId = Mapper.getId(statement);
            
            query = "INSERT into Users(Type, Login, Password, Name, SecondName, Email)" +
                    "VALUES (2, 'seller', 0, 'seller', 'seller', 'seller@seller.ru')";
            statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            userId = Mapper.getId(statement);

            query = "INSERT into Lots(Name, Description, Price, StartDate, FinishDate, CategoryId, SellerId, LastCustomerId,Status)" +
                    "VALUES ('Car', 'Sport car', 2500, '2013-05-05', '2013-05-05 05:05:05.111111111', "+categoryId+","+userId+", null, false) ";
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
            String query = "DELETE from Lots";
            statement = connection.createStatement();
            statement.executeUpdate(query);
            query = "DELETE from Users";
            statement = connection.createStatement();
            statement.executeUpdate(query);
            query = "DELETE from Category";
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
            final LotMapper mapper = new LotMapper(connection);
            final Lot lot = mapper.find("Car");
            assertEquals("Wrong name", "Car", lot.getName());
            assertEquals("Wrong description", "Sport car", lot.getDescription());
            assertNotNull("Start date not null", lot.getStartDate());
            assertNotNull("Finish date not null", lot.getFinishDate());
            assertNotNull("Category not null", lot.getCategory());
            assertNotNull("Seller not null", lot.getSeller());
            assertNull("Last customer null", lot.getCustomer());
            assertEquals("Status true", false, lot.getStatus());
        }
    }

    @Test
    public void insertTest() throws Exception {
        final TestConnectionManager manager = new TestConnectionManager();
        try (Connection connection = manager.getConnection("testdb")) {
            final Mapper<Category> catMapper = new CategoryMapper(connection);
            final Category category = catMapper.find(categoryId);
            assertNotNull("Category not found", category);
            assertThat("Retrieved wrong user type", category, instanceOf(Category.class));
            
            final Mapper<User> userMapper = new UserMapper(connection);
            final User user = userMapper.find(userId);
            assertNotNull("User not found", user);
            assertThat("Retrieved wrong user type", user, instanceOf(User.class));

            final Lot lot = new Lot("Ken","Barby boy",200,user,category,null,Calendar.getInstance().getTime(),Calendar.getInstance(),false);
            final LotMapper lotMapper = new LotMapper(connection);
            final int id = lotMapper.insert(lot);
            final Lot check = lotMapper.find("Ken");
            assertNotNull("Inserted lot not found", check);
            assertEquals("Wrong name of inserted lot", "Ken", lot.getName());
        }
    }

    @Test
    public void updateTest() throws Exception {
        final TestConnectionManager manager = new TestConnectionManager();
        try (Connection connection = manager.getConnection("testdb")) {
            final LotMapper lotMapper = new LotMapper(connection);
            final Lot lot = lotMapper.find("Car");
            assertNotNull("Lot not found", lot);
            assertThat("Retrieved wrong lot type", lot, instanceOf(Lot.class));

            lotMapper.confirmLot(lot.getName());
            
            final Lot newLot = lotMapper.find(lot.getName());
            assertEquals("Lot not confirmed", true, newLot.getStatus());
        }
    }

    @Test
    public void deleteTest() throws Exception {
        final TestConnectionManager manager = new TestConnectionManager();
        try (Connection connection = manager.getConnection("testdb")) {
            final LotMapper lotMapper = new LotMapper(connection);
            final Lot lot = lotMapper.find("Car");
            assertNotNull("Lot not found", lot);
            assertThat("Retrieved wrong user type", lot, instanceOf(Lot.class));
            
            lotMapper.deleteLot(lot.getName());
            assertNull("Lot was deleted", lotMapper.find(lot.getName()));
        }
    }
}
