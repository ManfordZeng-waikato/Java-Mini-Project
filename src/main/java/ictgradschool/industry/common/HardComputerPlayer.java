package ictgradschool.industry.common;

import java.util.*;


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
                        HashSet<Integer> dd = new HashSet<>();
                        Collections.addAll(dd, i, j, k, l);

                        if (dd.size() == 4) {
//                        }
//                        if (i != j && i != k && i != l && j != k && j != l && k != l) {
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
        String guess = getRandomGuess();

        return guess;

    }

    private String getRandomGuess() {
        Collections.shuffle(possibleGuesses);
        return possibleGuesses.get(0);
    }

    @Override
    public void feedback(String guess, Feedback fb) {
        super.feedback(guess, fb);

        Iterator<String> iterator = possibleGuesses.iterator();

        SecretNumber sc = new SecretNumber();
        sc.generateSecret(guess);

        while (iterator.hasNext()) {
            String possibleGuess = iterator.next();
            Feedback feedbackForPossibleGuess = sc.checkGuess(possibleGuess);

            if (fb.getBulls() != feedbackForPossibleGuess.getBulls()) {
                iterator.remove();
            } else if (fb.getCows() != feedbackForPossibleGuess.getCows()) {
                iterator.remove();
            }


        }

        System.out.println(possibleGuesses.size() + " remaining");
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

