package test;

import java.util.ArrayList;

public class MyThreadPool {

	int maxThreads;
	ArrayList<MyActiveObject> pool;
	
	public MyThreadPool(int maxThreads) {
		this.maxThreads=maxThreads;
		pool=new ArrayList<>();
	}
	
	public void execute(Runnable r){
		if(pool.size()<maxThreads){
			MyActiveObject ao=new MyActiveObject();
			ao.execute(()->r.run());
			ao.start();
			pool.add(ao);
		} else{
			int min=Integer.MAX_VALUE;
			MyActiveObject leastBusey=null;
			for(MyActiveObject ao : pool){
				if(ao.dispatchQueue.size()<min){
					min=ao.dispatchQueue.size();
					leastBusey=ao;
				}
			}
			leastBusey.execute(()->r.run());
		}
	}
	
	public int getActiveThreadsCount() {
		return pool.size();
	}
	
	public void stop(){
		for(MyActiveObject ao : pool)
			ao.stop();
	}
}
