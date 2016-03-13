package de.stoll.wladimir.terminkalender.controller;

import java.awt.EventQueue;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import sun.org.mozilla.javascript.internal.ast.ThrowStatement;

import de.stoll.wladimir.terminkalender.exceptions.WrongOldWeekOfYearException;
import de.stoll.wladimir.terminkalender.model.MainModel;
import de.stoll.wladimir.terminkalender.view.MainView;

public class MainController {
	
	MainView mainView;
	MainModel mainModel;
	private CommandQueue commandQueue;

	public MainModel getMainModel() {
		if (mainModel==null){
			mainModel = new MainModel(getMainView());
		}
		return mainModel;
	}

	public MainView getMainView() {
		if (mainView==null){
			mainView = new MainView(this);
		}
		return mainView;
	}

	public MainController() {
		super();
	}
	
	public void setVisible(){
		getMainView();
		getMainModel();
		getMainView().getFrame().setVisible(true);
	}
	
	/**
	 * Der neue Befehl wird erzeugt, der Queue angehängt,
	 * 	alle Befehl der Queue werden verarbeitet, 
	 * falls offene vorhanden(also mindestens der akteulle)
	 * @param weekOfYear << Die vom Benutzer ausgewählte Woche
	 */
	public void setWoche(int weekOfYear) {
		
		getCommandQueue().addCommand(new ICommand() {

			private Integer weekOfYear;
			private Integer oldWeekOfYear;
			private String errorMessage;

			@Override
			public void unRun() {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try{
							if (oldWeekOfYear!=null){
								getMainModel().setSelectedWeekOfYear(oldWeekOfYear);
							}else{
								throw new WrongOldWeekOfYearException("No old week of year!");
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}

			@Override
			public void run() {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							int oldWeekOfYear = getMainModel().getSelectedWeekOfYear();
							getMainModel().setSelectedWeekOfYear(weekOfYear);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}

			@Override
			public ICommand initialize(Object weekOfYear) {
				this.weekOfYear = (Integer) weekOfYear;
				return this;
			}

			@Override
			public void setErrorString(String errorMessage) {
				this.errorMessage = errorMessage;
			}

			@Override
			public String getErrorString() {
				if (errorMessage==null){
					errorMessage = "Unbekannter Fehler";
				}
				return errorMessage;
			}

		}.initialize(weekOfYear)).runAll(); // alle offenen Befehle abarbeiten.
	}

	public CommandQueue getCommandQueue() {
		if (commandQueue==null){
			commandQueue = new CommandQueue();
		}
		return commandQueue;
	}

}
