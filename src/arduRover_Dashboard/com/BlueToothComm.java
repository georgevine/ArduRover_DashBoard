package arduRover_Dashboard.com;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.SynchronousQueue;

import com.fazecast.jSerialComm.SerialPort;
/////////////////////////////////////////////////////////
//This class was created for the purpose of implementing
//bluetooth-based serial communication in a gui to command
//a remote controlled vehicle
//Author: George Vine
//Date: 02/16/16
///////////////////////////////////////////////////////////
public class BlueToothComm implements Runnable {
	SerialPort[] portsList;
	SerialPort connectedPort;
	GUI_Logic_Interface container;
	String command = " ";
	String threadName = "Logic Thread";
	private Thread logicThread;
	public ConcurrentLinkedQueue<CommandPasser> commandPassers = new ConcurrentLinkedQueue<CommandPasser>();
	
	boolean newDriveDelay = false;
	boolean newSteerDelay = false;
	int driveDelay = 50;
	int steerDelay = 50;
	
	public BlueToothComm(GUI_Logic_Interface container){
		portsList = SerialPort.getCommPorts();
		connectedPort = null;
		container = this.container;
	}
	
	//return a list of the com ports that we can access
	public String[] getPortNames(){
		String[] names = new String[portsList.length];
		for(int i = 0; i < names.length; i++){
			names[i] = portsList[i].getDescriptivePortName();
		}
		
		return names;
	}
	
	public void connect(int portIndex){
		String statusMessage = "";
		connectedPort = portsList[portIndex];
		connectedPort.openPort();
		if(connectedPort.isOpen()){
			statusMessage = "Connection Established!";
			start();
		}
		else{
			statusMessage = "Connection Failed.";
		}
		container.passConnectionStatusToGUI(statusMessage);
	}
	
	//function run by I/O thread
	public void run(){
		CommandPasser pass = null;
		System.out.println("starting");
		while(true){
			if(commandPassers.peek()!=null){
				pass = commandPassers.poll();
				command = pass.getCommand();
			}
			write(command);
			
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	//start the I/O thread
	void start(){
		if(logicThread == null){
			logicThread = new Thread(this, threadName);
			logicThread.start();
		}
	}

	//append the command with a header denoting it's length and a serperator between
	//the header and the command body, and send it over the serial connection
	public void write(String command) {
		//int commandLength = command.length();
		//String commandLengthString = Integer.toString(commandLength);
		//command = commandLengthString +"/" + command;
		//commandLength = 0;
		
		
		// Set port to scanner mode
        connectedPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
        //open output stream
        try (PrintWriter output = new PrintWriter(connectedPort.getOutputStream())) {
            try {
            	System.out.println(command);
                output.write(command);
                output.flush();
            } catch (Exception e) {
                System.out.println("Failed to send");
            }
        }

	}
	
	//pass the command string into the commandPasser which will be read by the I/O thread
	public void setCommand(String command){
		CommandPasser pass = new CommandPasser();
		pass.setCommand(command);
		commandPassers.add(pass);
	}

	//an integer inside of <> will be interpreted by the arduino as a new steer
	//delay value
	public void setRightMotorPower(int newVal) {
		setCommand(">"+Integer.toString(newVal));
	}

	//an integer inside of ^V will be interpreted by the arduino as a new
	//drive delay value
	public void setLeftMotorPower(int newVal) {
		setCommand("<" + Integer.toString(newVal));
	}

	public void setLeftMotorReversedValue(boolean b) {
		if(b){
			setCommand("lr");
		} else{
			setCommand("lf");
		}
	}
	
	public void setRightMotorReversedValue(boolean b) {
		if(b){
			setCommand("rr");
		} else{
			setCommand("rf");
		}
	}
}
