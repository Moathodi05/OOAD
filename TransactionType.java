// TransactionType.java
public enum TransactionType {
    DEPOSIT("Deposit"),
    WITHDRAWAL("Withdrawal"),
    INTEREST("Interest Payment"),
    TRANSFER("Transfer"),
    FEE("Service Fee"),
    OPENING("Account Opening");
    
    private final String displayName;
    
    TransactionType(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}