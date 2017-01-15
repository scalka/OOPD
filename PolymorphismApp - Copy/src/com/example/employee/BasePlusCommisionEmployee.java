
package com.example.employee;

public class BasePlusCommisionEmployee extends CommissionEmployee{
    
    private double baseSalary;
    
    //constructor
    public BasePlusCommisionEmployee(String first, String last, String ssn, double sales, double rate) {
        super(first, last, ssn, sales, rate);
    }
    
    public double getBaseSalary(){
        return baseSalary;
    }
    public final void setBaseSalary(double salary){
        if(salary>=0.0){
            baseSalary = salary;
        } else {
            throw new IllegalArgumentException(
            "Salary must be >= 0.0");
        }
    }
    @Override 
    public double getEarnings(){
        return getBaseSalary();
    }
    @Override
    public String toString(){
        return String.format("%s: %s\n%s: $%,.2f", 
                "base salary: ", super.toString(),
                "base salary : ", getBaseSalary());
    }
}



/*
baseSalary

getBaseSalary()
setBaseSalary()

getEarnings()
toString()
*/