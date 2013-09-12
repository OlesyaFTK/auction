package db;

import business.Auction;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.sound.midi.SysexMessage;

/**
 *
 * @author Олеся
 */
public class DerbyConnectionManager implements ConnectionManager {
    private static final Logger LOGGER = Logger.getLogger(Auction.class.getName());
    
    /**
     * Подключается к базе данных
     * @param url - имя базы данных
     * @return
     * @throws SQLException 
     */
    @Override
    public Connection getConnection(final String url) throws SQLException {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver"); //Драйвер
        } catch (ClassNotFoundException e) {
            LOGGER.severe("Can't load derby embedded driver");
            throw new IllegalStateException(e);
        }
        Connection result = DriverManager.getConnection("jdbc:derby://localhost:1527/" + url); 
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
