/*Implement two separate classes 
for the example above and write a test program to instantiate the classes*/
package onetoone;

public class OneToOne {

    public static void main(String[] args) {
        Manager manager = new Manager();
        System.out.println(manager);
        
        Manager manager2 = new Manager(10, "back-end", 3000);
        System.out.println(manager2);
    }
    
}
