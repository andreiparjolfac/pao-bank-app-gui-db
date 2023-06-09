/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package bankwithguianddb;

/**
 *
 * @author Andrei
 */
public class AccountDetailsPage extends javax.swing.JDialog {

    /**
     * Creates new form AccountDetailsPage
     */
    public AccountDetailsPage(java.awt.Frame parent, boolean modal,Bank bank,Customer customer) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);
        
        setTitle("Account Details for :"+customer.getFirstName()+" "+customer.getLastName());
        
        firstNameField.setText(customer.getFirstName());
        lastNameField.setText(customer.getLastName());
        cnpField.setText(customer.getCnp());
        accountTypeField.setText(customer.getAccount().getAccountType().name());
        accountNumberField.setText(""+customer.getAccount().getAccountNumber());
        balanceField.setText(String.format("%.2f",customer.getAccount().getBalance()));
        interestRateField.setText(""+bank.checkInterest(customer.getAccount().getBalance())*100+"%");
        transactionField.setText(""+bank.getTransactionFee(customer.getAccount().getAccountType()));
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        firstNameLabel = new javax.swing.JLabel();
        firstNameField = new javax.swing.JLabel();
        lastNameLabel = new javax.swing.JLabel();
        lastNameField = new javax.swing.JLabel();
        cnpLabel = new javax.swing.JLabel();
        cnpField = new javax.swing.JLabel();
        accountTypeLabel = new javax.swing.JLabel();
        accountTypeField = new javax.swing.JLabel();
        accountNumberLabel = new javax.swing.JLabel();
        accountNumberField = new javax.swing.JLabel();
        balanceLabel = new javax.swing.JLabel();
        balanceField = new javax.swing.JLabel();
        interestRateLabel = new javax.swing.JLabel();
        interestRateField = new javax.swing.JLabel();
        transactionLabel = new javax.swing.JLabel();
        transactionField = new javax.swing.JLabel();
        okButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("AccountDetails");
        getContentPane().setLayout(new java.awt.GridLayout(9, 2, 5, 5));

        firstNameLabel.setText("First Name:");
        getContentPane().add(firstNameLabel);
        getContentPane().add(firstNameField);

        lastNameLabel.setText("Last Name:");
        getContentPane().add(lastNameLabel);
        getContentPane().add(lastNameField);

        cnpLabel.setText("CNP:");
        getContentPane().add(cnpLabel);
        getContentPane().add(cnpField);

        accountTypeLabel.setText("Account Type:");
        getContentPane().add(accountTypeLabel);
        getContentPane().add(accountTypeField);

        accountNumberLabel.setText("Account Number:");
        getContentPane().add(accountNumberLabel);
        getContentPane().add(accountNumberField);

        balanceLabel.setText("Balance:");
        getContentPane().add(balanceLabel);
        getContentPane().add(balanceField);

        interestRateLabel.setText("Interest Rate:");
        getContentPane().add(interestRateLabel);
        getContentPane().add(interestRateField);

        transactionLabel.setText("Transaction Fee:");
        getContentPane().add(transactionLabel);
        getContentPane().add(transactionField);

        okButton.setText("OK");
        okButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });
        getContentPane().add(okButton);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_okButtonActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel accountNumberField;
    private javax.swing.JLabel accountNumberLabel;
    private javax.swing.JLabel accountTypeField;
    private javax.swing.JLabel accountTypeLabel;
    private javax.swing.JLabel balanceField;
    private javax.swing.JLabel balanceLabel;
    private javax.swing.JLabel cnpField;
    private javax.swing.JLabel cnpLabel;
    private javax.swing.JLabel firstNameField;
    private javax.swing.JLabel firstNameLabel;
    private javax.swing.JLabel interestRateField;
    private javax.swing.JLabel interestRateLabel;
    private javax.swing.JLabel lastNameField;
    private javax.swing.JLabel lastNameLabel;
    private javax.swing.JButton okButton;
    private javax.swing.JLabel transactionField;
    private javax.swing.JLabel transactionLabel;
    // End of variables declaration//GEN-END:variables
}
