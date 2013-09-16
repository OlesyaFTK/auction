/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import business.Administrator;
import business.Seller;
import business.Customer;
import business.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.instanceOf;
/**
 *
 * @author Олеся
 */
public class UserCatalogueTest {
    public static int administratorId;
    public static int sellerId;
    public static int customerId;
    
    @Before
    public void setUp(){
        try{
        UserCatalogue.createUser("admin_test", "admin_test", "admin_test", "admin_test", "admin@admin.ru", 0);
        administratorId = UserCatalogue.getUser("admin_test", "admin_test").getId();
        
        UserCatalogue.createUser("customer_test", "customer_test", "customer_test", "customer_test", "customer@customer.ru", 1);
        customerId = UserCatalogue.getUser("customer_test", "customer_test").getId();
        
        UserCatalogue.createUser("seller_test", "seller_test", "seller_test", "seller_test", "seller@seller.ru", 2);
        sellerId = UserCatalogue.getUser("seller_test", "seller_test").getId();
        } catch(EntryRedefinitionException | EntryNotFoundException | IncorrectPasswordException e){
            Logger.getLogger(LetterCatalogTest.class.getName()).log(Level.SEVERE, null, e);
        } 
    }
    
    @After
    public void tearDown(){
        try{
        User user = UserCatalogue.getUser("admin_test", "admin_test");
        UserCatalogue.deleteAccount(user);
        user = UserCatalogue.getUser("customer_test", "customer_test");
        UserCatalogue.deleteAccount(user);
        user = UserCatalogue.getUser("seller_test", "seller_test");
        UserCatalogue.deleteAccount(user);
        }catch(EntryNotFoundException | IncorrectPasswordException e){
            Logger.getLogger(LetterCatalogTest.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    @Test
    public void getUser(){
        try{
            final User admin = UserCatalogue.getUser("admin_test", "admin_test");
            assertThat("Got incorrect type from the database", admin, instanceOf(Administrator.class));
            assertEquals("Incorrect name", "admin_test", admin.getLogin());

            final User custom = UserCatalogue.getUser("customer_test", "customer_test");
            assertThat("Got incorrect type from the database", custom, instanceOf(Customer.class));
            assertEquals("Incorrect name", "customer_test", custom.getLogin());
            
            final User seller = UserCatalogue.getUser("seller_test", "seller_test");
            assertThat("Got incorrect type from the database", seller, instanceOf(Seller.class));
            assertEquals("Incorrect name", "seller_test", seller.getLogin());
        } catch(EntryNotFoundException | IncorrectPasswordException e){
            Logger.getLogger(LetterCatalogTest.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
