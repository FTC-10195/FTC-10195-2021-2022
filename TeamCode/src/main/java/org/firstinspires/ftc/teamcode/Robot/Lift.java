package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Lift extends Subsystem {
    private enum LiftState {
        START,
        LIFT,
        STOP
    }
    private LiftState liftState = LiftState.START;

    private double liftPower;

    private DcMotorEx lift;

    public Lift(HardwareMap hwMap) {
        lift = hwMap.get(DcMotorEx.class, "lift");
        lift.setDirection(DcMotorEx.Direction.FORWARD);
        lift.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        lift.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
    }

    public void drive(boolean liftUp, boolean liftDown) {
        manualLift(liftUp, liftDown);
    }

    private void manualLift(boolean liftUp, boolean liftDown) {
        if (liftUp) {
            lift.setPower(1);
        } else if (liftDown) {
            lift.setPower(-1);
        } else {
            lift.setPower(0);
        }
    }

    private void getPosition() {
        lift.getCurrentPosition();
    }

    private void resetPosition() {
        lift.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
    }

    @Override
    public void runMotorsAndServos() {
        lift.setPower(liftPower);
    }
}
