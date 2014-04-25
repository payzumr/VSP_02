package server;

import ggt.KoordinatorHelper;
import ggt.Process;

import java.util.List;


public class TerminatorThread extends Thread {
	private int termTime;
	private List<Process> ring; 
	private KoordinatorImpl koord;
	private volatile boolean running = true;
	private int sequenz = 0;

	public TerminatorThread(int termTime, List<Process> ring, KoordinatorImpl koord) {
		super();
		this.termTime = termTime;
		this.ring = ring;
		this.koord = koord;
	}

	@Override
	public void run() {
		
		while(running){
			try {
				sleep(termTime);
				
				int randomPlace = (int)Math.round(Math.random()*(ring.size()-1));
				
				if(!koord.terminated){
					ring.get(randomPlace).sendMarker("Koordinator", sequenz);
					System.out.println("termThread seq++: " + sequenz);
					sequenz++;
					
				}else{
					running = false;
					koord.monitor.ergebnis(koord.lastProcess.name(), 1234);
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		koord.exit();
	}
}
