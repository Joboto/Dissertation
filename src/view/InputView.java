package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import controller.EventController;

public class InputView extends JPanel {

	private static final long serialVersionUID = 1L;
	private TextField inputField;
	private JButton button;
	private EventController cntrlr;
	
	public InputView(EventController c) {
		setLayout(null);
		
		cntrlr = c;
		inputField = new TextField();
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		add(inputField);
		add(getButton());
		
	}
	
	public JButton getButton(){
		button = new JButton("OK");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.toString());
				cntrlr.addEvent(inputField.getText());
			}
		});
		button.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "pressed");
		button.getInputMap().put(KeyStroke.getKeyStroke("released ENTER"), "released");
		
		return button;
	}
	

}
