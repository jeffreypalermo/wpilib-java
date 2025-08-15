package org.firstinspires.ftc.common.math;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CircleDifferentialKinematicsTest {
    @Test
    void outerPowerClamped() {
        double[] p = CircleDifferentialKinematics.computeSidePowers(100, 14, 2.0);
        assertTrue(Math.abs(p[0]) <= 1.0);
        assertTrue(Math.abs(p[1]) <= 1.0);
    }

    @Test
    void innerLessOrEqualOuter() {
        double[] p = CircleDifferentialKinematics.computeSidePowers(100, 14, 0.5);
        assertTrue(p[0] >= p[1], "left should be >= right for positive radius (CCW)");
    }

    @Test
    void sideSwapForNegativeRadius() {
        double[] pos = CircleDifferentialKinematics.computeSidePowers(100, 14, 0.5);
        double[] neg = CircleDifferentialKinematics.computeSidePowers(-100, 14, 0.5);
        assertEquals(pos[0], neg[1], 1e-9);
        assertEquals(pos[1], neg[0], 1e-9);
    }
}
