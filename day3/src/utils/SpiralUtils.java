package utils;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpiralUtils {
    public static int lengthOfSprialArm(int n) {
        int length = (int) Math.ceil(Math.sqrt(n));

        if (length % 2 == 0) {
            return length++;
        }
        return length;
    }

    public static List<Integer> midpointsOfSprialArm(int armLength) {
        int maxSpiralArmValue = armLength * armLength;
        int midpointDistance = (armLength - 1) / 2;

        List<Integer> midpoints = new ArrayList<>();
        // each 'spiral' has three sides before moving to a new spiral, loop 3 times
        for (int i = 0; i < 3; i++) {
            midpoints.add(maxSpiralArmValue - (midpointDistance + (i * (armLength - 1))));
        }

        return midpoints;
    }

    public static int smallestDistanceFromMidpoints(int n, List<Integer> midpoints) {
        int min = -1;

        for (int midpoint : midpoints) {
            if (Math.abs(n - midpoint) < min || min < 0) {
                min = Math.abs(n - midpoint);
            }
        }

        return min;
    }

    public static int spiralUntilAtLeast(int n) {
        Point[] neighbors = {new Point(1, 0), new Point(1, 1), new Point(0, 1), new Point(-1, 1),
                new Point(-1, 0), new Point(-1, -1), new Point(0, -1), new Point(1, -1)};
        // Right Up Left Down
        Point[] directions = {new Point(1, 0), new Point(0, 1), new Point(-1, 0), new Point(0, -1)};
        Map<Point, Integer> spiral = new HashMap<>();
        spiral.put(new Point(0, 0), 1);
        int x = 0, y = 0, armSteps = 0, direction = 0;
        int spiralArmLength = 1;
        boolean secondSpiralArm = false; // each spiral has 2 arms with the same length, this keeps track of which we are on
        while (true) {
            x += directions[direction].x;
            y += directions[direction].y;
            int neighborSum = 0;

            // check for neighbors around current point, if they exist add it to the total sum
            for (Point neighbor : neighbors) {
                if (spiral.containsKey(new Point(x + neighbor.x, y + neighbor.y))) {
                    neighborSum += spiral.get(new Point(x + neighbor.x, y + neighbor.y));
                }
            }

            if (neighborSum > n) {
                return neighborSum;
            } else {
                // add our current point into the spiral
                spiral.put(new Point(x, y), neighborSum);
                armSteps++;
                // if we've reached the end of this spiral, change directions
                if (armSteps == spiralArmLength) {
                    armSteps = 0;
                    direction = (direction + 1) % 4;

                    // check if we can go one more length before increasing arm size
                    if (secondSpiralArm) {
                        secondSpiralArm = false;
                        spiralArmLength++;
                    } else {
                        secondSpiralArm = true;
                    }
                }
            }
        }
    }
}
