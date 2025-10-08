// Customer.java
import java.util.*;

public class Customer {
    private String customerId;
    private String firstName;
    private String surname;
    private String address;
    private List<Account> accounts;
    
    // Simple constructor with basic info
    public Customer(String firstName, String surname, String address) {
        this.customerId = generateCustomerId();
        this.firstName = firstName;
        this.surname = surname;
        this.address = address;
        this.accounts = new ArrayList<>();
    }
    
    private String generateCustomerId() {
        return "CUST" + System.currentTimeMillis();
    }
    
    public void addAccount(Account account) {
        if (account != null) {
            accounts.add(account);
        }
    }
    
    public boolean removeAccount(Account account) {
        return accounts.remove(account);
    }
    
    public Account findAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }
    
    public double getTotalBalance() {
        double total = 0.0;
        for (Account account : accounts) {
            total += account.getBalance();
        }
        return total;
    }
    
    // Getters
    public String getCustomerId() { return customerId; }
    public String getFirstName() { return firstName; }
    public String getSurname() { return surname; }
    public String getAddress() { return address; }
    public List<Account> getAccounts() { return new ArrayList<>(accounts); }
    
    @Override
    public String toString() {
        return String.format("Customer[%s]: %s %s - %s", customerId, firstName, surname, address);
    }
}