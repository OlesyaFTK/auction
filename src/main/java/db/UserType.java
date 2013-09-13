/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import util.EnumConverter;

/**
 *
 * @author Олеся
 */
public enum UserType implements EnumConverter{
    ADMIN(0),CUSTOMER(1), SELLER(2);
    
    private final int id_;
    UserType(final int id) { id_ = id; }

    @Override
    public int convert() { return id_; }
    
    
}
