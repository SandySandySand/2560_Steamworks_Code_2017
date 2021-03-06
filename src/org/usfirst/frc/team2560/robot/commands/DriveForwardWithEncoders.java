package org.usfirst.frc.team2560.robot.commands;

import org.usfirst.frc.team2560.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForwardWithEncoders extends Command {

    private double requiredDistance;
	private double power;
    
	public DriveForwardWithEncoders(double requiredDistance, double power) 
    {
        requires(Robot.drivetrain);
        this.requiredDistance = requiredDistance;
        this.power = power;
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	Robot.drivetrain.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	double endLeftDistance = Math.abs(Robot.drivetrain.getLeftEncoderPosition());
    	double endRightDistance = Robot.drivetrain.getRightEncoderPosition();
    	
    	while(endLeftDistance < requiredDistance && endRightDistance < requiredDistance);
    	{
    		endLeftDistance = Robot.drivetrain.getLeftEncoderPosition();
    		endRightDistance = Robot.drivetrain.getRightEncoderPosition();
    		Robot.drivetrain.tankDrive(power, power);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	Robot.drivetrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    	end();
    }
}
