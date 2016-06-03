package locked.executorImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CyclicBarrier;

import common.interfaces.SelfOrganizingListInterface;
import locked.executor.LockedExecutor;
import locked.threads.AddingLockedThread;
import locked.threads.RemovingLockedThread;
import locked.threads.SearchLockedThread;
import lockfree.selfOrganizing.SelfOrganizingList;

public class AddRemoveSearchExecutorImpl extends LockedExecutor {

	public volatile int failCount = 0;


	List<Set<Integer>> setAdd = new ArrayList<Set<Integer>>();
	List<List<Integer>> listSearch = new ArrayList<List<Integer>>();

	
	CyclicBarrier barrier = new CyclicBarrier(concurrentThreads);

	private int adds;
	private int removes;
	private int searches;
	List<Set<Integer>> removeSet=new ArrayList<Set<Integer>>();
	
	public AddRemoveSearchExecutorImpl(int adds, int removes, int searches,SelfOrganizingListInterface sol) {
		this.sol=sol;
		this.adds = adds;
		this.removes = removes;
		this.searches = searches;

		sol = new SelfOrganizingList();
		
		for(int i=0;i<ratioAdd;i++){
			setAdd.add(new HashSet<Integer>());
		}
		
		for (int i = 0; i < adds; i++)
		{
			sol.add(i);
			setAdd.get(i%ratioAdd).add(i+adds);
		}
		
		for(int i=0;i<ratioRemove;i++){
			removeSet.add(new HashSet<Integer>());
		}
		
		for (int i = 0; i <= removes; i++)
			removeSet.get(i%ratioRemove).add(i);

		
		int a, b, c;

		a = 800;
		b = 700;
		c = 990;

		for(int i=0;i<ratioSearches;i++)
			listSearch.add(new ArrayList<Integer>());
		
		for (int i = 0; i < searches / 3; i++) {
			listSearch.get(i%ratioSearches).add(a);
			listSearch.get(i%ratioSearches).add(b);
			listSearch.get(i%ratioSearches).add(c);
		}
		
	sol.list();
	}
	
	

	public HashMap<String, String> addRemoveSearch() throws InterruptedException {

		this.start = System.nanoTime();

		for (int i = 0; i < ratioAdd; i++)
			new AddingLockedThread(sol, setAdd.get(i).iterator(), adds, ratioAdd, i,barrier).start();

		for (int i = 0; i < ratioRemove; i++)
			new RemovingLockedThread(sol, removeSet.get(i).iterator(), removes, ratioRemove, i,barrier).start();

		for (int i = 0; i < ratioSearches; i++)
			new SearchLockedThread(sol, listSearch.get(i).iterator(), searches, ratioSearches, i,barrier).start();

		
		this.end = System.nanoTime();
		double seconds = (end - start) / 1000000.0;

		Thread.sleep(5000);
		 sol.list();

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("seconds", String.valueOf(seconds));
		map.put("size", String.valueOf(sol.size()));
		map.put("failCount", String.valueOf(sol.failedRemove(removes)));
		map.put("addFail", String.valueOf(sol.failedRemove(adds)-sol.failedRemove(removes)-500));

		System.out
		.println("AddRemoveSearch Time: " + seconds + "millis Size: " + sol.size() + " RemoveFailed: " + sol.failedRemove(removes)+" FailAdd :"+(sol.failedRemove(adds)-sol.failedRemove(removes)-removes));


		return map;
	}

}
