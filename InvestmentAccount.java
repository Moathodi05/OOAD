import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InvestmentAccount extends Account implements InterestBearing {
    private static final double MIN_BALANCE = 500.0;
    
    public InvestmentAccount(String accountNumber, double balance, String branch) {
        super(accountNumber, balance, branch);
        if (balance < MIN_BALANCE) {
            throw new IllegalArgumentException("Investment account requires minimum deposit of BWP500");
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
        balance -= amount;
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        transactions.add(timestamp + ": Withdrew " + amount);
        return balance;
    }
    
    @Override
    public double calculateInterest() {
        double interest = balance * 0.05; // 5% monthly interest
        balance += interest;
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        transactions.add(timestamp + ": Interest added " + interest);
        return interest;
    }
    
    @Override
    public AccountType getAccountType() {
        return AccountType.INVESTMENT;
    }
    
    @Override
    public String toString() {
        return "Investment Account: " + accountNumber + ", Balance: " + balance;
    }
}