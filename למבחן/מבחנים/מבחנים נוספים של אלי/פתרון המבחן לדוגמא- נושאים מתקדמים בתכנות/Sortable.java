package model;

public interface Sortable<E> {
	int compare(E e1, E e2);
	E get(int index);
	void swap(int i,int j);
	int size();
}
