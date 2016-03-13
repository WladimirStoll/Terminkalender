package de.stoll.wladimir.terminkalender;

import java.awt.EventQueue;

import de.stoll.wladimir.terminkalender.controller.MainController;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainController mainController = new MainController();
					mainController.setVisible();
					//mainController.window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
