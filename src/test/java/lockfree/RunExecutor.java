package lockfree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.ExcelWriter;
import lockfree.executorImpl.AddRemoveSearchExecutorImpl;
import lockfree.executorImpl.AddSearchExecutorImpl;

public class RunExecutor extends ExcelWriter {

	/*
	 * addRemoveSearch addRemove addSearch removeSearch
	 */

	private static final int ADDS = 1000;
	private static final int REMOVES = 100;
	private static final int SEARCHES = 3501;


	public static void main(String args[]) throws InterruptedException {

		List<HashMap<String, String>> listAddSearchRemove = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < 20; i++)
			listAddSearchRemove.add(new AddRemoveSearchExecutorImpl(ADDS, REMOVES, SEARCHES).addRemoveSearch());
			
		//	new AddSearchExecutorImpl(ADDS, SEARCHES).addSearch();
//422
		writeListToExcel("newAddRemSearch.xls", listAddSearchRemove);

	}

}
