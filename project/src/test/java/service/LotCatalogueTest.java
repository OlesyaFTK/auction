/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import business.Category;
import business.Lot;
import business.Seller;
import business.User;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Олеся
 */
public class LotCatalogueTest {
    public static String name = "Ваза_test";
    public static String description = "Китайская ваза";
    public static double price = 1000.00;
    public static String sellerName = "Юрий";
    public static String categoryName = "Антиквариат_test";
    public static User lastCustomer = null;
    public static Date startDate = new Date(Calendar.getInstance().getTimeInMillis());
    public static Calendar finishDateTime = Calendar.getInstance();
    public static boolean status = false;
    public Lot lot;
    Seller seller;
    Category category;
    
    @Before
    public void setUp() throws Exception{
        CategoryCatalogue.createCategory(categoryName);
        category = CategoryCatalogue.getCategory(categoryName);
        UserCatalogue.createUser("user_test", "user_test", "user_test", "user_test", "user_test@mail.ru", 2);
        seller = (Seller) UserCatalogue.getUser("user_test", "user_test");
        lot = new Lot(name,description,price,seller,category,lastCustomer,startDate,finishDateTime,status);
        LotCatalogue.createLot(lot);
    }
    
    @After
    public void tearDown(){
        LotCatalogue.deleteLot(lot);
        UserCatalogue.deleteAccount((User)seller);
        CategoryCatalogue.deleteCategory(category);
        lot = null;
    }
    
    @Test
    public void getNoConfirm(){
        List<Lot> lots = LotCatalogue.getNoConfirmLots();
        for(Lot lot:lots){
            assertEquals("Incorrect name", "Ваза_test", lot.getName());
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
    }
    
    @Test
    public void getConfirm(){
        LotCatalogue.confirmLot(lot);
        List<Lot> lots = LotCatalogue.getConfirmLots();
        for(Lot lot:lots){
            assertEquals("Incorrect name", "Ваза_test", lot.getName());
            assertEquals("Incorrect description", "Китайская ваза", lot.getDescription());
            assertNotNull(lot.getPrice());
            assertNotNull(lot.getSeller());
            assertNotNull(lot.getCategory());
            assertNull(lot.getCustomer());
            assertNotNull(lot.getStartDate());
            assertNotNull(lot.getStartDate());
            assertNotNull(lot.getFinishDate());
            assertEquals("Incorrect status", true, lot.getStatus());
        }
    }
    
    @Test
    public void createDelete(){
        
        Lot test = new Lot("test_test",description,price,seller,category,lastCustomer,startDate,finishDateTime,status);
        try {
            LotCatalogue.createLot(test);
            List<Lot> testList = LotCatalogue.getLotsByName("test_test");
            for(Lot lot:testList){
            assertEquals("Incorrect name", "Ваза_test", lot.getName());
            assertEquals("Incorrect description", "Китайская ваза", lot.getDescription());
            assertNotNull(lot.getPrice());
            assertNotNull(lot.getSeller());
            assertNotNull(lot.getCategory());
            assertNull(lot.getCustomer());
            assertNotNull(lot.getStartDate());
            assertNotNull(lot.getStartDate());
            assertNotNull(lot.getFinishDate());
            assertEquals("Incorrect status", true, lot.getStatus());
        }
            LotCatalogue.deleteLot(test);
            List<Lot> testList2 = LotCatalogue.getLotsByName("test_test");
            assertEquals(0, testList2.size());
        } catch (EntryRedefinitionException ex) {
            Logger.getLogger(LotCatalogueTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
