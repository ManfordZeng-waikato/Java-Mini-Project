package ictgradschool.industry.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ComputerPlayer extends Player{
    private SecretNumber secretNumber;
    @Override
    public String makeGuess() {
        List<Integer> digits = new ArrayList<>(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        Collections.shuffle(digits);
        return digits.subList(0, 4).stream().map(String::valueOf).collect(Collectors.joining());
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
