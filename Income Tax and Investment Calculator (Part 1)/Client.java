/*Author: Minn Thet Tin
*Student No: C3394937
*Date: 17/04/2022
*Description: This file contains all the methods for inputting several variables like name,salary,weekly expenses and calculating tax and medicare.
*/

// You can add extra methods if you think it is necessary

public class Client
{
    private String name;                     
    private Account savingAccount;
    double grossSalary;
    double netSalary;
    boolean resident;
    double tax;
    double medicare;
    double weeklyExpenses;
    
    

    public Client()
    {

    }

    public String getName()                     // method definition of returning the value of the name //
    {
        return (name);
    }

    
    public void setName(String inputName)       // method definition of setting name //
    {
        name = inputName;
    }
    
    
    public double getSalary()                   // method definition of returning the value of the gross salary //
    {
        return (grossSalary);
    }

    
    public void setSalary(double salary)        // method definition of setting gross salary //
    {
        grossSalary = salary;
    }
    
    
    public double getTaxYearly()                // method definition of returning the value of the tax per year //
    {
        return (tax);
    }

    
    public void calcTaxYearlyResident(double taxableAmount)     // method definition of calculating the value of the tax per year for residents //
    {
           if (grossSalary <= 18200) {
            tax = 0;   
           } else if(grossSalary >= 18201 && grossSalary <= 45000) {
               tax = (grossSalary - 18200) * 0.19;
           } else if(grossSalary >= 45001 && grossSalary <= 120000) {
               tax = ((grossSalary - 45000) * 0.325) + 5092;
           } else if(grossSalary >= 120001 && grossSalary <= 180000) {
               tax = ((grossSalary - 120000) * 0.37) + 29467;
           } else if(grossSalary >= 180001) {
               tax = ((grossSalary - 180000) * 0.45) + 51667;
           }    
    }   
    
    
    public void calcTaxYearlyNonresident(double taxAmount)      // method definition of calculating the value of the tax per year for non residents //
    {
           if (grossSalary <= 120000) {
            tax = grossSalary * 0.325;   
           } else if(grossSalary >= 120001 && grossSalary <= 180000) {
               tax = ((grossSalary - 120000) * 0.37) + 39000;
           } else if(grossSalary >= 180001) {
               tax = ((grossSalary - 180000) * 0.37) + 61200;
           }    
    }
    
    
    public void calcMedicare(double medicareAmount)             // method definition of calculating the Medicare amount for residents //
    {
            if (grossSalary > 29032) {
            medicare = grossSalary * 0.02;   
           }
    }
    
    
    public double getMedicare()                                 // method definition of returning the value of Medicare amount //
    {
            return (medicare);
    }

    
    public void calcNetSalaryResident(double salary)            // method definition of calculating the value of the net salary for residents //
    {
            netSalary = grossSalary - medicare - tax;
    }
    
    
    public void calcNetSalaryNonResident(double salary)         // method definition of calculating the value of the net salary for non residents //
    {
            netSalary = grossSalary - tax;
    }
    
    
    public double getNetSalary()                                // method definition of returning the value of net salary amount //
    {
        return (netSalary);
    }

    
    public void setWeeklyExpenses(double expenses)              // method definition of setting the weekly expenses //
    {
        weeklyExpenses = expenses;
    }
    
    
    public double getWeeklyExpenses()                           // method definition of returning the value of weekly expenses //
    {
        return (weeklyExpenses);
    }
    
    
    public void terminate(){                                    // method definition of exiting the program //
        System.exit(0);
    }
    
    

     
    

    
    
    
    
    
    
}
    

