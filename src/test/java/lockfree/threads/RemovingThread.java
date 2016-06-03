package lockfree.threads;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import lockfree.executorImpl.AddRemoveSearchExecutorImpl;
import lockfree.selfOrganizing.SelfOrganizingList;

public class RemovingThread extends Thread {

	SelfOrganizingList sol;
	private ArrayList<Integer> removeable;
	private int count;
	private int ratioRemove;
	private int start;
	private CyclicBarrier barrier;

	public RemovingThread(SelfOrganizingList sol, ArrayList<Integer> removeable, int count, int ratioRemove, int start,
			CyclicBarrier barrier) {
		this.sol = sol;
		this.removeable = removeable;
		this.count = count;
		this.ratioRemove = ratioRemove;
		this.start = start;
		this.barrier = barrier;
	}

	@Override
	public void run() {
		try {
			barrier.await();
			
			for (int i = start + 1; i < count; i += ratioRemove) 
				sol.remove(removeable.get(i));
								
			
			Thread.currentThread().join(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}

		

	}

}
