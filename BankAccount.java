//Atm Interface-Codesoft java intership Task1
import java.util.Scanner;
public class BankAccount {



        private double balance;
        private String lastTransaction;

        public BankAccount(double initialBalance) {
            this.balance = initialBalance;
            this.lastTransaction = "Account opened with Initial deposit of Rs" + initialBalance;
        }

        public double getBalance() {
            return balance;


        }

        public String getLastTransaction() {
            return lastTransaction;
        }

        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                lastTransaction = "Deposited Rs" + amount;
                System.out.println("Deposit of Rs" + amount + " successful. Current balance is: Rs" + balance);
            } else {
                System.out.println("Invalid deposit amount.");
            }
        }

        public void withdraw(double amount) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                lastTransaction = "Withdrew Rs" + amount;
                System.out.println("Withdrawal of Rs" + amount + " successful. Current balance: Rs" + balance);
            } else {
                System.out.println("Invalid withdrawal amount or insufficient balance.");
            }
        }
    }

    class ATM {
        private BankAccount bankAccount;

        public ATM(BankAccount bankAccount) {
            this.bankAccount = bankAccount;
        }

        public void displayMenu() {
            System.out.println("\nOptions Hub:");
            System.out.println("1. Check your current Balance");
            System.out.println("2.Make a Deposit");
            System.out.println("3.Cash Withdrawal");
            System.out.println("4.View your Last Transaction");
            System.out.println("5. Exit");
        }

        public void performTransaction(int choice, Scanner scanner) {
            switch (choice) {
                case 1:
                    System.out.println("Your Current balance is: Rs" + bankAccount.getBalance());
                    break;
                case 2:
                    System.out.print("Enter deposit amount: Rs");
                    double depositAmount = scanner.nextDouble();
                    bankAccount.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: Rs");
                    double withdrawalAmount = scanner.nextDouble();
                    bankAccount.withdraw(withdrawalAmount);
                    break;
                case 4:
                    System.out.println("Last transaction: " + bankAccount.getLastTransaction());
                    break;
                case 5:
                    System.out.println("Taking Exit, Thank you!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

     class CashMachine {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the base Amount: Rs");
            double initialBalance = scanner.nextDouble();
            BankAccount bankAccount = new BankAccount(initialBalance);
            ATM atm = new ATM(bankAccount);
            while (true) {
                atm.displayMenu();
                System.out.print("Select an option: ");
                int choice = scanner.nextInt();
                atm.performTransaction(choice, scanner);
            }
        }
    }
