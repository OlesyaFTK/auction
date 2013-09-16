/**
 * 
 */
package business;


/**
 *
 * @author Олеся
 */
public class Customer extends User {

    public Customer(final int id,
                    final String login,
                    final int password,
                    final String name,
                    final String secondName,
                    final String email,
                    final int rate) {
        super(id, login, password, name, secondName, email,rate);
    }

    public Customer(String login, int password, String name, String lastName, String email, int rate) {
        super(login, password, name, lastName, email,rate);
    }
   
}
