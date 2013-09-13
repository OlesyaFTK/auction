/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author Олеся
 */
public class Payment extends SelectObject{
    public Payment(int id,String name){
        super(id,name);
    }
    
    public Payment(String name){
        super(name);
    }
}
