package test;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Controller<T extends Command> {

	private PriorityQueue<T> queue;

	public Controller(Comparator<T> comparator) {
		queue=new PriorityQueue<T>(comparator);
	}
	
	public void insertCommand(T c){
		queue.add(c);
	}
	
	public void executeOne(){
		if(!queue.isEmpty())
			queue.poll().execute();
	}

}
