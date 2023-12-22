package ictgradschool.industry.common;
public class Game {
    private SecretNumber secretNumber;
    private int attempts;

    public Game() {
        this.secretNumber = new SecretNumber();
        this.attempts = 7;
    }
}
