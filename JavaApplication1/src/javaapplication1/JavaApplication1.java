/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.Scanner;

public class JavaApplication1 {

    public static void main(String[] args) {
        Menu menu;
        Scanner keyboard = new Scanner (System.in);
        System.out.println("Please login");
        System.out.println("M for manager A for Admission staff");
        String input = keyboard.nextLine();
        
        if (input.equalsIgnoreCase("M"))
            menu = new ManagerMenu();
        else if (input.equalsIgnoreCase("A"))
            menu = new AdmissionMenu();
        else 
            menu = new Menu();
        
    }
    
}
