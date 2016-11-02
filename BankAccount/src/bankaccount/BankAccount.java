package bankaccount;
public abstract class BankAccount {
    
    private String name;
    private String address;
    private double accountNumber;
    private double balance;

    public BankAccount (String name, String address, double accountNumber, double balance) {
        
        this.name = name;
        this.address = address;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
    public double setAccountNumber(double accNum){
        return accountNumber = accNum;
    }
    public double getAccountNumber(){
        return accountNumber;
    }
    public double setBalance(double bal){
        return balance = bal;
    }
    public double getBalance(){
        return balance;
    }
    
}
