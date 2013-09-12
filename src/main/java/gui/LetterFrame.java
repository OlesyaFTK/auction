/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import business.Delivery;
import business.Letter;
import db.LetterType;
import business.Lot;
import business.Payment;
import business.Seller;
import business.User;
import db.ConnectionManager;
import db.DataMapperException;
import db.DeliveryMapper;
import db.DerbyConnectionManager;
import db.PaymentMapper;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.DeliveryCatalogue;
import service.EntryRedefinitionException;
import service.LetterCatalogue;
import service.LotCatalogue;
import service.PaymentCatalogue;

/**
 *
 * @author Олеся
 */
public class LetterFrame extends javax.swing.JFrame {

    /**
     * Creates new form LetterFrame
     */
    public LetterFrame(Lot lot,LetterType type) {
        this.lot = lot;
        this.type = type;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblFrom = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblSubject = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        cbPayment = new javax.swing.JComboBox();
        cbDelivery = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        taDescription = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("From: ");

        if(this.type.equals(LetterType.ADMINMESSAGE)){
            lblFrom.setText("Administration");
        }else{
            lblFrom.setText(this.lot.getSeller().getSecondName()+" "+this.lot.getSeller().getName());
        }

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("To:");

        if(this.type.equals(LetterType.ADMINMESSAGE)){
            jLabel4.setText(this.lot.getSeller().getSecondName()+" "+this.lot.getSeller().getName());
        }else{
            jLabel4.setText(this.lot.getCustomer().getSecondName()+" "+this.lot.getCustomer().getName());
        }

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Subject: ");

        if(this.type.equals(LetterType.ADMINMESSAGE)){
            lblSubject.setText("Delete your lot: "+this.lot.getName());
        }else{
            lblSubject.setText("Confirm lot: "+this.lot.getName());
        }

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Description:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Payment:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Delivery: ");

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setText("Send");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        if(this.type.equals(LetterType.ADMINMESSAGE)){
            cbPayment.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Disabled"} ));
        }else{
            cbPayment.setModel(new javax.swing.DefaultComboBoxModel(PaymentCatalogue.getPaymentNames().toArray()));
        }

        if(this.type.equals(LetterType.ADMINMESSAGE)){
            cbDelivery.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Disabled"}));
        }else{
            cbDelivery.setModel(new javax.swing.DefaultComboBoxModel(DeliveryCatalogue.getDeliveryNames().toArray()));
        }

        taDescription.setColumns(20);
        taDescription.setRows(5);
        jScrollPane1.setViewportView(taDescription);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(lblFrom))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(33, 33, 33)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblSubject))
                            .addComponent(jLabel7)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbDelivery, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 141, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblFrom))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblSubject))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cbPayment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cbDelivery, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Dimension dialogSize = getSize();
        setLocation((screenSize.width-dialogSize.width)/2,(screenSize.height-dialogSize.height)/2);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        dispose();
        if(type.equals(LetterType.ADMINMESSAGE)){
            new MainAdminFrame().setVisible(true);
        }else{
            new MainSellerFrame((Seller)lot.getSeller()).setVisible(true);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        final ConnectionManager manager = new DerbyConnectionManager();
        try(Connection connection = manager.getConnection("db")){
        String from = lblFrom.getText();
        String description = taDescription.getText();
        String subject = lblSubject.getText();
        
        String paymentName = cbPayment.getSelectedItem().toString();
        Payment payment;
        if(paymentName.equals("Disabled")){
            payment = null;
        }else{
            PaymentMapper paymentMapper = new PaymentMapper(connection);
            payment = paymentMapper.find(paymentName);
        }
        
        String deliveryName = cbDelivery.getSelectedItem().toString();
        Delivery delivery;
        if(deliveryName.equals("Disabled")){
            delivery = null;
        }else{
            DeliveryMapper deliveryMapper = new DeliveryMapper(connection);
            delivery = deliveryMapper.find(deliveryName);
        }
        Letter letter;
        if(this.type.equals(LetterType.ADMINMESSAGE)){
            letter = new Letter(from,lot.getSeller(),subject,delivery,payment,description);
        }else{
            letter = new Letter(from,lot.getCustomer(),subject,delivery,payment,description);
        }
            try {
                LetterCatalogue.createLetter(letter);
            } catch (EntryRedefinitionException ex) {
                Logger.getLogger(NewLotFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }catch(SQLException | DataMapperException e){
            
        }
        dispose();
        LotCatalogue.deleteLot(lot);
        if(type.equals(LetterType.ADMINMESSAGE)){
            new MainAdminFrame().setVisible(true);
        }else{
            new MainSellerFrame((Seller)lot.getSeller()).setVisible(true);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbDelivery;
    private javax.swing.JComboBox cbPayment;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFrom;
    private javax.swing.JLabel lblSubject;
    private javax.swing.JTextArea taDescription;
    // End of variables declaration//GEN-END:variables

    private Lot lot;
    private LetterType type;
}
