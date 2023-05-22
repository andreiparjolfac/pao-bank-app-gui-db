/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankwithguianddb;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Andrei
 */
public class Transaction {
    
    private static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    private  int accountId;
    private  double amount;
    private  TransactionType type;
    private  Integer txid = null;
    private  Date dataTranzactie=null;
    private  String nume = null;
    public Transaction(int accountId,double amount,TransactionType type){
        this.accountId = accountId;
        this.amount = amount;
        this.type=type;
    }
    public Transaction(int accountId,double amount,TransactionType type,int txid,String dataTranzactie,String nume){
        this(accountId,amount,type);
        try {
            this.txid = txid;
            this.dataTranzactie = sf.parse(dataTranzactie);
            this.nume=nume;
        } catch (ParseException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String getNume(){
        return nume;
    }
    public int getTxid(){
        return txid;
    }
    public int getAccountId(){
        return accountId;
    }    
    public double getAmount(){
        return amount;
    }
    public TransactionType getType(){
        return type;
    }
    public Date getDataTranzactie(){
        return dataTranzactie;
    }
    
    @Override
    public String toString(){
        if(txid==null){
            return "Transaction : Account ID : "+ accountId +
                "Amount : " + amount + 
                "Type : " + type.toString();
        }else{
            return "Transaction : Account ID : "+ accountId + "\n" +
                    "Transaction ID : " + txid + "\n" +
                    "Nume : " + nume + "\n" +
                    "Amount : " + amount + "\n" +
                    "Type : " + type.toString() +"\n" +
                    "Data : "+dataTranzactie ;
                    
                    
        }
    }

//    public void setDataTranzactie(String data){
//        try {
//            this.dataTranzactie = sf.parse(data);
//        } catch (ParseException ex) {
//            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
