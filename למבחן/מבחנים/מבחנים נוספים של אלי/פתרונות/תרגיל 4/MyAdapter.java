package test;

public class MyAdapter extends MyPlayer implements Runnable{
	//@Override
	public void run() {
		stop();
		rewind();
		play();
	}
}
