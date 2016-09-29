package bankaccount;

public class SavingsAccount extends BankAccount{
    
    private double accountNumber;
    private double balance;
    private static double interestRate;
    
    public SavingsAccount(String name, String address, double accountNumber, double balance){
        super (name, address, accountNumber, balance );
        this.interestRate = 0.85;
    }
    
    public static double setInterestRate(double intRate){
        return interestRate = intRate;
    }
    public static double getInterestRate(){
        // use class name bcos it is static
        return SavingsAccount.interestRate = interestRate;
    }
//    public double getAccountNumber(){
//        return accountNumber;
//    }
//    public double getBalance(){
//        return balance;
//    }
    @Override
    public String toString() {
        return ( 
                "Account balance: " + getAccountNumber() +
                " Balance: " + getBalance()  );
    }
    
}
