// ChequeAccount.java
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
        logTransaction("Withdrew: " + amount + " | New Balance: " + balance);
        return balance;
    }
    
    @Override
    public AccountType getAccountType() {
        return AccountType.CHEQUE;
    }
    
    public Company getCompany() { return company; }
    
    @Override
    public String toString() {
        return String.format("Cheque Account[%s]: Balance: BWP%.2f, Company: %s, Branch: %s", 
                           accountNumber, balance, company.getName(), branch);
    }
}