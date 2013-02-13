
class Task {
	private int duration, startTime;
	private boolean isPhantom;

	public TaskType getIsPhantom(){
		return isPhantom
	}

	public int getTaskTime(){
		return taskTime
	}

	private Task(int startTime, int duration, boolean isPhantom){
		this.startTime = startTime;
		this.duration  = duration;
		this.isPhantom = isPhantom;
	}

	public Task makePhantomTask(int startTime){
		return Task(startTime, 0, true);
	}

	public Task makeRegularTask(int startTime, int duration){
		return Task(startTime, duration, false);
	}

}