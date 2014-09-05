package algorithms;

import java.util.LinkedList;

public class LRUAlgoImpl<T> implements IAlgo<T> {
	private LinkedList<T> lruList;
	private int capacity;
	
	public LRUAlgoImpl(int capacity)
	{
		lruList = new LinkedList<T>();
		this.capacity = capacity;
	}
	
	@Override
	public T get(T t) {
		if	(lruList.contains(t))
		{
			int index = lruList.indexOf(t);
			lruList.remove(index);
			lruList.addFirst(t);
			return t;
		}
		return null;
	}

	@Override
	public T add(T t) {
		T removed = null;
		if	(lruList.contains(t))
		{
			int index=lruList.indexOf(t);
			lruList.remove(index);
			lruList.addFirst(t);
			return removed;
		}
		if (lruList.size()==capacity)
		{
			removed = lruList.removeLast();
			lruList.addFirst(t);
			return removed;
		}
		lruList.addFirst(t);
		return removed;
	}

	@Override
	public void remove(T t) {
		if (lruList.contains(t))
			lruList.remove(t);
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
