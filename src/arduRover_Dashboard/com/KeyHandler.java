package arduRover_Dashboard.com;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	String input = "";
	GUI_Logic_Interface container;
	
	public KeyHandler(GUI_Logic_Interface container){
		this.container = container;
	}

	public void keyTyped(KeyEvent e) {
		// nothing we care about here
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W){
			if(!input.contains("w")){
				input = input + "w";
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_A){
			if(!input.contains("a")){
				input = input + "a";
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_D){
			if(!input.contains("d")){
				input = input + "d";
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_S){
			if(!input.contains("s")){
				input = input + "s";
			}
		}
		
		passKeyboardInput();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W){
			if(input.contains("w")){
				input = input.substring(0, input.indexOf('w')) + input.substring(input.indexOf("w")+1, input.length());
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_A){
			if(input.contains("a")){
				input = input.substring(0, input.indexOf('a')) + input.substring(input.indexOf("a")+1, input.length());
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_D){
			if(input.contains("d")){
				input = input.substring(0, input.indexOf('d')) + input.substring(input.indexOf("d")+1, input.length());
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_S){
			if(input.contains("s")){
				input = input.substring(0, input.indexOf('s')) + input.substring(input.indexOf("s")+1, input.length());
			}
		}
		
		passKeyboardInput();
		
	}
	
	//pass keyboard input string out to the GUI-Logic container
	public void passKeyboardInput(){
		container.passKeyboardInputToLogic(input);
	}
	

}
