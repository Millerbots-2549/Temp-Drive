/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
// import

/**
 * The VM is configured to automatically run this class, and to call the
 * methods corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot
{
    private static final String DEFAULT_AUTO = "Default";
    private static final String CUSTOM_AUTO = "My Auto";
    private String autoSelected;
    private final SendableChooser<String> chooser = new SendableChooser<>();


    //Motors
    private WPI_TalonSRX motorLeft0 = new WPI_TalonSRX(Constants.MOTOR_LEFT_0);
    private WPI_TalonSRX motorLeft1 = new WPI_TalonSRX(Constants.MOTOR_LEFT_1);
    private WPI_TalonSRX motorRight2 = new WPI_TalonSRX(Constants.MOTOR_RIGHT_2);
    private WPI_TalonSRX motorRight3 = new WPI_TalonSRX(Constants.MOTOR_RIGHT_3);
    // private WPI_TalonSRX flyWheel = new WPI_TalonSRX(Constants.FLYWHEEL_MOTOR);


    //Motor Control Groups
    private SpeedControllerGroup motorsLeft = new SpeedControllerGroup(motorLeft0, motorLeft1);
    private SpeedControllerGroup motorsRight = new SpeedControllerGroup(motorRight2, motorRight3);


    //Drivetrain object
    private final DifferentialDrive drive = new DifferentialDrive(motorsLeft, motorsRight);

    //Controller object
    private final Joystick driveCntrl = new Joystick(0);
    // private final JoystickButton shootButton = new JoystickButton(driveCntrl,0);


    /**
     * This method is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit()
    {
        chooser.setDefaultOption("Default Auto", DEFAULT_AUTO);
        chooser.addOption("My Auto", CUSTOM_AUTO);
        SmartDashboard.putData("Auto choices", chooser);
        

        motorLeft0.configFactoryDefault();
        motorLeft1.configFactoryDefault();
        motorRight2.configFactoryDefault();
        motorRight3.configFactoryDefault();
        drive.setRightSideInverted(false);
    }

    /**
     * This method is called every robot packet, no matter the mode. Use
     * this for items like diagnostics that you want ran during disabled,
     * autonomous, teleoperated and test.
     * <p>
     * This runs after the mode specific periodic methods, but before
     * LiveWindow and SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic()
    {
    }

    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendable
     * chooser code works with the Java SmartDashboard. If you prefer the
     * LabVIEW Dashboard, remove all of the chooser code and uncomment the
     * getString line to get the auto name from the text box below the Gyro
     * <p>
     * You can add additional auto modes by adding additional comparisons to
     * the switch structure below with additional strings. If using the
     * SendableChooser make sure to add them to the chooser code above as well.
     */
    @Override
    public void autonomousInit()
    {
        autoSelected = chooser.getSelected();
        // autoSelected = SmartDashboard.getString("Auto Selector",
        // defaultAuto);
        System.out.println("Auto selected: " + autoSelected);
    }

    /**
     * This method is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic()
    {
        switch (autoSelected)
        {
            case CUSTOM_AUTO:
                // Put custom auto code here
                break;
            case DEFAULT_AUTO:
            default:
                // Put default auto code here
                break;
        }
    }

    /**
     * This method is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic()
    {
        drive.arcadeDrive(driveCntrl.getY(), driveCntrl.getX());

        // if(shootButton.get() == true) {
        //     flyWheel.set(0.6);
        // }
        // else{
        //     flyWheel.set(0);
        // }
    }

    /**
     * This method is called periodically during test mode.
     */
    @Override
    public void testPeriodic()
    {
    }
}
