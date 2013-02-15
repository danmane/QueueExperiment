import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ProcessorTest {
	Processor testProcessor;
	Task task1, task2;

	@Before
	public void setUp() throws Exception {
		testProcessor = new Processor(1);
		task1 = new Task(1, 0, 5);
		task2 = new Task(2, 0, 3);
	}

	@After
	public void tearDown() throws Exception {
		testProcessor = null;
		task1 = null;
		task2 = null;
	}

	private void assertState(boolean isIdle, int time2C, int time2I, int queueL){
		assertEquals(testProcessor.isIdle(), isIdle);
		assertEquals(testProcessor.getTimeToCompletion(), time2C);
		assertEquals(testProcessor.getTimeToIdle(), time2I);
		assertEquals(testProcessor.getQueueLength(), queueL);
	}

	@Test
	public void testProcessorQueue() {
		int ct; // current time
		assertState(true, 0, 0, 0);
		assertEquals(1, testProcessor.getProcessorID());

		ct = 0;
		testProcessor.takeTask(task1, ct);

		assertState(false, 5, 5, 0);
		assertEquals(0, task1.getProcessedTime());


		testProcessor.takeTask(task2, ct);
		assertState(false, 5, 8, 1);
		assertEquals(-1, task2.getProcessedTime());


		while (ct < 4){
			ct++;
			testProcessor.tickTime(ct);
		}
		assertState(false, 1, 4, 1);

		ct++;
		testProcessor.tickTime(ct);

		assertState(false, 3, 3, 0);
		assertEquals(5, task2.getProcessedTime());

		while (ct<8){
			ct++;
			testProcessor.tickTime(ct);
		}
		assertState(true, 0, 0, 0);
	}
}
