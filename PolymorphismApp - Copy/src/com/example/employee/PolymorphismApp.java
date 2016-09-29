package com.example.employee;

public class PolymorphismApp {

    public static void main(String[] args) {
        HourlyPaidEmployee hourlyEmployee =
                new HourlyPaidEmployee("Karen", "Price", "222-22-2222", 16.75, 40);
        CommissionEmployee commissionEmployee =
                new CommissionEmployee(
                "Sue", "Jones", "333-33-3333", 10000, .06);
        WeeklySalaryEmployee weeklySalaryEmployee = new WeeklySalaryEmployee("Marielle", "Alado", "329-23-233", 450);
        BasePlusCommisionEmployee basePlusCommissionEmployee = new BasePlusCommisionEmployee("Irene", "Lamers", "45-896-784", 48, 0.7);
        
        
        System.out.println("Employees processed individually:\n");
    
        System.out.printf("%s\n%s: $%,.2f\n\n",
                hourlyEmployee, "earned", hourlyEmployee.getEarnings());
        System.out.printf("%s\n%s: $%,.2f\n\n",
                commissionEmployee, "earned", commissionEmployee.getEarnings());
       
        
        
        
        // Put each different type of object into an Employee array.
        Employee[] employees = new Employee[2];
        employees[0] = hourlyEmployee;
        employees[1] = commissionEmployee;
        employees[2] = weeklySalaryEmployee;
        employees[3] = basePlusCommissionEmployee;
        
        
        
        System.out.println("Employees processed polymorphically:\n");

        // ******** NOTE The polymorphism ******//
        // The variable currentEmployee "is a" Employee 
        // However currentEmployee.getEarnings() may call getEarnings() in any of the Employee Subclasses
        for (Employee currentEmployee : employees) {
            System.out.println(currentEmployee);
            System.out.printf(
                    "earned $%,.2f\n\n", currentEmployee.getEarnings());
        }
        
        // This prints out from which class each employee was created
        for (int j = 0; j < employees.length; j++) {
            System.out.printf("Employee %d is a %s\n", j,
                    employees[j].getClass().getName());
        }
    }
}
