package ictgradschool.industry.common;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FilePlayer extends Player {
    private List<String> fileGuesses;

    public FilePlayer() {
        super();
        this.fileGuesses = readGuessesFromFile(selectFilename());
    }

    private List<String> readGuessesFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            return reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Invalid filename. Please enter a valid filename.");
            // 在这里处理文件读取失败的情况
            return Collections.emptyList();
        }
    }

    private String selectFilename() {
        System.out.println("Enter the filename:");
        return Keyboard.readInput();
    }

    @Override
    public String makeGuess() {
        if (!fileGuesses.isEmpty()) {
            String nextGuess = fileGuesses.remove(0);
            return nextGuess.trim();
        } else {
            System.out.println("No more guesses in the file. Enter your own guess:");
            return Keyboard.readInput();
        }
    }

    @Override
    public Feedback checkGuess(String guess) {
        return secretNumber.checkGuess(guess);
    }

    @Override
    public List generateSecret() {
        String mySecretCode = Keyboard.readInput();

       return secretNumber.generateSecret(mySecretCode);
    }


}
