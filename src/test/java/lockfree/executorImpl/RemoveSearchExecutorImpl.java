//package lockfree.executorImpl;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Set;
//import java.util.concurrent.TimeUnit;
//
//import lockfree.executor.LockFreeExecutor;
//import lockfree.threads.RemovingThread;
//
//public class RemoveSearchExecutorImpl extends LockFreeExecutor {
//
//	public static int failCount = 0;
//
//	Iterator<Integer> it;
//	Iterator<Integer> itD;
//	Iterator<Integer> itSearch;
//
//	List<Integer> listSearch = new ArrayList<Integer>();
//	Set<Integer> setAdd = new HashSet<Integer>();
//	Set<Integer> setRemove = new HashSet<Integer>();
//
//	private int removes = 500;
//	private int searches = 550;
//
//	public RemoveSearchExecutorImpl(int solSize, int removes, int searches) {
//
//		this.removes = removes;
//		this.searches = searches;
//
//		for (int i = 0; i < 2000; i++)
//			sol.add(i);
//
//		for (int i = 0; i < removes; i++)
//			setRemove.add(i);
//
//		int a, b, c;
//
//		a = 800;
//		b = 1200;
//		c = 1600;
//
//		for (int i = 0; i < searches / 3; i++) {
//
//			listSearch.add(a);
//			listSearch.add(b);
//			listSearch.add(c);
//		}
//
//		it = setAdd.iterator();
//		itD = setRemove.iterator();
//		itSearch = listSearch.iterator();
//	}
//
//	public HashMap<String, String> removeSearch() {
//
//		this.start = System.nanoTime();
//
//		for (int i = 0; i < ratioRemove; i++) {
//			RemovingThread t = new RemovingThread(sol, itD, removes, ratioRemove, i);
//
//			executor.submit(t);
//
//		}
//
//		// for (int i = 0; i < ratioSearch; i++)
//		// executor.submit(new SearchThread(sol, itSearch, searches,
//		// ratioSearch, i));
//
//		try
//
//		{
//
//			executor.shutdown();
//			executor.awaitTermination(2, TimeUnit.SECONDS);
//			this.end = System.nanoTime();
//		} catch (InterruptedException e) {
//			System.out.println(e.getMessage());
//		}
//		sol.list();
//		double seconds = (end - start) / 1000000000.0;
//
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("seconds", String.valueOf(seconds));
//		map.put("size", String.valueOf(sol.size()));
//		map.put("failCount", String.valueOf(failCount));
//
//		System.out.println("RemoveSearch Time: " + seconds + "s Size: " + sol.size() + " RemoveFailed: " + failCount);
//		return map;
//	}
//}
