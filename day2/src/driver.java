import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/// Advent of Code 2017 Day 2 - Corruption Checksum
/// Author: Brandon Horlacher
/// Date: November 22, 2018
public class driver {
    public static void main(String[] args) {
        // variables to be used in solving the puzzle
        int checkSum = 0;
        int lineMax = -1;
        int lineMin = -1;

        // get filepath for the puzzle input
        System.out.print("Please provide the filepath of the text document containing the puzzle input: ");
        String filePath = new Scanner(System.in).nextLine();

        try {
            // attempt to read each line in the file into a list
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            /// PART 1
            for (String line : lines) {
                boolean firstEntry = true;
                for (String entry : line.split("\t")) {
                    int intEntry = Integer.parseInt(entry);

                    if (firstEntry) {
                        // if this is the first entry on a line, it is both the line max and min
                        lineMax = intEntry;
                        lineMin = intEntry;

                        firstEntry = false;
                    } else {
                        // determine if it is either a new max or new min
                        lineMax = intEntry > lineMax ? intEntry : lineMax;
                        lineMin = intEntry < lineMin ? intEntry : lineMin;
                    }
                }

                // add the difference to the checksum
                checkSum += (lineMax - lineMin);
            }

            System.out.println("The solution to part 1 is: " + checkSum);

            /// PART 2
            checkSum = 0;
            for (String line : lines) {
                String[] lineEntries = line.split("\t");

                // nested loop to compare each entry to every other entry
                for (int i = 0; i < lineEntries.length; i++) {
                    for (int j = 0; j < lineEntries.length; j++) {
                        // make sure we do not compare an entry against itself
                        if (i != j) {
                            int entry1 = Integer.parseInt(lineEntries[i]);
                            int entry2 = Integer.parseInt(lineEntries[j]);

                            // check if the values evenly divide into each other
                            if (entry1 % entry2 == 0) {
                                checkSum += (entry1 / entry2);

                                // force exit of for-loops as this rows solution has been found
                                // cannot use break since it will only break the first for loop
                                i = lineEntries.length;
                                j = lineEntries.length;
                            }
                        }
                    }
                }
            }

            System.out.println("The solution to part 2 is: " + checkSum);
        } catch (Exception ex) {
            System.out.println("An error occurred attempting to read your input file.");
        }
    }
}
