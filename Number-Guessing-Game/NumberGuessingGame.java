import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        int score = 0;
        String playAgain;

        do {
            printDivider();
            System.out.println("  WELCOME TO THE NUMBER GUESSING GAME  ");
            printDivider();
            
            int numberToGuess = random.nextInt(100) + 1;
            int attempts = 8;
            boolean hasGuessedCorrectly = false;

            System.out.println("\nI have selected a number between 1 and 100. Can you guess it?");
            System.out.println("You have " + attempts + " attempts to guess the number correctly.");
            while (attempts > 0 && !hasGuessedCorrectly) {
                System.out.print("\nEnter your guess: ");
                int userGuess = sc.nextInt();

                if (userGuess == numberToGuess) {
                    System.out.println("\nCONGRATULATIONS! You've guessed the correct number!");
                    hasGuessedCorrectly = true;
                    score++;
                } else if (userGuess < numberToGuess) {
                    System.out.println("\nThe number you guessed is TOO LOW!");
                } else {
                    System.out.println("\nThe number you guessed is TOO HIGH!");
                }

                attempts--;
                if (attempts > 0 && !hasGuessedCorrectly) {
                    System.out.println("Remaining attempts: " + attempts);
                }
            }

            if (!hasGuessedCorrectly) {
                System.out.println("\nYou've run out of attempts. The correct number was: " + numberToGuess);
            }

            System.out.println("\nYour current score: " + score);
            System.out.print("\nDo you want to play another round? (yes/no): ");
            playAgain = sc.next();
            clearConsole();

        } while (playAgain.equalsIgnoreCase("yes"));

        System.out.println("\nTHANKS FOR PLAYING! Your final score is: " + score);
        sc.close();
    }

    public static void clearConsole() {
        for (int i = 0; i < 5; i++) {
            System.out.println();
        }
    }

    public static void printDivider() {
        System.out.println("==========================================");
    }
}
