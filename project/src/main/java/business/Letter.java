/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author Олеся
 */
public class Letter {

    public Letter(int id, String from, User to, String subject, Delivery delivery, Payment payment, String description) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.delivery = delivery;
        this.payment = payment;
        this.description = description;
    }
    public Letter(String from, User to, String subject, Delivery delivery, Payment payment, String description) {
        this.id = 0;
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.delivery = delivery;
        this.payment = payment;
        this.description = description;
    }
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
    private String from;
    private User to;
    private String subject;
    private Delivery delivery;
    private Payment payment;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
