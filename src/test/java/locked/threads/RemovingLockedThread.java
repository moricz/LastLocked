package locked.threads;

import java.util.Iterator;
import java.util.concurrent.CyclicBarrier;

import common.interfaces.SelfOrganizingListInterface;
import locked.executorImpl.AddRemoveSearchExecutorImpl;

public class RemovingLockedThread extends Thread {

	private SelfOrganizingListInterface sol;
	private Iterator<Integer> removeable;
	private int count;
	private int ratioRemove;
	private int start;
	CyclicBarrier barrier;
	
	public RemovingLockedThread(SelfOrganizingListInterface sol, Iterator<Integer> removeable, int count,
			int ratioRemove, int start, CyclicBarrier barrier) {
		this.barrier=barrier;
		this.sol = sol;
		this.removeable = removeable;
		this.count = count;
		this.ratioRemove = ratioRemove;
		this.start = start;
	}

	@Override
	public void run() {
		try {
			barrier.await();

		for (int i = start + 1; i <= count; i += ratioRemove) {
			sol.remove(removeable.next());

		}		
		Thread.currentThread().join(0);
		}catch(Exception e){
			
		}

	}

}
