/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import business.Payment;
import db.ConnectionManager;
import db.DataMapperException;
import db.DerbyConnectionManager;
import db.PaymentMapper;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Олеся
 */
public class PaymentCatalogue {
    public static List<String> getPaymentNames() {
        final ConnectionManager manager = new DerbyConnectionManager();
        try (Connection connection = manager.getConnection("db")) {
            final PaymentMapper mapper = new PaymentMapper(connection);
            final List<Payment> payments = mapper.findAll();

            final List<String> result = new LinkedList<>();
            for (final Payment pay : payments) {
                result.add(pay.getName());
            }
            return result;
        } catch (SQLException | DataMapperException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
    
    public static Payment getPayment(final String name) throws EntryNotFoundException {
        final ConnectionManager manager = new DerbyConnectionManager();
        try (Connection connection = manager.getConnection("db")) {
            final PaymentMapper mapper = new PaymentMapper(connection);
            final Payment result = mapper.find(name);

            if (result == null) throw new EntryNotFoundException(("Payment not found"));
            return result;
        } catch (SQLException | DataMapperException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
    
    public static void deletePayment(final Payment pay) {
        assert (pay != null);
        
        final ConnectionManager manager = new DerbyConnectionManager();
        try (Connection connection = manager.getConnection("db")) {
            final PaymentMapper mapper = new PaymentMapper(connection);
            mapper.delete(pay.getId());
        } catch (SQLException | DataMapperException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
    
    public static void createPayment(final String name) {
        assert (name != null);
        
        final ConnectionManager manager = new DerbyConnectionManager();
        try (Connection connection = manager.getConnection("db")) {
            final PaymentMapper mapper = new PaymentMapper(connection);
            mapper.insert(new Payment(name));
        } catch (SQLException | DataMapperException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    private PaymentCatalogue() {}
}
