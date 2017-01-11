/*ANONYM CLASS
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anoninnerclass;


import java.util.ArrayList;

/**
 *
 * @author Anne
 */
public class OliveJar {
    
    public ArrayList<Olive> olives;

    {
            System.out.println("initializing...");
            olives = new ArrayList<>();
            olives.add(new Olive("Golden", 0xDA9100));
    }

    public OliveJar() {
            System.out.println("Constructor...");
    }

    public OliveJar(int nOlives, String oliveName, long color) {
            for (int i = 1; i <= nOlives; i++) {
                    olives.add(new Olive(oliveName, color));
            }
    }

    public void addOlive(String oliveName, long color) {
            olives.add(new Olive(oliveName, color));
    }

    public void reportOlives() {
         
            // new Object() instantiates the anon class 
            // Object() calls the constructer (it inherits from Object so call this constructer)
            // Code between the {} is the anon class code block.
            // .open() immediately call the method in that class
            new Object() {
                    public void open() {
                            System.out.println("Twist, twist, twist...");
                            System.out.println("Pop!");
                    }
            }.open();
       	
            for (Olive o : olives) {
                    System.out.println("It's a " + o.oliveName + " olive!!");
            }
    }


    class Olive {

            public static final long BLACK = 0x000000;

            public String oliveName;
            public long color = BLACK;

            public Olive() {
            }

            public Olive(String oliveName) {
                    this.oliveName = oliveName;
            }

            public Olive(String oliveName, long color) {
                    this(oliveName);
                    this.color = color;
            }

            public String toString() {
                    return "name: " + this.oliveName + ": " + "color: " +  this.color;
            }

    }

	
}
