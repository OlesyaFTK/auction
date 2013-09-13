/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Олеся
 */
public class Lot {

    private int id_;
    private String name_;
    private String description_;
    private double price_;
    private User seller_;
    private Category category_;
    private User lastCustomer_;
    private Date startDate_;
    private Calendar finishDate_;
    private boolean status_;

    public Lot(int id,
            String name,
            String description,
            double price,
            User seller,
            Category category,
            User lastCustomer,
            Date startDate,
            Calendar finishDate, 
            boolean status) {
        id_ = id;
        name_ = name;
        description_ = description;
        price_ = price;
        seller_ = seller;
        category_ = category;
        lastCustomer_ = lastCustomer;
        startDate_ = startDate;
        finishDate_ = finishDate;
        status_ = status;
    }
    
    public Lot(String name,
            String description,
            double price,
            User seller,
            Category category,
            User lastCustomer,
            Date startDate,
            Calendar finishDate, 
            boolean status) {
        id_ = 0;
        name_ = name;
        description_ = description;
        price_ = price;
        seller_ = seller;
        category_ = category;
        lastCustomer_ = lastCustomer;
        startDate_ = startDate;
        finishDate_ = finishDate;
        status_ = status;
    }
    
    public int getId(){
        return id_;
    }
    public String getName(){
        return name_;
    }
    
    public String getDescription(){
        return description_;
    }
    
    public double getPrice(){
        return price_;
    }
    
    public User getSeller(){
        return seller_;
    }
    
    public Category getCategory(){
        return category_;
    }
    
    public User getCustomer(){
        return lastCustomer_;
    }
    
    public Date getStartDate(){
        return startDate_;
    }
    
    public Calendar getFinishDate(){
        return finishDate_;
    }
    
    public boolean getStatus(){
        return status_;
    }
}
