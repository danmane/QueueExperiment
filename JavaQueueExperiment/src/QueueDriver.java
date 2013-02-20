import java.util.Random;

public class QueueDriver {
	int nProc;
	int maxTicks;
	double taskProbability;
	int meanLength;
	int stdev;
	QueueManager qm;
	Random myRandomGen;

	public QueueDriver(int seed){
		if (seed==0){
			myRandomGen = new Random();
		} else {
			myRandomGen = new Random(seed);
		}
	}
	
	public QueueManager runQueue(int nProc, int maxTicks, double taskProbability, int meanLength, int stdev){
		this.nProc = nProc;
		this.maxTicks = maxTicks;
		this.taskProbability = taskProbability;
		this.meanLength = meanLength;
		this.stdev = stdev;
		qm = new QueueManager(nProc);
		while (qm.tick() < maxTicks){
			if (binomialBool(taskProbability)){
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
		reportQM(qm);
		return qm;
	}
	
	public static void main(String[] args){
		QueueDriver qd = new QueueDriver(1);
		qd.runQueue(3, 500, .03, 70, 15);
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
	

	private boolean binomialBool(double p){
		return (myRandomGen.nextDouble() < p);
	}
	
	private int randomTaskLength(){
		double g;
		g = myRandomGen.nextGaussian();
		g *= stdev;
		g += meanLength;
		return (int) g;
	}
}
