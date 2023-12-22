package ictgradschool.industry.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SecretNumber {
    private List<Integer> secretCode;

    public void generateSecret() {
        List<Integer> digits = new ArrayList<>(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
//        Randomly generates secret code from List
        Collections.shuffle(digits);
        secretCode = digits.subList(0, 4);

//        Convert List to String then print
        StringBuilder sb = new StringBuilder();
        for (Integer element:secretCode
             ) {sb.append(element);
        }
        String secretCodeFromComputer= sb.toString();
        System.out.println("The secret code from computer is "+secretCodeFromComputer);
    }

    public Feedback checkGuess(String guess) {
        Feedback feedback = new Feedback();

        for (int i = 0; i < 4; i++) {
            int digit = Character.getNumericValue(guess.charAt(i));

            if (digit == secretCode.get(i)) {
                feedback.incrementBulls();
            } else if (secretCode.contains(digit)) {
                feedback.incrementCows();
            }
        }

        return feedback;
    }

    public List<Integer> getSecretCode() {
        return secretCode;
    }
}
