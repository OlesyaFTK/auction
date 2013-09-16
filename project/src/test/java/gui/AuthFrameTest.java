/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import business.User;
import business.Category;
import business.Lot;
import java.util.Calendar;
import gui.AuthFrame;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author Олеся
 */
public class AuthFrameTest {
    private static User user;
    
    @Before
    public void setUp(){
        user = new User("admin","admin".hashCode(),"admin","admin","admin",0);
    }
    
    @After
    public void tearDown(){
        user = null;
    }
    
    @Test
    public void test(){
        AuthFrame frame = new AuthFrame();
        assertNotNull(frame);
    }
}
