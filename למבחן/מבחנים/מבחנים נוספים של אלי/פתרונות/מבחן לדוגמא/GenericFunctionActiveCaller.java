package test;

import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class GenericFunctionActiveCaller {

	
	public interface F<P,R>{
		R apply(P p);
	}
	
	public class Future<V>{
		V v;
		public synchronized  void set(V v){
			this.v=v;
			notifyAll();
		}
		
		public V get(){
			if(v==null){
				synchronized (this) {
					try {
						wait();
					} catch (InterruptedException e) {}
				}
			}
			return v;
		}
	}
	
	
	HashMap<String,F> commands;
	BlockingQueue<Runnable> dispatchQueue;	
	volatile boolean stop;
	Thread t;
	
	public GenericFunctionActiveCaller() {
		commands=new HashMap<>();
		dispatchQueue = new LinkedBlockingQueue<Runnable>();
		stop=false;
		t=new Thread(()->{
			while(!stop){
		          try {
		              dispatchQueue.take().run(); 
		            } catch (InterruptedException e) {}
			}
		});
		t.start();
	}
	
	public <P,R> void addFunction(String key, F<P,R> a){		
		commands.put(key,a);
	}
	
	public <P,R> Future<R> exec(String key,P params){
		@SuppressWarnings("unchecked")
		F<P,R> func=commands.get(key);
		if(func!=null){
			Future<R> f=new Future<>(); 		
			try {
				dispatchQueue.put(()->f.set(func.apply(params)));
			} catch (InterruptedException e) {}
			
			return f;
		}
		return null;
	}
	
	public void stop(){
		stop=true;
		t.interrupt();
	}
}
