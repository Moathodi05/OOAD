// InvestmentAccount.java
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InvestmentAccount extends Account implements InterestBearing {
    private static final double MIN_BALANCE = 500.0;
    private static final double INTEREST_RATE = 0.05; // 5% monthly
    
    public InvestmentAccount(String accountNumber, double balance, String branch) {
        super(accountNumber, balance, branch);
        if (balance < MIN_BALANCE) {
            throw new IllegalArgumentException(
                "Investment account requires minimum deposit of BWP" + MIN_BALANCE);
        }
    }
    
    @Override
    public double withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        if (balance - amount < MIN_BALANCE) {
            throw new IllegalArgumentException(
                "Cannot withdraw below minimum balance of BWP" + MIN_BALANCE);
        }
        
        balance -= amount;
        logTransaction("Withdrew: " + amount + " | New Balance: " + balance);
        return balance;
    }
    
    @Override
    public double calculateInterest() {
        return balance * INTEREST_RATE;
    }
    
    @Override
    public void applyInterest() {
        double interest = calculateInterest();
        if (interest > 0) {
            balance += interest;
            logTransaction("Interest applied: " + interest + " | New Balance: " + balance);
        }
    }
    
    @Override
    public double getInterestRate() {
        return INTEREST_RATE;
    }
    
    @Override
    public AccountType getAccountType() {
        return AccountType.INVESTMENT;
    }
    
    @Override
    public String toString() {
        return String.format("Investment Account[%s]: Balance: BWP%.2f, Branch: %s", 
                           accountNumber, balance, branch);
    }
}