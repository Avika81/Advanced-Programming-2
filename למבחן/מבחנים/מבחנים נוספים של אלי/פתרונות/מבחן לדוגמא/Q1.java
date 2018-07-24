package test;

public class Q1 {

	public static void a(){
		A a=new A();		
		while(a.m1()<10);
		while(a.m2()<10){
			Thread t1=new Thread(()->a.m3());
			Thread t2=new Thread(()->a.m4());
			Thread t3=new Thread(()->a.m5());
			t1.start();
			t2.start();
			t3.start();
			try {
				t1.join();
				t2.join();
				t3.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void b(){
		A a1=new A();
		A a2=new A();
		
		Thread t1=new Thread(()->a1.m1());
		Thread t2=new Thread(()->a2.m1());
		t1.start();
		t2.start();
		try {
			t1.join(0);
			t2.join(0);
		} catch (InterruptedException e) {}
		for(int i=0;i<5;i++)
			a1.m2();
		a2.m3();
	}
	
	public static String c(){
		String[] answer="IOC,SOC,DIP,DI".split(",");
		return answer[0];
	}
}
