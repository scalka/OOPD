/*
Now the only purpose of the manager class is to hold information about the programmers
they manage. Implement the Programmer class as a member object of Manager. 
Write a method in the Manager class 
called printSalaries() this prints a report of all the salaries.  
 */
package memberclass;

public class MemberClass {

    public static void main(String[] args) {
        Manager manager = new Manager();
        manager.addProgrammers("front-end", 2220);
        manager.addProgrammers("back-end", 2000);
        manager.addProgrammers("full-stack", 3000);
        manager.reportProgrammers();
        manager.printSalaries();
        
    }
    
}
