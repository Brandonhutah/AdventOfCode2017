import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/// Advent of Code 2017 Day 5 - A Maze of Twisty Trampolines, All Alike
/// Author: Brandon Horlacher
/// Date: November 30, 2018
public class driver {
    public static void main(String[] args) {
        // variables to be used in solving the puzzle
        int stepsToExit = 0;
        int currentIndex = 0;

        // get filepath for the puzzle input
        System.out.print("Please provide the filepath of the text document containing the puzzle input: ");
        String filePath = new Scanner(System.in).nextLine();

        try {
            // attempt to read each line in the file into a list
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            List<String> linesCopy = new ArrayList<>(lines);

            /// PART 1
            do {
                int steps = Integer.parseInt(linesCopy.get(currentIndex));
                linesCopy.set(currentIndex, String.valueOf(steps + 1));

                currentIndex += steps;
                stepsToExit++;
            } while (currentIndex < lines.size());

            System.out.println("The solution to part 1 is: " + stepsToExit);

            /// PART 2
            linesCopy = new ArrayList<>(lines);
            stepsToExit = 0;
            currentIndex = 0;
            do {
                int steps = Integer.parseInt(linesCopy.get(currentIndex));

                if (steps >= 3) {
                    linesCopy.set(currentIndex, String.valueOf(steps - 1));
                } else {
                    linesCopy.set(currentIndex, String.valueOf(steps + 1));
                }

                currentIndex += steps;
                stepsToExit++;
            } while (currentIndex < lines.size());

            System.out.println("The solution to part 2 is: " + stepsToExit);
        } catch (Exception ex) {
            System.out.println("An error occurred attempting to read your input file.");
        }
    }
}
