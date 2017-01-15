/*
 *ADVANCED CLASSES - ONE TO MANY
 */
package onetoone;

import java.util.ArrayList;

public class Manager {

    public ArrayList<Programmer> programmers;
    {
        System.out.println("searching..");
        programmers = new ArrayList<>();
        programmers.add(new Programmer("Front-end", 2000));
    }
    
    public Manager(){
        System.out.println("Constructor...");
    }
    
    public Manager(int nProgrammers, String programmerType, double salary){
        for (int i = 1; i <= nProgrammers; i++){
            programmers.add(new Programmer(programmerType, salary));
        }
    }
    
}
