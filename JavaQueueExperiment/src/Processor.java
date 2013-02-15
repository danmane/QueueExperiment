import java.util.*;

public class Processor {
	private boolean idle; // true if processor has no jobs, false otherwise
	private int timeToCompletion; // time until current job is completed
	private int timeToIdle; // time until every job in the queue is completed
	private LinkedList<Task> processorQueue;
	private int processorID;
	
	public Processor(int processorID){
		this.processorID = processorID;
		timeToCompletion = 0;
		timeToIdle = 0;
		idle = true;
		processorQueue = new LinkedList<Task>();		
	}

	public boolean isIdle() {
		return idle;
	}

	public int getTimeToCompletion() {
		return timeToCompletion;
	}

	public int getTimeToIdle() {
		return timeToIdle;
	}

	public int getQueueLength() {
		return processorQueue.size();
	}
	
	public int getProcessorID() {
		return processorID;
	}
	
	public boolean tickTime(int currentTime){
		// if occupied - reduce time to completion by 1
		// if time to completion goes to zero: either take a task from teh queue or
		// if queue is empty, change state to idle
		// returns true if processor is now idle and false otherwise
		if (idle){
			return idle;
		}
		
		timeToCompletion--;
		timeToIdle--;
		
		if (timeToCompletion == 0){
			if (processorQueue.size() > 0){
				processTask(processorQueue.pop(), currentTime);
			} else {
				idle = true;
			}
		}
		return idle;
	}
	
	public void takeTask(Task task, int currentTime){
		// Takes a task (this should be called by the QueueManager)
		// if the processor is idle it will process the task immediately
		// otherwise it will add it to the queue
		if (idle){ // process the task immediately
			processTask(task, currentTime);
		}
		else { // add the task to the queue
			timeToIdle += task.getDuration();
			processorQueue.add(task);
		}
	} 
	
	private void processTask(Task task, int currentTime){
		// Processes the task in question; tells the task it is now being served + updates timeToCompletion etc accordingly
		assert (task.getDuration() > 0);

		idle = false;
		timeToCompletion = task.getDuration();
		timeToIdle = task.getDuration();	

		task.processMe(currentTime);
	}




	
	
}