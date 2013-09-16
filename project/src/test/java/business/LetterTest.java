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
public class LetterTest {
    public static String from = "Administrator";
    public static String toName = "Олеся";
    public static String subject = "Ответ";
    public static String deliveryName = "Курьером";
    public static String paymentName = "Наличными";
    public static String description = "Удаление лота";
    public Letter letter;
    
    @Before
    public void setUp(){
        User to = new User("user2","user2".hashCode(),toName,"Вильчинская","novasilka@yandex.ru",0);
        Delivery delivery = new Delivery(deliveryName);
        Payment payment = new Payment(paymentName);
        letter = new Letter(from,to,subject,delivery,payment,description);
    }
    
    @After
    public void tearDown(){
        letter = null;
    }
    
    @Test
    public void getInformation(){
        assertEquals("Incorrect from", "Administrator", letter.getFrom());
        assertEquals("Incorrect subject", "Ответ", letter.getSubject());
        assertEquals("Incorrect description", "Удаление лота", letter.getDescription());
        assertNotNull(letter.getDelivery());
        assertNotNull(letter.getTo());
        assertNotNull(letter.getPayment());
    }
    
    @Test
    public void create(){
        User to = new User("user2","user2".hashCode(),toName,"Вильчинская","novasilka@yandex.ru",0);
        Delivery delivery = new Delivery(deliveryName);
        Payment payment = new Payment(paymentName);
        Letter test = new Letter(from,to,subject,delivery,payment,description);
        assertNotNull(test);
        test = null;
    }
}
