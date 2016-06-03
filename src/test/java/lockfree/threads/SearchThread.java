package lockfree.threads;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import lockfree.selfOrganizing.SelfOrganizingList;

public class SearchThread extends Thread {

	private SelfOrganizingList sol;
	private ArrayList<Integer> search;
	private int count;
	private int ratioSearch;
	private int start;
	private CyclicBarrier barrier;

	public SearchThread(SelfOrganizingList sol, ArrayList<Integer> search, int count, int ratioSearch, int start,
			CyclicBarrier barrier) {
		this.sol = sol;
		this.search = search;
		this.count = count;
		this.ratioSearch = ratioSearch;
		this.start = start;
		this.barrier = barrier;
	}

	@Override
	public void run() {
		try {
			
			barrier.await();
			for (int i = start; i < count; i += ratioSearch)
				sol.search(search.get(i));
			
			Thread.currentThread().join(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}

	}
}
