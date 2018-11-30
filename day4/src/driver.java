import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/// Advent of Code 2017 Day 4 - High-Entropy Passphrases
/// Author: Brandon Horlacher
/// Date: November 28, 2018
public class driver {
    public static void main(String[] args) {
        // variables to be used in solving the puzzle
        int validPhrases = 0;

        // get filepath for the puzzle input
        System.out.print("Please provide the filepath of the text document containing the puzzle input: ");
        String filePath = new Scanner(System.in).nextLine();

        try {
            // attempt to read each line in the file into a list
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            /// PART 1
            for (String line : lines) {
                List<String> usedWords = new ArrayList<>();
                boolean validPassphrase = true;

                for (String word : line.split(" ")) {
                    if (usedWords.contains(word)) {
                        validPassphrase = false;
                        break;
                    } else {
                        usedWords.add(word);
                    }
                }

                if (validPassphrase) {
                    validPhrases++;
                }
            }

            System.out.println("The solution to part 1 is: " + validPhrases);
            validPhrases = 0;

            /// PART 2
            for (String line : lines) {
                // this time around each word will be a dictionary mapping every character in the word, to how many
                // times that character is used in the word
                List<Map<Character, Integer>> usedWords = new ArrayList<>();
                boolean validPassphrase = true;

                for (String word : line.split(" ")) {
                    Map<Character, Integer> charMap = new HashMap<>();
                    for (char c : word.toCharArray()) {
                        // if the character has already been used, increment it's value, otherwise add it to the dict
                        if (charMap.containsKey(c)) {
                            charMap.put(c, charMap.get(c) + 1);
                        } else {
                            charMap.put(c, 1);
                        }
                    }

                    // now we check if the word we're looking at is an anagram of any we've seen before on this line
                    for (Map<Character, Integer> usedWord : usedWords) {
                        // no need to check characters if the char maps are a different size
                        if (usedWord.keySet().size() == charMap.keySet().size()) {
                            boolean hasDifferingValues = false;
                            for (char c : charMap.keySet()) {
                                // we can short circuit checking every single character by breaking as soon as we see
                                // any difference between the two words
                                if (!usedWord.containsKey(c) || usedWord.get(c) != charMap.get(c)) {
                                    hasDifferingValues = true;
                                    break;
                                }
                            }

                            if (!hasDifferingValues) {
                                validPassphrase = false;
                                break;
                            }
                        }
                    }

                    // we don't need to check the rest of the words if we already know the line is invalid
                    if (!validPassphrase)
                        break;
                    usedWords.add(charMap);
                }

                if (validPassphrase) {
                    validPhrases++;
                }
            }

            System.out.println("The solution to part 2 is: " + validPhrases);
        } catch (Exception ex) {
            System.out.println("An error occurred attempting to read your input file.");
        }
    }
}
