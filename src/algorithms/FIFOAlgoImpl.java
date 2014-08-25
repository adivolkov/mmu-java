package algorithms;

import java.util.Queue;

public class FIFOAlgoImpl<T> implements IAlgo<T> {

	private Queue<T> fifoQ;
	private int capacity;
	
	public FIFOAlgoImpl(Queue<T> queue, int initCapacity) {
		fifoQ = queue;
		capacity = initCapacity;
	}
	
	/**********finds the object t in the queue 
	while removing and adding the wrong one's to the back of the line*******/
	
	@Override
	public T get(T t) {
				
		if (fifoQ.isEmpty())
		{
			System.out.println("FIFO " + t.getClass() + ".get(" + t.toString() +")");
			return null;
		}			
		
		if (fifoQ.contains(t))
		{
			System.out.println("FIFO " + t.getClass() + ".get(" + t.toString() +")");
			return t;
		}
		System.out.println("Object was not found");
		return null;
	}

	@Override
	public T add(T t) {
		if (fifoQ.size() == capacity)
		{
			fifoQ.remove();
		}
		fifoQ.add(t);
		return t;
	}

	@Override
	public void remove(T t) {
		if (!fifoQ.isEmpty())
		fifoQ.remove(t);
		// TODO Auto-generated method stub		
	}
	public void remove() {
		if (!fifoQ.isEmpty())
		fifoQ.remove();
		// TODO Auto-generated method stub		
	}

	public void print()
	 {
		Object[] array = new Object[fifoQ.size()] ;
		array = fifoQ.toArray();
		for(int i=0;i<array.length;i++)
		{
			System.out.println(array[i]);
		}
	 }
	
	
	
	
}
