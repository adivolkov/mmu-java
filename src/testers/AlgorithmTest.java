package testers;

import algorithms.FIFOAlgoImpl;
import algorithms.LRUAlgoImpl;
//import algorithms.MFUAlgoImpl;

public class AlgorithmTest {

	public static void main(String[] args) {
		FIFOAlgoImpl<String> fifoAlgo = new FIFOAlgoImpl<String>(4);
			
		fifoAlgo.add("one");
		fifoAlgo.add("two");
		fifoAlgo.add("three");
		fifoAlgo.add("four");
		
		System.out.println("--FIFO---");
		fifoAlgo.print();
		System.out.println("---------");
		fifoAlgo.add("five");
		fifoAlgo.print();
		System.out.println("---------");
		fifoAlgo.get("test");
		fifoAlgo.print();
		System.out.println("---------");
		fifoAlgo.remove("five");
		fifoAlgo.print();
		System.out.println("---------");
		//fifoAlgo.remove();
		fifoAlgo.print();
		System.out.println("---------");
		fifoAlgo.add("test1");
		fifoAlgo.add("test2");
		fifoAlgo.add("test3");
		fifoAlgo.print();
		
		LRUAlgoImpl<String> lruAlgo = new LRUAlgoImpl<String>(4);
		
		lruAlgo.add("one");
		lruAlgo.add("two");
		lruAlgo.add("three");
		lruAlgo.add("four");
		
		System.out.println("---LRU---");
		lruAlgo.get("three");
		lruAlgo.print();
		System.out.println("---------");
		lruAlgo.add("five");
		lruAlgo.print();
		System.out.println("---------");
		lruAlgo.get("two");
		lruAlgo.print();
		System.out.println("---------");
		lruAlgo.remove("three");
		lruAlgo.print();
		System.out.println("---------");
	}
}
