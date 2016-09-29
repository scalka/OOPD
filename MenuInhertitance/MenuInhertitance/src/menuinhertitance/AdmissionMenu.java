/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menuinhertitance;

/**
 *
 * @author user10
 */
public class AdmissionMenu extends Menu{
    
    // Instance Variables
    
    
    // Constructor
    public AdmissionMenu()
    {
        super();
    }
    
    
    // methods
    @Override
    public void drawMenu()
    {
        super.drawMenu();
        System.out.println("3. Add Student");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
    }
    
    
}
