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
	private static boolean inMemoryStorage = false;
	private static boolean initialzed      = false;

	public DirectoryHelper() {

		if (DirectoryHelper.initialzed) {
			return;
		}

		DirectoryHelper.initialzed = true;

		DirectoryHelper.dirPath = "";

		dirPath = System.getProperty("user.home");

		if (!DirectoryHelper.dirPath.equals(""))
			DirectoryHelper.dirPath = dirPath + "/";
		if (!DirectoryHelper.dirPath.endsWith("/")) {
			DirectoryHelper.dirPath += "/";
		}

		File file = new File(DirectoryHelper.dirPath + DirectoryHelper.dirPathFileName);
		if (!file.exists()) {
			try {
				setDir("");
			} catch (IOException e) {
				e.printStackTrace();
				// System.out.println("it seems we can't create file in given location");
				DirectoryHelper.inMemoryStorage = true;
				DirectoryHelper.screenShotDirectory = "";
			}
		} else {
			// File exist so check the validity of the content
			try {
				String d = getDir();
				// System.out.println("Dir found :: " + dir);
				file = new File(d);
				if (!file.exists()) {
					setDir("");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	private String getDir(boolean readFromFile) throws FileNotFoundException {
		if (!readFromFile) {
			return getDir();
		}
		String result = "*";
		File file = new File(dirPath + dirPathFileName);
		Scanner in = new Scanner(file);
		if (in.hasNext())
			result = in.nextLine();
		in.close();
		DirectoryHelper.screenShotDirectory = result;
		return result;

	}

	/**
	 * To return the directory for storing screenshots.
	 */
	public String getDir() throws FileNotFoundException {
		if (DirectoryHelper.inMemoryStorage) {
			return DirectoryHelper.screenShotDirectory;
		}

		if (screenShotDirectory == null) {
			return getDir(true);
		}
		return DirectoryHelper.screenShotDirectory;
	}

	/**
	 * To store the selected directory in user/home/.screenShotTaker folder.
	 *
	 */
	public int setDir(String dir) throws IOException {

		if (DirectoryHelper.inMemoryStorage) {
			DirectoryHelper.screenShotDirectory = dir;
			return 1;
		}

		int result = 0;
		File file = new File(DirectoryHelper.dirPath + DirectoryHelper.dirPathFileName);
		FileWriter out = new FileWriter(file);
		out.write(dir);
		result = 1;
		out.close();
		return result;

	}
}
