/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import business.Letter;
import business.Lot;
import business.User;
import db.ConnectionManager;
import db.DataMapperException;
import db.DerbyConnectionManager;
import db.LetterMapper;
import db.LotMapper;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Олеся
 */
public class LetterCatalogue {
    public static void createLetter(final Letter letter) throws EntryRedefinitionException {
        assert (letter != null);
        final ConnectionManager manager = new DerbyConnectionManager();
        try (Connection connection = manager.getConnection("db")) {
            final LetterMapper mapper = new LetterMapper(connection);
            mapper.insert(letter);
        } catch (SQLException | DataMapperException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
    
    public static void deleteLetter(final Letter letter){
        assert (letter != null);
        final ConnectionManager manager = new DerbyConnectionManager();
        try (Connection connection = manager.getConnection("db")) {
            final LetterMapper mapper = new LetterMapper(connection);
            mapper.delete(letter);
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
    
    public static List<Letter> getUserLetters(User user){
        final ConnectionManager manager = new DerbyConnectionManager();
        try (Connection connection = manager.getConnection("db")) {
            final LetterMapper mapper = new LetterMapper(connection);
            return mapper.getUserLetters(user);
        } catch (SQLException | DataMapperException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
}
