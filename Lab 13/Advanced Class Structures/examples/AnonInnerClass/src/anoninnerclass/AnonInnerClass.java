/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anoninnerclass;

/**
 *
 * @author user10
 */
public class AnonInnerClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

		OliveJar jar = new OliveJar();
		jar.addOlive("Kalamata", 0x000000);
		jar.addOlive("Kalamata", 0x000000);
		jar.addOlive("Kalamata", 0x000000);
		jar.addOlive("Kalamata", 0x000000);
		jar.reportOlives();
	}
	    
}
