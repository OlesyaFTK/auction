/**
 * 
 */
package business;


/**
 *
 * @author Олеся
 */
public class Seller extends User {

    public Seller(final int id,
                    final String login,
                    final int password,
                    final String name,
                    final String secondName,
                    final String email,
                    final int rate) {
        super(id, login, password, name, secondName, email,rate);
    }
    
}
