/**
 * 
 */
package business;

/**
 *
 * @author Олеся
 */
public class User {
    protected final int id_;
    protected String login_;
    protected int password_;
    protected String name_;
    protected String secondName_;
    protected String email_;
    protected int rate_;

    public User(final int id,
                final String login,
                final int password,
                final String name,
                final String secondName,
                final String email,
                final int rate) {
        assert (login != null);
        assert (name != null);
        assert (secondName != null);

        id_ = id;
        login_ = login;
        password_ = password;
        name_ = name;
        secondName_ = secondName;
        email_ = email;
        rate_ = rate;
    }
    
    public User(final String login,
                final int password,
                final String name,
                final String secondName,
                final String email,
                final int rate) {
       this(0, login, password, name, secondName, email,rate);         
    }

    public int getId() {
        return id_;
    }

    public String getLogin() {
        return login_;
    }

    public int getPasswordHash() {
        return password_;
    }

    public String getName() {
        return name_;
    }

    public String getSecondName() {
        return secondName_;
    }

    public String getEmail() {
        return email_;
    }
    
    public void setPasswordHash(int password) {
        password_= password;
    }

    public void setName(String name) {
        name_=name;
    }

    public void setSecondName(String secondName) {
        secondName_=secondName;
    }

    public void setEmail(String email) {
        email_=email;
    }
    
    public int getRate(){
        return rate_;
    }
}
