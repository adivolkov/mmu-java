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
		
		LRUAlgoImpl<String> la = new LRUAlgoImpl<String>(4);
		
		la.add("one");
		la.add("two");
		la.add("three");
		la.add("four");
		
		System.out.println("---LRU---");
		la.get("three");
		la.print();
		System.out.println("---------");
		la.add("five");
		la.print();
		System.out.println("---------");
		la.get("two");
		la.print();
		System.out.println("---------");
		la.remove("three");
		la.print();
		System.out.println("---------");
	}
}
