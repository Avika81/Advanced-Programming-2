package test;

import java.util.Observable;
import java.util.Observer;

public class Property<V> extends Observable implements Observer{
	
	private V value;
	
	public void setValue(V value){
		if(value!=this.value){
			this.value=value;
			setChanged();
			notifyObservers();
		}
	}
	
	public V getValue(){
		return value;
	}

	public void bind(Property<V> p){
		addObserver(p);
	}
	
	
	@Override
	public void update(Observable o, Object arg1) {
		Property<V> p=(Property<V>) o;
		setValue(p.getValue());
	}

}
