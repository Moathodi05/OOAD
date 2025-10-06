import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SavingsAccount extends Account implements InterestBearing {
    public SavingsAccount(String accountNumber, double balance, String branch) {
        super(accountNumber, balance, branch);
    }
    
    @Override
    public double withdraw(double amount) {
        throw new UnsupportedOperationException("Withdrawals not allowed from Savings Account");
    }
    
    @Override
    public double calculateInterest() {
        double interest = balance * 0.0005; // 0.05% monthly interest
        balance += interest;
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        transactions.add(timestamp + ": Interest added " + interest);
        return interest;
    }
    
    @Override
    public AccountType getAccountType() {
        return AccountType.SAVINGS;
    }
    
    @Override
    public String toString() {
        return "Savings Account: " + accountNumber + ", Balance: " + balance;
    }
}