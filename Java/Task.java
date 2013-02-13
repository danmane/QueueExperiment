
class Task {
	private int duration, startTime;
	private boolean phantom;

	public boolean isPhantom(){
		return phantom;
	}

	public int getDuration(){
		return duration;
	}

	private Task(int startTime, int duration, boolean isPhantom){
		this.startTime = startTime;
		this.duration  = duration;
		this.phantom = isPhantom;
	}

	public Task makePhantomTask(int startTime){
		return new Task(startTime, 0, true);
	}

	public Task makeRegularTask(int startTime, int duration){
		return new Task(startTime, duration, false);
	}

}