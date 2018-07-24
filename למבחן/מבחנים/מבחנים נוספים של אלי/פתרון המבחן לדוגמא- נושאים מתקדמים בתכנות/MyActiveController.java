package controller;

import java.util.Comparator;
import java.util.PriorityQueue;


public class MyActiveController {

	public interface Command{
		public void execute();		
		default int getPriority(){return 0;}
	}
	
	volatile boolean stop;	// 2 points
	
	
	Thread activeThread;
	
	PriorityQueue<Command> queue;	// 2 points
	
	public MyActiveController() {
		stop=false;
		queue=new PriorityQueue<>(new Comparator<Command>() {	// 5 points
			@Override
			public int compare(Command o1, Command o2) {
				return o1.getPriority()-o2.getPriority();
			}
		});
		
		activeThread=new Thread(()->{	// 10 points
			while(!stop){
				while(!queue.isEmpty())
					queue.poll().execute();
				
				try { Thread.sleep(Long.MAX_VALUE);} catch (InterruptedException e) {}
			}
		});
		
		activeThread.start();
	}
	
	public void insertCommand(Command c) throws Exception{ // 12 points:
		if(!stop) // // 2 points
			synchronized(this){ // 5 points
				queue.add(c); // 1 point
				activeThread.interrupt();	// 2 points
			}
		else
			throw new Exception("cannot insert new commands after the controller has stopped"); // 2 points
	}
	
	public void stop(){ // 4 points
		stop=true;
		activeThread.interrupt();
	}
	
}
