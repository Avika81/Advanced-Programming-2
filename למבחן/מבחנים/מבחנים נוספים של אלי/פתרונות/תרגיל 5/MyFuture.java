package test;

public class MyFuture<V> {
	
	V v;
	public synchronized void set(V v){
		this.v=v;
		notifyAll();
	}
	
	public V get(){
		if(v==null)
			synchronized (this) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		return v;
	}

}
