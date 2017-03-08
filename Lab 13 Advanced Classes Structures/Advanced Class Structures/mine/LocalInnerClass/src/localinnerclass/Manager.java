/*
 *ADVANCED CLASSES - ONE TO MANY
 */
package localinnerclass;

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
    
    public void addProgrammers(String programmerType, double salary){
        programmers.add(new Programmer(programmerType, salary));
    }
    
    public void reportProgrammers(){
        class Permission {
            /*******LOCAL CLASS INSIDE OF REPORTPROG. LOCAL TO that block of code********/
            public void grantPermission(){
                System.out.print("Granting Permsssions to print Salaries…");
            }
        }
        // instantiating the class above and calling its method reportProgrammers()
        new Permission().grantPermission();

        for (Programmer p : programmers){
            System.out.println("It's a " + p.programmerType + " programmer.");
        }
    }
    
    class Programmer {
    
        public String programmerType;
        public double salary;

        public Programmer(){

        }

        public Programmer(String programmerType){
            this.programmerType = programmerType;
        }

        public Programmer(String programmerType, double salary){
            this(programmerType);
            this.salary = salary;
        }

        public String toString() {
            return "Type: " + this.programmerType + " salary: " + this.salary;
        }
    }
    
}
