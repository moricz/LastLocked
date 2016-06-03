//package locked.executorImpl;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Set;
//
//import common.interfaces.SelfOrganizingListInterface;
//import locked.executor.LockedExecutor;
//import locked.threads.RemovingLockedThread;
//import locked.threads.SearchLockedThread;
//
//public class RemoveSearchExecutorImpl extends LockedExecutor {
//
//	private int failCount = 0;
//
//	Iterator<Integer> it;
//	Iterator<Integer> itD;
//
//	Set<Integer> setAdd = new HashSet<Integer>();
//	Set<Integer> setRemove = new HashSet<Integer>();
//
//	private int removes = 500;
//	private int searches = 550;
//	List<Integer> searchList = new ArrayList<Integer>();
//	private Iterator<Integer> listSearch;
//
//	public RemoveSearchExecutorImpl(int solSize, int removes, int searches, SelfOrganizingListInterface sol) {
//		this.sol = sol;
//		this.removes = removes;
//		this.searches = searches;
//
//		for (int i = 0; i < solSize; i++)
//			sol.add(i);
//
//		for (int i = 0; i < removes; i++)
//			setRemove.add(i);
//
//		int a = solSize / 2, b = solSize / 3, c = solSize / 2 * 3;
//
//		for (int i = 0; i < searches / 3; i++) {
//			searchList.add(a);
//			searchList.add(b);
//			searchList.add(c);
//		}
//		listSearch = searchList.iterator();
//
//		it = setAdd.iterator();
//		itD = setRemove.iterator();
//	}
//
//	public HashMap<String, String> removeSearch() {
//
//		this.start = System.nanoTime();
//
//		for (int i = 0; i < ratioRemove; i++)
//			new RemovingLockedThread(sol, itD, removes, ratioRemove, i).run();
//
//		for (int i = 0; i < ratioSearches; i++)
//			new SearchLockedThread(sol, listSearch, searches, ratioSearches, i).run();
//
//		this.end = System.nanoTime();
//
//		double seconds = (end - start) / 1000000000.0;
//
//		// sol.list();
//
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("seconds", String.valueOf(seconds));
//		map.put("size", String.valueOf(sol.size()));
//		map.put("failCount", String.valueOf(failCount));
//
//		System.out.println("RemoveSearch Time: " + seconds + "s Size: " + sol.size());
//		return map;
//	}
//}
