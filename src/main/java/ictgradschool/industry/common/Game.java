package ictgradschool.industry.common;

public class Game {


    private Player player1;
    private Player computerPlayer;
    private int playerAttempts;
    private int computerAttempts;

    public Game() {
        /*this.secretNumber = new SecretNumber();
        this.attempts = 7;*/

        this.player1 = new Player1();
        this.computerPlayer = new ComputerPlayer();
        this.playerAttempts = 7;
        this.computerAttempts = 7;
    }

    public void startGame() {
        System.out.println("pls enter your secret code");
        player1.generateSecret();
        System.out.println("---");
        computerPlayer.generateSecret();
        playGame();
    }

    private void playGame() {


        while (playerAttempts > 0 && computerAttempts > 0) {

            String playerGuess = player1.makeGuess();
            Feedback playerFeedback = computerPlayer.checkGuess(playerGuess);
            System.out.println("Result: " + playerFeedback.getBulls() + " bulls and " + playerFeedback.getCows() + " cows");
            if (playerFeedback.getBulls() == 4) {
                System.out.println("You win! ：）");
                return;
            }
            playerAttempts--;
            System.out.println("Your attempts left: " + playerAttempts);


            String computerGuess = computerPlayer.makeGuess();
            Feedback computerFeedback = player1.checkGuess(computerGuess);
            System.out.println("Computer's guess: " + computerGuess);
            System.out.println("Result: " + computerFeedback.getBulls() + " bulls and " + computerFeedback.getCows() + " cows");
            if (computerFeedback.getBulls() == 4) {
                System.out.println("Oops! The computer guessed your secret code!");
                return;
            }

            computerAttempts--;
            System.out.println("Computer's attempts left: " + computerAttempts);

        }
        System.out.println("Game over. No one guessed the secret code.");
    }


    //  Check if guess is valid
    private boolean isValidGuess(String guess) {
        // Check if the guess is a 4-digit number with distinct digits
        return guess.matches("\\d{4}") && guess.chars().distinct().count() == 4;
    }
}
