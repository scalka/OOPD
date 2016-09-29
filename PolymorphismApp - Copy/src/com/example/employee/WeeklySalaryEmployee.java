
package com.example.employee;

public class WeeklySalaryEmployee extends Employee {

    private double weeklysalary;
    
    public WeeklySalaryEmployee(String first, String last, String ssn, double weeklysalary) {
        super(first, last, ssn);
        setWeeklySalary(weeklysalary);
    }

    
    public double getWeeklySalary(){
        return weeklysalary;
    }
    
    public final void setWeeklySalary(double weeklySalary){
        if (weeklySalary >= 0.0){
            weeklySalary = weeklySalary;
        } else {
            throw new IllegalArgumentException( "Weekly salary must be >=0.0");
        }
    }
    

    @Override
    public double getEarnings() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public String toString(){
        return String.format("weekly salary: %s\n%s: $%,.2f",
                super.toString(), "weekly salary", getWeeklySalary());
    }
    
}
