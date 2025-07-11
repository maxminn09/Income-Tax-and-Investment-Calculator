/*Author: Minn Thet Tin
 *Student No: C3394937
 *Date: 26/05/2022
 *Description: This file contains the "main" method, the execution of switch by using cases, inputs and outputs done by the "run" method while using other methods from Account and Client classes.
 */

// You can add extra methods if you think it is necessary

import java.util.*;
import java.io.*;

public class CalculatorInterface
{
    Scanner console = new Scanner (System.in);
    static Client client = new Client();
    int clientSize = 5;
    Client[] c = new Client[clientSize];
    int noClients = 0;
    int noAccounts =0; 
    double weeklyExpenses;
    Account account= new Account();

    public void run() throws IOException
    {   
        for(int i = 0; i < clientSize; i++){
            c[i] = new Client();
        }

        String displayName;
        String accountName;
        String displayAccountname;
        int choice;
        showMenu(); 
        choice = console.nextInt();
        while(choice != 9)  {
            switch(choice) {                                                                    //choices given for each case//
                case 1: addClient();
                    break;
                case 2: deleteClient();
                    break;

                case 3: System.out.print("inform the name of the client :");
                    displayName = console.next();
                    displayClient(displayName);
                    break;

                case 4: displayAllClients();
                    break;

                case 5: System.out.print("inform the name of the client to add account: ");
                    accountName = console.next();
                    addAccount(accountName);
                    break;

                case 6: System.out.print("inform the name of the client to display account: ");
                    displayAccountname = console.next();
                    displayAccount(displayAccountname);
                    break;

                case 7: deleteAccount();
                    break;

                case 8: saveFile();
                    break;

                case 9: System.exit(0);
                    break;

                default: System.out.println("Invalid Selection");
            }//end switch    
            showMenu(); 
            choice = console.nextInt();
        }

    }

    public static void main(String[] args) throws IOException
    {
        CalculatorInterface calc = new CalculatorInterface();
        calc.run();
    }

    public static void showMenu()
    {
        System.out.println("Select and enter");
        System.out.println("1 - Add a client");
        System.out.println("2 - Delete Client");
        System.out.println("3 - Display a client");
        System.out.println("4 - Display all clients");
        System.out.println("5 - Add an account");
        System.out.println("6 - Display account");
        System.out.println("7 - Delete Account");
        System.out.println("8 - Save in a file ");
        System.out.println("9 - Exit");
    }

    public void addClient()                                                             //method to add client to array//
    {
        if (noClients <5){
            System.out.print("Please enter client's name = ");
            String name = console.next();                       // asks the user to input name //

            System.out.print("Please type in client's annual salary = ");           // asks the user to input annual salary // 
            double grossSalary  = console.nextDouble();
            while(grossSalary <= 0){                                            // verifies the user input by checking the number whether it is less than or equal to zero, if so ask again //
                System.out.println("The Salary amount is invalid, please type again: ");
                grossSalary = console.nextDouble();
            }  

            System.out.print("Are you a resident? (True) or (False): ");        // asks the user to identify as a resident or not //
            boolean resident = true;
            resident = console.nextBoolean();

            System.out.print("Please type in the client's amount of weekly expenses: ");     // asks the user to input weekly expenses //
            weeklyExpenses  = console.nextDouble();
            while(weeklyExpenses <= 0){                                             // verifies the user input by checking the number whether it is less than or equal to zero, if so ask again //
                System.out.println("The weekly expenses amount is invalid, please type again: ");
                weeklyExpenses = console.nextDouble();
            } 
            while ((grossSalary / 52) < weeklyExpenses){                  // verifies the user input if the weekly expenses exceed more than the weekly earnings, if so ask again //
                System.out.println("The weekly earnings are not sufficient to cover the expenses, please type in a new amount or type in a negative number to quit");
                weeklyExpenses = console.nextDouble();
                if (weeklyExpenses < 0)                                             // gives the user option to quit the program by typing in a negative number //
                    client.terminate();
            }   

            if (resident){
                c[noClients].setName(name);
                c[noClients].setSalary(grossSalary);
                c[noClients].setResidenceStatus(resident);
                c[noClients].setWeeklyExpenses(weeklyExpenses);
                c[noClients].calcTaxYearlyResident(grossSalary);
                c[noClients].calcMedicare(grossSalary);
                c[noClients].calcNetSalaryResident(grossSalary);
                noClients++;
            }else {
                c[noClients].setName(name);
                c[noClients].setSalary(grossSalary);
                c[noClients].setResidenceStatus(resident);
                c[noClients].setWeeklyExpenses(weeklyExpenses);
                c[noClients].calcTaxYearlyNonresident(grossSalary);
                c[noClients].calcNetSalaryNonResident(grossSalary);
                noClients++;
            }
        }
        else {
            System.out.println("It is not possible to add another client");
        }
    }
    //-------------------------//  
    public void deleteClient()                                                                  //method to delete client from array//
    {
        System.out.print("Please enter the client's name you wish to delete = ");
        String nameDelete = console.next();
        int counter = 0;
        for (int n = 0; n < noClients; n++){
            if(!c[n].getName().equals(nameDelete)){
                counter++;
            }
        }
        if(counter == noClients){
            System.out.println("Could not find the client");   
        }

        for(int i = 0; i < noClients; i++){
            if (c[i].getName().equals(nameDelete)){
                for (int j = 0; j < noClients - 1; j++){
                    c[i] = c[i+1];
                }
                noClients--;
                System.out.println("The client is deleted");
            } 
        }
    }

    public void displayClient(String nameDisplay)                                               //method to display client from array//
    {
        for (int i = 0; i < noClients; i++){
            if(c[i].getName().equals(nameDisplay)){
                System.out.println("name: " + c[i].getName());
                System.out.printf("Salary per year : $%.2f \n", c[i].getSalary());
                System.out.printf("Salary per week : $%.2f \n", c[i].getSalary() / 52);
                System.out.println("Residence Status : "+c[i].getResidenceStatus());
                if (c[i].getResidenceStatus()){
                    c[i].calcTaxYearlyResident(c[i].getSalary());
                    c[i].calcMedicare(c[i].getSalary());
                    c[i].calcNetSalaryResident(c[i].getSalary());
                    System.out.println(" ");
                    System.out.println("Net Salary");
                    System.out.printf("Per Year: $%.2f \n" , c[i].getNetSalary());
                    System.out.printf("Per Week: $%.2f \n" , (c[i].getNetSalary() / 52));
                    System.out.println(" ");
                    System.out.println("Tax");
                    System.out.printf("Per Year: $%.2f \n" , c[i].getTaxYearly());
                    System.out.printf("Per Week: $%.2f \n" , (c[i].getTaxYearly() / 52));
                    System.out.println(" ");
                    System.out.printf("Medicare per year: $%.2f \n" , c[i].getMedicare());
                    System.out.println(" ");
                }else {
                    c[i].calcTaxYearlyNonresident(c[i].getSalary());
                    c[i].calcNetSalaryNonResident(c[i].getSalary());
                    System.out.println(" ");
                    System.out.println("Net Salary");
                    System.out.printf("Per Year: $%.2f \n" , c[i].getNetSalary());
                    System.out.printf("Per Week: $%.2f \n" , (c[i].getNetSalary() / 52));
                    System.out.println(" ");
                    System.out.println("Tax");
                    System.out.printf("Per Year: $%.2f \n" , c[i].getTaxYearly());
                    System.out.printf("Per Week: $%.2f \n" , (c[i].getTaxYearly() / 52));
                    System.out.println(" ");
                }
                System.out.println("Weekly Expenses: " + c[i].getWeeklyExpenses());
                System.out.println("Amount invested per week: " + client.savingAccount[i].getAmount());
                System.out.println("Interest rate: " + client.savingAccount[i].getRate());
                System.out.println("Number of weeks: " + client.savingAccount[i].getWeeks());
            }
        }
        int counter = 0;
        for (int n = 0; n < noClients; n++){
            if(!c[n].getName().equals(nameDisplay)){
                counter++;
            }
        }
        if(counter == noClients){
            System.out.println("Could not find the client");   
        }
    }

    public void addAccount(String accName)                                                              //method to add account to array//
    {
        for (int i = 0; i < noClients; i++){
            if(c[i].getName().equals(accName)){
                if (noAccounts < 2){
                    System.out.print("Please type in the amount to invest per week: ");    
                    double investAmount = console.nextDouble();
                    while(((c[i].getNetSalary() / 52) - weeklyExpenses) < investAmount){  // verifies the user input if the invest amount exceed more than the weekly net salary, if so ask again //
                        System.out.println("The invest amount cannot be covered by your weekly net salary, please type in a new amount: ");
                        investAmount = console.nextDouble();
                    }

                    System.out.print("Please type in the interest rate annually (1% and 20%): ");   // asks the user to input annual interest rate //
                    double rate = console.nextDouble();
                    while(rate < 1 || rate > 20){                                                   // verifies the user input whether the rate remains in the given range, if not ask again //        
                        System.out.println("The interest amount is invalid, please try again: ");
                        rate = console.nextDouble();
                    }

                    System.out.print("Please type in the duration of the investment: ");   // asks the user to input duration of the investment //
                    int numberOfWeeks = console.nextInt();
                    while(numberOfWeeks <= 0){                                             // verifies the user input by checking the number whether it is less than or equal to zero, if so ask again //
                        System.out.println("The duration amount is invalid, please try again: ");
                        numberOfWeeks = console.nextInt();
                    }

                    client.savingAccount[noAccounts].setAmount(investAmount);                                        // sets the input into the invest amount variable //
                    client.savingAccount[noAccounts].setRate(rate);                                                  // sets the input into the rate variable //   
                    client.savingAccount[noAccounts].setWeeks(numberOfWeeks);                                        // sets the input of number of weeks variable//
                    noAccounts++;
                }
                else{
                    System.out.println("It is not possible to add another account");
                }
            }
            int counter = 0;
            for (int n = 0; n < noClients; n++){
                if(!c[n].getName().equals(accName)){
                    counter++;
                }
            }
            if(counter == noClients){
                System.out.println("Could not find the client");   
            }
        }
    }

    public void displayAccount(String displayaccName)                                                   //method to display account from array//
    {
        for (int i = 0; i < noClients; i++){
            if(c[i].getName().equals(displayaccName)){
                int accountNumber;
                System.out.println("Please type in the account number (1) or (2): ");
                accountNumber = console.nextInt();
                if(noAccounts > 0 && noAccounts <= 2){
                    if(accountNumber == 1){
                        System.out.println("Weeks       Balance");                             // prints out the table //
                        System.out.println("--------------------");
                        for (int weeks = 4; weeks <= client.savingAccount[i].getWeeks(); weeks = weeks + 4){   // calculates the monthly investment with interest //
                            if (weeks < 10){                                                   // condition to make proper formatting of the table //
                                System.out.print(weeks);
                                System.out.print("          ");
                                client.savingAccount[i].calcInvestmentMonthly(client.savingAccount[i]);
                                System.out.printf("$%.2f \n" , client.savingAccount[i].getInvestmentMonthly());
                            } else if (weeks >= 10){                                               // condition to make proper formatting of the table //
                                System.out.print(weeks);
                                System.out.print("         ");
                                client.savingAccount[i].calcInvestmentMonthly(client.savingAccount[i]);
                                System.out.printf("$%.2f \n" , client.savingAccount[i].getInvestmentMonthly());
                            }
                        }    
                        if (client.savingAccount[i].getWeeks() % 4 != 0){                                      // calculates the remaining weeks after each month //
                            if (client.savingAccount[i].getWeeks() < 10){                                      // condition to make proper formatting of the table //
                                System.out.print(client.savingAccount[i].getWeeks());
                                System.out.print("          ");
                                client.savingAccount[i].calcInvestmentNoInterest(client.savingAccount[i]);
                                System.out.printf("$%.2f \n" , client.savingAccount[i].getInvestmentNoInterest());
                            }   else if (client.savingAccount[i].getWeeks() > 10){                                 // condition to make proper formatting of the table //
                                System.out.print(client.savingAccount[i].getWeeks());
                                System.out.print("         ");
                                client.savingAccount[i].calcInvestmentNoInterest(client.savingAccount[i]);
                                System.out.printf("$%.2f \n" , client.savingAccount[i].getInvestmentNoInterest());
                            }
                        }
                    }

                    if(accountNumber == 2){
                        System.out.println("Weeks       Balance");                             // prints out the table //
                        System.out.println("--------------------");
                        for (int weeks = 4; weeks <= client.savingAccount[i].getWeeks(); weeks = weeks + 4){   // calculates the monthly investment with interest //
                            if (weeks < 10){                                                   // condition to make proper formatting of the table //
                                System.out.print(weeks);
                                System.out.print("          ");
                                client.savingAccount[i].calcInvestmentMonthly(account);
                                System.out.printf("$%.2f \n" , client.savingAccount[i+1].getInvestmentMonthly());
                            } else if (weeks >= 10){                                               // condition to make proper formatting of the table //
                                System.out.print(weeks);
                                System.out.print("         ");
                                client.savingAccount[i].calcInvestmentMonthly(account);
                                System.out.printf("$%.2f \n" , client.savingAccount[i].getInvestmentMonthly());
                            }
                        }    
                        if (client.savingAccount[i].getWeeks() % 4 != 0){                                      // calculates the remaining weeks after each month //
                            if (client.savingAccount[i].getWeeks() < 10){                                      // condition to make proper formatting of the table //
                                System.out.print(client.savingAccount[i].getWeeks());
                                System.out.print("          ");
                                client.savingAccount[i].calcInvestmentNoInterest(account);
                                System.out.printf("$%.2f \n" , client.savingAccount[i].getInvestmentNoInterest());
                            }   else if (client.savingAccount[i].getWeeks() > 10){                                 // condition to make proper formatting of the table //
                                System.out.print(client.savingAccount[i].getWeeks());
                                System.out.print("         ");
                                client.savingAccount[i].calcInvestmentNoInterest(account);
                                System.out.printf("$%.2f \n" , client.savingAccount[i].getInvestmentNoInterest());
                            }
                        }
                    }
                }
                else {
                    System.out.println("The account does not exist");
                }
            }
            int counter = 0;
            for (int n = 0; n < noAccounts; n++){
                if(!c[n].getName().equals(displayaccName)){
                    counter++;
                }
            }
            if(counter == noAccounts){
                System.out.println("Could not find the Client");   
            }
        }

    }

    public void displayAllClients()                                                                 //method to display all clients from array//
    {
        for (int i = 0; i < noClients; i++){
            System.out.println("name: " + c[i].getName());
            System.out.printf("Salary per year : $%.2f \n", c[i].getSalary());
            System.out.printf("Salary per week : $%.2f \n", c[i].getSalary() / 52);
            System.out.println("Residence Status : "+c[i].getResidenceStatus());
            if (c[i].getResidenceStatus()){
                c[i].calcTaxYearlyResident(c[i].getSalary());
                c[i].calcMedicare(c[i].getSalary());
                c[i].calcNetSalaryResident(c[i].getSalary());
                System.out.println(" ");
                System.out.println("Net Salary");
                System.out.printf("Per Year: $%.2f \n" , c[i].getNetSalary());
                System.out.printf("Per Week: $%.2f \n" , (c[i].getNetSalary() / 52));
                System.out.println(" ");
                System.out.println("Tax");
                System.out.printf("Per Year: $%.2f \n" , c[i].getTaxYearly());
                System.out.printf("Per Week: $%.2f \n" , (c[i].getTaxYearly() / 52));
                System.out.println(" ");
                System.out.printf("Medicare per year: $%.2f \n" , c[i].getMedicare());
                System.out.println(" ");
            }else {
                c[i].calcTaxYearlyNonresident(c[i].getSalary());
                c[i].calcNetSalaryNonResident(c[i].getSalary());
                System.out.println(" ");
                System.out.println("Net Salary");
                System.out.printf("Per Year: $%.2f \n" , c[i].getNetSalary());
                System.out.printf("Per Week: $%.2f \n" , (c[i].getNetSalary() / 52));
                System.out.println(" ");
                System.out.println("Tax");
                System.out.printf("Per Year: $%.2f \n" , c[i].getTaxYearly());
                System.out.printf("Per Week: $%.2f \n" , (c[i].getTaxYearly() / 52));
                System.out.println(" ");
            }
            System.out.println("Weekly Expenses: " + c[i].getWeeklyExpenses());
            if (noAccounts > 0 && noAccounts <=2){
                System.out.println("Weeks       Balance");                             // prints out the table //
                System.out.println("--------------------");
                for (int weeks = 4; weeks <= client.savingAccount[i].getWeeks(); weeks = weeks + 4){   // calculates the monthly investment with interest //
                    if (weeks < 10){                                                   // condition to make proper formatting of the table //
                        System.out.print(weeks);
                        System.out.print("          ");
                        client.savingAccount[i].calcInvestmentMonthly(client.savingAccount[i]);
                        System.out.printf("$%.2f \n" , client.savingAccount[i].getInvestmentMonthly());
                    } else if (weeks >= 10){                                               // condition to make proper formatting of the table //
                        System.out.print(weeks);
                        System.out.print("         ");
                        client.savingAccount[i].calcInvestmentMonthly(client.savingAccount[i]);
                        System.out.printf("$%.2f \n" , client.savingAccount[i].getInvestmentMonthly());
                    }
                }    
                if (client.savingAccount[i].getWeeks() % 4 != 0){                                      // calculates the remaining weeks after each month //
                    if (client.savingAccount[i].getWeeks() < 10){                                      // condition to make proper formatting of the table //
                        System.out.print(client.savingAccount[i].getWeeks());
                        System.out.print("          ");
                        client.savingAccount[i].calcInvestmentNoInterest(client.savingAccount[i]);
                        System.out.printf("$%.2f \n" , client.savingAccount[i].getInvestmentNoInterest());
                    }   else if (client.savingAccount[i].getWeeks() > 10){                                 // condition to make proper formatting of the table //
                        System.out.print(client.savingAccount[i].getWeeks());
                        System.out.print("         ");
                        client.savingAccount[i].calcInvestmentNoInterest(client.savingAccount[i]);
                        System.out.printf("$%.2f \n" , client.savingAccount[i].getInvestmentNoInterest());
                    }
                }
            }
            else{
                System.out.println("There are no Accounts");
            }
        }
    }

    public void deleteAccount()                                                             //method to delete account from array//
    {
        System.out.print("Please enter the client's name you wish to delete = ");
        String nameDelete = console.next();
        int counter = 0;
        for (int n = 0; n < noClients; n++){
            if(!c[n].getName().equals(nameDelete)){
                counter++;
            }
        }
        if(counter == noClients){
            System.out.println("Could not find the client");   
        }

        for(int i = 0; i < noAccounts; i++){
            if (c[i].getName().equals(nameDelete)){
                for (int j = 0; j < noClients - 1; j++){
                    c[i] = c[i+1];
                }
                noAccounts--;
                System.out.println("The account is deleted");
            } 
        }
    }

    public  void saveFile ()throws IOException {                                                             //method to save display of clients to a text file//

        Scanner console = new Scanner(System.in);
        System.out.println("\n\n File name: ");
        String fileName = console.next();

        PrintWriter outFile = new PrintWriter(fileName);
        for (int i = 0; i < noClients; i++){
            outFile.println("name: " + c[i].getName());
            outFile.printf("Salary per year : $%.2f \n", c[i].getSalary());
            outFile.printf("Salary per week : $%.2f \n", c[i].getSalary() / 52);
            outFile.println("Residence Status : "+c[i].getResidenceStatus());
            if (c[i].getResidenceStatus()){
                c[i].calcTaxYearlyResident(c[i].getSalary());
                c[i].calcMedicare(c[i].getSalary());
                c[i].calcNetSalaryResident(c[i].getSalary());
                outFile.println(" ");
                outFile.println("Net Salary");
                outFile.printf("Per Year: $%.2f \n" , c[i].getNetSalary());
                outFile.printf("Per Week: $%.2f \n" , (c[i].getNetSalary() / 52));
                outFile.println(" ");
                outFile.println("Tax");
                outFile.printf("Per Year: $%.2f \n" , c[i].getTaxYearly());
                outFile.printf("Per Week: $%.2f \n" , (c[i].getTaxYearly() / 52));
                outFile.println(" ");
                outFile.printf("Medicare per year: $%.2f \n" , c[i].getMedicare());
                outFile.println(" ");
            }else {
                c[i].calcTaxYearlyNonresident(c[i].getSalary());
                c[i].calcNetSalaryNonResident(c[i].getSalary());
                outFile.println(" ");
                outFile.println("Net Salary");
                outFile.printf("Per Year: $%.2f \n" , c[i].getNetSalary());
                outFile.printf("Per Week: $%.2f \n" , (c[i].getNetSalary() / 52));
                outFile.println(" ");
                outFile.println("Tax");
                outFile.printf("Per Year: $%.2f \n" , c[i].getTaxYearly());
                outFile.printf("Per Week: $%.2f \n" , (c[i].getTaxYearly() / 52));
                outFile.println(" ");
            }
            outFile.println("Weekly Expenses: " + c[i].getWeeklyExpenses());
            if (noAccounts > 0 && noAccounts <=2){
                outFile.println("Weeks       Balance");                             // prints out the table //
                outFile.println("--------------------");
                for (int weeks = 4; weeks <= client.savingAccount[i].getWeeks(); weeks = weeks + 4){   // calculates the monthly investment with interest //
                    if (weeks < 10){                                                   // condition to make proper formatting of the table //
                        outFile.print(weeks);
                        outFile.print("          ");
                        client.savingAccount[i].calcInvestmentMonthly(client.savingAccount[i]);
                        outFile.printf("$%.2f \n" , client.savingAccount[i].getInvestmentMonthly());
                    } else if (weeks >= 10){                                               // condition to make proper formatting of the table //
                        outFile.print(weeks);
                        outFile.print("         ");
                        client.savingAccount[i].calcInvestmentMonthly(client.savingAccount[i]);
                        outFile.printf("$%.2f \n" , client.savingAccount[i].getInvestmentMonthly());
                    }
                }    
                if (client.savingAccount[i].getWeeks() % 4 != 0){                                      // calculates the remaining weeks after each month //
                    if (client.savingAccount[i].getWeeks() < 10){                                      // condition to make proper formatting of the table //
                        outFile.print(client.savingAccount[i].getWeeks());
                        outFile.print("          ");
                        client.savingAccount[i].calcInvestmentNoInterest(client.savingAccount[i]);
                        outFile.printf("$%.2f \n" , client.savingAccount[i].getInvestmentNoInterest());
                    }   else if (client.savingAccount[i].getWeeks() > 10){                                 // condition to make proper formatting of the table //
                        outFile.print(client.savingAccount[i].getWeeks());
                        outFile.print("         ");
                        client.savingAccount[i].calcInvestmentNoInterest(client.savingAccount[i]);
                        outFile.printf("$%.2f \n" , client.savingAccount[i].getInvestmentNoInterest());
                    }
                }
            }
            else{
                outFile.println("There are no Accounts");
            } 
        }
        outFile.close();
    }
}

