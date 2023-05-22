package bankwithguianddb;

import java.io.Serializable;

public class Customer implements Serializable {
    private final String firstName,lastName,cnp;
    private final Account account;
    public Customer(String firstName,String lastName,String cnp,Account account){
        this.firstName=firstName;
        this.lastName=lastName;
        this.cnp=cnp;
        this.account=account;
    }


    @Override
    public String toString(){
        return "\nCustomer information\n"+
                "First name: "+firstName+"\n"+
                "Last name: "+lastName+"\n"+
                "CNP: "+cnp+"\n"+
                account;
    }
    public String basicInfo(){
        return "Account number: "+account.getAccountNumber()+" - Name: "+firstName+" "+lastName;
    }

    Account getAccount(){
        return account;
    }

    String getFirstName() {
        return firstName;
    }

    String getLastName() {
        return lastName;
    }
    String getCnp(){
        return cnp;
    }
}
