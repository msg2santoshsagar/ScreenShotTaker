package com.alife.screenshot;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

/**
 * This Class will help to take the screen shot.
 *
 * @author Santosh Sagar
 */

public class ScreenCapture {

	/**
	 * This will take screenshot and save the image in selected directory
	 */
	public  String doCapture() {

		String result="Default";

		try {

			Robot robot = new Robot();

			String format = "jpg";
			String fileName ;
			String directory="";

			try{
				directory=new DirectoryHelper().getDir();
				System.out.println("Got the directory :: "+directory);
			}catch (Exception e){
				System.err.println("Error occured whle getting user directory");
				e.printStackTrace();
			}

			Date date=new Date();
			SimpleDateFormat dateFormat=new SimpleDateFormat("d-M-y");
			SimpleDateFormat dateFormat2=new SimpleDateFormat("H:mm:ss");
			String dateString=dateFormat.format(date);
			String dateString2=dateFormat2.format(date);
			fileName=dateString;
			String subFileName="0";
			File f=new File(directory+fileName+"_"+subFileName+"."+format);

			for(int i=1;f.exists();i++){
				subFileName=String.valueOf(i);
				f=new File(directory+fileName+"_"+subFileName+"."+format);
			}

			fileName=fileName+"_"+subFileName;
			//System.out.println("File Path :: "+f.getAbsolutePath());
			//System.out.println(fileName+"."+format);

			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
			ImageIO.write(screenFullImage, format, new File(directory+fileName+"."+format));

			result = "captured successfully At "+dateString2;
			//System.out.println("A full screenshot saved!");
		} catch (IOException ex) {
			//System.err.println(ex);
			result="error occured";
		}
		catch (AWTException ex) {
			//System.err.println(ex);
			result="error occured";
		}

		return result;
	}
}

