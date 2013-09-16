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
public class PaymentTest {
    public static String name = "Наличными";
    public Payment payment;
    
    @Before
    public void setUp(){
        payment = new Payment(name);
    }
    
    @After
    public void tearDown(){
        payment = null;
    }
    
    @Test
    public void getInformation(){
        assertEquals("Incorrect name", "Наличными", payment.getName());
    }
    
    @Test
    public void create(){
        Payment test = new Payment(name);
        assertNotNull(test);
        test = null;
    }
}
