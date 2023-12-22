package ictgradschool.industry.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SecretNumber {
    private List<Integer> secretCode;

    public void generateSecret() {
        List<Integer> digits = new ArrayList<>(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        Collections.shuffle(digits);

        secretCode = digits.subList(0, 4);
    }
}
