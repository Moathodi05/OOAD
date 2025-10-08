// Account.java
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Account {
    protected String accountNumber;
    protected double balance;
    protected String branch;
    protected Customer customer;  // Make sure this field exists
    protected List<String> transactionHistory;
    
    public Account(String accountNumber, double balance, String branch) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.branch = branch;
        this.transactionHistory = new ArrayList<>();
        logTransaction("Account opened with initial balance: " + balance);
    }
    
    public double deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
        logTransaction("Deposited: " + amount + " | New Balance: " + balance);
        return balance;
    }
    
    public abstract double withdraw(double amount);
    
    // Add this setter method
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    // Add this getter method if not already present
    public Customer getCustomer() {
        return customer;
    }
    
    protected void logTransaction(String transaction) {
        String timestamp = LocalDateTime.now().format(
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        transactionHistory.add(timestamp + " - " + transaction);
    }
    
    // Getters
    public String getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }
    public String getBranch() { return branch; }
    public List<String> getTransactions() { return new ArrayList<>(transactionHistory); }
    
    public abstract AccountType getAccountType();
    
    @Override
    public abstract String toString();
}