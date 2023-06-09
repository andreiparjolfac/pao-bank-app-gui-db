/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package bankwithguianddb;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Andrei
 */
public class WithdrawMenu extends javax.swing.JDialog {

    private Customer customer;
    private Bank bank;

    /**
     * Creates new form WithdrawMenu
     */
    public WithdrawMenu(java.awt.Frame parent, boolean modal,Bank bank,Customer customer) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);
        this.customer=customer;
        this.bank=bank;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        withdrawLabel = new javax.swing.JLabel();
        withdrawField = new javax.swing.JTextField();
        withdrawButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(2, 2));

        withdrawLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        withdrawLabel.setText("Withdrawal Amount:");
        getContentPane().add(withdrawLabel);

        withdrawField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                withdrawFieldActionPerformed(evt);
            }
        });
        getContentPane().add(withdrawField);

        withdrawButton.setText("Withdraw");
        withdrawButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                withdrawButtonActionPerformed(evt);
            }
        });
        getContentPane().add(withdrawButton);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        getContentPane().add(cancelButton);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void withdrawFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_withdrawFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_withdrawFieldActionPerformed

    private void withdrawButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_withdrawButtonActionPerformed
        StringBuilder sb = new StringBuilder();
        Double amount;
        if(withdrawField.getText().isEmpty()){
            sb.append("Withdraw Field must not be empty.\n");
        }else{
            try{
                amount = Bank.round(Double.parseDouble(withdrawField.getText()),2);
                if(amount<0){
                    sb.append("Withdraw amount must be a positve number.\n");
                }else{
                    int result = JOptionPane.showConfirmDialog(this,"Withdraw "+amount+" from the account?\nTotal(+taxes): $ "+Bank.round(amount+bank.getTransactionFee(customer.getAccount().getAccountType()),2));
                    if(result==JOptionPane.OK_OPTION){
                        bank.withdraw(customer.getAccount().getAccountNumber(), amount);
                        this.dispose();
                    }
                }
            }catch(NumberFormatException ex){
                sb.append("Withdraw amount must be a number.\n");
            }catch (InsufficientFundsException ex) {
                Logger.getLogger(WithdrawMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(!sb.isEmpty()){
            JOptionPane.showMessageDialog(this, sb, "Input Error!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_withdrawButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed




    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton withdrawButton;
    private javax.swing.JTextField withdrawField;
    private javax.swing.JLabel withdrawLabel;
    // End of variables declaration//GEN-END:variables
}
