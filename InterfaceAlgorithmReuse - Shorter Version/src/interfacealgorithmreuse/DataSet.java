/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacealgorithmreuse;

/**
 *
 * @author user10
 */
public class DataSet {
    
    private double sum;
    
   // Create a variable called maximum of type "Measurable 
   
    private int count;
    
    // add should accept a Measurable object called x
    public void add(){
        
    // Which getMeasure() method is called here...
    sum = sum + x.getMeasure();
    if(count == 0 || maximum.getMeasure() < x.getMeasure())
        maximum = x;
    count++;
    }
    
    // alter the code below so getMaximum() return type is a Measurable object
    public void getMaximum(){
    return maximum;
    }
    
    public double getAverage(){
     if(count == 0)
         return 0;
     else
         return sum/count;
    }
    
 }
    
    
    
