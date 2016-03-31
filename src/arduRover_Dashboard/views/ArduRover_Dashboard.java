package arduRover_Dashboard.views;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import arduRover_Dashboard.com.GUI_Logic_Interface;
import javax.swing.JCheckBox;

public class ArduRover_Dashboard extends JFrame {

	private JPanel contentPane;
	private JTextField leftMotorPowerTextBox;
	private JTextField rightMotorPowerTextBox;
	private JSlider leftMotorPowerSlider;
	private JSlider rightMotorPowerSlider;
	private JComboBox comPortSelector;
	private JButton connectButton;
	private GUI_Logic_Interface container;
	private JTextArea console;
	private final int MAX_POWER = 255;
	private final int MIN_POWER = 0;
	private JCheckBox leftMotorReverseChkBox;
	private JCheckBox rightMotorReverseChkBox;
	

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public ArduRover_Dashboard(GUI_Logic_Interface container) {
		init();
		createEvents();
		this.container = container;
	}

	////////////////////////////////////////
	//Init all components
	////////////////////////////////////////
	public void init(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		//sliders
		leftMotorPowerSlider = new JSlider();
		rightMotorPowerSlider = new JSlider();
		rightMotorPowerSlider.setValue(0);
		rightMotorPowerSlider.setMaximum(255);
		leftMotorPowerSlider.setValue(0);
		leftMotorPowerSlider.setMaximum(255);
		
		//labels
		JLabel leftMotorPowerLabel = new JLabel("Left Motor Power: ");
		JLabel rightMotorPowerLabel = new JLabel("Right Motor Power: ");
		
		//textboxes
		leftMotorPowerTextBox = new JTextField();
		leftMotorPowerTextBox.setColumns(10);
		leftMotorPowerTextBox.setText("0");
		rightMotorPowerTextBox = new JTextField();
		rightMotorPowerTextBox.setColumns(10);
		rightMotorPowerTextBox.setText("0");
		
		//combobox
		comPortSelector = new JComboBox();
		
		//button
		connectButton = new JButton("Connect!");
		
		//console
		console = new JTextArea();
		console.setFocusable(false);
		console.setEditable(false);
		
		//checkboxes
		leftMotorReverseChkBox = new JCheckBox("Reverse?");
		rightMotorReverseChkBox = new JCheckBox("Reverse?");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(console, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(comPortSelector, 0, 204, Short.MAX_VALUE)
								.addGap(31))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(connectButton)
								.addPreferredGap(ComponentPlacement.RELATED))))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(rightMotorPowerSlider, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
						.addComponent(leftMotorPowerSlider, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(leftMotorReverseChkBox))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(6)
									.addComponent(leftMotorPowerLabel, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(rightMotorPowerTextBox, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)))
							.addGap(13))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(rightMotorReverseChkBox))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(5)
									.addComponent(rightMotorPowerLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(leftMotorPowerTextBox, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)))
							.addGap(13)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(comPortSelector, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(connectButton)
							.addGap(27)
							.addComponent(console, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(leftMotorPowerSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE, false)
								.addComponent(leftMotorPowerLabel, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
								.addComponent(rightMotorPowerTextBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(leftMotorReverseChkBox)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rightMotorPowerSlider, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE, false)
								.addComponent(rightMotorPowerLabel)
								.addComponent(leftMotorPowerTextBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rightMotorReverseChkBox)
					.addGap(48))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	
	////////////////////////////////////////
	//Set up all event handlers
	////////////////////////////////////////
	private void createEvents() {
		//button event listeners
		connectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				container.passPortSelection();
			}
		});
		//slider change listeners
		leftMotorPowerSlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				if(!leftMotorPowerSlider.getValueIsAdjusting()){
					rightMotorPowerTextBox.setText(Integer.toString(leftMotorPowerSlider.getValue()));
					leftMotorPowerChanged(leftMotorPowerSlider.getValue());
				}
			}
		});
		rightMotorPowerSlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				if(!rightMotorPowerSlider.getValueIsAdjusting()){
					leftMotorPowerTextBox.setText(Integer.toString(rightMotorPowerSlider.getValue()));
					rightMotorPowerChanged(rightMotorPowerSlider.getValue());
				}
			}
		});
		
		
		//check box listeners
		leftMotorReverseChkBox.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent event) {
		        JCheckBox cb = (JCheckBox) event.getSource();
		        if (cb.isSelected()) {
		            leftMotorReversed(true);
		        } else {
		            leftMotorReversed(false);
		        }
		    }
		});
		rightMotorReverseChkBox.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent event) {
		        JCheckBox cb = (JCheckBox) event.getSource();
		        if (cb.isSelected()) {
		            rightMotorReversed(true);
		        } else {
		            rightMotorReversed(false);
		        }
		    }
		});
		//text box listeners
		rightMotorPowerTextBox.getDocument().addDocumentListener(new DocumentListener(){
			public void changedUpdate(DocumentEvent e){
				update();
			}
			public void insertUpdate(DocumentEvent e) {
				update();
			}
			public void removeUpdate(DocumentEvent e) {
				update();
			}
			
			private void update() {
			    Runnable doUpdate = new Runnable() {
			        @Override
			        public void run() {
			        	if(!isNumeric(rightMotorPowerTextBox.getText())){
			        		rightMotorPowerTextBox.setText(Integer.toString(0));
			        	}
			        	else if(Integer.parseInt(rightMotorPowerTextBox.getText()) > MAX_POWER){
							rightMotorPowerTextBox.setText(Integer.toString(MAX_POWER));
						}
						else if(Integer.parseInt(rightMotorPowerTextBox.getText()) < MIN_POWER){
							rightMotorPowerTextBox.setText(Integer.toString(MIN_POWER));
						}
						leftMotorPowerSlider.setValue(Integer.parseInt(rightMotorPowerTextBox.getText()));
			        }
			    };       
			    SwingUtilities.invokeLater(doUpdate);
			}
		});
		
		
		leftMotorPowerTextBox.getDocument().addDocumentListener(new DocumentListener(){
			public void changedUpdate(DocumentEvent e){
				update();
			}
			public void insertUpdate(DocumentEvent e) {
				update();
			}
			public void removeUpdate(DocumentEvent e) {
				update();
			}
			
			private void update() {
			    Runnable doUpdate = new Runnable() {
			        @Override
			        public void run() {
			        	if(!isNumeric(leftMotorPowerTextBox.getText())){
			        		leftMotorPowerTextBox.setText(Integer.toString(0));
			        	}
			        	else if(Integer.parseInt(leftMotorPowerTextBox.getText()) > MAX_POWER){
							leftMotorPowerTextBox.setText(Integer.toString(MAX_POWER));
						}
						else if(Integer.parseInt(leftMotorPowerTextBox.getText()) < MIN_POWER){
							leftMotorPowerTextBox.setText(Integer.toString(MIN_POWER));
						}
						rightMotorPowerSlider.setValue(Integer.parseInt(leftMotorPowerTextBox.getText()));
			        }
			    };       
			    SwingUtilities.invokeLater(doUpdate);
			}
		});
	}
	
	//update the items in the com port drop-down
	public void updateComPortSelector(String[] names){
		comPortSelector.removeAllItems();
		for(int i = 0; i < names.length; i++){
			comPortSelector.addItem(names[i]);
		}
	}
	
	public void writeToConsole(String string){
		console.setText(string);
	}
	
	public int getSelectedPortIndex(){
		int index = comPortSelector.getSelectedIndex();
		return index;
	}
	
	//method returns false if str NaN
	private static boolean isNumeric(String str){  
	  try{  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe){  
	    return false;  
	  }  
	  return true;  
	}
	
	private void rightMotorPowerChanged(int newVal){
		container.passRightMotorPower(newVal);
	}
	
	private void leftMotorPowerChanged(int newVal){
		container.passLeftMotorPower(newVal);
	}
	
	private void leftMotorReversed(boolean b) {
		container.passLeftMotorReversedValue(b);
	}
	
	private void rightMotorReversed(boolean b){
		container.passRightMotorReversedValue(b);
	}
}
