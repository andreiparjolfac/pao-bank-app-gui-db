package bankwithguianddb;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Andrei
 */
public final class Reporting {
    
   
    private Reporting(){
    }
    
    public static void Report(Connection con,String Action) {
        
        try {
            con.setAutoCommit(false);
            String insertSql = "insert into reporting(Actiune) values (?)";
            PreparedStatement insertAction  = con.prepareStatement(insertSql);
            insertAction.setString(1,Action);
            insertAction.executeUpdate();
            con.commit();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBService.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}
