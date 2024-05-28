import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the ATM");
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String userPin = scanner.nextLine();

        User currentUser = bank.authenticateUser(userId, userPin);

        if (currentUser != null) {
            ATMOperations atmOperations = new ATMOperations(currentUser, bank, scanner);
            atmOperations.start();
        } else {
            System.out.println("Invalid User ID or PIN");
        }

        scanner.close();
    }
}
