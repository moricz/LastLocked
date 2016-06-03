package lockfree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import locked.base.SelfOrganizingListFineGrained;
import lockfree.executorImpl.AddRemoveSearchExecutorImpl;

public class LockFreeCase1 {

	private static final int ADDS = 1000;
	private static final int REMOVES = 500;
	private static final int SEARCHES = 500;


	public static void main(String args[]) throws InterruptedException {

		List<HashMap<String, String>> listAddSearchRemove = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < 30; i++)
			listAddSearchRemove.add(new AddRemoveSearchExecutorImpl(ADDS, REMOVES, SEARCHES).addRemoveSearch());
//422
		//writeListToExcel("addRemSearch611.xls", listAddSearchRemove);

	}
}
