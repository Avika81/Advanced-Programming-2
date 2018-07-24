package test;

import java.util.Observable;
import java.util.Observer;

public  abstract class Task extends Observable implements Observer{

	abstract void theTask();
	
	// triggers another task on completing the task
	public void triggers(Task t){
		addObserver(t);
	}
	
	public void run(){
		theTask();
		setChanged();
		notifyObservers();
	}

	@Override
	public void update(Observable o, Object arg) {
		run();
	}
	
}
