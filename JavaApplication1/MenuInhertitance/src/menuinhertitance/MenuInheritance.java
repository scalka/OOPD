/*
 * This program Demonstrates Classes, Objects, Inheritance, Method Overrides and Polymorphism. 
 */
package menuinhertitance;

import java.util.Scanner;

/**
 *
 * @author user10
 */
public class MenuInheritance {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // This is simple Test Code the demonstrate inheritance and polymorphism
        
        Menu menu = determineMenu();
        
        // Which drawMenu() will be called? What are we demonstrating here??
        menu.drawMenu();
       
    }
    
    
    public static Menu determineMenu()
    {
        Menu menu;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please Login....");
        System.out.println("Enter M for manager or A for Admissions staff");
        String input = keyboard.nextLine();
        
        if (input.equalsIgnoreCase("M"))
                menu = new ManagerMenu();
        else 
        if (input.equalsIgnoreCase("A"))
               menu = new AdmissionMenu();
        else
            menu = new Menu();
        
        return menu;
    }
    
}
