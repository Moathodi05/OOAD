// BankingSystem.java
import java.util.*;

public class BankingSystem {
    private static Bank bank;
    private static Scanner scanner;
    
    public static void main(String[] args) {
        initializeSystem();
        runBankingSystem();
    }
    
    private static void initializeSystem() {
        bank = new Bank("Botswana International Bank");
        scanner = new Scanner(System.in);
        initializeSampleData();
    }
    
    private static void initializeSampleData() {
        // Create sample data for demonstration
        Customer customer1 = new Customer("John", "Doe", "123 Main St, Gaborone");
        Customer customer2 = new Customer("Mary", "Smith", "456 Broad St, Francistown");
        
        bank.addCustomer(customer1);
        bank.addCustomer(customer2);
        
        // Create companies for cheque accounts
        Company company1 = new Company("Tech Solutions Ltd", "789 Innovation Drive, Gaborone");
        Company company2 = new Company("Global Corp", "321 Business Park, Francistown");
        
        // Open sample accounts
        try {
            Account savingsAcc = bank.openAccount(customer1, AccountType.SAVINGS, 1000.0);
            System.out.println("Opened: " + savingsAcc);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        try {
            Account investmentAcc = bank.openAccount(customer1, AccountType.INVESTMENT, 600.0);
            System.out.println("Opened: " + investmentAcc);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        try {
            Account chequeAcc = bank.openAccount(customer1, AccountType.CHEQUE, company1, 2000.0);
            System.out.println("Opened: " + chequeAcc);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void runBankingSystem() {
        System.out.println("=== Botswana International Banking System ===");
        System.out.println("Bank: " + bank.getName());
        System.out.println("Total Customers: " + bank.getCustomers().size());
        System.out.println("Total Accounts: " + bank.getAccounts().size());
        
        // Demonstrate system functionality
        demonstrateSystemFeatures();
    }
    
    private static void demonstrateSystemFeatures() {
        System.out.println("\n=== System Demonstration ===");
        
        // Show all customers
        System.out.println("\n--- Registered Customers ---");
        for (Customer customer : bank.getCustomers()) {
            System.out.println(customer);
        }
        
        // Show all accounts
        System.out.println("\n--- All Accounts ---");
        for (Account account : bank.getAccounts()) {
            System.out.println(account);
        }
        
        // Test transactions
        testTransactions();
    }
    
    private static void testTransactions() {
        System.out.println("\n=== Transaction Testing ===");
        
        // Get first customer and their accounts
        if (!bank.getCustomers().isEmpty()) {
            Customer customer = bank.getCustomers().get(0);
            if (!customer.getAccounts().isEmpty()) {
                
                for (Account account : customer.getAccounts()) {
                    System.out.println("\nTesting " + account.getAccountType() + " Account:");
                    
                    // Test deposit
                    double newBalance = account.deposit(500.0);
                    System.out.println("  Deposit successful. New balance: " + newBalance);
                    
                    // Test withdrawal if applicable
                    if (!(account instanceof SavingsAccount)) {
                        try {
                            newBalance = account.withdraw(200.0);
                            System.out.println("  Withdrawal successful. New balance: " + newBalance);
                        } catch (Exception e) {
                            System.out.println("  Withdrawal failed: " + e.getMessage());
                        }
                    } else {
                        System.out.println("  Withdrawals not allowed for Savings accounts");
                    }
                    
                    // Show transaction history
                    System.out.println("  Transaction History:");
                    for (String transaction : account.getTransactions()) {
                        System.out.println("    " + transaction);
                    }
                }
            }
        }
        
        // Test interest payment for all accounts
        System.out.println("\n--- Applying Interest to All Accounts ---");
        bank.payInterest();
        
        // Show final balances
        System.out.println("\n--- Final Account Balances ---");
        for (Customer customer : bank.getCustomers()) {
            System.out.println("\nCustomer: " + customer.getFirstName() + " " + customer.getSurname());
            for (Account account : customer.getAccounts()) {
                System.out.println("  " + account.getAccountType() + ": BWP" + account.getBalance());
            }
        }
    }
}