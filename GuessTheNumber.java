import java.util.Scanner;
import java.util.Random;

public class GuessTheNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int minRange = 1;
        int maxRange = 100;
        int randomNumber = random.nextInt(maxRange - minRange + 1) + minRange;

        int attempts = 0;
        int maxAttempts = 3; // Limiting the number of attempts to 3
        int score = 100; // Initial score

        System.out.println("Welcome to Guess the Number game!");
        System.out.println("I have chosen a number between " + minRange + " and " + maxRange + ". Try to guess it.");

        while (attempts < maxAttempts) {
            System.out.print("Enter your guess: ");
            int guess = scanner.nextInt();
            attempts++;

            if (guess == randomNumber) {
                System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                System.out.println("Your score is: " + score);
                break;
            } else if (guess < randomNumber) {
                System.out.println("The number is higher than " + guess + ". Try again.");
            } else {
                System.out.println("The number is lower than " + guess + ". Try again.");
            }

            // Deducting score for each attempt
            score -= 20;
        }

        if (attempts == maxAttempts) {
            System.out.println("Sorry, you've used all your attempts. The number was " + randomNumber + ".");
        }

        scanner.close();
    }
}
