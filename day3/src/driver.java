import utils.SpiralUtils;

import java.util.List;
import java.util.Scanner;

/// Advent of Code 2017 Day 3 - Spiral Memory
/// Author: Brandon Horlacher
/// Date: November 27, 2018
public class driver {
    public static void main(String[] args) {
        // variables to be used in solving the puzzle
        int input = 0;
        int armLength;
        int solution = 0;

        // get the puzzle input
        System.out.print("Please provide the puzzle input: ");
        input = new Scanner(System.in).nextInt();

        /// PART 1
        // figure out which spiral input is on to calculate distance on one axis
        armLength = SpiralUtils.lengthOfSprialArm(input);
        solution = (armLength - 1) / 2;

        // find the midpoints of the spiral the input is on
        List<Integer> midpoints = SpiralUtils.midpointsOfSprialArm(armLength);

        // calculate the smallest distance from any midpoint to our input for other access
        solution += SpiralUtils.smallestDistanceFromMidpoints(input, midpoints);

        System.out.println("The solution to part 1 is: " + solution);

        /// PART 2
        // this part is less elegant, as I'm just going to create the spiral instead of run calculations
        System.out.println("The solution to part 2 is: " + SpiralUtils.spiralUntilAtLeast(input));
    }
}
