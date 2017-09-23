package org.deeaae.learnings.app;

import org.deeaae.learnings.app.ds.DSApplication;

public class Application {
public static void main(String[] args) {
		/*System.out.println("Starting App");
		MessageProvider messageProvider = new MessageProvider();
		String message = messageProvider.getMessage();
		System.out.println("Message : " + message);

		ClientApp clientApp = new ClientApp();
		clientApp.init();
		clientApp.writeData();
		System.out.println("AeroRecord Written");*/

		/*IronmanClient ironmanClient = new IronmanClient();
		ironmanClient.test();*/

		DSApplication dsApplication = new DSApplication();
		dsApplication.execute();

	}
}