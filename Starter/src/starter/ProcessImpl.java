package starter;

import monitor.Monitor;
import ggt.Koordinator;
import ggt.Process;
import ggt.ProcessPOA;

public class ProcessImpl extends ProcessPOA implements Runnable{
	private String name;
	private volatile boolean running = true;
	
	private Process rightNeighbor;
	private Process leftNeighbor;
	private int startGGT;
	private int delay;
	private int timeout;
	private Monitor theMonitor;
	private Koordinator koor;
	
	
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
		//theMonitor.rechnen(name, 	, number);
		if(number < startGGT){
			try {
				this.wait(delay);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int newNumber = null;
			
			
			if(newNumber < startGGT){
				rightNeighbor.getNumber(newNumber);
				leftNeighbor.getNumber(newNumber);
			}
			
		}else{
			//???
		}
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
	public synchronized void init(Process rightNeighbor, Process leftNeighbor, int startGGT,
			int delay, int timeout, Monitor theMonitor, Koordinator koor) {
		this.rightNeighbor = rightNeighbor;
		this.leftNeighbor = leftNeighbor;
		this.startGGT = startGGT;
		this.delay = delay;
		this.timeout = timeout;
		this.theMonitor = theMonitor;
		this.koor = koor;
		
	}

	@Override
	public void startCalulation() {
		
		
	}

	@Override
	public void run() {
		while(running){
			
		}
	}

}
