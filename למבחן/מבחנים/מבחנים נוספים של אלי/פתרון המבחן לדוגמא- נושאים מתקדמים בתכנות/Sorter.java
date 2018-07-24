package model;

public interface Sorter {
	<E> void sort(Sortable<E> sortable);
}
