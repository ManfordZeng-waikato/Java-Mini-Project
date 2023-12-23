package ictgradschool.industry.common;

public abstract class Player {
    public SecretNumber secretNumber;

    public Player() {
        this.secretNumber = new SecretNumber();
    }

    public abstract String makeGuess();
    public abstract Feedback checkGuess(String guess);
    /* {

        return secretNumber.checkGuess(guess);
    }*/

    public abstract void generateSecret();

}
