
class Processor {
	private int timeToCompletion; // time until current job is done
	private boolean occupied; // true if it is currently working on a job, false otherwise

	public Processor(){
		timeToCompletion = 0;
		occupied = false;
	}

	public boolean takeTask(Task task){
		// return true if task was taken, false otherwise
		return false;
	} 

	public boolean isOccupied(){
		return occupied;
	}

	public void tickTime(){
		// if occupied - reduce time to completion by 1
		// if time to completion goes to zero - report that the task is finished 
		// && the processor is free
	}
}