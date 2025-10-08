// AccountType.java
public enum AccountType {
    SAVINGS("SAV", "Savings Account", 0.0005), // 0.05% monthly
    INVESTMENT("INV", "Investment Account", 0.05), // 5% monthly
    CHEQUE("CHQ", "Cheque Account", 0.0); // No interest
    
    private final String code;
    private final String description;
    private final double interestRate;
    
    AccountType(String code, String description, double interestRate) {
        this.code = code;
        this.description = description;
        this.interestRate = interestRate;
    }
    
    public String getCode() { return code; }
    public String getDescription() { return description; }
    public double getInterestRate() { return interestRate; }
    public String getDisplayName() { return description; }
}