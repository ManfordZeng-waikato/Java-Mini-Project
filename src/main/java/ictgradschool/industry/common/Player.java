package ictgradschool.industry.common;

import java.util.List;

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

    public abstract List generateSecret();

}
