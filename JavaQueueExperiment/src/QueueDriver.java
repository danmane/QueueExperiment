
public class QueueDriver {
	

	public static void main(String[] args){
		QueueManager qm = new QueueManager(5);
		int numTasks;
		while (qm.tick() < 200){
			//System.out.println(qm.getQueueLength());
			numTasks = randomNumTasks();
			for (int i=0; i<numTasks; i++){
				qm.addTask(randomTaskLength());
			}	
		}
		
		while (!qm.isFinished()){
			qm.tick();
			if (qm.getCurrentTime() % 100 == 0){
				reportQM(qm);
				System.out.println("------------");
			}
		}
		//qm.tick();
		reportQM(qm);
	}
	public static void reportQM(QueueManager qm){
		System.out.println("Time: " + qm.getCurrentTime());
		System.out.println("nTicks: " + qm.getnProcessorTicks());
		System.out.println("idleTicks: " + qm.getIdleProcessorTicks());
		System.out.println("actTicks: " + qm.getActiveProcessorTicks());
		System.out.println("totalTasks: " + qm.getTotalNumTasks());
		System.out.println("TotalTimeTasks: " + qm.getTotalTimeTasks());
		System.out.println("TotalQueueTime: " + qm.getTotalQueueTime());
		System.out.println("TotalWaitTime: " + qm.getTotalWaitTime());
		System.out.println("ProcessedTasks: " + qm.getProcessedTasks());
	}
	

	public static int randomNumTasks(){
		return (int) (Math.random() * 2);
	}
	
	public static int randomTaskLength(){
		return (int) (Math.random() * 20);
	}
}
