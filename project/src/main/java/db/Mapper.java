package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Олеся
 */

public abstract class Mapper<T> {

    public static int getId(final Statement statement) throws SQLException, DataMapperException {
        try (ResultSet keys = statement.getGeneratedKeys()) {
            assert (keys != null): "Forgot to add Statement.RETURN_GENERATED_KEYS";
            if (keys.next()) {
                return keys.getInt(1);
            }
            throw new DataMapperException("Error occurred while retrieving primary key");
        }
    }
    protected Connection connection_;

    public Mapper(final Connection connection) {
        assert (connection != null);
        connection_ = connection;
    }

    public abstract T find(final int id) throws DataMapperException;

    public abstract int insert(final T param) throws DataMapperException;
    public abstract void delete(final int id) throws DataMapperException;
}
