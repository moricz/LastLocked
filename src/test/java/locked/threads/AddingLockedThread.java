package locked.threads;

import java.util.Iterator;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import common.interfaces.SelfOrganizingListInterface;
import locked.executorImpl.AddRemoveSearchExecutorImpl;

public class AddingLockedThread extends Thread {

	private SelfOrganizingListInterface sol;
	private Iterator<Integer> addable;
	private int count;
	private int ratioAdd;
	private int start;
	CyclicBarrier barrier;
	long sum = 0;

	public AddingLockedThread(SelfOrganizingListInterface sol, Iterator<Integer> add, int count, int ratioAdd,
			int start,CyclicBarrier barrier) {
		this.barrier=barrier;
		this.sol = sol;
		this.addable = add;
		this.count = count;
		this.ratioAdd = ratioAdd;
		this.start = start;
	}

	@Override
	public void run() {
		
		try {
			barrier.await();

			for (int i = start; i < count; i += ratioAdd)
				sol.add(addable.next());
			Thread.currentThread().join(0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
