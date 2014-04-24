package starter;

import java.util.LinkedList;

import monitor.Monitor;
import ggt.Koordinator;
import ggt.Process;
import ggt.ProcessPOA;

public class ProcessImpl extends ProcessPOA implements Runnable {
	private String name;
	private volatile boolean running = true;
	private ProcessStatus status = ProcessStatus.IDLE;
	private LinkedList<Integer> newNumbers = new LinkedList<Integer>();

	private Process rightNeighbor;
	private Process leftNeighbor;
	private int startGGT;
	private int delay;
	private int timeout;
	private Monitor theMonitor;
	private Koordinator koor;

	private int mi;

	public ProcessImpl(String name) {
		super();
		this.name = name;
	}

	@Override
	public synchronized String name() {
		return name;
	}

	@Override
	public synchronized void getNumber(int number) {
		//theMonitor.rechnen(name, "absender", number);
		newNumbers.add(number);
		this.status = ProcessStatus.CALCULATE;
	}

	@Override
	public synchronized void terminate(Process terminator) {
		// TODO Auto-generated method stub

	}

	@Override
	public synchronized void stop(Process stopper) {
		// TODO Auto-generated method stub

	}

	@Override
	public synchronized void quit(String starterName) {
		// TODO Auto-generated method stub

	}

	@Override
	public synchronized void init(Process rightNeighbor, Process leftNeighbor,
			int startGGT, int delay, int timeout, Monitor theMonitor,
			Koordinator koor) {
		this.rightNeighbor = rightNeighbor;
		this.leftNeighbor = leftNeighbor;
		this.startGGT = startGGT;
		this.delay = delay;
		this.timeout = timeout;
		this.theMonitor = theMonitor;
		this.koor = koor;

		this.mi = startGGT;

	}

	@Override
	public void startCalulation() {

	}

	@Override
	public void run() {
		while (running) {
			switch (status) {
			case CALCULATE:
				while (!newNumbers.isEmpty()) {
					calculateMi();
				}
				
				rightNeighbor.getNumber(mi);
				leftNeighbor.getNumber(mi);
				this.status = ProcessStatus.IDLE;
				break;

			default:
				break;
			}
		}
	}

	private void calculateMi() {
		if (newNumbers.getFirst() < mi) {
			try {
				this.wait(delay);// simulate complex calculation
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mi = ((mi - 1) % newNumbers.poll()) + 1;
			System.out.println("Process: " + name + " Mi = " + mi);

		} else {
			// ???
			newNumbers.poll();
		}
		
	}

}
