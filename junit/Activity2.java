package in.ibm.com.Junit5;

import org.junit.jupiter.api.Test;
//import in.ibm.com.Junit5.BankAccount;
//import in.ibm.com.Junit5.NotEnoughFundsException;
import static org.junit.jupiter.api.Assertions.*;

class Activity2 {

    @Test
    void notEnoughFunds() {
        
        BankAccount account = new BankAccount(9);

        
        assertThrows(NotEnoughFundsException.class, () -> account.withdraw(10),"Balance must be greater than amount of withdrawal");
    }

    @Test
    void enoughFunds() 
    {
        
        BankAccount account = new BankAccount(100);
    
       
        assertDoesNotThrow(() -> account.withdraw(99));
    }
}