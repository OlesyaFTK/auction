package service;

import business.Auction;
import business.Customer;
import business.Seller;
import business.Administrator;
import business.User;
import db.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Олеся
 */
public final class UserCatalogue {
    private static final Logger LOGGER = Logger.getLogger(Auction.class.getName());

    public static User getUser(final String login, final String password) throws EntryNotFoundException, IncorrectPasswordException {
        final ConnectionManager manager = new DerbyConnectionManager();
        try (Connection connection = manager.getConnection("db")) {
            final UserMapper mapper = new UserMapper(connection);
            final User result = mapper.find(login);
            
            System.out.println(password.hashCode());
            
            if (result == null) {
                throw new EntryNotFoundException("user not found");
            }
            if (password.hashCode() != result.getPasswordHash()) {
                throw new IncorrectPasswordException("incorrect password");
            }

            return result;
        } catch (SQLException | DataMapperException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            throw new IllegalStateException(e.getMessage());
        }
    }
    
    public static void createUser(final String login, 
                                  final String password,
                                  final String name,
                                  final String secondName,
                                  final String email,final int type) throws EntryRedefinitionException {
        final ConnectionManager manager = new DerbyConnectionManager();
        try (Connection connection = manager.getConnection("db")) {
            final UserMapper mapper = new UserMapper(connection);
            
            final User test = mapper.find(login);
            if (test != null) throw new EntryRedefinitionException("user already exists");
            final User user;
            if(type == 1){
                user = new Customer(-1, login, password.hashCode(), name, secondName, email,0);
            }else if(type == 0){
                user = new Administrator(-1, login, password.hashCode(), name, secondName, email,0);
            }else{
                user = new Seller(-1, login, password.hashCode(), name, secondName, email,0);
            }
            mapper.insert(user);
        } catch (SQLException | DataMapperException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            throw new IllegalStateException(e.getMessage());
        }
    }
    
    public static void addRate(int rate,User user){
         final ConnectionManager manager = new DerbyConnectionManager();
        try (Connection connection = manager.getConnection("db")) {
            final UserMapper mapper = new UserMapper(connection);
            mapper.AddRate(rate, user);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            throw new IllegalStateException(e.getMessage());
        }
    }
    
    public static void updateAccount(User user){
        final ConnectionManager manager = new DerbyConnectionManager();
        try (Connection connection = manager.getConnection("db")) {
            final UserMapper mapper = new UserMapper(connection);
            mapper.updateAccount(user);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            throw new IllegalStateException(e.getMessage());
        }
    }
    
    public static void deleteAccount(User user){
        final ConnectionManager manager = new DerbyConnectionManager();
        try (Connection connection = manager.getConnection("db")) {
            final UserMapper mapper = new UserMapper(connection);
            mapper.deleteAccount(user);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            throw new IllegalStateException(e.getMessage());
        }
    }

    private UserCatalogue() {}
}
