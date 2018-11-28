package test;

import org.junit.jupiter.api.Test;
import utils.SpiralUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpiralTests {
    @Test
    public void testSpiralArmLength() {
        assertEquals(1, SpiralUtils.lengthOfSprialArm(1));
        assertEquals(2, SpiralUtils.lengthOfSprialArm(2));
        assertEquals(3, SpiralUtils.lengthOfSprialArm(5));
        assertEquals(4, SpiralUtils.lengthOfSprialArm(12));
        assertEquals(5, SpiralUtils.lengthOfSprialArm(19));
        assertEquals(6, SpiralUtils.lengthOfSprialArm(26));
    }

    @Test
    public void testSpiralMidpoints() {
        List<Integer> midpoints = SpiralUtils.midpointsOfSprialArm(5);

        assertEquals(3, midpoints.size());

        assertTrue(midpoints.contains(23));
        assertTrue(midpoints.contains(19));
        assertTrue(midpoints.contains(15));
    }

    @Test
    public void testSmallestDistanceFromMidpoints() {
        List<Integer> midpoints = SpiralUtils.midpointsOfSprialArm(5);

        assertEquals(0, SpiralUtils.smallestDistanceFromMidpoints(23, midpoints));
        assertEquals(0, SpiralUtils.smallestDistanceFromMidpoints(19, midpoints));
        assertEquals(0, SpiralUtils.smallestDistanceFromMidpoints(15, midpoints));

        assertEquals(1, SpiralUtils.smallestDistanceFromMidpoints(22, midpoints));
        assertEquals(1, SpiralUtils.smallestDistanceFromMidpoints(20, midpoints));
        assertEquals(1, SpiralUtils.smallestDistanceFromMidpoints(14, midpoints));

        assertEquals(2, SpiralUtils.smallestDistanceFromMidpoints(25, midpoints));
        assertEquals(2, SpiralUtils.smallestDistanceFromMidpoints(17, midpoints));
        assertEquals(2, SpiralUtils.smallestDistanceFromMidpoints(13, midpoints));
    }

    @Test
    public void testSpiralUntil() {
        assertEquals(2, SpiralUtils.spiralUntilAtLeast(1));
        assertEquals(5, SpiralUtils.spiralUntilAtLeast(4));
        assertEquals(25, SpiralUtils.spiralUntilAtLeast(23));
        assertEquals(57, SpiralUtils.spiralUntilAtLeast(54));
        assertEquals(133, SpiralUtils.spiralUntilAtLeast(122));
        assertEquals(351, SpiralUtils.spiralUntilAtLeast(330));
    }
}
