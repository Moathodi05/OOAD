import java.util.*;

public class BankingSystem {
    public static void main(String[] args) {
        // Create a bank
        Bank bank = new Bank("Botswana Bank");
        
        // Create a customer
        Customer customer1 = new Customer("John", "Doe", "123 Main St, Gaborone");
        bank.addCustomer(customer1);
        
        // Create a company for cheque account
        Company company = new Company("Tech Corp", "456 Industrial Ave, Gaborone");
        
        // Open different types of accounts for the customer
        try {
            Account savingsAcc = bank.openAccount(customer1, AccountType.SAVINGS, 1000.0);
            System.out.println("Opened " + savingsAcc);
        } catch (Exception e) {
            System.out.println("Error opening savings account: " + e.getMessage());
        }
        
        try {
            Account investmentAcc = bank.openAccount(customer1, AccountType.INVESTMENT, 600.0);
            System.out.println("Opened " + investmentAcc);
        } catch (Exception e) {
            System.out.println("Error opening investment account: " + e.getMessage());
        }
        
        try {
            Account chequeAcc = bank.openAccount(customer1, AccountType.CHEQUE, company, 2000.0);
            System.out.println("Opened " + chequeAcc);
        } catch (Exception e) {
            System.out.println("Error opening cheque account: " + e.getMessage());
        }
        
        // Demonstrate enhanced deposits with Deposit class
        System.out.println("\n--- Enhanced Deposit System ---");
        List<Account> accounts = customer1.getAccounts();
        Account savings = null, investment = null, cheque = null;
        
        for (Account acc : accounts) {
            if (acc.getAccountType() == AccountType.SAVINGS) savings = acc;
            else if (acc.getAccountType() == AccountType.INVESTMENT) investment = acc;
            else if (acc.getAccountType() == AccountType.CHEQUE) cheque = acc;
        }
        
        // Create deposits using Deposit class
        if (savings != null) {
            Deposit savingsDeposit1 = savings.deposit(500.0, "Monthly Savings");
            Deposit savingsDeposit2 = savings.deposit(200.0, "Bonus Deposit");
            System.out.println("Created deposit: " + savingsDeposit1);
            System.out.println("Created deposit: " + savingsDeposit2);
        }
        
        if (investment != null) {
            Deposit investmentDeposit = investment.deposit(300.0, "Investment Top-up");
            System.out.println("Created deposit: " + investmentDeposit);
        }
        
        if (cheque != null) {
            Deposit chequeDeposit1 = cheque.deposit(1500.0, "Salary Deposit");
            Deposit chequeDeposit2 = cheque.deposit(800.0, "Freelance Payment");
            System.out.println("Created deposit: " + chequeDeposit1);
            System.out.println("Created deposit: " + chequeDeposit2);
        }
        
        System.out.println("\n--- Account Balances ---");
        System.out.println("Savings account balance: " + (savings != null ? savings.getBalance() : "N/A"));
        System.out.println("Investment account balance: " + (investment != null ? investment.getBalance() : "N/A"));
        System.out.println("Cheque account balance: " + (cheque != null ? cheque.getBalance() : "N/A"));
        
        // Demonstrate deposit history
        System.out.println("\n--- Deposit History ---");
        for (Account account : customer1.getAccounts()) {
            System.out.println("\n" + account.getAccountType() + " Account Deposit History:");
            List<Deposit> deposits = account.getDepositHistory();
            if (deposits.isEmpty()) {
                System.out.println("  No deposits found");
            } else {
                for (Deposit deposit : deposits) {
                    System.out.println("  " + deposit);
                }
            }
        }
        
        // Demonstrate withdrawals
        System.out.println("\n--- Making withdrawals ---");
        try {
            if (investment != null) {
                investment.withdraw(200.0);
                System.out.println("Withdrew 200 from investment account. New balance: " + investment.getBalance());
            }
        } catch (Exception e) {
            System.out.println("Withdrawal error: " + e.getMessage());
        }
        
        try {
            if (cheque != null) {
                cheque.withdraw(1000.0);
                System.out.println("Withdrew 1000 from cheque account. New balance: " + cheque.getBalance());
            }
        } catch (Exception e) {
            System.out.println("Withdrawal error: " + e.getMessage());
        }
        
        // Try to withdraw from savings (should fail)
        try {
            if (savings != null) {
                savings.withdraw(100.0);
            }
        } catch (Exception e) {
            System.out.println("Could not withdraw from savings: " + e.getMessage());
        }
        
        // Pay interest to interest-bearing accounts
        System.out.println("\n--- Paying interest ---");
        bank.payInterest();
        
        System.out.println("Savings account balance after interest: " + (savings != null ? savings.getBalance() : "N/A"));
        System.out.println("Investment account balance after interest: " + (investment != null ? investment.getBalance() : "N/A"));
        System.out.println("Cheque account balance: " + (cheque != null ? cheque.getBalance() : "N/A") + " (no interest)");
        
        // Show customer account summary
        System.out.println("\n--- Customer Summary ---");
        System.out.println(customer1);
        for (Account account : customer1.getAccounts()) {
            System.out.println("  - " + account);
        }
        
        // Show transaction history
        System.out.println("\n--- Transaction History ---");
        for (Account account : customer1.getAccounts()) {
            System.out.println("\n" + account.getAccountType() + " Account Transactions:");
            for (String transaction : account.getTransactions()) {
                System.out.println("  " + transaction);
            }
        }
    }
}