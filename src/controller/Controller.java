package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.View;
import model.Model;


public class Controller implements ActionListener {
	
	Model model;
	View view;
	
	public Controller() {
		System.out.println("Controller()");
	}

	public void actionPerformed(ActionEvent e) {
		/*
		System.out.println ("Controller: The " + e.getActionCommand() 
			+ " button is clicked at " + new java.util.Date(e.getWhen())
			+ " with e.paramString " + e.paramString() );
		*/
		System.out.println("Controller: acting on Model: "+e.getSource().toString());
		//model.incrementValue();
		model.compare(view.getInputText(), view.getRegex());
	}
	
	public void addModel(Model m){
		System.out.println("Controller: adding model");
		this.model = m;
	}
	
	public void addView(View v){
		System.out.println("Controller: adding view");
		this.view = v;
	}
	
	
}
