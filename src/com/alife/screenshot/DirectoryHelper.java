package com.alife.screenshot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DirectoryHelper {

	private static String dirPath;
	private static final String dirPathFileName=".scrDirectory"; 
	
	public DirectoryHelper() {
		dirPath="";
		dirPath=System.getProperty("user.home");
		if(!dirPath.equals(""))
			dirPath=dirPath+"\\";
	}
	
	/*public static void main(String[] args) throws IOException {
		DirectoryHelper dh=new DirectoryHelper();
		dh.setDir("d:/");
		System.out.println(dh.getDir());
	}*/
	public String getDir() throws FileNotFoundException{
		String result="*";
		File file=new File(dirPath+dirPathFileName);
		Scanner in=new Scanner(file);
		if(in.hasNext())
			result=in.nextLine();
		in.close();
		return result;
			
	}
	
	/*
	 * This Method will store the selected directory in user/Home/.directory folder. 
	 * 
	 * @Param dir String
	 * @author Santosh Sagar
	 * @version 1.0.0
	 */
	public int setDir(String dir) throws IOException{
		int result=0;
		File file=new File(dirPath+dirPathFileName);
		FileWriter out=new FileWriter(file);
		out.write(dir);
		result=1;
		out.close();
		return result;
			
	}
}
