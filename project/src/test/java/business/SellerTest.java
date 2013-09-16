/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Олеся
 */
public class SellerTest {
    public static String name = "Олеся";
    public static String lastName = "Вильчинская";
    public static String login = "user";
    public static int password = "user".hashCode();
    public static String email = "novosilka@yandex.ru";
    public static int rate = 0;
    public Seller seller;
    
    @Before
    public void setUp(){
        seller = new Seller(login, password, name, lastName, email, rate);
    }
    
    @After
    public void tearDown(){
        seller = null;
    }
    
    @Test
    public void getInformation(){
        assertEquals("Incorrect name", "Олеся", seller.getName());
        assertEquals("Incorrect last name", "Вильчинская", seller.getSecondName());
        assertEquals("Incorrect login", "user", seller.getLogin());
        assertEquals("Incorrect password", "user".hashCode(), seller.getPasswordHash());
        assertEquals("Incorrect mail", "novosilka@yandex.ru", seller.getEmail());
        assertEquals("Incorrect rate", 0, seller.getRate());
    }
    
    @Test
    public void create(){
        Seller test = new Seller(login, password, name, lastName, email, rate);
        assertNotNull(test);
        test = null;
    }
}
