/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author Олеся
 */
@SuppressWarnings("serial")
public class LotParseException extends Exception {

    /**
     * Creates a new instance of
     * <code>BookParseException</code> without detail message.
     */
    public LotParseException() {
    }

    /**
     * Constructs an instance of
     * <code>BookParseException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public LotParseException(String msg) {
        super(msg);
    }
}
