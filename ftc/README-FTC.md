# FTC TeamCode (2025–2026 Season)

This folder contains TeamCode you can drop into the official FTC SDK (2025–2026). It targets a 4-wheel, 4-motor drivetrain from the FTC Starter Kit (SKU 3200-4008-2526).

What’s included:
- 4-motor Drivetrain wrapper with tank/arcade helpers
- Circle-driving kinematics for differential drive
- Sample TeleOp (Tank Drive)
- Sample Autonomous (Drive a circle by radius)

## How to use
1. Download/clone the official FTC SDK (Android Studio project) for the 2025–2026 season.
2. In that SDK, open the `TeamCode` module. Copy the contents of `ftc/TeamCode/src/main/java` from this repo into the SDK’s `TeamCode/src/main/java`.
3. Open `Constants.java` and set motor names to match your Robot Controller configuration.
4. Build and deploy using Android Studio to the robot controller phone/hub.

## Motor mapping
By default, motor names are:
- `leftFront`, `leftRear`, `rightFront`, `rightRear`

Update these names in `Constants.java` to match your configuration (RC app > Configure Robot).

## Track width
`DriveConstants.TRACK_WIDTH_IN` defaults to 14.0 inches. Measure the distance between the left and right wheel midlines and adjust for your robot.

## OpModes
- TeleOp: `TeleOpTankDrive` — Left stick Y drives left side; right stick Y drives right side.
- Autonomous: `AutoDriveCircle` — Drives a circle with configurable radius and base power for a fixed duration; tune the duration for your field task and robot.

## Notes
- If your right side runs backward, leave the default direction reversal in `Drivetrain` as-is. If your wiring differs, adjust the motor directions.
- The `AutoDriveCircle` uses time-based control for simplicity. For precise paths, enable encoders and closed-loop control.
