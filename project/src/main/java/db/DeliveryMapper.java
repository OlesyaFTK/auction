/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import business.Delivery;
import static db.Mapper.getId;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Олеся
 */
public class DeliveryMapper extends Mapper<Delivery> {
    
    public DeliveryMapper(final Connection connection) {
        super(connection);
    }
    
    public List<Delivery> findAll() throws DataMapperException {
        try (final Statement statement = connection_.createStatement()) {
            final String query = "SELECT * from Deliveries";
            final ResultSet rs = statement.executeQuery(query);
            final List<Delivery> result = new LinkedList<>();
            while (rs.next()) {
                final int id = rs.getInt("Id");
                final String name = rs.getString("Name");
                result.add(new Delivery(id, name));
            }
            return result;
        } catch (SQLException e) {
            throw new DataMapperException("Error occurred while searching for publisher", e);
        }
    }
    
    public Delivery find(final String name) throws DataMapperException {
        assert(name != null);

        PreparedStatement statement = null;
        try {
            final String query = "SELECT * from Deliveries where Name=?";
            statement = connection_.prepareStatement(query);
            statement.setString(1, name);
            
            final ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("Id");
                return new Delivery(id, name);
            }
            return null;
        } catch (SQLException e) {
            throw new DataMapperException("Error occurred while searching for user: " + e.getMessage());
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {}
        }
    }
    
    @Override
    public Delivery find(final int id) throws DataMapperException {
        PreparedStatement statement = null;
        try {
            final String query = "SELECT * from Deliveries where Id=?";
            statement = connection_.prepareStatement(query);
            statement.setInt(1, id);
            final ResultSet result = statement.executeQuery();
            if (result.next()) {
                final String name = result.getString("Name");
                return new Delivery(id, name);
            }
            return null;
        } catch (SQLException e) {
            throw new DataMapperException("Error occurred while searching for publisher", e);
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {}
        }
    }
    
    @Override
    public void delete(final int id) throws DataMapperException {
        PreparedStatement statement = null;
        try {
            final String query = "DELETE from Deliveries where Id=?";
            statement = connection_.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataMapperException("Error occurred while deleting a publisher", e);
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {}
        }
    }
    
    @Override
    public int insert(final Delivery pub) throws DataMapperException {
        assert (pub != null);

        PreparedStatement statement = null;
        try {
            final String query = "INSERT into Deliveries(Name) " +
                                 "VALUES (?)";
            statement = connection_.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, pub.getName());
            statement.executeUpdate();
            return getId(statement);
        } catch (SQLException e) {
            throw new DataMapperException("Error occurred while inserting a publisher", e);
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {}
        }
    }
}
