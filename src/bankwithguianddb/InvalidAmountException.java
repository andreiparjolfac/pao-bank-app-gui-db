/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankwithguianddb;

/**
 *
 * @author Andrei
 */
public class InvalidAmountException extends Exception {

    public InvalidAmountException() {
        super("Invalid amount for transaction!");
    }
    
}
