/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import business.Delivery;
import db.ConnectionManager;
import db.DataMapperException;
import db.DeliveryMapper;
import db.DerbyConnectionManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Олеся
 */
public class DeliveryCatalogue {
    public static List<String> getDeliveryNames() {
        final ConnectionManager manager = new DerbyConnectionManager();
        try (Connection connection = manager.getConnection("db")) {
            final DeliveryMapper mapper = new DeliveryMapper(connection);
            final List<Delivery> deliverys = mapper.findAll();

            final List<String> result = new LinkedList<>();
            for (final Delivery del : deliverys) {
                result.add(del.getName());
            }
            return result;
        } catch (SQLException | DataMapperException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
    
    public static Delivery getDelivery(final String name) throws EntryNotFoundException {
        final ConnectionManager manager = new DerbyConnectionManager();
        try (Connection connection = manager.getConnection("db")) {
            final DeliveryMapper mapper = new DeliveryMapper(connection);
            final Delivery result = mapper.find(name);

            if (result == null) throw new EntryNotFoundException(("Publisher not found"));
            return result;
        } catch (SQLException | DataMapperException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
    
    public static void deleteDelivery(final Delivery del) {
        assert (del != null);
        
        final ConnectionManager manager = new DerbyConnectionManager();
        try (Connection connection = manager.getConnection("db")) {
            final DeliveryMapper mapper = new DeliveryMapper(connection);
            mapper.delete(del.getId());
        } catch (SQLException | DataMapperException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
    
    public static void createDelivery(final String name) {
        assert (name != null);
        
        final ConnectionManager manager = new DerbyConnectionManager();
        try (Connection connection = manager.getConnection("db")) {
            final DeliveryMapper mapper = new DeliveryMapper(connection);
            mapper.insert(new Delivery(name));
        } catch (SQLException | DataMapperException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    private DeliveryCatalogue() {}
}
