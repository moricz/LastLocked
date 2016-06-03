package lockfree.executorImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CyclicBarrier;

import lockfree.executor.LockFreeExecutor;
import lockfree.selfOrganizing.SelfOrganizingList;
import lockfree.threads.AddingThread;
import lockfree.threads.RemovingThread;
import lockfree.threads.SearchThread;

public class AddRemoveSearchExecutorImpl extends LockFreeExecutor {

	public volatile int failCount = 0;


	ArrayList<Integer> setAdd = new ArrayList<Integer>();
	ArrayList<Integer> listSearch = new ArrayList<Integer>();

	
	CyclicBarrier barrier = new CyclicBarrier(concurrentThreads);

	private int adds;
	private int removes;
	private int searches;
	
	ArrayList<Integer> removeList=new ArrayList<Integer>();
	public AddRemoveSearchExecutorImpl(int adds, int removes, int searches) {
		this.adds = adds;
		this.removes = removes;
		this.searches = searches;

		sol = new SelfOrganizingList();
		
		
		for (int i = 0; i < adds; i++)
		{
			sol.add(i);
			setAdd.add(i+adds);
		}
		
		
		
		for (int i = 0; i <= removes; i++)
			removeList.add(i);

		
		int a, b, c;

		a = 800;
		b = 700;
		c = 990;

		
		for (int i = 0; i < searches / 3; i++) {
			listSearch.add(a);
			listSearch.add(b);
			listSearch.add(c);
		}
		
	
		
	}

	public HashMap<String, String> addRemoveSearch() throws InterruptedException {

		
		
		this.start = System.nanoTime();
		for (int i = 0; i < ratioAdd; i++)
			new AddingThread(sol, setAdd, adds, ratioAdd, i, barrier).start();

		
		for (int i = 0; i < ratioRemove; i++)
			new RemovingThread(sol, removeList, removes, ratioRemove, i, barrier).start();
			
		
		for (int i = 0; i < ratioSearch; i++)
			new SearchThread(sol, listSearch, searches, ratioSearch, i, barrier).start();

		
		this.end = System.nanoTime();
		
		double  seconds = (end - start) / 1000000.0;

		Thread.sleep(1500);

	//	sol.list();
		
	//	sol.listFromTo(0, (adds-removes));
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("seconds", String.valueOf(seconds));
		map.put("size", String.valueOf(sol.size()));
		map.put("failCount", String.valueOf(sol.failedRemove(removes)));
		int addfail=0;
		for(int i=removes;i<sol.size();i++)
			if(!sol.contains(i)){
				System.out.println("FAILED "+ i+ " Not Found");
				addfail++;
			}
		map.put("addfail", String.valueOf(addfail));
		
		System.out
		.println("AddRemoveSearch Time: " + seconds + "millis Size: " + sol.size() + " RemoveFailed: " +( sol.failedRemove(removes)));

		
		
		
		return map;
	}

}
