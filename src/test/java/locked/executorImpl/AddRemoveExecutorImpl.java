//package locked.executorImpl;
//
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.Set;
//
//import common.interfaces.SelfOrganizingListInterface;
//import locked.executor.LockedExecutor;
//import locked.threads.AddingLockedThread;
//import locked.threads.RemovingLockedThread;
//
//public class AddRemoveExecutorImpl extends LockedExecutor {
//
//	private int failCount = 0;
//
//	Iterator<Integer> it;
//	Iterator<Integer> itD;
//
//	Set<Integer> setAdd = new HashSet<Integer>();
//	Set<Integer> setRemove = new HashSet<Integer>();
//
//	private int adds = 1000;
//	private int removes = 500;
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
//		return removes;
//	}
//
//	public void setRemoves(int removes) {
//		this.removes = removes;
//	}
//
//	public AddRemoveExecutorImpl(int adds, int removes, SelfOrganizingListInterface sol) {
//		this.sol = sol;
//		this.adds = adds;
//		this.removes = removes;
//
//		for (int i = 0; i < adds; i++)
//			setAdd.add(i);
//
//		for (int i = 0; i < removes; i++)
//			setRemove.add(i);
//
//		it = setAdd.iterator();
//		itD = setRemove.iterator();
//
//	}
//
//	public HashMap<String, String> addRemove() {
//
//		this.start = System.nanoTime();
//
//		for (int i = 0; i < ratioAdd; i++)
//			new AddingLockedThread(sol, it, adds, ratioAdd, i).run();
//
//		for (int i = 0; i < ratioRemove; i++)
//			new RemovingLockedThread(sol, itD, removes, ratioRemove, i).run();
//
//		this.end = System.nanoTime();
//		double seconds = (end - start) / 1000000000.0;
//
//		// sol.list();
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
