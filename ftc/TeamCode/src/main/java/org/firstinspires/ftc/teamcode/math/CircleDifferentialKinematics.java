package org.firstinspires.ftc.teamcode.math;

public final class CircleDifferentialKinematics {
    private CircleDifferentialKinematics() {}

    // radiusIn: requested circle radius in inches; trackWidthIn: distance between left/right wheels (in)
    // basePower: nominal [-1,1] power for the outer side
    public static double[] computeSidePowers(double radiusIn, double trackWidthIn, double basePower) {
        double r = Math.abs(radiusIn);
        double t = Math.max(0.1, trackWidthIn); // prevent div by zero
        double outer = clamp(basePower, -1.0, 1.0);
        double innerScale = (r - (t / 2.0)) / (r + (t / 2.0));
        innerScale = clamp(innerScale, 0.0, 1.0);
        double inner = outer * innerScale;
        // Direction: positive radius => left is outer when turning CCW; negative flips sides
        if (radiusIn >= 0) {
            return new double[]{outer, inner}; // left, right
        } else {
            return new double[]{inner, outer};
        }
    }

    public static double clamp(double v, double min, double max) {
        return Math.max(min, Math.min(max, v));
    }
}
