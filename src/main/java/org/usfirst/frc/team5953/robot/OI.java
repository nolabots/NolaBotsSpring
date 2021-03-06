
package org.usfirst.frc.team5953.robot;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {


    public OI(Robot redo) {

    	// You can put Some buttons on the SmartDashboard
    	//maybe we can add buttons onto the dashboard for extra functionality?
    	
        //connect some buttons to commands
    	//here is an example
        if (redo.xboxClear.getAButton()){
        	redo.driveForward();
        }
        
        if(!redo.xboxClear.getAButton()){
        	redo.makeItStop();
        }
        
        if(redo.xboxClear.getRightBumper()){
        	redo.autonomousInit();
        	redo.autonomousPeriodic();
        }

       //for the rest of the controls for an XboxController, 
       //add methods to control the robot
        
    }
    
    
}

