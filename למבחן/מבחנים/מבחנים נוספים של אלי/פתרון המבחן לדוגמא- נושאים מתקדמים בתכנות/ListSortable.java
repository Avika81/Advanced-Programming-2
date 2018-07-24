package model;

import java.util.Comparator;
import java.util.List;

public class ListSortable<E> implements Sortable<E>{

	List<E> list;
	Comparator<E> c;
	
	public ListSortable(List<E> list, Comparator<E> c) {
		this.list=list;
		this.c=c;
	}
	
	@Override
	public int compare(E e1, E e2) {
		return c.compare(e1, e2);
	}

	@Override
	public E get(int index) {
		return list.get(index);
	}

	@Override
	public void swap(int i, int j) {
		E ei=list.remove(i);
		E ej=list.remove(j);
		list.add(j, ei);
		list.add(i, ej);
	}

	@Override
	public int size() {
		return list.size();
	}

}
