package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import controller.EventController;

/**
 * Text box for input. Button click, or key stroke 'RETURN', passes text box content to Controller. 
 *
 */
public class InputView extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextArea inputField;
	private JButton button;
	private EventController cntrlr;
	
	public InputView(EventController c) {
		
		cntrlr = c;
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		add(getInputField());
		add(getButton());
		
		
	}
	
	public JButton getButton(){
		button = new JButton("OK");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cntrlr.addEvent(inputField.getText());
				inputField.setText("");
			}
		});
		//button.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "pressed");
		//button.getInputMap().put(KeyStroke.getKeyStroke("released ENTER"), "released");
		
		return button;
	}

	public JTextArea getInputField() {
		inputField = new JTextArea();
		inputField.setLineWrap(true);
		inputField.setWrapStyleWord(true);
		inputField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					e.consume();
					button.doClick();
				}
				if(e.getKeyCode() == KeyEvent.VK_TAB){
					e.consume();
					transferFocus();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					e.consume();
					button.doClick();
				}
				if(e.getKeyCode() == KeyEvent.VK_TAB){
					e.consume();
					transferFocus();
				}
			}
		});
		return inputField;
	}

	
}
