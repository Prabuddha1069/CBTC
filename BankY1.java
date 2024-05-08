import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Random;

class Account {
    private String accountNumber;
    private String accountHolderName;
    private String address;
    private String adharNumber;
    private String phoneNumber;
    private double balance;

    public Account(String accountHolderName, String address, String adharNumber, String phoneNumber) {
        this.accountHolderName = accountHolderName;
        this.address = address;
        this.adharNumber = adharNumber;
        this.phoneNumber = phoneNumber;
        this.balance = 0;
        generateAccountNumber(); // Generate account number automatically
    }

    private void generateAccountNumber() {
        Random random = new Random();
        int accountNum = 100000 + random.nextInt(900000); // Generate a 6-digit random number
        this.accountNumber = String.valueOf(accountNum);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getAddress() {
        return address;
    }

    public String getAdharNumber() {
        return adharNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public boolean transfer(Account recipient, double amount) {
        if (withdraw(amount)) {
            recipient.deposit(amount);
            return true;
        }
        return false;
    }
}

public class BankY1 {
    private static Map<String, Account> accounts = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to BankY!");

        while (true) {
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    transfer();
                    break;
                case 5:
                    System.out.println("Exiting BankY. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void createAccount() {
        System.out.println("Enter your name:");
        String name = scanner.nextLine();
        System.out.println("Enter your address:");
        String address = scanner.nextLine();
        System.out.println("Enter your Aadhar number:");
        String adharNumber = scanner.nextLine();
        System.out.println("Enter your phone number:");
        String phoneNumber = scanner.nextLine();

        Account account = new Account(name, address, adharNumber, phoneNumber);
        accounts.put(account.getAccountNumber(), account);
        System.out.println("Account created successfully. Your account number is: " + account.getAccountNumber());
    }

    public static void deposit() {
        System.out.println("Enter your account number:");
        String accountNumber = scanner.nextLine();
        Account account = accounts.get(accountNumber);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }
        System.out.println("Enter amount to deposit:");
        double amount = scanner.nextDouble();
        account.deposit(amount);
        System.out.println("Deposit successful. New balance: $" + account.getBalance());
    }

    public static void withdraw() {
        System.out.println("Enter your account number:");
        String accountNumber = scanner.nextLine();
        Account account = accounts.get(accountNumber);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }
        System.out.println("Enter amount to withdraw:");
        double amount = scanner.nextDouble();
        if (account.withdraw(amount)) {
            System.out.println("Withdrawal successful. New balance: $" + account.getBalance());
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public static void transfer() {
        System.out.println("Enter sender account number:");
        String senderAccountNumber = scanner.nextLine();
        Account senderAccount = accounts.get(senderAccountNumber);
        if (senderAccount == null) {
            System.out.println("Sender account not found.");
            return;
        }
        System.out.println("Enter recipient account number:");
        String recipientAccountNumber = scanner.nextLine();
        Account recipientAccount = accounts.get(recipientAccountNumber);
        if (recipientAccount == null) {
            System.out.println("Recipient account not found.");
            return;
        }
        System.out.println("Enter amount to transfer:");
        double amount = scanner.nextDouble();
        if (senderAccount.transfer(recipientAccount, amount)) {
            System.out.println("Transfer successful.");
        } else {
            System.out.println("Transfer failed. Insufficient funds.");
        }
    }
}
