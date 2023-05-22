
package bankwithguianddb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrei
 */
public class DBService {
    String url = "jdbc:mysql://localhost:3306/bankdb";
    String user = "bank";
    String password = "securepassword";
    
    private Connection connect(){
        Connection connection=null;

        try {
            connection = DriverManager.getConnection(url,user,password);
        } catch (SQLException ex) {
            Logger.getLogger(DBService.class.getName()).log(Level.SEVERE, null, ex);
        }

    return connection;
    }
    //CRUD
    int AddAccount(String firstName,String lastName,String cnp,AccountType accountType,Double balance) throws SQLException{
       int userId = -1;
       int accountId = -1;
       Connection connection = connect();
       connection.setAutoCommit(false);
       String addUserSql = "insert into users(FirstName,LastName,CNP) values (?,?,?)";
       PreparedStatement addUser = connection.prepareStatement(addUserSql,Statement.RETURN_GENERATED_KEYS);
       addUser.setString(1,firstName);
       addUser.setString(2,lastName);
       addUser.setString(3,cnp);
       addUser.executeUpdate();
       ResultSet addUserResults = addUser.getGeneratedKeys();
       if(addUserResults.next()){
           userId = addUserResults.getInt(1);
       }
       String addAccountSql = "insert into accounts(Type,Balance) values (?,?)";
       PreparedStatement addAccount = connection.prepareStatement(addAccountSql,Statement.RETURN_GENERATED_KEYS);
       addAccount.setString(1,accountType.name());
       addAccount.setDouble(2,balance);
       addAccount.executeUpdate();
       ResultSet addAccountResults = addAccount.getGeneratedKeys();
       if(addAccountResults.next()){
           accountId = addAccountResults.getInt(1);
       }
       if(userId>0 && accountId>0){
           String linkAccountSql = "insert into mappings(UserId,AccountId) values (?,?)";
           PreparedStatement linkAccount = connection.prepareStatement(linkAccountSql);
           linkAccount.setInt(1,userId);
           linkAccount.setInt(2,accountId);
           linkAccount.executeUpdate();
           
           Reporting.Report(connect(),"SUCCESS_INSERTED_USER");
           connection.commit();
       }else{
           Reporting.Report(connect(),"ROLLBACK_FAIL_INSERT_USER");
           connection.rollback();
       }
       
       connection.close();
       return accountId;
    }
    
    Customer GetAccount(int accountId){
        Customer customer = null;
        Connection connection;
        try {
            connection = connect();
            String findUserSql = "select FirstName,LastName,CNP,Type,Balance from Users a join Mappings b on a.ID = b.UserId join Accounts c on c.ID = b.AccountId where c.ID = ?";
            PreparedStatement findUser = connection.prepareStatement(findUserSql);
            findUser.setInt(1,accountId);
            ResultSet findUserResults = findUser.executeQuery();
            if (findUserResults.next()){
                String firstName = findUserResults.getString("FirstName");
                String lastName = findUserResults.getString("LastName");
                String cnp = findUserResults.getString("CNP");
                AccountType type = AccountType.valueOf(findUserResults.getString("Type"));
                double balance = findUserResults.getDouble("Balance");
                Account account ;
                if (type == AccountType.Checking){
                    account = new Checking(accountId,balance);
                }else if (type == AccountType.Savings){
                    account = new Savings(accountId,balance);
                }else{
                    System.err.println("Invalid account type");
                    throw new Exception("Unknown account type");
                }
                Reporting.Report(connect(),"SUCCESS_RETRIEVED_USER");
                customer = new Customer(firstName,lastName,cnp,account);
            }else{
                Reporting.Report(connect(),"FAIL_NO_USER_FOUND");
                System.err.println("No user account was found for ID "+accountId);
            }
        } catch (Exception ex) {
            Logger.getLogger(DBService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return customer;
    }
    
    boolean UpdateAccount(int accountId,double balance){
        boolean success = false;
        try {
            Connection connection = connect();
            String updateSql = "update Accounts SET Balance = ? where ID = ?  ";
            PreparedStatement updateBalance  = connection.prepareStatement(updateSql);
            updateBalance.setDouble(1,balance);
            updateBalance.setInt(2,accountId);
            updateBalance.executeUpdate();
            success = true;
            Reporting.Report(connect(),"SUCCESS_UPDATED_USER");
        } catch (SQLException ex) {
            Reporting.Report(connect(),"FAIL_UPDATED_USER");
            Logger.getLogger(DBService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return success;
    }
    
    boolean DeleteAccount(int accountId){
        boolean success = false;
        try {
            Connection connection = connect();
            String deleteSql = "delete Users,Accounts from Users join Mappings on Users.ID = Mappings.UserId join Accounts on Accounts.ID = Mappings.AccountId where Accounts.ID = ?";
            PreparedStatement deleteAccount  = connection.prepareStatement(deleteSql);
            deleteAccount.setInt(1,accountId);
            deleteAccount.executeUpdate();
            success = true;
            Reporting.Report(connect(),"SUCCESS_DELETED_USER");
        } catch (SQLException ex) {
            Reporting.Report(connect(),"FAIL_DELETED_USER");
            Logger.getLogger(DBService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return success;
    }
    void RegisterTransaction(Transaction tr){
        try{
            Connection con = connect();
            String insertTransactionSql = "insert into transactions(TipTranzactie,Amount,AccountId) values (?,?,?)";
            PreparedStatement insertTransaction = con.prepareStatement(insertTransactionSql);
            insertTransaction.setString(1,tr.getType().toString());
            insertTransaction.setDouble(2,tr.getAmount());
            insertTransaction.setInt(3,tr.getAccountId());
            insertTransaction.executeUpdate();
        }catch (SQLException ex){
            Logger.getLogger(DBService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    ArrayList<Transaction> GetAllTransactions(){
        ArrayList<Transaction> transactions = new ArrayList<>();
        try{
            Connection con = connect();
            String findAllTransactionsSql = "select t.ID,FirstName,LastName,t.TipTranzactie,t.Amount,t.Timp,t.AccountId from transactions t left join mappings m on m.AccountId = t.AccountId left join users u on m.UserId=u.ID"; 
            PreparedStatement findAllTransactions = con.prepareStatement(findAllTransactionsSql);
            ResultSet findAllTransactionsResults = findAllTransactions.executeQuery();
            while(findAllTransactionsResults.next()){
                int TXID = findAllTransactionsResults.getInt("ID");
                String firstName = findAllTransactionsResults.getString("FirstName");
                String lastName = findAllTransactionsResults.getString("LastName");
                String tipTranzactieStr = findAllTransactionsResults.getString("TipTranzactie");
                Double amount = findAllTransactionsResults.getDouble("Amount");
                String timp = findAllTransactionsResults.getString("Timp");
                int accountId = findAllTransactionsResults.getInt("AccountId");
                TransactionType tipTranzactie= null;
                if(tipTranzactieStr.compareTo("Deposit")==0)
                    tipTranzactie=TransactionType.Deposit;
                if(tipTranzactieStr.compareTo("WithDraw")==0)
                    tipTranzactie=TransactionType.WithDraw;
                if(tipTranzactieStr.compareTo("InitialDeposit")==0)
                    tipTranzactie=TransactionType.InitialDeposit;
                if(tipTranzactieStr.compareTo("Liquidation")==0)
                    tipTranzactie=TransactionType.Liquidation;
                String nume = null;
                if (firstName==null || lastName==null)
                    nume = "UTILIZATOR STERS";
                else
                    nume = firstName + " " +lastName;
                Transaction tr = new Transaction(accountId,amount,tipTranzactie,TXID,timp,nume);
                transactions.add(tr);
                
            }
        }catch (Exception ex){
            Logger.getLogger(DBService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return transactions;
    }
    ArrayList<Customer> GetAllAccounts(){
        ArrayList<Customer> customers = new ArrayList<>();
        try {
            Connection connection = connect();
            String findAllUsersSql = "select AccountId,FirstName,LastName,CNP,Type,Balance from Users a join Mappings b on a.ID = b.UserId join Accounts c on c.ID = b.AccountId";
            PreparedStatement findAllUsers = connection.prepareStatement(findAllUsersSql);
            ResultSet findAllUsersResults = findAllUsers.executeQuery();
            while(findAllUsersResults.next()){
                String firstName = findAllUsersResults.getString("FirstName");
                String lastName = findAllUsersResults.getString("LastName");
                String cnp = findAllUsersResults.getString("CNP");
                AccountType type = AccountType.valueOf(findAllUsersResults.getString("Type"));
                double balance = findAllUsersResults.getDouble("Balance"); 
                int accountId = findAllUsersResults.getInt("AccountId");
                Account account ;
                if (type == AccountType.Checking){
                    account = new Checking(accountId,balance);
                }else if (type == AccountType.Savings){
                    account = new Savings(accountId,balance);
                }else{
                    System.err.println("Invalid account type");
                    throw new Exception("Unknown account type");
                }
                
                customers.add( new Customer(firstName,lastName,cnp,account));
            }
        } catch (Exception ex) {
            Reporting.Report(connect(),"FAIL_RETRIEVED_ALL_USERS");
            Logger.getLogger(DBService.class.getName()).log(Level.SEVERE, null, ex);
        }
        Reporting.Report(connect(),"SUCCESS_RETRIEVED_ALL_USERS");
        return customers;
    }
}
