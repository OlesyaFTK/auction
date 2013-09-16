/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import business.Letter;
import business.User;
import business.Category;
import business.Delivery;
import business.Lot;
import business.Payment;
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
public class LetterViewFrameTest {
    private static User user;
    private static Letter letter;
    
    @Before
    public void setUp(){
        user = new User("admin","admin".hashCode(),"admin","admin","admin",0);
        letter = new Letter("test", user, "test", new Delivery("test"), new Payment("test"), "test");
    }
    
    @After
    public void tearDown(){
        user = null;
        letter = null;
    }
    
    @Test
    public void test(){
        LetterViewFrame frame = new LetterViewFrame(letter);
        assertNotNull(frame);
    }
}
