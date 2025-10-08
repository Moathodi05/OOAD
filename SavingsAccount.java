// SavingsAccount.java
public class SavingsAccount extends Account implements InterestBearing {
    private static final double INTEREST_RATE = 0.0005; // 0.05% monthly
    
    public SavingsAccount(String accountNumber, double balance, String branch) {
        super(accountNumber, balance, branch);
    }
    
    @Override
    public double withdraw(double amount) {
        throw new UnsupportedOperationException("Withdrawals not allowed from Savings Account");
    }
    
    @Override
    public double calculateInterest() {
        return balance * INTEREST_RATE;
    }
    
    @Override
    public void applyInterest() {
        double interest = calculateInterest();
        if (interest > 0) {
            balance += interest;
            logTransaction("Interest applied: " + interest + " | New Balance: " + balance);
        }
    }
    
    @Override
    public double getInterestRate() {
        return INTEREST_RATE;
    }
    
    @Override
    public AccountType getAccountType() {
        return AccountType.SAVINGS;
    }
    
    @Override
    public String toString() {
        return String.format("Savings Account[%s]: Balance: BWP%.2f, Branch: %s", 
                           accountNumber, balance, branch);
    }
}