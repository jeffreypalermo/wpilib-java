package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import static org.firstinspires.ftc.teamcode.drivetrain.Constants.DriveConstants.*;
import org.firstinspires.ftc.teamcode.drivetrain.Drivetrain;
import org.firstinspires.ftc.teamcode.math.CircleDifferentialKinematics;

@Autonomous(name = "Auto Drive Circle", group = "Auto")
public class AutoDriveCircle extends LinearOpMode {
    // Configure these as needed
    private static final double RADIUS_IN = 120.0;  // 10 feet in inches
    private static final double BASE_POWER = DEFAULT_BASE_POWER;
    private static final long DRIVE_MS = 6000;      // tune for your robot

    @Override public void runOpMode() throws InterruptedException {
        Drivetrain drive = new Drivetrain(hardwareMap);
        waitForStart();

        long end = System.currentTimeMillis() + DRIVE_MS;
        while (opModeIsActive() && System.currentTimeMillis() < end) {
            double[] powers = CircleDifferentialKinematics.computeSidePowers(RADIUS_IN, TRACK_WIDTH_IN, BASE_POWER);
            drive.tank(powers[0], powers[1]);
            telemetry.addData("L,R", "%.2f, %.2f", powers[0], powers[1]);
            telemetry.update();
        }
        drive.stop();
    }
}
