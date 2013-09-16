/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import business.Delivery;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Олеся
 */
public class DeliveryCatalogueTest {
    private static String name = "Курьером";

    @BeforeClass
    public static void setUpDatabase() {
        DeliveryCatalogue.createDelivery(name);
    }

    @AfterClass
    public static void clearDatabase() {
        Delivery del;
        try {
            del = DeliveryCatalogue.getDelivery(name);
            DeliveryCatalogue.deleteDelivery(del);
        } catch (EntryNotFoundException ex) {
            Logger.getLogger(DeliveryCatalogueTest.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }

    @Test
    public void selectTest() throws Exception {
        final Delivery del = DeliveryCatalogue.getDelivery(name);
        assertEquals("Incorrect name", "Курьером", del.getName());
    }

    @Test
    public void selectName(){
        final List<String> list = DeliveryCatalogue.getDeliveryNames();
        for(String name:list){
            assertEquals("Incorrect name", "Курьером", name);
        }
    }
    
    @Test
    public void deleteTest() throws Exception {
        Delivery test;
        try {
            DeliveryCatalogue.createDelivery("Тест");
            test = DeliveryCatalogue.getDelivery("Тест");
            DeliveryCatalogue.deleteDelivery(test);
        } catch (EntryNotFoundException ex) {
            Logger.getLogger(DeliveryCatalogueTest.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
}
