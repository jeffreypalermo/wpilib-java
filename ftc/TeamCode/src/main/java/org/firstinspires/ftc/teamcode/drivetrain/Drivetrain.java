package org.firstinspires.ftc.teamcode.drivetrain;

import static org.firstinspires.ftc.teamcode.drivetrain.Constants.DriveConstants.*;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Drivetrain {
    private final DcMotor leftFront;
    private final DcMotor leftRear;
    private final DcMotor rightFront;
    private final DcMotor rightRear;

    public Drivetrain(HardwareMap hw) {
        leftFront = hw.get(DcMotor.class, LEFT_FRONT);
        leftRear = hw.get(DcMotor.class, LEFT_REAR);
        rightFront = hw.get(DcMotor.class, RIGHT_FRONT);
        rightRear = hw.get(DcMotor.class, RIGHT_REAR);

        // Reverse right side so positive power drives forward (adjust if your wiring differs)
        leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
        leftRear.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightRear.setDirection(DcMotorSimple.Direction.REVERSE);

        // Brake when zero power
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void tank(double leftPower, double rightPower) {
        leftFront.setPower(leftPower);
        leftRear.setPower(leftPower);
        rightFront.setPower(rightPower);
        rightRear.setPower(rightPower);
    }

    public void arcade(double forward, double turn) {
        double left = clip(forward + turn);
        double right = clip(forward - turn);
        tank(left, right);
    }

    public void stop() { tank(0, 0); }

    private static double clip(double v) { return Math.max(-1.0, Math.min(1.0, v)); }
}
