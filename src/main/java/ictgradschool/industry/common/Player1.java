package ictgradschool.industry.common;

import java.util.List;

public class Player1 extends Player {


    public Player1() {
        super();

    }

    @Override
    public String makeGuess() {
        System.out.println("Enter your guess (4 distinct digits):");
        String guess = Keyboard.readInput();
        while (!isValidGuess(guess)) {
            System.out.println("Invalid guess. Please enter 4 distinct digits:");
            guess = Keyboard.readInput();
        }
        return guess;
    }

    @Override
    public Feedback checkGuess(String guess) {
        return secretNumber.checkGuess(guess);
    }


    @Override
    public List generateSecret() {
        String mySecretCode = Keyboard.readInput();
        while (!isValidGuess(mySecretCode)) {
            System.out.println("Invalid input. Please enter 4 distinct digits:");
            mySecretCode = Keyboard.readInput();
        }
        return secretNumber.generateSecret(mySecretCode);

    }


    //  Check if guess is valid
    boolean isValidGuess(String guess) {
        // Check if the guess is a 4-digit number with distinct digits
        return guess.matches("\\d{4}") && guess.chars().distinct().count() == 4;
    }
}

