import controller.Controller;
import view.View;
import model.Model;


public class RunMVC {
	
	private int start_value = 10;
	
	public RunMVC(){
		
		Model myModel = new Model();
		View myView = new View();
		
		myModel.addObserver(myView);
		
		Controller myController = new Controller();
		myController.addModel(myModel);
		myController.addView(myView);
		//myController.initModel(start_value);
		
		myView.addController(myController);
		//this was only needed when the view inits the model
		//myView.addModel(myModel);
	}

}
