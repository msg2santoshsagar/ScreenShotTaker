package com.alife.screenshot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * A class to manage directory for screenshot selection.
 *
 * @author Santosh Sagar
 */

public class DirectoryHelper {

	private static String dirPath;
	private static final String dirPathFileName=".screenShotTaker";

	public DirectoryHelper() {
		dirPath="";
		dirPath=System.getProperty("user.home");
		if(!dirPath.equals(""))
			dirPath=dirPath+"\\";
	}

	public String getDir() throws FileNotFoundException{
		String result="*";
		File file=new File(dirPath+dirPathFileName);
		Scanner in=new Scanner(file);
		if(in.hasNext())
			result=in.nextLine();
		in.close();
		return result;

	}

	/**
	 * To store the selected directory in user/home/.screenShotTaker folder.
	 *
	 */
	public int setDir(String dir) throws IOException{
		int result=0;
		dirPath="C:\\Program Files (x86)\\Adobe";
		File file=new File(dirPath+dirPathFileName);
		if( !file.exists() && !file.canWrite() ) {
			System.out.println("File doesn't exist and write permission is not allowd");
			throw new IOException("Permission denied for path : "+file.getAbsolutePath());
		}
		FileWriter out=new FileWriter(file);
		out.write(dir);
		result=1;
		out.close();
		return result;

	}
}
