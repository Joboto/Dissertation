package view;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.Label;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class View extends JPanel implements Observer {
	
	private TextField myTextField;
	private TextField myRegexField;
	private Label myOutput;
	private Button button;
	
	//private Model model;		//needed only if view initialises model - not doing in this iteration

	public View() {
		System.out.println("View()");
		
		Frame frame = new Frame("simple MVC");
		frame.setLayout(new GridLayout(5, 1));
		
		frame.add(new Label("NL Text"));
		myTextField = new TextField();
		frame.add(myTextField);
		
		frame.add(new Label("Regex to search"));
		myRegexField = new TextField();
		frame.add(myRegexField);
		
		//panel doesn't need to visible to whole class
		Panel panel = new Panel();
		button = new Button("PressMe"){public String toString(){return "this is my source, there are many like it but this one is mine";}};
		myOutput = new Label();
		panel.add(button);
		panel.add(myOutput);
		frame.add(panel);
		
		frame.addWindowListener(new CloseListener());
		frame.setSize(200, 100);
		frame.setLocation(100, 100);
		frame.setVisible(true);
		
	}

	public void update(Observable obs, Object obj) {

		//who called us and what did they send?
		//System.out.println ("View      : Observable is " + obs.getClass() + ", object passed is " + obj.getClass());

		//model Pull 
		//ignore obj and ask model for value, 
		//to do this, the view has to know about the model (which I decided I didn't want to do)
		//uncomment next line to do Model Pull
		//myTextField.setText("" + model.getValue());

		//model Push 
		//parse obj
		//myTextField.setText("" + ((Integer)obj).intValue());	//obj is an Object, need to cast to an Integer
		System.out.println("updating output field");
		myOutput.setText(((String)obj).trim());

	}
	
	public void setValue(int v){
		myTextField.setText("" + v);
	}
	
	public void addController(ActionListener controller){
		System.out.println("View : adding controller");
		button.addActionListener(controller);
	}
	
	public String getInputText(){
		return myTextField.getText();
	}
	
	public String getRegex(){
		return myRegexField.getText();
	}
	
	//uncomment to allow controller to use view to initialise model	
	//public void addModel(Model m){
	//	System.out.println("View      : adding model");
	//	this.model = m;
	//}
	
	public static class CloseListener extends WindowAdapter {
		public void windowClosing(WindowEvent e){
			e.getWindow().setVisible(false);
			System.exit(0);
		}
	}

}
