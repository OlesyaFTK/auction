/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import business.Delivery;
import business.Letter;
import business.Payment;
import business.User;
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
public class LetterMapper extends Mapper<Letter>{
    
    public LetterMapper(final Connection connection) {
        super(connection);
    }
    /**
     * Добавляет письмо в бд
     * @param letter
     * @return
     * @throws DataMapperException 
     */
    @Override
    public int insert(Letter letter) throws DataMapperException {
        assert (letter != null);

        PreparedStatement statement = null;
        try {
            final String query = "INSERT into Letters(FROMNAME,TOID,SUBJECT,DELIVERYID,PAYMENTID,DESCRIPTION)" +
                                 "VALUES (?, ?, ?, ?, ?, ?)";
            statement = connection_.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, letter.getFrom());
            statement.setInt(2, letter.getTo().getId());
            statement.setString(3, letter.getSubject());
            if(letter.getDelivery()!=null){
                statement.setInt(4, letter.getDelivery().getId());
            }else{
                statement.setNull(4, java.sql.Types.INTEGER);
            }
            if(letter.getPayment()!=null){
            statement.setInt(5, letter.getPayment().getId());
            }else{
                statement.setNull(5, java.sql.Types.INTEGER);
            }
            statement.setString(6, letter.getDescription());
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
    
    public void delete(Letter letter) throws SQLException {
        PreparedStatement statement = null; 
         final String query = "DELETE FROM Letters WHERE id=?";
         statement = connection_.prepareStatement(query);
         statement.setInt(1, letter.getId());
         statement.executeUpdate();
         statement.close();
    }
    
    private Letter createLetter(final ResultSet rs,final User user) throws DataMapperException, SQLException {
        final int id = rs.getInt("Id");
        final String fromName = rs.getString("FromName");
        final String description = rs.getString("Description");
        final String subject = rs.getString("Subject");

        final Mapper<Delivery> delMapper = new DeliveryMapper(connection_);
        Delivery delivery = delMapper.find(rs.getInt("DeliveryId"));
        if (delivery == null) {
            delivery = new Delivery("Disabled");
        }
        
        final Mapper<Payment> paymentMapper = new PaymentMapper(connection_);
        Payment payment = paymentMapper.find(rs.getInt("PaymentId"));
        if (payment == null) {
            payment = new Payment("Disabled");
        }

        return new Letter(id,fromName,user,subject,delivery,payment,description);
    }
    //Получить весь список писем для определенного пользователя
    public List<Letter> getUserLetters(User user) throws DataMapperException{
        List list = new LinkedList();
         PreparedStatement statement = null;
        try {
            final String query = "SELECT * from Letters where ToId=?";
            statement = connection_.prepareStatement(query);
            statement.setInt(1, user.getId());

            final ResultSet rs = statement.executeQuery();
            while(rs.next()){
                list.add(createLetter(rs,user));
            }
        } catch (SQLException e) {
            throw new DataMapperException("Error occurred while searching for letter: " + e.getMessage());
        } finally {
            try {
                if (statement != null) statement.close();
                return list;
            } catch (SQLException e) {
                return list;
            }
        }
    }

    @Override
    public Letter find(int id) throws DataMapperException {
        PreparedStatement statement = null;
        try {
            final String query = "SELECT * from Letters where Id=?";
            statement = connection_.prepareStatement(query);
            statement.setInt(1, id);

            final ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return createLetter(rs,null);
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
    public void delete(int id) throws DataMapperException {
        try{
        PreparedStatement statement = null; 
         final String query = "DELETE FROM Letters WHERE id=?";
         statement = connection_.prepareStatement(query);
         statement.setInt(1, id);
         statement.executeUpdate();
        }catch(SQLException e){
            throw new DataMapperException("Error occurred while delete for letter: " + e.getMessage());
        }
    }
    
}
