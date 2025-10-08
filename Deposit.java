import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deposit {
    private String depositId;
    private String accountNumber;
    private double amount;
    private LocalDateTime depositDate;
    private String description;
    private String status; // "COMPLETED", "PENDING", "FAILED"
    
    private static int depositCounter = 1;
    
    // Constructor
    public Deposit(String accountNumber, double amount, String description) {
        this.depositId = generateDepositId();
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.depositDate = LocalDateTime.now();
        this.description = description;
        this.status = "COMPLETED";
    }
    
    // Generate unique deposit ID
    private String generateDepositId() {
        return "DEP" + String.format("%06d", depositCounter++);
    }
    
    // Getters
    public String getDepositId() { return depositId; }
    public String getAccountNumber() { return accountNumber; }
    public double getAmount() { return amount; }
    public LocalDateTime getDepositDate() { return depositDate; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
    
    // Setters
    public void setStatus(String status) { this.status = status; }
    public void setDescription(String description) { this.description = description; }
    
    // Method to process deposit
    public boolean processDeposit(Account account) {
        try {
            if (amount <= 0) {
                this.status = "FAILED";
                return false;
            }
            
            account.deposit(amount);
            this.status = "COMPLETED";
            return true;
            
        } catch (Exception e) {
            this.status = "FAILED";
            return false;
        }
    }
    
    // Method to get formatted deposit date
    public String getFormattedDate() {
        return depositDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
    
    @Override
    public String toString() {
        return String.format("Deposit[ID: %s, Account: %s, Amount: %.2f, Date: %s, Status: %s]",
                depositId, accountNumber, amount, getFormattedDate(), status);
    }
    
    // Static method to create deposit and process immediately
    public static Deposit createAndProcessDeposit(Account account, double amount, String description) {
        Deposit deposit = new Deposit(account.getAccountNumber(), amount, description);
        deposit.processDeposit(account);
        return deposit;
    }
}