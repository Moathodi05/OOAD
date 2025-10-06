import java.util.*;

public class Bank {
    private String name;
    private List<Customer> customers;
    private List<Account> accounts;
    private int accountCounter;
    
    public Bank(String name) {
        this.name = name;
        this.customers = new ArrayList<>();
        this.accounts = new ArrayList<>();
        this.accountCounter = 1000;
    }
    
    private String generateAccountNumber() {
        accountCounter++;
        return "ACC" + accountCounter;
    }
    
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }
    
    public Account openAccount(Customer customer, AccountType accountType, Object... params) {
        String accountNumber = generateAccountNumber();
        Account account;
        
        switch (accountType) {
            case SAVINGS:
                double savingsBalance = params.length > 0 && params[0] instanceof Double ? (Double) params[0] : 0.0;
                String savingsBranch = params.length > 1 && params[1] instanceof String ? (String) params[1] : "Main";
                account = new SavingsAccount(accountNumber, savingsBalance, savingsBranch);
                break;
                
            case INVESTMENT:
                double investmentBalance = params.length > 0 && params[0] instanceof Double ? (Double) params[0] : 500.0;
                String investmentBranch = params.length > 1 && params[1] instanceof String ? (String) params[1] : "Main";
                account = new InvestmentAccount(accountNumber, investmentBalance, investmentBranch);
                break;
                
            case CHEQUE:
                if (params.length < 1 || !(params[0] instanceof Company)) {
                    throw new IllegalArgumentException("Company information required for Cheque account");
                }
                Company company = (Company) params[0];
                double chequeBalance = params.length > 1 && params[1] instanceof Double ? (Double) params[1] : 0.0;
                String chequeBranch = params.length > 2 && params[2] instanceof String ? (String) params[2] : "Main";
                account = new ChequeAccount(accountNumber, company, chequeBalance, chequeBranch);
                break;
                
            default:
                throw new IllegalArgumentException("Invalid account type");
        }
        
        customer.addAccount(account);
        accounts.add(account);
        return account;
    }
    
    public void payInterest() {
        for (Account account : accounts) {
            if (account instanceof InterestBearing) {
                ((InterestBearing) account).calculateInterest();
            }
        }
    }
    
    // Getters
    public String getName() { return name; }
    public List<Customer> getCustomers() { return customers; }
    public List<Account> getAccounts() { return accounts; }
}