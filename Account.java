import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Account {
    protected String accountNumber;
    protected double balance;
    protected String branch;
    protected List<String> transactions;
    protected List<Deposit> depositHistory; // New: Track deposit objects
    
    public Account(String accountNumber, double balance, String branch) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.branch = branch;
        this.transactions = new ArrayList<>();
        this.depositHistory = new ArrayList<>(); // Initialize deposit history
    }
    
    // Enhanced deposit method that creates Deposit objects
    public Deposit deposit(double amount) {
        return deposit(amount, "Cash Deposit");
    }
    
    // Overloaded deposit method with description
    public Deposit deposit(double amount, String description) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        
        // Create deposit object
        Deposit deposit = new Deposit(accountNumber, amount, description);
        
        // Process the deposit
        if (deposit.processDeposit(this)) {
            balance += amount;
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            transactions.add(timestamp + ": Deposited " + amount + " - " + description);
            depositHistory.add(deposit);
            return deposit;
        } else {
            throw new RuntimeException("Deposit processing failed");
        }
    }
    
    public abstract double withdraw(double amount);
    
    // Getters
    public String getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }
    public String getBranch() { return branch; }
    public List<String> getTransactions() { return transactions; }
    public List<Deposit> getDepositHistory() { return depositHistory; } // New getter
    
    public abstract AccountType getAccountType();
    
    @Override
    public abstract String toString();
}