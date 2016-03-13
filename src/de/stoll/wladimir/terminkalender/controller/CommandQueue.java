package de.stoll.wladimir.terminkalender.controller;

import java.util.ArrayList;

public class CommandQueue {

	private ArrayList<ICommand> commands;
	private ArrayList<ICommand> wrongCommands; //die Command, welche mit Fehlern beendet wurden.
	private ICommand lastCommand; //das letzte ausgeführte Command
	
	public ArrayList<ICommand> getCommands() {
		if (commands==null){
			commands = new ArrayList<ICommand>();
		}
		return commands;
	}

	public CommandQueue() {
		super();
	}

	public CommandQueue addCommand(ICommand iCommand) {
		getCommands().add(iCommand);
		return this;
	}

	
	public void runAll() {
		//alle Befehle nach dem letzten Command ausführen, falls vorhanden
		while(runNextCommand()){
			System.out.println("run");
		};
	}

	/**
	 * führt den nexten anstehenden Befehl, falls vorhanden 
	 * @return
	 */
	private boolean runNextCommand() {
		
		//Position des letzten Befehls in der Queue finden
		ICommand iNextCommand=null;
		if (getLastCommand()==null&&getCommands().size()>0){
			iNextCommand = getCommands().get(0); //ab dem ersten Befehl ausführen, falls noch nix ausgeführt
		} else {
			int indexOfLastCommand = getCommands().indexOf(getLastCommand());
			if (indexOfLastCommand < getCommands().size()-1){
				//nach dem letzten Befehl gibt den nächsten noch nicht ausgeführten Befehl
				iNextCommand = getCommands().get(indexOfLastCommand+1);
			}
		}
		if (iNextCommand!=null){
			try {
				iNextCommand.run(); //Befehl ausführen
				setLastCommand(iNextCommand); //letzen ausgeführten Befehl setzen.
				return true;
			} catch (Exception e) {
				getCommands().remove(iNextCommand); //Der Befehl konnte nicht ausgführt werden. Weg damit!
				iNextCommand.setErrorString(e.getMessage());
				getWrongCommands().add(iNextCommand);//Fehlerhafte Befehle werden extra abgespeichert
				e.printStackTrace();
				return false; //Ausführung nicht möglich
			}
		}
		
		return false; //kein Befehl
	}

	/**
	 * Liefert das erste Commando in der Queue, sofern vorhanden. Sonst null
	 * @return
	 */
	private ICommand getFirstCommand() {
		if (getCommands().size()>0){
			return getCommands().get(0);
		}else return null;
	}

	public ICommand getLastCommand() {
		return lastCommand;
	}

	public void setLastCommand(ICommand lastCommand) {
		this.lastCommand = lastCommand;
	}

	public ArrayList<ICommand> getWrongCommands() {
		if (wrongCommands==null){
			wrongCommands = new ArrayList<ICommand>();
		}
		return wrongCommands;
	}


}
