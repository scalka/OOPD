package com.example.employee;

public class HourlyPaidEmployee extends Employee {

    private double hourlyWage;
    private double hours;

    public HourlyPaidEmployee(String first, String last, 
            String ssn, double hourlyWage, double hoursWorked) {
        super(first, last, ssn);
        setHourlyWage(hourlyWage);
        setHours(hoursWorked);
    }
    public final void setHourlyWage(double hourlyWage) {
        if (hourlyWage >= 0.0) {
            hourlyWage = hourlyWage;
        } else {
            throw new IllegalArgumentException(
                    "Hourly wage must be >= 0.0");
        }
    }
    public double getHourlyWage() {
        return hourlyWage;
    }
    public final void setHours(double hoursWorked) {
        if ((hoursWorked >= 0.0) && (hoursWorked <= 168.0)) {
            hours = hoursWorked;
        } else {
            throw new IllegalArgumentException(
                    "Hours worked must be >= 0.0 and <= 168.0");
        }
    }
    public double getHours() {
        return hours;
    }
    @Override
    public double getEarnings() {
        if (getHours() <= 40) {
            return getHourlyWage() * getHours();
        } else {
            return 40 * getHourlyWage() + (getHours() - 40) * getHourlyWage() * 1.5;
        }
    }
    @Override
    public String toString() {
        return String.format("hourly employee: %s\n%s: $%,.2f; %s: %,.2f",
                super.toString(), "hourly wage", getHourlyWage(),
                "hours worked", getHours());
    }
}
