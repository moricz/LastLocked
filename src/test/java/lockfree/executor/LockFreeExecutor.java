package lockfree.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lockfree.selfOrganizing.SelfOrganizingList;

public abstract class LockFreeExecutor {

	protected final int concurrentThreads = 10;

	public long end = 0;

	public long start = 0;


	public static SelfOrganizingList sol;

	public int ratioAdd = 2;
	public int ratioRemove = 6;
	public int ratioSearch = 2;

}
