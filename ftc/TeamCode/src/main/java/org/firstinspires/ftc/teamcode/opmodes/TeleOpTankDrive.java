package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.teamcode.drivetrain.Drivetrain;

@TeleOp(name = "TeleOp Tank Drive", group = "Driver")
public class TeleOpTankDrive extends OpMode {
    private Drivetrain drive;

    @Override public void init() {
        drive = new Drivetrain(hardwareMap);
    }

    @Override public void loop() {
        Gamepad gp = gamepad1;
        double left = -gp.left_stick_y;  // invert Y
        double right = -gp.right_stick_y;
        drive.tank(left, right);

        telemetry.addData("Left", "%.2f", left);
        telemetry.addData("Right", "%.2f", right);
        telemetry.update();
    }

    @Override public void stop() { drive.stop(); }
}
