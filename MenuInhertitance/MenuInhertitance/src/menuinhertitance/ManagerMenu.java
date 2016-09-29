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
public class ManagerMenu extends Menu{
    
     // Instance Variables
    
    
    // Constructor
    public ManagerMenu()
    {
        super();
    }
    
    
    // methods
    @Override
    public void drawMenu()
    {
        super.drawMenu();
        System.out.println("3. Update Staff");
        System.out.println("4. Delete Staff");
        
    }
    
}
