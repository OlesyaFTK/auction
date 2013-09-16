/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import business.Payment;
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
public class PaymentCatalogueTest {
    private static String name = "Наличными";

    @BeforeClass
    public static void setUpDatabase() {
        PaymentCatalogue.createPayment(name);
    }

    @AfterClass
    public static void clearDatabase() {
        Payment pay;
        try {
            pay = PaymentCatalogue.getPayment(name);
            PaymentCatalogue.deletePayment(pay);
        } catch (EntryNotFoundException ex) {
            Logger.getLogger(PaymentCatalogueTest.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }

    @Test
    public void selectTest() throws Exception {
        final Payment pay = PaymentCatalogue.getPayment(name);
        assertEquals("Incorrect name", "Наличными", pay.getName());
    }

    @Test
    public void selectName(){
        final List<String> list = PaymentCatalogue.getPaymentNames();
        for(String name:list){
            assertEquals("Incorrect name", "Наличными", name);
        }
    }
    
    @Test
    public void deleteTest() throws Exception {
        Payment test;
        try {
            PaymentCatalogue.createPayment("Тест");
            test = PaymentCatalogue.getPayment("Тест");
            PaymentCatalogue.deletePayment(test);
        } catch (EntryNotFoundException ex) {
            Logger.getLogger(PaymentCatalogueTest.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
}
