/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author Олеся
 */
public class SelectObject {
    protected int id_;
    protected String name_;
    
    public SelectObject(int id, String name){
        id_ = id;
        name_ = name;
    }
    
    public SelectObject(String name){
        this(0,name);
    }
    
    public int getId(){
        return id_;
    }
    
    public String getName(){
        return name_;
    }
}
