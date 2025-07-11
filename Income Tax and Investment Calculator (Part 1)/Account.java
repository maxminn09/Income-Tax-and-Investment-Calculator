/*Author: Minn Thet Tin
*Student No: C3394937
*Date: 17/04/2022
*Description: This file contains all the methods for setting the rate of investment, the amount for investment, the number of weeks for investment and calculating the invested amount
*             after a period of time
*/

// You can add extra methods if you think it is necessary



public class Account
{
    double rate;
    int numberOfWeeks;
    double amount;
 
    double investedAmount; //added my own variable
    
   // constructor
    public Account()
    {
        
    }
     
    
    public void setRate(double inputRate)           // method definition of setting the input rate  //
    {
        rate = inputRate;
    }

    
    public double getRate()                         // method definition of returning the value of the rate //
    {
        return(rate);
    }
    
    
    public void setAmount(double inputAmount)       // method definition of setting the investment amount //
    {
        amount = inputAmount;
    }

    
    public double getAmount()                       // method definition of returning the value of the investment amount //
    {
        return(amount);
    }
    
    
    public void setWeeks(int inputWeek)             // method definition of setting the investment duration in weeks //
    {
        numberOfWeeks = inputWeek;
    }

    
    public int getWeeks()                           // method definition of returning the value of the duration in weeks //
    {
        return(numberOfWeeks);
    }

    
    public void calcInvestmentMonthly(Account savingAccount){   // method definition of calculating the monthly investment // 
        double weeklyInterest = Math.round(((savingAccount.getRate() / 12) + 100));
        double weeklyInterestAfterRound = weeklyInterest / 100;
        investedAmount = (investedAmount + savingAccount.getAmount() * 4) * weeklyInterestAfterRound;
    }
    
    
    public double getInvestmentMonthly()                        // method definition of returning the value of the monthly investment //
    {   
        return (investedAmount);
    }
    
    
    public void calcInvestmentNoInterest(Account savingAccount) // method definition of calculating the remaining weekly investment if possible //
    {    
        int remainderWeek = savingAccount.getWeeks() % 4;  
        investedAmount = (investedAmount + savingAccount.getAmount() * remainderWeek);
    }
    
    
    public double getInvestmentNoInterest()                         // method definition of returning the value of remaining the weekly investment //
    {   
        return (investedAmount);
    }
}
