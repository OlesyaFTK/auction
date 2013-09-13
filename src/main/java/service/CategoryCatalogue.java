/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import business.Category;
import db.CategoryMapper;
import db.ConnectionManager;
import db.DataMapperException;
import db.DerbyConnectionManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Олеся
 */
public class CategoryCatalogue {
    public static List<String> getCategoryNames() {
        final ConnectionManager manager = new DerbyConnectionManager();
        try (Connection connection = manager.getConnection("db")) {
            final CategoryMapper mapper = new CategoryMapper(connection);
            final List<Category> categorys = mapper.findAll();

            final List<String> result = new LinkedList<>();
            for (final Category cat : categorys) {
                result.add(cat.getName());
            }
            return result;
        } catch (SQLException | DataMapperException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
    
    public static Category getCategory(final String name) throws EntryNotFoundException {
        final ConnectionManager manager = new DerbyConnectionManager();
        try (Connection connection = manager.getConnection("db")) {
            final CategoryMapper mapper = new CategoryMapper(connection);
            final Category result = mapper.find(name);

            if (result == null) throw new EntryNotFoundException(("Publisher not found"));
            return result;
        } catch (SQLException | DataMapperException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
    
    public static void deleteCategory(final Category cat) {
        assert (cat != null);
        
        final ConnectionManager manager = new DerbyConnectionManager();
        try (Connection connection = manager.getConnection("db")) {
            final CategoryMapper mapper = new CategoryMapper(connection);
            mapper.delete(cat.getId());
        } catch (SQLException | DataMapperException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
    
    public static void createCategory(final String name) {
        assert (name != null);
        
        final ConnectionManager manager = new DerbyConnectionManager();
        try (Connection connection = manager.getConnection("db")) {
            final CategoryMapper mapper = new CategoryMapper(connection);
            mapper.insert(new Category(name));
        } catch (SQLException | DataMapperException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    private CategoryCatalogue() {}
}
