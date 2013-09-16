/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import business.Category;
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
public class CategoryCatalogueTest {
    private static String name = "Компьютеры";

    @BeforeClass
    public static void setUpDatabase() {
        CategoryCatalogue.createCategory(name);
    }

    @AfterClass
    public static void clearDatabase() {
        Category cat;
        try {
            cat = CategoryCatalogue.getCategory(name);
            CategoryCatalogue.deleteCategory(cat);
        } catch (EntryNotFoundException ex) {
            Logger.getLogger(CategoryCatalogueTest.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }

    @Test
    public void selectTest() throws Exception {
        final Category cat = CategoryCatalogue.getCategory(name);
        assertEquals("Incorrect name", "Компьютеры", cat.getName());
    }

    @Test
    public void selectName(){
        final List<String> list = CategoryCatalogue.getCategoryNames();
        for(String name:list){
            assertEquals("Incorrect name", "Компьютеры", name);
        }
    }
    
    @Test
    public void deleteTest() throws Exception {
        Category test;
        try {
            CategoryCatalogue.createCategory("Тест");
            test = CategoryCatalogue.getCategory("Тест");
            CategoryCatalogue.deleteCategory(test);
        } catch (EntryNotFoundException ex) {
            Logger.getLogger(CategoryCatalogueTest.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
}
