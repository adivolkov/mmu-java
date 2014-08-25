package algorithms;

import java.util.LinkedList;

public class LRUAlgoImpl<T> implements IAlgo<T> {
	private LinkedList<T> lruList;
	private int capacity;
	
	public LRUAlgoImpl(LinkedList<T> list, int initCapacity)
	{
		lruList = list;
		capacity = initCapacity;
	}
	
	@Override
	public T get(T t) {
		int index=0;
		if (lruList.isEmpty()==true)
			return null;
		if	(lruList.contains(t)==true)
		{
			index =lruList.indexOf(t);
			lruList.remove(index);
			lruList.addFirst(t);
			return t;
		}
	return null;
	}

	@Override
	public T add(T t) {
		int index=0;
		if (lruList.isEmpty()== true)
		{
			lruList.addFirst(t);
			return t;
		}
		if	(lruList.contains(t)==true)
		{
			index=lruList.indexOf(t);
			lruList.remove(index);
			lruList.addFirst(t);
			return t;
		}
		if (lruList.size()==capacity)
		{
			lruList.removeLast();
			lruList.addFirst(t);
			return t;
		}
		lruList.addFirst(t);
		return t;
	}

	@Override
	public void remove(T t) {
		if (lruList.contains(t))
			lruList.remove(t);
		else System.out.println("no such T");
	}

	 public void print()
	 {
		 Object[] array = new Object[capacity];
		 array=lruList.toArray();
		 for(int i=0;i<array.length;i++)
		 {
			 System.out.println(array[i]);
		 }
	 }
}
