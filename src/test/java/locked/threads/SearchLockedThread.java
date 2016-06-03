package locked.threads;

import java.util.Iterator;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import common.interfaces.SelfOrganizingListInterface;
import locked.executorImpl.AddRemoveSearchExecutorImpl;

public class SearchLockedThread extends Thread {

	private SelfOrganizingListInterface sol;
	private Iterator<Integer> search;
	private int count;
	private int ratioSearch;
	private int start;
	CyclicBarrier barrier;
	
	public SearchLockedThread(SelfOrganizingListInterface sol, Iterator<Integer> search, int count, int ratioSearch,
			int start, CyclicBarrier barrier) {
		this.sol = sol;
		this.barrier= barrier;
		this.search = search;
		this.count = count;
		this.ratioSearch = ratioSearch;
		this.start = start;
	}

	@Override
	public void run() {
		
		
			try {
				barrier.await();
				

				for (int i = start; i < count; i += ratioSearch)
					if(search.hasNext())
					sol.search(search.next());
				
				Thread.currentThread().join(0);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			} catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}


	}
}
