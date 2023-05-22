/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankwithguianddb;

/**
 *
 * @author Andrei
 */
public class InsufficientFundsException extends Exception {

    public InsufficientFundsException() {
        super("You have insufficient funds for a withdrawal");
    }
    
}
