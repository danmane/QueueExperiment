import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TaskTest {
	Task testTask1, testTask2;

	@Before
	public void setUp() throws Exception {
		testTask1 = new Task(0, 0, 1);
		testTask2 = new Task(1, 20, 5);
	}

	@After
	public void tearDown() throws Exception {
		testTask1 = null;
		testTask2 = null;
	}

	@Test
	public void testGetProcessed() {
		assertFalse(testTask1.getProcessed());
		testTask1.processMe(5);
		assertTrue(testTask1.getProcessed());
	}

	@Test
	public void testGetWait() {
		assertEquals(-1, testTask1.getWait());
		testTask1.processMe(10);
		assertEquals(10, testTask1.getWait());
		
		testTask2.processMe(500);
		assertEquals(480, testTask2.getWait());
		
	}

}
