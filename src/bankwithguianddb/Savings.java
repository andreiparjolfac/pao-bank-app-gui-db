
package bankwithguianddb;

public class Savings extends Account{

    public Savings(int accountId,double initialDeposit){
        super(accountId);
        this.setBalance(initialDeposit);
    }
    
    public AccountType getAccountType(){
        return AccountType.Savings;
    }
}
