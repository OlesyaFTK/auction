package service;

/**
 *
 * @author Олеся
 */
public class IncorrectPasswordException extends Exception {
    public IncorrectPasswordException(final String message) { super(message); }
}
