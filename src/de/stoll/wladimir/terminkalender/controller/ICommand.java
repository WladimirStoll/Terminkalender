package de.stoll.wladimir.terminkalender.controller;

public interface ICommand {
	public ICommand initialize(Object o);
	public void run();
	public void unRun();
	public void setErrorString(String errorMessage);
	public String getErrorString();
}
