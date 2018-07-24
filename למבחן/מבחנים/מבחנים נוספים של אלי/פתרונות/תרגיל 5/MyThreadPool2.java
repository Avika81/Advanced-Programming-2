package test;

import java.util.concurrent.Callable;

public class MyThreadPool2 extends MyThreadPool {

	public MyThreadPool2(int maxThreads) {
		super(maxThreads);
	}
	
	public <V> MyFuture<V> submit(Callable<V> callable){
		MyFuture<V> f=new MyFuture<>();
		execute(()->{
			try {
				f.set(callable.call());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		return f;
	}

}
