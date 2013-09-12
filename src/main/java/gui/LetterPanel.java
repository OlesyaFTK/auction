/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import business.Letter;
import service.LetterCatalogue;

/**
 *
 * @author Олеся
 */
public class LetterPanel extends javax.swing.JPanel {

    /**
     * Creates new form LetterPanel
     */
    public LetterPanel(Letter letter) {
        this.letter = letter;
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
        jLabel2 = new javax.swing.JLabel();
        btnOpen = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText(letter.getFrom());
        jLabel1.setMaximumSize(new java.awt.Dimension(120, 14));
        jLabel1.setMinimumSize(new java.awt.Dimension(120, 14));

        jLabel2.setText(letter.getSubject());
        jLabel2.setMaximumSize(new java.awt.Dimension(120, 14));
        jLabel2.setMinimumSize(new java.awt.Dimension(120, 14));

        btnOpen.setText("Open");
        btnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnOpen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOpen)
                    .addComponent(btnDelete))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        LetterCatalogue.deleteLetter(letter);
        btnOpen.setEnabled(false);
        btnDelete.setEnabled(false);
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenActionPerformed
        // TODO add your handling code here:
        new LetterViewFrame(letter).setVisible(true);
    }//GEN-LAST:event_btnOpenActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnOpen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
    private Letter letter;
}