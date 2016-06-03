package tests;

import java.util.HashSet;
import java.util.Iterator;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import lockfree.selfOrganizing.SelfOrgList;
import lockfree.selfOrganizing.SelfOrganizingList;

public class LockFreeTest {

	SelfOrganizingList sol;
	Long start = (long) 0, end = (long) 0;
	int i = 0;
	static Long len = (long) 0;
	int count = 0;
	Iterator<Integer> it;
	@BeforeTest
	protected void setUp() throws Exception {

		sol = new SelfOrganizingList();

		System.gc();

		HashSet<Integer> set= new HashSet<Integer>();
		for (int i = 0; i < 1500; i++){
			sol.add(i);
			set.add(i);
		}

		sol.add(7);
		
		it=set.iterator();
		System.out.println(sol.size());
		start = System.nanoTime();
		
	}

	@AfterTest
	protected void tearDown() throws Exception {
		end = System.nanoTime();

		double seconds = (end - start) / 1000000000.0;
		System.out.println("Time: " + seconds + "s Size: " + sol.size()+" Fails:"+ count);
		sol.list();

	}

	@Test(threadPoolSize = 2, invocationCount = 1000, timeOut = 10000)
	public void test2() {

		sol.add(10);
		if(!sol.remove(it.next())) count++;

		// sol.remove(10);
		// sol.remove(10);
		// sol.search(7);

	}

}
