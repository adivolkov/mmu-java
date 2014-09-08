package algorithms;

import java.util.LinkedList;
import java.util.Queue;

public class FIFOAlgoImpl<T> implements IAlgo<T> {

	private Queue<T> queue;
	private int capacity;
	
	public FIFOAlgoImpl(int capacity) {
		queue = new LinkedList<T>();
		this.capacity = capacity;
	}
	
	/**********finds the object t in the queue 
	while removing and adding the wrong one's to the back of the line*******/
	
	@Override
	public T get(T t) {
		if (queue.contains(t))
		{
			return t;
		}
		return null;
	}

	@Override
	public T add(T t) {
		T removed = null;
		if	(queue.contains(t))
		{
			return null;
		}
		if (queue.size() >= capacity)
		{
			removed = queue.remove();
		}
		queue.add(t);
		return removed;
	}

	@Override
	public void remove(T t) {
		if (!queue.isEmpty())
			queue.remove(t);
	}

	public void print()
	 {
		Object[] array = new Object[queue.size()] ;
		array = queue.toArray();
		for(int i=0;i<array.length;i++)
		{
			System.out.print(array[i]);
			if (i+1 == capacity)
			{
				System.out.println();
			}
			else
			{
				System.out.print("|");
			}
		}
		for(int i=array.length;i<capacity;i++)
		{			
			if (i+1 == capacity)
			{
				System.out.println();
			}
			else
			{
				System.out.print(" |");
			}
		}		
	 }
	
	
	
	
}
