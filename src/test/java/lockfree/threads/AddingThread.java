package lockfree.threads;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import lockfree.executorImpl.AddRemoveSearchExecutorImpl;
import lockfree.selfOrganizing.SelfOrganizingList;

public class AddingThread extends Thread {

	SelfOrganizingList sol;
	private ArrayList<Integer> addable;
	private int count;
	private int ratioAdd;
	private int start;
	private CyclicBarrier barrier;
	public long sum = 0;

	public AddingThread(SelfOrganizingList sol, ArrayList<Integer> add, int count, int ratioAdd, int start,
			CyclicBarrier barrier) {
		this.sol=sol;
		this.addable = add;
		this.count = count;
		this.ratioAdd = ratioAdd;
		this.start = start;
		this.barrier = barrier;
	}

	@Override
	public void run() {

		try {
			barrier.await();
			for (int i = start; i < count; i += ratioAdd)
				{
				sol.add(addable.get(i));
				}
			
			Thread.currentThread().join(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}

	}

}
