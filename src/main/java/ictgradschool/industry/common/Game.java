package ictgradschool.industry.common;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {


    private Player player1;
    private Player computerPlayer;
    private int playerAttempts;
    private int computerAttempts;
    private List<String> computerGuessStringList;
    private List<String> playerGuessStringList;


    public Game() {
        /*this.secretNumber = new SecretNumber();
        this.attempts = 7;*/

        this.player1 = new Player1();
        this.computerPlayer = new ComputerPlayer();
        this.playerAttempts = 7;
        this.computerAttempts = 7;
        this.computerGuessStringList = new ArrayList<>();
        this.playerGuessStringList = new ArrayList<>();
    }

    public void startGame() {
        System.out.println("pls enter your secret code");
        player1.generateSecret();
        System.out.println("---");
        int aiDifficulty = selectAIDifficulty();
        switch (aiDifficulty) {
            case 1 -> this.computerPlayer = new ComputerPlayer();
            case 2 -> this.computerPlayer = new MediumComputerPlayer();
            case 3 -> this.computerPlayer = new HardComputerPlayer();
            default -> {
                System.out.println("pls enter 1,2 or 3");
                return;
            }
        }

        int manualOrFile = selectManualOrFile();
        switch (manualOrFile) {
            case 1:
                break;
            case 2:
                this.player1 = new FilePlayer();
                break;
            default:
                System.out.println("pls enter 1 or 2 ");
                return;
        }
        computerPlayer.generateSecret();
        playGame();
        saveResultToFile();
    }

    private int selectManualOrFile() {
        System.out.println("Select manual or file (input 1 or 2):");
        System.out.println("1.manual");
        System.out.println("2. file");
        return Integer.parseInt(Keyboard.readInput());
    }

    private int selectAIDifficulty() {
        System.out.println("Select AI difficulty(input 1,2 or 3):");
        System.out.println("1. simple");
        System.out.println("2. medium");
        System.out.println("3. hard");
        return Integer.parseInt(Keyboard.readInput());
    }

    private void playGame() {


        while (playerAttempts > 0 && computerAttempts > 0) {

            String playerGuess = player1.makeGuess();
            playerGuessStringList.add(playerGuess);
            Feedback playerFeedback = computerPlayer.checkGuess(playerGuess);
            System.out.println("Result: " + playerFeedback.getBulls() + " bulls and " + playerFeedback.getCows() + " cows");
            if (playerFeedback.getBulls() == 4) {
                System.out.println("You win! ：）");
                return;
            }
            playerAttempts--;
            System.out.println("Your attempts left: " + playerAttempts);


            String computerGuess = computerPlayer.makeGuess();
            computerGuessStringList.add(computerGuess);
            Feedback computerFeedback = player1.checkGuess(computerGuess);
            System.out.println("Computer's guess: " + computerGuess);
            System.out.println("Result: " + computerFeedback.getBulls() + " bulls and " + computerFeedback.getCows() + " cows");

            if (computerFeedback.getBulls() == 4) {
                System.out.println("Oops! The computer guessed your secret code!");
                return;
            }

            computerAttempts--;
            System.out.println("Computer's attempts left: " + computerAttempts);
            System.out.println("----");

        }
        System.out.println("Game over. No one guessed the secret code.");
    }

    private void saveResultToFile() {
        System.out.println("Do you want to save the result to file? (yes/no)");
        String response = Keyboard.readInput().toLowerCase();
        if (response.equals("yes")) {
            System.out.println("Enter the filename:");
            String fileName = Keyboard.readInput();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                writer.write("Your code: " + this.player1.secretNumber.getSecretCode() + "\n");
                writer.write("Computer’s code: " + this.computerPlayer.secretNumber.getSecretCode() + "\n\n");

                for (String computerGuess : computerGuessStringList) {
                    writer.write("Computer guess: " + computerGuess + " result : " + player1.checkGuess(computerGuess).getCows()+" cows " + " and "+player1.checkGuess(computerGuess).getBulls()+" bulls"+ "\n");
                }
                writer.write("\n");
                for (String playerGuess : playerGuessStringList) {
                    writer.write("You guess: " + playerGuess + " result: " + computerPlayer.checkGuess(playerGuess).getCows()+" cows" + " and "+computerPlayer.checkGuess(playerGuess).getBulls()+" bulls"+ "\n");
                }
            } catch (IOException e) {
                System.out.println("Error saving results to the file.");
            }
        }
    }

}
