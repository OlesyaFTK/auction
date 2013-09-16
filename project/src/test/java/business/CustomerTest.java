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
public class CustomerTest {
    public static String name = "Олеся";
    public static String lastName = "Вильчинская";
    public static String login = "user";
    public static int password = "user".hashCode();
    public static String email = "novosilka@yandex.ru";
    public static int rate = 0;
    public Customer customer;
    
    @Before
    public void setUp(){
        customer = new Customer(login, password, name, lastName, email, rate);
    }
    
    @After
    public void tearDown(){
        customer = null;
    }
    
    @Test
    public void getInformation(){
        assertEquals("Incorrect name", "Олеся", customer.getName());
        assertEquals("Incorrect last name", "Вильчинская", customer.getSecondName());
        assertEquals("Incorrect login", "user", customer.getLogin());
        assertEquals("Incorrect password", "user".hashCode(), customer.getPasswordHash());
        assertEquals("Incorrect mail", "novosilka@yandex.ru", customer.getEmail());
        assertEquals("Incorrect rate", 0, customer.getRate());
    }
    
    @Test
    public void create(){
        Customer test = new Customer(login, password, name, lastName, email, rate);
        assertNotNull(test);
        test = null;
    }
}
