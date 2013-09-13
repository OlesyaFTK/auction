package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 *
 * @author Олеся
 */
public class TestConnectionManager implements ConnectionManager {
    private static final Logger LOGGER = Logger.getLogger(ConnectionManager.class.getName());

    @Override
    public Connection getConnection(final String url) throws SQLException {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException e) {
            LOGGER.severe("Can't load derby embedded driver");
        }
        final Connection result = DriverManager.getConnection("jdbc:derby://localhost:1527/testdb");
        result.setAutoCommit(true);
        return result;
    }

    @Override
    public void closeConnection(final Connection conn) throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }
}
