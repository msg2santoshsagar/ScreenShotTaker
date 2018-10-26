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
	private static final String dirPathFileName = ".screenShotTaker";

	private static String screenShotDirectory = null;

	public DirectoryHelper() {

		dirPath = "";

		dirPath = System.getProperty("user.home");

		if (!dirPath.equals(""))
			dirPath = dirPath + "/";
		if (!dirPath.endsWith("/")) {
			dirPath += "/";
		}

		File file = new File(dirPath + dirPathFileName);
		if (!file.exists()) {
			try {
				setDir("");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			// File exist so check the validity of the content
			//System.out.println("Check validity of the content");
			try {
				String dir = getDir();
				//System.out.println("Dir found :: " + dir);
				file = new File(dir);
				if (!file.exists()) {
					setDir("");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public String getDir(boolean readFromFile) throws FileNotFoundException {
		if (!readFromFile) {
			return getDir();
		}
		String result = "*";
		File file = new File(dirPath + dirPathFileName);
		Scanner in = new Scanner(file);
		if (in.hasNext())
			result = in.nextLine();
		in.close();
		screenShotDirectory = result;
		return result;

	}

	public String getDir() throws FileNotFoundException {
		if (screenShotDirectory == null) {
			return getDir(true);
		}
		return screenShotDirectory;
	}

	/**
	 * To store the selected directory in user/home/.screenShotTaker folder.
	 *
	 */
	public int setDir(String dir) throws IOException {
		//System.out.println("Set dir called :: "+dir);
		int result = 0;
		File file = new File(dirPath + dirPathFileName);
		FileWriter out = new FileWriter(file);
		out.write(dir);
		result = 1;
		out.close();
		return result;

	}
}
