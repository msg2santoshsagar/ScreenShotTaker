package com.alife.screenshot;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;


/*
 * GUI
 */
public class ScreenCaptureGUI {
	
			static JFrame frame;
			static JButton button1;
			static JButton button2;
			static JButton button3;
			static JTextField textField;
			static ScreenCapture screenCapture;
			static JFileChooser chooser;
			static DirectoryHelper dh;
		public static void main(String args[]){
			screenCapture=new ScreenCapture();
			dh=new DirectoryHelper();
			chooser=new JFileChooser();
			
			frame=new JFrame("Screen Shot");
			frame.setSize(300,150);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLayout(null);
			
			button1=new JButton("Capture A");
			button1.setLayout(null);
		    button1.setBounds(10, 10, 100, 25);
		    
		    button2=new JButton("Capture B");
			button2.setLayout(null);
		    button2.setBounds(120, 10, 100, 25);
			
		    button3=new JButton("D");
			button3.setLayout(null);
		    button3.setBounds(225, 85, 50, 25);
			
		    
		    ActionListener actionListener = new ActionListener() {
		        public void actionPerformed(ActionEvent actionEvent) {
		          String command = actionEvent.getActionCommand();
		          
		          if(command.equals("captureA")){
		        	  String result=screenCapture.doCapture();
		        	  textField.setText(result);
		        	  
		          }
		          if(command.equals("captureB")){
		        	  int state=frame.getState();
		        	  frame.setState(Frame.ICONIFIED);
		        	  String result=screenCapture.doCapture();
		        	  textField.setText(result);
		        	  frame.setState(state);
		          }
		          if(command.equals("d")){
		        	  
		        	  chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		        	  
		        	  try {
						String cdr=dh.getDir();
						File f=new File(cdr);
						if(f.exists())
							chooser.setCurrentDirectory(f);
		        	  } catch (FileNotFoundException e) {
						
					}
		        	  
		        	 if( chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
		        	 
		        	  File f=chooser.getSelectedFile();
		        	  try {
						String absPath=f.getAbsolutePath();
		        		 if(absPath.charAt(absPath.length()-1)!='\\')
		        			 absPath=absPath+"\\";
		        		dh.setDir(f.getAbsolutePath()+"/");
						textField.setText("cwd : "+f.getAbsolutePath());
					} catch (IOException e) {
						textField.setText("Error in path selection");
						e.printStackTrace();
					}
		        	  
		          }
		          }
		          
		        }
		      };

		    
		    
		    
		    
		    button1.setActionCommand("captureA");
		    button1.addActionListener(actionListener);
		
		    button2.setActionCommand("captureB");
		    button2.addActionListener(actionListener);
		    
		    button3.setActionCommand("d");
		    button3.addActionListener(actionListener);
		    
		    textField=new JTextField();
			textField.setBounds(10, 40, 250, 25);
			textField.setEditable(false);
			textField.setText("");
		    frame.add(button1);
		    frame.add(button2);
		    frame.add(button3);
			frame.add(textField);
		    frame.setAlwaysOnTop(true);
			frame.setVisible(true);
		}

		
		
}
