package testers;

import java.util.LinkedList;

import algorithms.FIFOAlgoImpl;
import algorithms.LRUAlgoImpl;
//import algorithms.MFUAlgoImpl;

public class AlgorithmTest {

	
	//public static String strings[] = {"page1", "page2", "page3", "page4"};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String strings[] = {"page1", "page2", "page3", "page4"};
		LinkedList<String> list1 = new LinkedList<String>();
		int sizeoflist=4;
			
		list1.add("one");
		list1.add("two");
		list1.add("three");
		list1.add("four");
		
		FIFOAlgoImpl fa = new FIFOAlgoImpl(list1, 4);
		
		System.out.println("--FIFO---");
		fa.print();
		System.out.println("---------");
		fa.add("one");
		fa.print();
		System.out.println("---------");
		fa.get("test");
		fa.print();
		System.out.println("---------");
		fa.remove("five");
		fa.print();
		System.out.println("---------");
		fa.remove();
		fa.print();
		System.out.println("---------");
		fa.add("test1");
		fa.add("test2");
		fa.add("test3");
		fa.print();
		
		LRUAlgoImpl la = new LRUAlgoImpl(list1, 4);
		
		System.out.println("---LRU---");
		la.add("five");
		la.print();
		System.out.println("---------");
		la.remove("one");
		la.print();
		System.out.println("---------");
		la.get("test2");
		la.print();
		System.out.println("---------");
		la.add("test1");
		la.print();
		System.out.println("---------");
		la.add("nonexistent");
		la.print();
		
	}
}
