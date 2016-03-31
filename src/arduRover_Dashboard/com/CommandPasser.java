package arduRover_Dashboard.com;

/****************************************************/
/*The purpose of this class is to serve as a data structure
 * for passing command strings back and forth between a main
 * thread and an I/O thread. This object will be added to a 
 * SynchronousQueue to achieve this effect
 /****************************************************/
public class CommandPasser {
	private String command;
	
	public CommandPasser(){
		command = "";
	}
	
	public void setCommand(String command){
		this.command = command;
	}
	
	public String getCommand(){
		return command;
	}
}
