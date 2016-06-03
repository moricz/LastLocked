package locked.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import common.interfaces.SelfOrganizingListInterface;

public abstract class LockedExecutor {

	protected final int concurrentThreads = 8;

	public long end = 0;

	public long start = 0;

	public ExecutorService executor = Executors.newFixedThreadPool(concurrentThreads);

	protected int ratioAdd = 3;
	protected int ratioRemove = 3;
	protected int ratioSearches = 2;

	protected SelfOrganizingListInterface sol;
}
