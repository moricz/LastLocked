package lockfree.executorImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CyclicBarrier;

import lockfree.executor.LockFreeExecutor;
import lockfree.selfOrganizing.SelfOrganizingList;
import lockfree.threads.AddingThread;
import lockfree.threads.SearchThread;

public class AddSearchExecutorImpl extends LockFreeExecutor {

	private int failCount = 0;

	Iterator<Integer> iteratorDelete;
	Iterator<Integer> itSearch;
	ArrayList<Integer> listAdd = new ArrayList<Integer>();
	ArrayList<Integer> listSearch = new ArrayList<Integer>();
	
	private int adds = 1000;
	private int searches = 550;

	CyclicBarrier barrier = new CyclicBarrier(concurrentThreads);
	
	public AddSearchExecutorImpl(int adds, int searches) {
		
		this.adds = adds;
		this.searches = searches;

		sol=new SelfOrganizingList();
		
		for (int i = 0; i < adds; i++){
			listAdd.add(i);
			sol.add(i+adds);
		}
		int a=500, b=800, c=900;
		
		for (int i = 0; i < searches / 3; i++) {
			listSearch.add(a);
			listSearch.add(b);
			listSearch.add(c);
		}
		sol.add(b);
		sol.add(a);
		sol.add(c);
		

		
		
	}

	public HashMap<String, String> addSearch() {

		
		this.start = System.nanoTime();
		for (int i = 0; i < ratioAdd; i++)
			new AddingThread(sol, listAdd, adds, ratioAdd, i,barrier).start();

		for (int i = 0; i < ratioSearch; i++)
			new SearchThread(sol, listSearch, searches, ratioSearch, i,barrier).start();
		

		this.end=System.nanoTime();
		double seconds = (end - start) / 1000000.0;

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 sol.list();

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("seconds", String.valueOf(seconds));
		map.put("size", String.valueOf(sol.size()));
		map.put("failCount", String.valueOf(failCount));

		for(int i=0;i<sol.size();i++)
			if(!sol.contains(i))
				System.out.println("FAILED "+ i+ " Not Found");
		
		System.out.println("AddSearch Time: " + seconds + "millis Size: " + sol.size() + " RemoveFailed: " + failCount);
		return map;
	}

}
