package test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class D implements I{
	I i;
	BlockingQueue<Runnable> dispatchQueue;
	volatile boolean stop;
	Thread t;
	public D(I i) {
		this.i=i;
		dispatchQueue  = new LinkedBlockingQueue<Runnable>();
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
	
	@Override
	public void run() {
		try {
			dispatchQueue.put(()->i.run());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void stop(){
		stop=true;		
		t.interrupt();
	}
}
