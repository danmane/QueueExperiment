
import java.util.*;

public class QueueManager {
	// Simulates a queue (maybe the line at a bank) and records data. The queue has a sequence
	// of tasks of variable length which are serviced FIFO. The manager has a fixed number of
	// processors which can each independently handle one task at a time. The number of processors
	// may change in response to queue conditions. 
	// The QueueManager records two types of data. First, what is the length of the queue at each timestep.
	// Second, how long does a new task appearing at each timestep have to wait before it
	// reaches the head of the queue and is serviced. This data is collected via a 'phantom task'
	// which is generated at each timestep. The 'phantom task' never is sent to a processor, but 
	// when it arrives the time it waited since it was generated is recorded

	private LinkedList<Task> queue; // Linked list of the queue of tasks to be completed
	private int nProcessors; // current number of active processors 
	private LinkedList<Processor> processors; // a list of every processor
	private LinkedList<Processor> activeProcessors; // a list of active processors
	private LinkedList<Processor> freeProcessors; // a list of unoccupied processors
	private int currentTime; // the current timestep
	private int closingTime; // time when the simulation stops adding new tasks
	private int nextTaskID;
	private int nextProcessorID;
	


	public QueueManager(int nProcessors, int closingTime) { // constructor
		currentTime = 0;
		this.closingTime = closingTime;
		nextTaskID = 0;
		nextProcessorID = 0;
		processors = new LinkedList<Processor>();
		for (int i=0; i<nProcessors; i++){
			addProcessor();
		}
		
	}

	public void tick(){ // Tick one second of the queue; returns number of people waiting
		currentTime++;
		addRandomTasks();
		updateActiveProcessors();
		assignJobs();
	}

	private void addRandomTasks(){ // randomly add tasks to the queue

	}

	private int randomTaskLength(){ // get a random length for a task
		return 1;
	}

	private void updateActiveProcessors(){ // tick the time in each processor
		// and move newly freed processors to the freeProcessor list
		for (Processor p : processors){
			boolean idle;
			idle = p.tickTime(currentTime);
			
		}

	}

	private void assignJobs(){ // while there are free processors and tasks, assign tasks 

	}

	public void addProcessor(){ // Add a processor
		Processor newProcessor = new Processor(nextProcessorID);
		nextProcessorID++;
		processors.add(newProcessor);
	}
	
	public void addTask(int duration){
		Task newTask = new Task(nextTaskID, currentTime, duration);
		nextTaskID++;
		queue.add(newTask);
	}

	public int removeProcessor() { // remove a processor - not implemented
		return 1;

	}
}