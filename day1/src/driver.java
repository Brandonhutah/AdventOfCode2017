import java.util.Scanner;

/// Advent of Code 2017 Day 1 - Reverse Captcha
/// Author: Brandon Horlacher
/// Date: November 22, 2018
public class driver {
    public static void main(String[] args) {
        // variables to be used in solving the puzzle
        int sum = 0;
        String puzzle = "";

        // get puzzle input
        System.out.print("Please enter the puzzle to use: ");
        puzzle = new Scanner(System.in).nextLine();

        // convert puzzle input into something iterable and iterate over it
        char[] puzzleChars = puzzle.toCharArray();

        /// PART 1
        for (int i = 0; i < puzzleChars.length; i++) {
            int currentInt = Integer.parseInt(String.valueOf(puzzleChars[i]));
            int nextInt = 0;

            // if we're not looking at the last char
            if (i != puzzleChars.length - 1) {
                nextInt = Integer.parseInt(String.valueOf(puzzleChars[i + 1]));
            } else {
                nextInt = Integer.parseInt(String.valueOf(puzzleChars[0]));
            }

            // compare and add to sum if they're the same
            if (currentInt == nextInt) {
                sum += currentInt;
            }
        }

        System.out.println("The solution to part 1 is: " + sum);
        sum = 0;

        /// PART 2
        for (int i = 0; i < puzzleChars.length; i++) {
            int currentInt = Integer.parseInt(String.valueOf(puzzleChars[i]));
            int nextInt = 0;
            int digitStep = puzzleChars.length / 2;

            // if we're not looking at a character within 5 digits of the end
            if (i < (puzzleChars.length - digitStep)) {
                nextInt = Integer.parseInt(String.valueOf(puzzleChars[i + digitStep]));
            } else {
                // calculate how many digits we need to loop around
                int overflow = digitStep - (puzzleChars.length - i);
                nextInt = Integer.parseInt(String.valueOf(puzzleChars[overflow]));
            }

            // compare and add to sum if they're the same
            if (currentInt == nextInt) {
                sum += currentInt;
            }
        }

        System.out.println("The solution to part 2 is: " + sum);
    }
}
