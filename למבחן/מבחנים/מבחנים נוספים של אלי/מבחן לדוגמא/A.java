package test;

import java.util.concurrent.ArrayBlockingQueue;

public class A {

	public static int r,v;
	public static String s="";
	
	
	public int m1(){
		r++;
		s+=r;
		return r;
	}
	public int m2(){
		v++;
		s+=v;
		return v;
	}
	public void m3(){
		System.out.println("m3");
	}
	public void m4(){
		System.out.println("m4");
	}
	public void m5(){
		System.out.println("m5");
	}
}
