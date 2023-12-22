package ictgradschool.industry.common;

import java.util.Scanner;

public class Game {
    private SecretNumber secretNumber;
    private int attempts;

    public Game() {
        this.secretNumber = new SecretNumber();
        this.attempts = 7;
    }
    public void startGame() {
        System.out.println("Please enter your secret code:");
        String mySecretCode=Keyboard.readInput();
        System.out.println("---");

        secretNumber.generateSecret();
        playGame();
    }

    private void playGame() {


        while (attempts > 0) {
            System.out.println("Enter your guess (4 distinct digits):");
            String guess = Keyboard.readInput();

            if (isValidGuess(guess)) {
                Feedback feedback = secretNumber.checkGuess(guess);
                System.out.println("Result: " + feedback.getBulls() + " bulls and " + feedback.getCows()+" cows");

                if (feedback.getBulls() == 4) {
                    System.out.println("You win! ：）");
                    return;
                }

                attempts--;
                System.out.println("Attempts left: " + attempts);
            } else {
                System.out.println("Invalid guess. Please enter 4 distinct digits.");
            }
        }

        System.out.println("Sorry, you've run out of attempts. The secret code was: " + secretNumber.getSecretCode());
    }

//  Check if guess is valid
    private boolean isValidGuess(String guess) {
        // Check if the guess is a 4-digit number with distinct digits
        return guess.matches("\\d{4}") && guess.chars().distinct().count() == 4;
    }
}
