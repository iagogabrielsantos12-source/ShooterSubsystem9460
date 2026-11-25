package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

import com.revrobotics.PersistMode;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkFlexConfig;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkBase.ControlType;

public class ShooterSubsystem extends SubsystemBase {

    private final SparkFlex leader = new SparkFlex(ShooterConstants.kLeaderMotorID, MotorType.kBrushless);

    private final SparkFlex follower = new SparkFlex(ShooterConstants.kFollowerMotorID, MotorType.kBrushless);

    private final SparkClosedLoopController shooterPID = leader.getClosedLoopController();

    public ShooterSubsystem(){

        SparkFlexConfig followerConfig = new SparkFlexConfig();

        followerConfig.follow(leader, true);
        followerConfig.smartCurrentLimit(ShooterConstants.kCurrentLimit);


        follower.configure(followerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        SparkFlexConfig leaderConfig = new SparkFlexConfig();

        leaderConfig.smartCurrentLimit(ShooterConstants.kCurrentLimit);

        leaderConfig.inverted(true);

        leaderConfig.closedLoop.pid(0.0, 0.0, 0.0);

        leaderConfig.closedLoop.feedForward.kV(0.00207);

        leaderConfig.closedLoop.outputRange(0.0, 1.0);

        leader.configure(leaderConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);




    }

    public void Shoot(){
        shooterPID.setSetpoint(ShooterConstants.kShooterRPM, com.revrobotics.spark.SparkBase.ControlType.kVelocity);
    }

    public void Stop(){
        shooterPID.setSetpoint(0.0, com.revrobotics.spark.SparkBase.ControlType.kVelocity);
    }

    public double getLeaderRPM() {
    return leader.getEncoder().getVelocity();
}

public double getFollowerRPM() {
    return follower.getEncoder().getVelocity();
}

public double getLeaderCurrent() {
    return leader.getOutputCurrent();
}

public double getFollowerCurrent() {
    return follower.getOutputCurrent();
}

public double getLeaderOutput() {
    return leader.getAppliedOutput();
}

public double getFollowerOutput() {
    return follower.getAppliedOutput();
}

}