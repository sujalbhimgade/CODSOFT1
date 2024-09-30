import java.io.IOException;
import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount() {
        this.balance = 0; 
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited: Rs" + amount);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrawn: Rs" + amount);
        } else if (amount > balance) {
            System.out.println("Insufficient balance for this withdrawal!");
        } else {
            System.out.println("Invalid withdrawal amount!");
        }
    }

    public double getBalance() {
        return balance;
    }
}

class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void showMenu() {
        Scanner sc = new Scanner(System.in);
        boolean continueOperations = true;

        while (continueOperations) {
            clearConsole();
            System.out.println("\n=== ATM Menu ===");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Current Balance: Rs" + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: Rs");
                    double depositAmount = sc.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: Rs");
                    double withdrawAmount = sc.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.println("Exiting ATM. Thank you!");
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }

            System.out.print("\nDo you want to perform another operation? (yes/no): ");
            String response = sc.next();
            if (!response.equalsIgnoreCase("yes")) {
                continueOperations = false;
                System.out.println("Exiting ATM. Thank you!");
            }
        }
    }

    public void clearConsole() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException e) {
            System.out.println("Error clearing the console.");
        }
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        ATM atm = new ATM(account);
        atm.showMenu();
    }
}
