package org.usfirst.frc.team5953;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.HLUsageReporting;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary;
import edu.wpi.first.wpilibj.communication.UsageReporting;
import edu.wpi.first.wpilibj.internal.HardwareHLUsageReporting;
import edu.wpi.first.wpilibj.internal.HardwareTimer;
import org.h2.server.web.WebServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.jar.Manifest;

@SpringBootApplication
@ComponentScan(basePackages={"org.usfirst.frc.team5953"})
@EnableJpaRepositories
public class Application {
    
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		initializeHardwareConfiguration();

		UsageReporting.report(FRCNetworkCommunicationsLibrary.tResourceType.kResourceType_Language, FRCNetworkCommunicationsLibrary.tInstances.kLanguage_Java);

		String robotName = "";
		Enumeration<URL> resources = null;
		try {
			resources = RobotBase.class.getClassLoader().getResources("META-INF/MANIFEST.MF");
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (resources != null && resources.hasMoreElements()) {
			try {
				Manifest manifest = new Manifest(resources.nextElement().openStream());
				robotName = manifest.getMainAttributes().getValue("Robot-Class");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		RobotBase robot;
		try {
			robot = (RobotBase) Class.forName(robotName).newInstance();
		} catch (Throwable t) {
			DriverStation.reportError("ERROR Unhandled exception instantiating robot " + robotName + " "
					+ t.toString() + " at " + Arrays.toString(t.getStackTrace()), false);
			System.err.println("WARNING: Robots don't quit!");
			System.err.println("ERROR: Could not instantiate robot " + robotName + "!");
			System.exit(1);
			return;
		}

		File file = null;
		FileOutputStream output = null;
		try {
			file = new File("/tmp/frc_versions/FRC_Lib_Version.ini");

			if (file.exists())
				file.delete();

			file.createNewFile();

			output = new FileOutputStream(file);

			output.write("2016 Java Release 5".getBytes());

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException ex) {
				}
			}
		}

		boolean errorOnExit = false;
		try {
			System.out.println("********** Robot program starting **********");
			robot.startCompetition();
		} catch (Throwable t) {
			DriverStation.reportError(
					"ERROR Unhandled exception: " + t.toString() + " at "
							+ Arrays.toString(t.getStackTrace()), false);
			errorOnExit = true;
		} finally {
			// startCompetition never returns unless exception occurs....
			System.err.println("WARNING: Robots don't quit!");
			if (errorOnExit) {
				System.err
						.println("---> The startCompetition() method (or methods called by it) should have handled the exception above.");
			} else {
				System.err.println("---> Unexpected return from startCompetition() method.");
			}
		}
		System.exit(1);
	}

	/**
	 * Common initialization for all robot programs.
	 */
	public static void initializeHardwareConfiguration() {
		FRCNetworkCommunicationsLibrary.FRCNetworkCommunicationReserve();

		// Set some implementations so that the static methods work properly
		Timer.SetImplementation(new HardwareTimer());
		HLUsageReporting.SetImplementation(new HardwareHLUsageReporting());
		RobotState.SetImplementation(DriverStation.getInstance());
	}

	/**
	 * Loads a (autogenerated) developer web interface mapped to DOMAIN/console.
	 * @return
	 */
    @Bean
    public ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
        registration.addUrlMappings("/console/*");
        return registration;
    }
}



