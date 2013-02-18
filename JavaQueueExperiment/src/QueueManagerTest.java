import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class QueueManagerTest {
	QueueManager qm1, qm2;
	
	@Before
	public void setUp() throws Exception {
		qm1 = new QueueManager(1);
		qm2 = new QueueManager(2);
	}

	@After
	public void tearDown() throws Exception {
		qm1 = null;
		qm2 = null;
	}

	@Test
	public void testTick() {
		assertEquals(1,qm1.tick());
		qm1.addTask(1);
		assertEquals(1,qm1.getQueueLength());
		qm1.tick();
		assertEquals(0,qm1.getQueueLength());
		
		
		qm2.addTask(1);
		qm2.addTask(2);
		qm2.tick();
		assertEquals(0, qm2.getQueueLength());
		
		qm2.addTask(3);
		qm2.addTask(3);
		qm2.tick();
		assertEquals(1, qm2.getQueueLength());
		
	}

	@Test
	public void testAddProcessor() {
		assertEquals(1, qm1.getNumProcessors());
		assertEquals(2, qm2.getNumProcessors());
		qm2.addProcessor();
		assertEquals(3, qm2.getNumProcessors());
	}

	@Test
	public void testAddTask() {
		assertEquals(0, qm1.getQueueLength());
		qm1.addTask(3);
		assertEquals(1, qm1.getQueueLength());
		qm1.addTask(2);
		assertEquals(2, qm1.getQueueLength());
	}

	@Test
	public void  testTimeTracker(){
		assertTimes(qm2, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		qm2.tick();
		assertTimes(qm2, 1, 2, 2, 0, 0, 0, 0, 0, 0);
		qm2.addTask(2);
		qm2.addTask(3);
		qm2.tick();
		assertTimes(qm2, 2, 4, 2, 2, 2, 5, 0, 0, 0);
	}

	@Test
	public void testActiveTicks1(){
		qm2.addTask(3);
		qm2.addTask(5);
		qm2.addTask(10);
		qm2.addTask(9);
		for (int i=0; i<100; i++){
			qm2.tick();
		}
		qm2.addTask(3);
		qm2.addTask(10);
		for (int i=0; i<100; i++){
			qm2.tick();
		}
		assertEquals(40, qm2.getActiveProcessorTicks());
		assertEquals(40, qm2.getTotalTimeTasks());
		assertEquals(200, qm2.getCurrentTime());
	}
	
	public void testActiveTicks2(){
		for (int i=1; i<201; i++){
			if (i%2 == 0){
				qm2.addTask(3);
			}
			if (i%3 == 0){
				qm2.addTask(4);
			}
			qm2.tick();
		}
		assertEquals(564, qm2.getTotalTimeTasks());
		while (!qm2.isFinished()){
			qm2.tick();
		}
		assertEquals(564, qm2.getActiveProcessorTicks());
	}
	
	private void assertTimes(QueueManager qm, 
		int currentTime, int pTicks, int iTicks, int aTicks,
		int nTasks, int tTasks, int qTime, int wTime, int mWait){
		assertEquals(currentTime, qm.getCurrentTime());
		assertEquals(pTicks,  qm.getnProcessorTicks());
		assertEquals(iTicks, qm.getIdleProcessorTicks());
		assertEquals(aTicks, qm.getActiveProcessorTicks());
		assertEquals(nTasks, qm.getTotalNumTasks());
		assertEquals(tTasks, qm.getTotalTimeTasks());
		assertEquals(qTime, qm.getTotalQueueTime());
		assertEquals(wTime, qm.getTotalWaitTime());
		assertEquals(mWait, qm.getMaxWaitTime());
	}
	
	@Test
	public void testRemoveProcessor() {
		// Not yet implemented
	}

}
