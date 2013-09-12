package db;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Олеся
 */
public interface ConnectionManager {
    Connection getConnection(final String url) throws SQLException;
    void closeConnection(Connection conn) throws SQLException;
}
