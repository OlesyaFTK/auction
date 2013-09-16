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
public class DeliveryTest {
    public static String name = "Курьером";
    public Delivery delivery;
    
    @Before
    public void setUp(){
        delivery = new Delivery(name);
    }
    
    @After
    public void tearDown(){
        delivery = null;
    }
    
    @Test
    public void getInformation(){
        assertEquals("Incorrect name", "Курьером", delivery.getName());
    }
    
    @Test
    public void create(){
        Delivery test = new Delivery(name);
        assertNotNull(test);
        test = null;
    }
}
