package view;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

public class NASAMonitor implements Observer {

	private LinkedList<String> messages;

	public NASAMonitor() {
		super();
		messages = new LinkedList<String>();
	}

	@Override
	public void update(Observable rover, Object roverMessage) {
		messages.addFirst((String) roverMessage);
		updateView();

	}

	private void updateView() {
		System.out.println(messages.getFirst());

	}

}
