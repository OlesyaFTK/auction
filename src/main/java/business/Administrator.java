/**
 * 
 */
package business;

/**
 *
 * @author Олеся
 */
public class Administrator extends User {
    public Administrator(final int id,
                         final String login,
                         final int password,
                         final String name,
                         final String secondName,
                         final String email,
                         final int rate) {
        super(id, login, password, name, secondName, email,rate);
    }
    
    public Administrator(final String login,
                         final int password,
                         final String name,
                         final String secondName,
                         final String email,
                         final int rate) {
        super(login, password, name, secondName, email,rate);
    }
}
