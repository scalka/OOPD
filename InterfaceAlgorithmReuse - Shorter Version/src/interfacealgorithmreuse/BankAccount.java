/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacealgorithmreuse;

/**
 *
 * @author user10
 */
public class BankAccount implements Measurable{
    
    private int accountNumber;
    private double balance;
    
    public BankAccount(int an, double bal){
    accountNumber = an;
    balance = bal;
    }
    
    public void deposit(double amount){
    balance = balance + amount;
    }
    
    public void withdraw(double amount)
    {
        balance = balance - amount;
    }
    
    
    @Override
    public double getMeasure()
    { 
        return balance;
    }
    
}
