package test;

import java.util.Comparator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class MyActiveObject{
	BlockingQueue<I> dispatchQueue;
	volatile boolean stop;
	Thread t;
	public MyActiveObject() {
		dispatchQueue  = new PriorityBlockingQueue<I>(10,new Comparator<I>() {
			@Override
			public int compare(I o1, I o2) {
				return o1.getPriority()-o2.getPriority();
			}
		});
	}
	
	public void start(){
		stop=false;
	    t=new Thread(new Runnable() {
	        public void run() {
	          while (!stop) {
	            try {
	              dispatchQueue.take().run(); 	              
	            } catch (InterruptedException e) {}
	          }
	        }
	      });
	    t.start();
	}
	
	public void execute(I i) {
		try {
			dispatchQueue.put(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void stop(){
		stop=true;		
		t.interrupt();
	}
	
	public int getTasksCount(){
		return dispatchQueue.size();
	}
}
