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
public class CategoryTest {
    public static String name = "Компьютеры";
    public Category category;
    
    @Before
    public void setUp(){
        category = new Category(name);
    }
    
    @After
    public void tearDown(){
        category = null;
    }
    
    @Test
    public void getInformation(){
        assertEquals("Incorrect name", "Компьютеры", category.getName());
    }
    
    @Test
    public void create(){
        Category test = new Category(name);
        assertNotNull(test);
        test = null;
    }
}
