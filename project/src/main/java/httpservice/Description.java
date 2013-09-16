/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package httpservice;

/**
 *
 * @author Олеся
 */
public class Description {
    private String url;
    private String text;
    
    public Description(String url,String text){
        this.url = url;
        this.text = text;
    }
    
    public String getUrl(){
        return this.url;
    }
    
    public String getText(){
        return this.text;
    }
}
