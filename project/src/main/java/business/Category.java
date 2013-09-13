/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author Олеся
 */
public class Category extends SelectObject{
    public Category(int id, String name){
        super(id,name);
    }
    
    public Category(String name){
        super(name);
    }
}
