//package lockfree.executorImpl;
//
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.Set;
//import java.util.concurrent.TimeUnit;
//
//import lockfree.executor.LockFreeExecutor;
//import lockfree.threads.AddingThread;
//
//public class AddRemoveExecutorImpl extends LockFreeExecutor {
//
//	private int failCount;
//
//	Iterator<Integer> it;
//	Iterator<Integer> itRemove;
//	Set<Integer> setAdd = new HashSet<Integer>();
//	Set<Integer> setRemove = new HashSet<Integer>();
//
//	private int adds = 1000;
//	private int nrRemovals = 500;
//
//	public int getAdds() {
//		return adds;
//	}
//
//	public void setAdds(int adds) {
//		this.adds = adds;
//	}
//
//	public int getRemoves() {
//		return nrRemovals;
//	}
//
//	public void setRemoves(int removes) {
//		this.nrRemovals = removes;
//	}
//
//	public AddRemoveExecutorImpl(int adds, int removes) {
//		this.adds = adds;
//		this.nrRemovals = removes;
//
//		for (int i = 1; i <= adds; i++)
//			sol.add(i);
//
//		for (int i = adds; i < 2 * adds; i++)
//			setAdd.add(i);
//
//		for (int i = 1; i <= removes; i++)
//			setRemove.add(i);
//
//		it = setAdd.iterator();
//		itRemove = setRemove.iterator();
//
//	}
//
//	public HashMap<String, String> addRemove() {
//
//		this.start = System.nanoTime();
//
//		for (int i = 0; i < ratioAdd; i++) {
//			AddingThread t = new AddingThread(sol, it, adds, ratioAdd, i);
//			executor.submit(t);
//		}
//		/*
//		 * for (int i = 0; i < ratioRemove; i++) { RemovingThread t = new
//		 * RemovingThread(sol, itRemove, nrRemovals, ratioRemove, i);
//		 * executor.submit(t); failCount += t.failCount; }
//		 */
//
//		try {
//			executor.shutdown();
//			executor.awaitTermination(2, TimeUnit.SECONDS);
//			this.end = System.nanoTime();
//		} catch (InterruptedException e) {
//			System.out.println(e.getMessage());
//		}
//		double seconds = (end - start) / 1000000000.0;
//
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("seconds", String.valueOf(seconds));
//		map.put("size", String.valueOf(sol.size()));
//		map.put("failCount", String.valueOf(failCount));
//
//		System.out.println("AddRemove Time: " + seconds + "s Size: " + sol.size() + " RemoveFailed: " + failCount);
//
//		return map;
//	}
//
//}
