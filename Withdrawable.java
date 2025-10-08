// Withdrawable.java
public interface Withdrawable {
    double withdraw(double amount);
    double getAvailableBalance();
    boolean canWithdraw(double amount);
}