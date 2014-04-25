package server;

import ggt.Process;
import ggt.Starter;

import java.util.List;

public class TerminatorThread extends Thread {
	private int termTime;
	private List<Process> ring;
	private KoordinatorImpl koord;
	private volatile boolean running = true;
	private int sequenz = 1;

	public TerminatorThread(int termTime, List<Process> ring,
			KoordinatorImpl koord) {
		super();
		this.termTime = termTime;
		this.ring = ring;
		this.koord = koord;
	}

	@Override
	public void run() {

		while (running) {
			try {
				sleep(termTime);

				int randomPlace = (int) Math.round(Math.random()
						* (ring.size() - 1));

				if (!koord.terminated) {
					ring.get(2).sendMarker("Koordinator", sequenz);
					sequenz++;

				} else {
					running = false;
					koord.monitor.ergebnis(koord.lastProcess.name(), koord.lastProcess.getMi());
					//Aufruf der Exit Methode der Starter
					Starter[] tmp = koord.getStarterListe();
					for (int i = 0; i < tmp.length; i++) {
						//processExit muss in die IDL (aus der StartImpl)
						tmp[i].processExit();
					}
					
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		koord.exit();
	}
}
