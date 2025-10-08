// Transaction.java
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String transactionId;
    private String accountNumber;
    private TransactionType transactionType;
    private double amount;
    private double balanceAfter;
    private String description;
    private LocalDateTime timestamp;
    private String referenceNumber;
    
    public Transaction(String transactionId, String accountNumber, TransactionType transactionType, 
                      double amount, double balanceAfter, String description) {
        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.description = description;
        this.timestamp = LocalDateTime.now();
        this.referenceNumber = generateReferenceNumber();
    }
    
    private String generateReferenceNumber() {
        return "REF" + System.currentTimeMillis() + (int)(Math.random() * 1000);
    }
    
    // Getters
    public String getTransactionId() { return transactionId; }
    public String getAccountNumber() { return accountNumber; }
    public TransactionType getTransactionType() { return transactionType; }
    public double getAmount() { return amount; }
    public double getBalanceAfter() { return balanceAfter; }
    public String getDescription() { return description; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getReferenceNumber() { return referenceNumber; }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("%s | %s | %s: %.2f | Balance: %.2f | %s", 
                           timestamp.format(formatter), accountNumber, 
                           transactionType.getDisplayName(), amount, balanceAfter, description);
    }
}