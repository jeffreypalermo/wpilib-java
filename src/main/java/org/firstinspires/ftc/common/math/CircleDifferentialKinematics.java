package org.firstinspires.ftc.common.math;

/**
 * Differential drive circle-driving kinematics helper for FTC.
 * Computes left/right side power ratios to drive along a circle of given radius.
 */
public final class CircleDifferentialKinematics {
    private CircleDifferentialKinematics() {}

    /**
     * @param radiusIn      Circle radius in inches. Positive = CCW (left side is outer). Negative = CW.
     * @param trackWidthIn  Distance between left/right wheel centers in inches.
     * @param basePower     Nominal outer-side power in [-1, 1].
     * @return double[]{leftPower, rightPower}
     */
    public static double[] computeSidePowers(double radiusIn, double trackWidthIn, double basePower) {
        double r = Math.abs(radiusIn);
        double t = Math.max(0.1, trackWidthIn); // avoid div-by-zero
        double outer = clamp(basePower, -1.0, 1.0);

        double innerScale = (r - (t / 2.0)) / (r + (t / 2.0));
        innerScale = clamp(innerScale, 0.0, 1.0);
        double inner = outer * innerScale;

        if (radiusIn >= 0) {
            return new double[]{outer, inner};
        } else {
            return new double[]{inner, outer};
        }
    }

    public static double clamp(double v, double min, double max) {
        return Math.max(min, Math.min(max, v));
    }
}
