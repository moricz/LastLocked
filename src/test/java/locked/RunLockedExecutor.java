package locked;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.ExcelWriter;
import locked.base.SelfOrganizingListFineGrained;
import locked.base.SelfOrganizingListWSynch;
import locked.base.SelfOrganizingLockList;
import locked.executorImpl.AddRemoveSearchExecutorImpl;

public class RunLockedExecutor extends ExcelWriter {

	private static final int ADDS = 1000;
	private static final int REMOVES = 500;
	private static final int SEARCHES = 1500;

	public static void main(String args[]) throws InterruptedException {

		List<HashMap<String, String>> listAddSearchRemove = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < 1; i++) {

			listAddSearchRemove
					.add(new AddRemoveSearchExecutorImpl(ADDS, REMOVES, SEARCHES, new SelfOrganizingLockList())
							.addRemoveSearch());
		}
	//	writeListToExcel("LockaddRemSearch161.xls", listAddSearchRemove);

	}
}
