package test;

import java.util.Observable;
import java.util.Observer;

import test.GenericFunctionActiveCaller.Future;

public class MainAPItest {

	public static void main(String[] args) {
		test1();
		test2();
		test3();
		test4();
	}

	private static void test4() {
		if(Q4.q1() || Q4.q2() || Q4.q3())
			System.out.println("ok");			
	}

	private static void test3() {
		Task t1=new Task() {		
			@Override
			void theTask() {
				System.out.println("taks1...");
			}
		};
		Task t2=new Task() {		
			@Override
			void theTask() {
				System.out.println("taks2...");
			}
		};
		Task t3=new Task() {		
			@Override
			void theTask() {
				System.out.println("taks3...");
			}
		};
		
		t1.triggers(t2);
		t2.triggers(t3);

		t1.run();
	}

	private static void test2() {
		GenericFunctionActiveCaller gfc=new GenericFunctionActiveCaller();
		gfc.addFunction("mul2", (Double x)->x*2);
		gfc.addFunction("print", (Object x)->{System.out.println(x.toString()); return x.toString();});
		gfc.addFunction("sqr", (Double x)->x*x);
		gfc.addFunction("len", (String x)->x.length());
				
		Future<Integer> f  = gfc.exec("len", "bla bla");
		System.out.println("this is done in parallel"); 
		System.out.println(f.get()); // 7
		
		gfc.exec("print",gfc.exec("mul2", gfc.exec("sqr",2.0).get()).get()).get();
		
		gfc.stop();
	}

	private static void test1() {
		Q1.a();		
		Q1.b();
		Q1.c();		
	}

}
