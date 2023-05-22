
package bankwithguianddb;
import java.io.Serializable;
import java.util.Scanner;

public abstract class Account implements Serializable {


    private double balance=0;
    private int accountNumber;


    public Account(int accountId){
        accountNumber=accountId;
    }
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public abstract AccountType getAccountType();

    @Override
    public String toString(){
        return "Account type: "+getAccountType()+" account\n"+
                "Account number: "+this.getAccountNumber()+"\n"+
                "Balance: "+this.getBalance()+"\n";
    }
}
