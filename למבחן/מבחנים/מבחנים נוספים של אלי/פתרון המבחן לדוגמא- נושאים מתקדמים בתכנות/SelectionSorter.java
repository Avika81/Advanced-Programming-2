package model;

public class SelectionSorter implements Sorter{

	@Override
	public <E> void sort(Sortable<E> sortable) {
		for(int i=0;i<sortable.size()-1;i++){
			E currentMin=sortable.get(i);
			int currentMinIndex=i;
			for(int j=i+1;j<sortable.size();j++){
				if(sortable.compare(currentMin, sortable.get(j))>0){
					currentMin=sortable.get(i);
					currentMinIndex=j;
				}
			}
			if(currentMinIndex!=i)
				sortable.swap(i, currentMinIndex);
		}
	}
}
