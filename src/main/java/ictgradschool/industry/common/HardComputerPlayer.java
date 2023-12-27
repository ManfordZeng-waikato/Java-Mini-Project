package ictgradschool.industry.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class HardComputerPlayer extends Player {
    private SecretNumber secretNumber;
    private List<String> possibleGuesses;


    public HardComputerPlayer() {
        super();
        this.possibleGuesses = generateAllPossibleCodes();


    }

    private List<String> generateAllPossibleCodes() {
        List<String> allCodes = new ArrayList<>();
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = 0; k <= 9; k++) {
                    for (int l = 0; l <= 9; l++) {
                        if (i != j && i != k && i != l && j != k && j != l && k != l) {
                            allCodes.add(String.format("%d%d%d%d", i, j, k, l));
                        }
                    }
                }
            }
        }
        return allCodes;
    }

    @Override
    public String makeGuess() {
        if (possibleGuesses.size() == generateAllPossibleCodes().size()) {
            return getRandomGuess();
        }

        // 如果不是第一次猜测，使用智能剪枝方法选择猜测
        String guess = chooseSmartGuess();
        possibleGuesses.remove(guess);
        return guess;
    }

    private String getRandomGuess() {
        Collections.shuffle(possibleGuesses);
        return possibleGuesses.get(0);
    }

    private String chooseSmartGuess() {
        do {
            String lastGuess = getRandomGuess();
            Feedback lastFeedback = secretNumber.checkGuess(lastGuess);

            for (Iterator<String> iterator = possibleGuesses.iterator(); iterator.hasNext(); ) {
                String possibleGuess = iterator.next();
                Feedback feedbackForPossibleGuess = secretNumber.checkGuess(possibleGuess);


                if (!(lastFeedback.getBulls() == feedbackForPossibleGuess.getBulls() && lastFeedback.getCows() == feedbackForPossibleGuess.getCows())) {
                    iterator.remove();
                }
            }
        } while (possibleGuesses.size() > 0);


        return possibleGuesses.get(0);
    }

    @Override
    public Feedback checkGuess(String guess) {

        return secretNumber.checkGuess(guess);
    }

    @Override
    public void generateSecret() {
        if (secretNumber == null) {
            secretNumber = new SecretNumber();
        }
        secretNumber.generateSecret();
    }
}
