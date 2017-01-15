
package onetoone;

public class Programmer {
    
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
