import java.util.Scanner;

public class BankMachine {
    private double accountBalance;

    public BankMachine(double initialBalance) {
        this.accountBalance = initialBalance;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void addFunds(double amount) {
        if (amount > 0) {
            accountBalance += amount;
            System.out.println("Successfully deposited: $" + amount);
        } else {
            System.out.println("Amount to deposit must be positive.");
        }
    }

    public void withdrawFunds(double amount) {
        if (amount > 0 && amount <= accountBalance) {
            accountBalance -= amount;
            System.out.println("Successfully withdrawn: $" + amount);
        } else if (amount > accountBalance) {
            System.out.println("Error: Insufficient funds.");
        } else {
            System.out.println("Amount to withdraw must be positive.");
        }
    }

    public void showMenu() {
        System.out.println("\nBank Machine Menu:");
        System.out.println("1. Check Account Balance");
        System.out.println("2. Deposit Funds");
        System.out.println("3. Withdraw Funds");
        System.out.println("4. Exit");
        System.out.print("Select an option: ");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankMachine bankMachine = new BankMachine(1000); 

        while (true) {
            bankMachine.showMenu();
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Current Account Balance: $" + bankMachine.getAccountBalance());
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    bankMachine.addFunds(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawalAmount = scanner.nextDouble();
                    bankMachine.withdrawFunds(withdrawalAmount);
                    break;
                case 4:
                    System.out.println("Thank you for using the Bank Machine. Goodbye!");
                    scanner.close();
                    return;  //  return to exit
                default:
                    System.out.println("Invalid option selected. Please try again.");
            }

           
            System.out.print("\nWould you like to perform another transaction? (yes/no): ");
            String anotherTransaction = scanner.next();
            if (!anotherTransaction.equalsIgnoreCase("yes")) {
                System.out.println("Thank you for using the Bank Machine. Goodbye!");
                scanner.close();
                break;
            }
        }
    }
}
