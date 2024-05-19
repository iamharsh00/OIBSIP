import java.util.Random;
import java.util.Scanner;

public class GuessingGame {

    private static final int MAX_GUESSES = 6;
    private static final int LOWER_BOUND = 1;
    private static final int UPPER_BOUND = 100;

    public static void main(String[] args) {
        Random random = new Random();
        int computersNumber = random.nextInt(UPPER_BOUND - LOWER_BOUND + 1) + LOWER_BOUND;
        int guessCount = 0;

        System.out.println("I have picked a number between " + LOWER_BOUND + " and " + UPPER_BOUND + ". Try to guess it.");

        Scanner scanner = new Scanner(System.in);

        while (guessCount < MAX_GUESSES) {
            System.out.print("Enter your guess: ");
            int userGuess;
            try {
                userGuess = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Please enter a valid integer.");
                scanner.nextLine(); // Consume the invalid input
                continue;
            }

            guessCount++;

            if (userGuess == computersNumber) {
                System.out.println("You got it in " + guessCount + " guesses! My number was " + computersNumber);
                break;
            } else if (guessCount == MAX_GUESSES) {
                System.out.println("You didn't get the number in " + MAX_GUESSES + " guesses. You lose. My number was " + computersNumber);
                break;
            } else if (userGuess < computersNumber) {
                System.out.println("Your number is too low. Try again:");
            } else {
                System.out.println("Your number is too high. Try again:");
            }
        }

        scanner.close();
    }
}

