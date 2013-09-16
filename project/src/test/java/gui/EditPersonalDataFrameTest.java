/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.EditPersonalDataFrame;
import business.User;
import business.Category;
import business.Lot;
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
public class EditPersonalDataFrameTest {
    private static User user;
    private static Lot lot;
    
    @Before
    public void setUp(){
        user = new User("admin","admin".hashCode(),"admin","admin","admin",0);
        lot = new Lot("test","test",12.00,user,new Category("test"),null,new Date(),Calendar.getInstance(),false);
    }
    
    @After
    public void tearDown(){
        user = null;
        lot = null;
    }
    
    @Test
    public void test(){
        EditPersonalDataFrame frame = new EditPersonalDataFrame(user);
        assertNotNull(frame);
    }
}
