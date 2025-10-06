import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChequeAccount extends Account {
    private Company company;
    
    public ChequeAccount(String accountNumber, Company company, double balance, String branch) {
        super(accountNumber, balance, branch);
        this.company = company;
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
    public AccountType getAccountType() {
        return AccountType.CHEQUE;
    }
    
    public Company getCompany() {
        return company;
    }
    
    @Override
    public String toString() {
        return "Cheque Account: " + accountNumber + ", Company: " + company.getName() + ", Balance: " + balance;
    }
}