// Enum for Account Types
public enum AccountType {
    SAVINGS("Savings"),
    INVESTMENT("Investment"),
    CHEQUE("Cheque");
    
    private final String displayName;
    
    AccountType(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}