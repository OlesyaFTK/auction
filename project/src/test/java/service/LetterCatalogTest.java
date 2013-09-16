/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import business.Letter;
import business.User;
import business.Payment;
import business.Delivery;
import business.Seller;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Олеся
 */
public class LetterCatalogTest {
    public static String from = "Administrator_test";
    public static String subject = "Ответ_test";
    public static String description = "Описание_test";
    public static Delivery delivery;
    public static Payment payment;
    public static User user;
    public Letter letter;
    
    @Before
    public void setUp(){
        
        try {
            DeliveryCatalogue.createDelivery("test_доставка");
            delivery = DeliveryCatalogue.getDelivery("test_доставка");
            PaymentCatalogue.createPayment("test_оплата");
            payment = PaymentCatalogue.getPayment("test_оплата");
            UserCatalogue.createUser("user_test", "user_test", "user_test", "user_test", "user_test@mail.ru", 2);
            user = UserCatalogue.getUser("user_test", "user_test");
            letter = new Letter(from,user,subject,delivery,payment,description);
            LetterCatalogue.createLetter(letter);
            letter = LetterCatalogue.getUserLetters(user).get(0);
        } catch (EntryNotFoundException | EntryRedefinitionException | IncorrectPasswordException ex) {
            Logger.getLogger(LetterCatalogTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @After
    public void tearDown(){
        LetterCatalogue.deleteLetter(letter);
        PaymentCatalogue.deletePayment(payment);
        DeliveryCatalogue.deleteDelivery(delivery);
        UserCatalogue.deleteAccount(user);
    }
    
    @Test
    public void getUserLetter(){
        List<Letter> letterList = LetterCatalogue.getUserLetters(user);
        for(Letter letter:letterList){
            assertEquals("Incorrect from","Administrator_test", letter.getFrom());
            assertEquals("Incorrect subject","Ответ_test", letter.getSubject());
            assertEquals("Incorrect description","Описание_test", letter.getDescription());
            assertNotNull(letter.getDelivery());
            assertNotNull(letter.getPayment());
            assertNotNull(letter.getTo());
        }
    }
    
}
