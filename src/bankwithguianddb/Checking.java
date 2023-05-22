package bankwithguianddb;
public class Checking extends Account{


    public Checking(int accountId,double initialDeposit){
        super(accountId);
        this.setBalance(initialDeposit);
    }

    public AccountType getAccountType(){
        return AccountType.Checking;
    }
}
