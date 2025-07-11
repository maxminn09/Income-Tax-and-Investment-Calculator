/*Author: Minn Thet Tin
*Student No: C3394937
*Date: 17/04/2022
*Description: This file contains the "main" method, the execution of inputs and outputs done by the "run" method while using other methods from Account and Client classes.
*/

// You can add extra methods if you think it is necessary

import java.util.*;

public class CalculatorInterface
{
     Scanner console = new Scanner (System.in);
        Client client = new Client();
    public void run()
    {   
        System.out.print("Name = ");
        inputName();
        
        System.out.print("Please type in your annual salary = ");           // asks the user to input annual salary //         
        inputSalary_ResidentCheck();
        
        System.out.print("Please type in the amount of weekly expenses: ");     // asks the user to input weekly expenses //
        double weeklyExpenses  = console.nextDouble();
        while(weeklyExpenses <= 0){                                             // verifies the user input by checking the number whether it is less than or equal to zero, if so ask again //
            System.out.println("The weekly expenses amount is invalid, please type again: ");
            weeklyExpenses = console.nextDouble();
        } 
        while ((client.getNetSalary() / 52) < weeklyExpenses){                  // verifies the user input if the weekly expenses exceed more than the weekly earnings, if so ask again //
            System.out.println("The weekly earnings are not sufficient to cover the expenses, please type in a new amount or type in a negative number to quit");
            weeklyExpenses = console.nextDouble();
            if (weeklyExpenses < 0)                                             // gives the user option to quit the program by typing in a negative number //
                client.terminate();
        }   
        client.setWeeklyExpenses(weeklyExpenses);                               // sets the input into the weekly expenses variable //   
        
        
        System.out.println(" ");                                                // next line //
        
        
        Account account;                                                        // creates a new object from Account class //
        account = new Account();
        System.out.print("Would you like to invest? ");                         // asks the user whether to invest or not //
        String answer = console.next();
        
        
        if (answer.equals("yes") || answer.equals("Yes")) {                     // if the user agrees, asks the user for investment per week //
        System.out.print("Please type in the amount to invest per week: ");    
        double investAmount = console.nextDouble();
        while(((client.getNetSalary() / 52) - weeklyExpenses) < investAmount){  // verifies the user input if the invest amount exceed more than the weekly net salary, if so ask again //
            System.out.println("The invest amount cannot be covered by your weekly net salary, please type in a new amount: ");
            investAmount = console.nextDouble();
        }
        account.setAmount(investAmount);                                        // sets the input into the invest amount variable //
        System.out.print("Please type in the interest rate annually (1% and 20%): ");   // asks the user to input annual interest rate //
        double rate = console.nextDouble();
        while(rate < 1 || rate > 20){                                                   // verifies the user input whether the rate remains in the given range, if not ask again //        
            System.out.println("The interest amount is invalid, please try again: ");
            rate = console.nextDouble();
        }
        account.setRate(rate);                                                          // sets the input into the rate variable //
        
        
        System.out.print("Please type in the duration of the investment: ");   // asks the user to input duration of the investment //
        int numberOfWeeks = console.nextInt();
        while(numberOfWeeks <= 0){                                             // verifies the user input by checking the number whether it is less than or equal to zero, if so ask again //
            System.out.println("The duration amount is invalid, please try again: ");
            numberOfWeeks = console.nextInt();
        }
        account.setWeeks(numberOfWeeks);                                       // sets the input into the weeks variable // 
        
        
        System.out.println(" ");                                               // next line //
        
        
        System.out.println("Weeks       Balance");                             // prints out the table //
        System.out.println("--------------------");
        for (int weeks = 4; weeks <= account.getWeeks(); weeks = weeks + 4){   // calculates the monthly investment with interest //
            if (weeks < 10){                                                   // condition to make proper formatting of the table //
                System.out.print(weeks);
                System.out.print("          ");
                account.calcInvestmentMonthly(account);
                System.out.printf("$%.2f \n" , account.getInvestmentMonthly());
        } else if (weeks >= 10){                                               // condition to make proper formatting of the table //
                System.out.print(weeks);
                System.out.print("         ");
                account.calcInvestmentMonthly(account);
                System.out.printf("$%.2f \n" , account.getInvestmentMonthly());
         }
        }    
        if (account.getWeeks() % 4 != 0){                                      // calculates the remaining weeks after each month //
            if (account.getWeeks() < 10){                                      // condition to make proper formatting of the table //
                System.out.print(account.getWeeks());
                System.out.print("          ");
                account.calcInvestmentNoInterest(account);
                System.out.printf("$%.2f \n" , account.getInvestmentNoInterest());
        }   else if (account.getWeeks() > 10){                                 // condition to make proper formatting of the table //
                System.out.print(account.getWeeks());
                System.out.print("         ");
                account.calcInvestmentNoInterest(account);
                System.out.printf("$%.2f \n" , account.getInvestmentNoInterest());
                }
            }
        }
        }
    
        
    public static void main(String[] args)
    {
        CalculatorInterface calc = new CalculatorInterface();
        calc.run();
        }
        
    public void inputName(){
        String name = console.nextLine();                       // asks the user to input name //
        while(!name.matches(".*\\s+.*")){                       // verifies the user input by checking for two seperate first name and last name strings,if not ask again //
            System.out.println("Name must have first name and last name with space in between, please type again: ");
            name = console.nextLine();
        }   
        client.setName(name);                                   // sets the input into the name variable //
        System.out.println(" ");                                // next line //
    }

    public void inputSalary_ResidentCheck(){
        double grossSalary  = console.nextDouble();
        while(grossSalary <= 0){                                            // verifies the user input by checking the number whether it is less than or equal to zero, if so ask again //
            System.out.println("The Salary amount is invalid, please type again: ");
            grossSalary = console.nextDouble();
        }
        client.setSalary(grossSalary);                                      // sets the input into the gross salary variable //
        
        
        System.out.println(" ");                                            // next line //
        
        
        System.out.print("Are you a resident? (True) or (False): ");        // asks the user to identify as a resident or not //
        boolean resident = true;
        resident = console.nextBoolean();
        if(resident){                                                       // if the user is a resident, the following calculations will be executed and printed //
            client.calcTaxYearlyResident(grossSalary);
            client.calcMedicare(grossSalary);
            client.calcNetSalaryResident(grossSalary);
            System.out.println(" ");
            System.out.println("Net Salary");
            System.out.printf("Per Year: $%.2f \n" , client.getNetSalary());
            System.out.printf("Per Week: $%.2f \n" , (client.getNetSalary() / 52));
            System.out.println(" ");
            System.out.println("Tax");
            System.out.printf("Per Year: $%.2f \n" , client.getTaxYearly());
            System.out.printf("Per Week: $%.2f \n" , (client.getTaxYearly() / 52));
            System.out.println(" ");
            System.out.printf("Medicare per year: $%.2f \n" , client.getMedicare());
            System.out.println(" ");           
        } else {                                                            // if the user is not a resident, the following calculations will be executed and printed //        
            client.calcTaxYearlyNonresident(grossSalary);
            client.calcNetSalaryNonResident(grossSalary);
            System.out.println(" ");
            System.out.println("Net Salary");
            System.out.printf("Per Year: $%.2f \n" , client.getNetSalary());
            System.out.printf("Per Week: $%.2f \n" , (client.getNetSalary() / 52));
            System.out.println(" ");
            System.out.println("Tax");
            System.out.printf("Per Year: $%.2f \n" , client.getTaxYearly());
            System.out.printf("Per Week: $%.2f \n" , (client.getTaxYearly() / 52));
            System.out.println(" ");
        }
    }
}
        
    

    
    
    
    
    
    
    


        

    