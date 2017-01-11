
package localinnerclass;

public class LocalInnerClass {

    public static void main(String[] args) {
        Manager manager = new Manager();
        manager.addProgrammers("front-end", 2220);
        manager.addProgrammers("back-end", 2000);
        manager.addProgrammers("full-stack", 3000);
        manager.reportProgrammers();
    }
    
}
