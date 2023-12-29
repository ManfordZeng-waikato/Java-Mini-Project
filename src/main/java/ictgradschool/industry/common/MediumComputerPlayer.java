package ictgradschool.industry.common;

import java.util.*;
import java.util.stream.Collectors;

public class MediumComputerPlayer extends Player {
    private SecretNumber secretNumber;
    private Set<String> previousGuesses;


    public MediumComputerPlayer() {
        super();
        this.previousGuesses = new HashSet<>();
    }

    @Override
    public String makeGuess() {
        List<Integer> digits = new ArrayList<>(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        Collections.shuffle(digits);
        String guess= digits.subList(0, 4).stream().map(String::valueOf).collect(Collectors.joining());
        while (previousGuesses.contains(guess)) {
            Collections.shuffle(digits);
            guess = digits.subList(0, 4).stream().map(String::valueOf).collect(Collectors.joining());
        }

        previousGuesses.add(guess);
        return guess;
    }

    @Override
    public Feedback checkGuess(String guess) {

        return secretNumber.checkGuess(guess);
    }

    @Override
    public List generateSecret() {
        if (secretNumber == null) {
            secretNumber = new SecretNumber();
        }
       return secretNumber.generateSecret();
    }


}
