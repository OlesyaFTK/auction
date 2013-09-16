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
public class AdministratorTest {
    public static String name = "Олеся";
    public static String lastName = "Вильчинская";
    public static String login = "user";
    public static int password = "user".hashCode();
    public static String email = "novosilka@yandex.ru";
    public static int rate = 0;
    public Administrator administrator;
    
    @Before
    public void setUp(){
        administrator = new Administrator(login, password, name, lastName, email, rate);
    }
    
    @After
    public void tearDown(){
        administrator = null;
    }
    
    @Test
    public void getInformation(){
        assertEquals("Incorrect name", "Олеся", administrator.getName());
        assertEquals("Incorrect last name", "Вильчинская", administrator.getSecondName());
        assertEquals("Incorrect login", "user", administrator.getLogin());
        assertEquals("Incorrect password", "user".hashCode(), administrator.getPasswordHash());
        assertEquals("Incorrect mail", "novosilka@yandex.ru", administrator.getEmail());
        assertEquals("Incorrect rate", 0, administrator.getRate());
    }
    
    @Test
    public void create(){
        Administrator test = new Administrator(login, password, name, lastName, email, rate);
        assertNotNull(test);
        test = null;
    }
}
