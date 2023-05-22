package bankwithguianddb;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Bank implements Serializable{

    private DBService database = new DBService();
    
    int openAccount(String firstName,String lastName,String cnp,AccountType accountType,double balance){
       
        try {
            int accountId = database.AddAccount(firstName,lastName,cnp,accountType,balance);
            Transaction tr = new Transaction(accountId,balance,TransactionType.InitialDeposit);
            database.RegisterTransaction(tr);
            return accountId;
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    Customer getCustomer(int accountId){
        return database.GetAccount(accountId);
    }

    public ArrayList<Customer> getCustomers(){
        return database.GetAllAccounts();
    }
    
    public ArrayList<Transaction> getAllTransactions(){
        return database.GetAllTransactions();
   
        
    }

    boolean closeAccount(int accountId) {
        Customer c = getCustomer(accountId);
        Transaction tr = new Transaction(accountId,c.getAccount().getBalance(),TransactionType.Liquidation);
        database.RegisterTransaction(tr);
        return database.DeleteAccount(accountId);
        
    }
    public static double round(double value,int places){
        if(places<0){
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places,RoundingMode.FLOOR);
        return bd.doubleValue();
    }
    
    public void withdraw(int accountId,double amount) throws InsufficientFundsException{
        Customer customer = getCustomer(accountId);
        double transactionFee=getTransactionFee(customer.getAccount().getAccountType());
        if(amount+5>customer.getAccount().getBalance()){
           throw new InsufficientFundsException();
        }else{
            double newBalance = customer.getAccount().getBalance() - (amount+transactionFee);
            database.UpdateAccount(accountId, newBalance);
            Transaction tr = new Transaction(accountId,amount+transactionFee,TransactionType.WithDraw);
            database.RegisterTransaction(tr);
        }

    }
    public void deposit(int accountId,double amount) throws InvalidAmountException{
        Customer customer = getCustomer(accountId);
        if(amount<=0){
            throw new InvalidAmountException();
        }else{
            double interest = checkInterest(customer.getAccount().getBalance());
            double amounttoDeposit = amount+amount*interest;
            database.UpdateAccount(accountId,customer.getAccount().getBalance()+amounttoDeposit);
            Transaction tr = new Transaction(accountId,amounttoDeposit,TransactionType.Deposit);
            database.RegisterTransaction(tr);
        }
    }
    public double checkInterest(double balance){
        double interest=0;
        if(balance>10000){
            interest=0.05;
        }else{
            interest=0.02;
        }
        return interest;
    }

    public double getTransactionFee(AccountType type) {
        double transactionFee = 0;
        switch(type){
            case Checking:
            case Savings:
                transactionFee=5;
                break;
            case Undefined:
            default:
                transactionFee=0;
                break;
        }
        return transactionFee;
    }
}