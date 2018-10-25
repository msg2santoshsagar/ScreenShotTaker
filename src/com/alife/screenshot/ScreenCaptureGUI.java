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


/**
 * Screen shot taker GUI
 *
 * @author Santosh Sagar
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

		frame=new JFrame("Screen Shot Taker");
		frame.setSize(340,150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setResizable(false);

		button1=new JButton("CAPTURE A");
		button1.setLayout(null);
		button1.setBounds(10, 10, 120, 25);
		button1.setToolTipText("It will take screenshot with current window open.");

		button2=new JButton("CAPTURE B");
		button2.setLayout(null);
		button2.setBounds(200, 10, 120, 25);
		button2.setToolTipText("It will hide current window then take screen shot");

		button3=new JButton("CHOOSE DIRECTORY");
		button3.setLayout(null);
		button3.setBounds(140, 80, 180, 25);
		button3.setToolTipText("Help you to choose directory where you can save the screenshots");


		ActionListener actionListener = new ActionListener() {

			public void actionPerformed(ActionEvent actionEvent) {

				String command = actionEvent.getActionCommand();

				if(command.equals("captureA")){
					String result=screenCapture.doCapture();
					textField.setText(result);
					textField.setToolTipText(textField.getText());
				}

				if(command.equals("captureB")){
					int state=frame.getState();
					frame.setState(Frame.ICONIFIED);
					String result=screenCapture.doCapture();
					textField.setText(result);
					textField.setToolTipText(textField.getText());
					frame.setState(state);
				}

				if(command.equals("chooseDirectory")){

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
							textField.setText("CWD : "+f.getAbsolutePath());
							textField.setToolTipText(textField.getText());
						} catch (IOException e) {
							textField.setText(e.getMessage());
							textField.setToolTipText(textField.getText());
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

		button3.setActionCommand("chooseDirectory");
		button3.addActionListener(actionListener);

		textField=new JTextField();
		textField.setBounds(10, 45, 310, 20);
		textField.setEditable(false);


		textField.setText("CWD : ");


		try {
			String cdr=dh.getDir();
			File f=new File(cdr);
			if(f.exists())
				textField.setText("CWD : "+f.getAbsolutePath());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		textField.setToolTipText(textField.getText());

		frame.add(button1);
		frame.add(button2);
		frame.add(button3);
		frame.add(textField);


		frame.setAlwaysOnTop(true);
		frame.setVisible(true);

	}



}
