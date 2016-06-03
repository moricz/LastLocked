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
//import locked.threads.AddingLockedThread;
//import locked.threads.SearchLockedThread;
//
//public class AddSearchExecutorImpl extends LockedExecutor {
//
//	private int failCount = 0;
//
//	Iterator<Integer> it;
//	Iterator<Integer> itD;
//
//	Set<Integer> setAdd = new HashSet<Integer>();
//
//	private int adds = 1000;
//	private int searches = 550;
//	List<Integer> searchList = new ArrayList<Integer>();
//	private Iterator<Integer> listSearch;
//
//	public AddSearchExecutorImpl(int adds, int searches, SelfOrganizingListInterface sol) {
//		this.sol = sol;
//		this.adds = adds;
//		this.searches = searches;
//		for (int i = 0; i < adds; i++)
//			setAdd.add(i);
//
//		int a = adds / 2, b = adds / 3, c = adds / 2 * 3;
//
//		for (int i = 0; i < searches / 3; i++) {
//			searchList.add(a);
//			searchList.add(b);
//			searchList.add(c);
//		}
//		listSearch = searchList.iterator();
//		it = setAdd.iterator();
//
//	}
//
//	public HashMap<String, String> addSearch() {
//
//		this.start = System.nanoTime();
//
//		for (int i = 0; i < ratioAdd; i++)
//			new AddingLockedThread(sol, it, adds, ratioAdd, i).run();
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
//		System.out.println("AddSearch Time: " + seconds + "s Size: " + sol.size());
//		return map;
//	}
//
//}
