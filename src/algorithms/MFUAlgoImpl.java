package algorithms;

import java.util.LinkedList;

public class MFUAlgoImpl<T> implements IAlgo<T> {
	private LinkedList<T> mfuList;
	private LinkedList<Integer> frequenciesList;
	private int capacity;
	
	public MFUAlgoImpl(int capacity)
	{
		mfuList = new LinkedList<T>();
		frequenciesList = new LinkedList<Integer>();
		this.capacity = capacity;
	}
	
	@Override
	public T get(T t) {
		if	(mfuList.contains(t))
		{
			int index = mfuList.indexOf(t);
			
			int newPriority = frequenciesList.get(index) + 1;
			frequenciesList.set(index, newPriority);
			
			if (newPriority > frequenciesList.getFirst())
			{
				mfuList.remove(index);
				mfuList.addFirst(t);
				frequenciesList.remove(index);
				frequenciesList.addFirst(newPriority);				
			}			
			return t;
		}
		return null;
	}

	@Override
	public T add(T t) {
		T removed = null;
		if	(mfuList.contains(t))
		{
			int index=mfuList.indexOf(t);
			
			int newPriority = frequenciesList.get(index) + 1;
			frequenciesList.set(index, newPriority);
			
			if (newPriority > frequenciesList.getFirst())
			{
				mfuList.remove(index);
				mfuList.addFirst(t);
				frequenciesList.remove(index);
				frequenciesList.addFirst(newPriority);				
			}
			return removed;
		}
		if (mfuList.size()==capacity)
		{
			removed = mfuList.removeFirst();
			frequenciesList.removeFirst();
			mfuList.addLast(t);
			frequenciesList.addLast(1);
			return removed;
		}
		mfuList.addLast(t);
		frequenciesList.addLast(1);
		return removed;
	}

	@Override
	public void remove(T t) {
		if (mfuList.contains(t))
		{
			mfuList.remove(t);
			frequenciesList.remove(mfuList.indexOf(t));
		}
			
	}

	public void print()
	 {
		Object[] array = new Object[mfuList.size()] ;
		array = mfuList.toArray();
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