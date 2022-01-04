package in.ibm.com.Junit5;


@SuppressWarnings("serial")
public class NotEnoughFundsException extends RuntimeException 
{

    public  NotEnoughFundsException(Integer amount, Integer balance) 
    {
        System.out.println("Attempted to withdraw " + amount + " with a balance of " + balance);
    }

}