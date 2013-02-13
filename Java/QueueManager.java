
import java.util.*

class QueueManager {
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
	private int currentQueueLength; // length of the queue at current timestep
	private LinkedList<Processor> processors; // a list of every processor
	private LinkedList<Processor> activeProcessors; // a list of active processors
	private LinkedList<Processor> freeProcessors; // a list of unoccupied processors
	private int currentTime; // the current timestep
	private int maxTime; // the final time for which we record new results, or equivalently,
		// the number of datapoints the simulation will generate
	private int[] queueLengths; // an array listing queue length for each time step
	private int[] waitTimes; // records how long the new 'phantom task' added at the start
		// of each timestep has to wait until it reaches head of queue and goes to a free processor
	private int processedTimeStep; // records what is the maximum timestep for which the
		// phantom timestep reached the head of the queue. this allows us to ensure that 
		// when we finish iteration, we fill up both the queueLengths array and the waitTimes
		// array. without this variable, we would probably have some phantom tasks waiting in the queue
		// when we currentTime == maxTime

	public QueueManager(int nProcessors, int simulationLength) { // constructor
		currentTime = 0;
		results

	}

	public int tick(){ // Tick one second of the queue; returns number of people waiting
		currentTime++;
		addRandomTasks();
		updateActiveProcessors();
		assignJobs();
		return queueLength;
	}

	private void addRandomTasks(){ // randomly add tasks to the queue

	}

	private int randomTaskLength(){ // get a random length for a task

	}

	private void updateActiveProcessors(){ // tick the time in each processor
		// and move newly freed processors to the freeProcessor list

	}

	private void assignJobs(){ // while there are free processors and tasks, assign tasks 

	}

	public int addProcessor(){ // Add a processor, return new # of processors

	}

	public int removeProcessor() { // remove a processor and return # of processors

	}
}