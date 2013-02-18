
import java.util.*;

public class QueueManager {
	// Simulates a queue (maybe the line at a bank) and records data. The queue has a sequence
	// of tasks of variable length which are serviced FIFO. The manager has a fixed number of
	// processors which can each independently handle one task at a time, and each can support an internal queue. The number of processors
	// may change in response to queue conditions. 

	private LinkedList<Task> queue; // Linked list of the queue of tasks to be completed
	private LinkedList<Processor> processors; // a list of every processor
	private int currentTime; // the current timestep
	private int nextTaskID;
	private int nextProcessorID;
	
	private LinkedList<Task> activeTasks; // All tasks that havent yet been processed
	// implementation assumes strict FIFO which isn't true when using processor subqueues
	
	private int nProcessorTicks;
	private int idleProcessorTicks;
	private int activeProcessorTicks;
	
	private int totalNumTasks;
	private int totalTimeTasks;
	private int totalQueueTime;
	
	private int totalWaitTime;
	private int maxWaitTime;
	private int processedTasks;
	private int activeProcessors;

	public QueueManager(int nProcessors) { // constructor
		currentTime = 0;
		nextTaskID = 0;
		nextProcessorID = 0;
		processors = new LinkedList<Processor>();
		for (int i=0; i<nProcessors; i++){
			addProcessor();
		}
		queue = new LinkedList<Task>();
		activeTasks = new LinkedList<Task>();
		
		nProcessorTicks = 0;
		idleProcessorTicks = 0;
		activeProcessorTicks = 0;
		totalNumTasks = 0;
		totalTimeTasks = 0;
		totalQueueTime = 0;
		
		totalWaitTime = 0;
		maxWaitTime = 0;
		processedTasks = 0;
		activeProcessors = 0;
		
	}
	public int tick(){ // Tick one second of the queue; returns the current time
		assignJobs();
		tickProcessors();
		totalQueueTime += queue.size();
		recordWaitTimes();
		currentTime++;
		return currentTime;
	}

	private void recordWaitTimes(){
		Task pTask;
		
		while (activeTasks.size() > 0 && activeTasks.peek().getProcessed()){
			pTask = activeTasks.pop();
			totalWaitTime += pTask.getWait();
			processedTasks++;
			maxWaitTime = Math.max(maxWaitTime, pTask.getWait());
		}
	}
	
	
	private void tickProcessors(){ // tick the time in each processor
		activeProcessors = 0;
		for (Processor p : processors){
			 if (p.isIdle()){
				 idleProcessorTicks++;
			 } else {
				 activeProcessorTicks++;
				 activeProcessors++;
			 }
			 p.tickTime(currentTime);
		}
		nProcessorTicks += processors.size();
	}

	private void assignJobs(){ // while there are free processors and tasks, assign tasks 
		for (Processor p : processors){
			if (p.isIdle() && queue.size() > 0){
				p.takeTask(queue.pop(), currentTime);
			}
		}
	}

	public void addProcessor(){ // Add a processor
		Processor newProcessor = new Processor(nextProcessorID);
		nextProcessorID++;
		processors.add(newProcessor);
	}
	
	public void addTask(int duration){
		if (duration == 0){
			System.out.println("Bad task length");
			return;
		}
		Task newTask = new Task(nextTaskID, currentTime, duration);
		nextTaskID++;
		queue.add(newTask);
		activeTasks.add(newTask);
		totalNumTasks++;
		totalTimeTasks += duration;
		
	}

	public boolean isFinished(){
		return (queue.size() == 0 && activeProcessors == 0);
	}
	
	public int removeProcessor() { // remove a processor - not implemented
		return 1;
	}
	
	public int getProcessedTasks(){
		return processedTasks;
	}

	public int getCurrentTime() {
		return currentTime;
	}
	public int getNumProcessors(){
		return processors.size();
	}
	public int getQueueLength(){
		return queue.size();
	}
	public int getnProcessorTicks() {
		return nProcessorTicks;
	}
	public int getIdleProcessorTicks() {
		return idleProcessorTicks;
	}
	public int getActiveProcessorTicks() {
		return activeProcessorTicks;
	}
	public int getTotalNumTasks() {
		return totalNumTasks;
	}
	public int getTotalTimeTasks() {
		return totalTimeTasks;
	}
	public int getTotalQueueTime() {
		return totalQueueTime;
	}
	public int getTotalWaitTime() {
		return totalWaitTime;
	}
	public int getMaxWaitTime() {
		return maxWaitTime;
	}
}