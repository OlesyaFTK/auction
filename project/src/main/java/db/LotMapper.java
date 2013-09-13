/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import business.Category;
import business.Lot;
import business.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Олеся
 */
public class LotMapper extends Mapper<Lot> {

    public LotMapper(final Connection connection) {
        super(connection);
    }
    
    public Lot find(String name) throws DataMapperException {
        assert(name != null);

        PreparedStatement statement = null;
        try {
            final String query = "SELECT * from Lots where Name=?";
            statement = connection_.prepareStatement(query);
            statement.setString(1, name);

            final ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return createLot(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new DataMapperException("Error occurred while searching for book: " + e.getMessage());
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {}
        }
    }
    
    @Override
    public Lot find(int id) throws DataMapperException {

        PreparedStatement statement = null;
        try {
            final String query = "SELECT * from Lots where Id=?";
            statement = connection_.prepareStatement(query);
            statement.setInt(1, id);

            final ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return createLot(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new DataMapperException("Error occurred while searching for book: " + e.getMessage());
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {}
        }
    }

    @Override
    public int insert(Lot lot) throws DataMapperException {
        assert (lot != null);

        PreparedStatement statement = null;
        try {
            final String query = "INSERT into Lots(Name, Description, Price, StartDate, FinishDate, CategoryId, SellerId, LastCustomerId,Status)" +
                                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            statement = connection_.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, lot.getName());
            statement.setString(2, lot.getDescription());
            statement.setDouble(3, lot.getPrice());
            statement.setDate(4, new java.sql.Date(lot.getStartDate().getTime()));
            statement.setTimestamp(5, new java.sql.Timestamp(lot.getFinishDate().getTimeInMillis()));
            statement.setInt(6, lot.getCategory().getId());
            statement.setInt(7, lot.getSeller().getId());
            User user = lot.getCustomer();
            if(user != null){
                statement.setInt(8, lot.getCustomer().getId());
            }else{
                statement.setNull(8, Types.INTEGER);
            }
            statement.setBoolean(9, lot.getStatus());
            statement.executeUpdate();

            final int id = getId(statement);
            return id;
        } catch (SQLException e) {
            throw new DataMapperException("Error occurred while inserting a book: " + e.getMessage());
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {}
        }    
    }

    @Override
    public void delete(int id) throws DataMapperException {
        try{
        PreparedStatement statement = null; 
         final String query = "DELETE FROM Lots WHERE Id=?";
         statement = connection_.prepareStatement(query);
         statement.setInt(1, id);
         statement.executeUpdate();
         statement.close();
        }catch(SQLException e){
            throw new DataMapperException("Error while delete lots: "+e.getMessage());
        }
    }
    
    private Lot createLot(final ResultSet rs) throws DataMapperException, SQLException {
        final int id = rs.getInt("Id");
        final String name = rs.getString("Name");
        final String description = rs.getString("Description");
        final double price = rs.getDouble("Price");
        final Date startDate = rs.getDate("StartDate");
        final Calendar finishDate = Calendar.getInstance();
        Timestamp ts = rs.getTimestamp("FinishDate");
        finishDate.setTimeInMillis(ts.getTime());
        final boolean status = rs.getBoolean("Status");

        final Mapper<Category> catMapper = new CategoryMapper(connection_);
        final Category category = catMapper.find(rs.getInt("CategoryId"));
        if (category == null) {
            throw new DataMapperException("Category not found");
        }
        
        final Mapper<User> userMapper = new UserMapper(connection_);
        final User seller = userMapper.find(rs.getInt("SellerId"));
        if (seller == null) {
            throw new DataMapperException("Seller not found");
        }
        
        Integer customerInt = rs.getInt("LastCustomerId");
        User customer = null;
        if(customerInt != null && customerInt!=0){
            customer = userMapper.find(customerInt);
            if (customer == null) {
            throw new DataMapperException("Seller not found");
        }
        }

        return new Lot(id,name,description,price,seller,category,customer,startDate,finishDate,status);
    }
    
    public List<Lot> getConfirmLots() throws DataMapperException{
        List list = new LinkedList();
         PreparedStatement statement = null;
        try {
            final String query = "SELECT * from Lots where Status=true";
            statement = connection_.prepareStatement(query);

            final ResultSet rs = statement.executeQuery();
            while(rs.next()){
                list.add(createLot(rs));
            }
        } catch (SQLException e) {
            throw new DataMapperException("Error occurred while searching for lot: " + e.getMessage());
        } finally {
            try {
                if (statement != null) statement.close();
                return list;
            } catch (SQLException e) {
                return list;
            }
        }
    }
    
     public List<Lot> getNoConfirmLots() throws DataMapperException{
        List list = new LinkedList();
         PreparedStatement statement = null;
        try {
            final String query = "SELECT * from Lots where Status=false";
            statement = connection_.prepareStatement(query);

            final ResultSet rs = statement.executeQuery();
            while(rs.next()){
                list.add(createLot(rs));
            }
        } catch (SQLException e) {
            throw new DataMapperException("Error occurred while searching for book: " + e.getMessage());
        } finally {
            try {
                if (statement != null) statement.close();
                return list;
            } catch (SQLException e) {
                return list;
            }
        }
    }
     
     public List<Lot> getSellerLots(User seller) throws DataMapperException{
        List list = new LinkedList();
         PreparedStatement statement = null;
        try {
            final String query = "SELECT * from Lots where SellerId=?";
            statement = connection_.prepareStatement(query);
            statement.setInt(1, seller.getId());

            final ResultSet rs = statement.executeQuery();
            while(rs.next()){
                list.add(createLot(rs));
            }
        } catch (SQLException e) {
            throw new DataMapperException("Error occurred while searching for book: " + e.getMessage());
        } finally {
            try {
                if (statement != null) statement.close();
                return list;
            } catch (SQLException e) {
                return list;
            }
        }
    }
     
     public List<Lot> getLotsByName(String name) throws DataMapperException{
        List list = new LinkedList();
         PreparedStatement statement = null;
        try {
            final String query = "SELECT * from Lots where Name=? and Status=true";
            statement = connection_.prepareStatement(query);
            statement.setString(1, name);

            final ResultSet rs = statement.executeQuery();
            while(rs.next()){
                list.add(createLot(rs));
            }
        } catch (SQLException e) {
            throw new DataMapperException("Error occurred while searching for book: " + e.getMessage());
        } finally {
            try {
                if (statement != null) statement.close();
                return list;
            } catch (SQLException e) {
                return list;
            }
        }
    }
     
    public void confirmLot(String name) throws SQLException{
         PreparedStatement statement = null; 
         final String query = "UPDATE Lots SET Status=true WHERE Name=?";
         statement = connection_.prepareStatement(query);
         statement.setString(1, name);
         statement.execute();
    }
    
    public void deleteLot(String name) throws SQLException{
         PreparedStatement statement = null; 
         final String query = "DELETE FROM Lots WHERE Name=?";
         statement = connection_.prepareStatement(query);
         statement.setString(1, name);
         statement.executeUpdate();
         statement.close();
    }
    
    public void createLotBid(Lot lot,User user,double bid) throws SQLException{
        PreparedStatement statement = null; 
         final String query = "UPDATE Lots SET LastCustomerId=?, Price=?  WHERE Name=?";
         statement = connection_.prepareStatement(query);
         statement.setInt(1, user.getId());
         statement.setDouble(2, bid);
         statement.setString(3, lot.getName());
         statement.executeUpdate();
    }
}
