package bankaccount;
public class TestClass {
    public static void main(String[] args) {
        
        BankAccount sa1 = new SavingsAccount("Greta", "Dublin", 29830138, 989.25);
        
        BankAccount sa2 = new SavingsAccount("John", "Cork", 000000, 125);
//        error : bankaccount is abstract can not be initialised
//         BankAccount ba = new BankAccount("sss", "s", 1333, 125);

        
        System.out.println(sa1.toString());
        System.out.println(sa2.toString());

        
        System.out.println("Current interest rate: " + SavingsAccount.getInterestRate());
    }
}
