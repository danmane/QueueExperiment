
public class Task {
	private int duration; // how long will this task need at the processor
	private int startTime; // when was this task generated
	private int processedTime; // when was this task processed (-1 indicated not yet processed)
	private int taskID; // unique identifier for the task

	public Task(int taskID, int startTime, int duration){
		assert (duration > 0);
		this.taskID = taskID;
		this.startTime = startTime;
		this.duration  = duration;
		processedTime = -1;
	}
	
	public int getDuration(){
		return duration;
	}

	public int getStartTime() {
		return startTime;
	}

	public int getProcessedTime() {
		return processedTime;
	}

	public int getTaskID() {
		return taskID;
	}
	
	public void processMe(int processedTime) {
		this.processedTime = processedTime;
		
	}
	
	public boolean getProcessed(){
		return (processedTime != -1);
	}
	
	public int getWait(){
		if (processedTime != -1){
			return processedTime - startTime;
		}
		else {
			return -1;
		}
	}
	
}
