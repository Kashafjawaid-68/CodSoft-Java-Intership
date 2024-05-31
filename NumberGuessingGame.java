import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            Random random = new Random();

            int lowerBound = 1;
            int upperbound = 100;

            int maxAttempts = 10;
            int score = 0;
            boolean playagain = true;
            while (playagain) {
                int generatedNumber = random.nextInt(upperbound - lowerBound + 1) + lowerBound;
                int attempts = 0;
                boolean guessedCorrectly = false;

                System.out.println("Welcome to Guess the Number Game!");
                System.out.println("you've to select number between"  +  lowerBound  +  "and"  +  upperbound  + ".");
                System.out.println("You have " + maxAttempts + " trial to guess the hidden number.");

                while (attempts < maxAttempts) {
                    System.out.println("Guess and Enter the number:");
                    int userGuess = scanner.nextInt();
                    attempts++;

                    if (userGuess == generatedNumber) {
                        guessedCorrectly = true;
                        score += maxAttempts - attempts + 1;
                        System.out.println("Yippie! You guessed the correct number in"  +  attempts  +  " attempts. ");
                        break;
                    } else if (userGuess < generatedNumber) {
                        System.out.println("Oh No! Its too low, Try Again");
                    } else {
                        System.out.println("Sorry! its too high, Try Again");
                    }
                }
                if (guessedCorrectly) {
                    System.out.println("Oops, Your attempts reached the limit.. Better luck next time:)");

                }
                System.out.println("Your Score is:" + score);
                System.out.println("Do you want to play again? (Yes/No) : ");
                String playagainresponse = scanner.next();
                playagain = playagainresponse.equalsIgnoreCase( "yes");
            }
            System.out.println("Thankyou for playing");
            scanner.close();


        }
    }