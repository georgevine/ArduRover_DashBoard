package arduRover_Dashboard.com;

import java.awt.EventQueue;

import arduRover_Dashboard.views.ArduRover_Dashboard;

public class GUI_Logic_Interface {
	
	static ArduRover_Dashboard frame;
	static BlueToothComm blueTooth; 
	public static KeyHandler keys;
	

	public GUI_Logic_Interface() {
		final GUI_Logic_Interface container = this;
		keys = new KeyHandler(container);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ArduRover_Dashboard(container);
					frame.setVisible(true);
					blueTooth = new BlueToothComm(container);
					frame.updateComPortSelector(blueTooth.getPortNames());
					frame.addKeyListener(keys);
					frame.setFocusable(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//passes the port that was selected in the gui back to the BlueToothComm object
	public static void passPortSelection(){
		int index = frame.getSelectedPortIndex();
		blueTooth.connect(index);
	}
	
	//passes the status of the serial connection from the BlueToothComm object
	//to the GUI
	public static void passConnectionStatusToGUI(String message){
		frame.writeToConsole(message);
	}
	
	//pass keyboard input strings from the GUI to the BlueToothComm object
	public static void passKeyboardInputToLogic(String input){
		blueTooth.setCommand(input);
	}

	public void passRightMotorPower(int newVal) {
		blueTooth.setRightMotorPower(newVal);
	}

	public void passLeftMotorPower(int newVal) {
		blueTooth.setLeftMotorPower(newVal);
	}

	public void passLeftMotorReversedValue(boolean b) {
		blueTooth.setLeftMotorReversedValue(b);
	}

	public void passRightMotorReversedValue(boolean b) {
		blueTooth.setRightMotorReversedValue(b);
	}
	

}
