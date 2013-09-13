/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import business.Lot;
import business.User;
import db.ConnectionManager;
import db.DataMapperException;
import db.DerbyConnectionManager;
import db.LotMapper;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Олеся
 */
public class LotCatalogue {
    public static void createLot(final Lot lot) throws EntryRedefinitionException {
        assert (lot != null);
        final ConnectionManager manager = new DerbyConnectionManager();
        try (Connection connection = manager.getConnection("db")) {
            final LotMapper mapper = new LotMapper(connection);
            final Lot test = mapper.find(lot.getName());
            if (test != null){ 
                JOptionPane.showMessageDialog(null, "Lot already exist!");
            }else{
                mapper.insert(lot);
            }
        } catch (SQLException | DataMapperException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
    
    public static List<Lot> getConfirmLots(){
        final ConnectionManager manager = new DerbyConnectionManager();
        try (Connection connection = manager.getConnection("db")) {
            final LotMapper mapper = new LotMapper(connection);
            return mapper.getConfirmLots();
        } catch (SQLException | DataMapperException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
    
    public static List<Lot> getNoConfirmLots(){
        final ConnectionManager manager = new DerbyConnectionManager();
        try (Connection connection = manager.getConnection("db")) {
            final LotMapper mapper = new LotMapper(connection);
            return mapper.getNoConfirmLots();
        } catch (SQLException | DataMapperException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
    
    public static List<Lot> getSellerLots(User seller){
        final ConnectionManager manager = new DerbyConnectionManager();
        try (Connection connection = manager.getConnection("db")) {
            final LotMapper mapper = new LotMapper(connection);
            return mapper.getSellerLots(seller);
        } catch (SQLException | DataMapperException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
    
    public static List<Lot> getLotsByName(String name){
        final ConnectionManager manager = new DerbyConnectionManager();
        try (Connection connection = manager.getConnection("db")) {
            final LotMapper mapper = new LotMapper(connection);
            return mapper.getLotsByName(name);
        } catch (SQLException | DataMapperException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
    
    public static void confirmLot(Lot lot){
       final ConnectionManager manager = new DerbyConnectionManager();
        try (Connection connection = manager.getConnection("db")) {
            final LotMapper mapper = new LotMapper(connection);
            mapper.confirmLot(lot.getName());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Cannot confirm lot");
        } 
    }
    
    public static void deleteLot(Lot lot){
       final ConnectionManager manager = new DerbyConnectionManager();
        try (Connection connection = manager.getConnection("db")) {
            final LotMapper mapper = new LotMapper(connection);
            mapper.deleteLot(lot.getName());
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot confirm lot");
        } 
    }
    
    public static void createLotBid(Lot lot,User user,double bid){
       final ConnectionManager manager = new DerbyConnectionManager();
        try (Connection connection = manager.getConnection("db")) {
            final LotMapper mapper = new LotMapper(connection);
            mapper.createLotBid(lot,user,bid);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot add bid!");
        } 
    }
}
