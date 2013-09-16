/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.Calendar;
import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Олеся
 */
public class LotTest {
    public static String name = "Ваза";
    public static String description = "Китайская ваза";
    public static double price = 1000.00;
    public static String sellerName = "Юрий";
    public static String categoryName = "Антиквариат";
    public static User lastCustomer = null;
    public static Date startDate = new Date(Calendar.getInstance().getTimeInMillis());
    public static Calendar finishDateTime = Calendar.getInstance();
    public static boolean status = false;
    public Lot lot;
    
    @Before
    public void setUp(){
        Category category = new Category(categoryName);
        Seller seller = new Seller("user2","user2".hashCode(),sellerName,"Малков","malkov@gmail.com",0);
        lot = new Lot(name,description,price,seller,category,lastCustomer,startDate,finishDateTime,status);
    }
    
    @After
    public void tearDown(){
        lot = null;
    }
    
    @Test
    public void getInformation(){
        assertEquals("Incorrect name", "Ваза", lot.getName());
        assertEquals("Incorrect description", "Китайская ваза", lot.getDescription());
        assertNotNull(lot.getPrice());
        assertNotNull(lot.getSeller());
        assertNotNull(lot.getCategory());
        assertNull(lot.getCustomer());
        assertNotNull(lot.getStartDate());
        assertNotNull(lot.getStartDate());
        assertNotNull(lot.getFinishDate());
        assertEquals("Incorrect status", false, lot.getStatus());
    }
    
    @Test
    public void create(){
        Category category = new Category(categoryName);
        Seller seller = new Seller("user2","user2".hashCode(),sellerName,"Малков","malkov@gmail.com",0);
        Lot test = new Lot(name,description,price,seller,category,lastCustomer,startDate,finishDateTime,status);
        assertNotNull(test);
        test = null;
    }
}
