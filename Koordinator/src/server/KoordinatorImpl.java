package server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.omg.PortableServer.POA;

import monitor.Monitor;
import ggt.Koordinator;
import ggt.KoordinatorHelper;
import ggt.KoordinatorPOA;
import ggt.Process;
import ggt.Starter;
import ggt.KoordinatorPackage.exAlreadyExists;
import ggt.StarterPackage.exInvalidCount;

public class KoordinatorImpl extends KoordinatorPOA{
	
	private Map<Starter, String> starters = new HashMap<Starter, String>();
	private List<Process> processes = new ArrayList<Process>();
	private List<Process> ringProcesses = new ArrayList<Process>(); 
	private Monitor monitor;
	private Koordinator koord;
	private POA poa;
	private boolean notTerminated = true;
	
	public KoordinatorImpl(POA poa){
		this.poa = poa;
	}
	

	@Override
	public synchronized void registerProcess(Process process) throws exAlreadyExists {
		processes.add(process);
		
	}

	@Override
	public synchronized boolean terminationComplete(Process terminator, int result) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public synchronized void activateStarter(Starter starter, String starterName) throws exAlreadyExists {
		starters.put(starter, starterName);
			}

	@Override
	public synchronized void deleteStarter(Starter starter) {
		starters.remove(starter);
		}
	
	public synchronized void deleteMonitor(Monitor monitor) {
		this.monitor = monitor;
	}
	
	public synchronized void deleteMonitor(){
		
	}

	@Override
	public synchronized void startCalculation(int minProcesses, int maxProcesses, int minDelay, int maxDelay, int timeout, int ggt) {
		koord = KoordinatorHelper.narrow(poa);
		int [] startZahlen = new int[ringProcesses.size()];
		int numOfProcess = (int)Math.round(Math.random()*minProcesses)+maxProcesses;
		
		for (Starter e : starters.keySet()) {
			try{
				e.createProcess(numOfProcess);
			}catch(exInvalidCount ex){
				System.out.println(ex.s);
			}
		}
		
		Map<Integer, Process> newList = new HashMap<Integer, Process>();
		int length = processes.size();
		for(int j = 0; j < length; j++)
		{
			int randomPlace = (int)Math.round(Math.random()*processes.size());
			ringProcesses.add(processes.get(randomPlace));
			processes.remove(randomPlace);
		}
		for (int i = 0; i < ringProcesses.size(); i++){
			
			int startGGT = ggt*((int)Math.round(Math.random()*1)+100)*((int)Math.round(Math.random()*1)+100);
			startZahlen[i] = startGGT;
			int delay = (int)Math.round(Math.random()*minDelay)+maxDelay;
		
			if(i == 0){
				ringProcesses.get(i).init(ringProcesses.get(i+1), ringProcesses.get(ringProcesses.size()), startGGT , delay, timeout, monitor, koord);
			}
			else if(i == ringProcesses.size()){
				ringProcesses.get(i).init(ringProcesses.get(0), ringProcesses.get(i-1), startGGT , delay, timeout, monitor, koord);
			}
			else{
				ringProcesses.get(i).init(ringProcesses.get(i+1), ringProcesses.get(i-1), startGGT , delay, timeout, monitor, koord);
			}	
			newList.put(startGGT,ringProcesses.get(i) );
		}
		String[] showRing = new String[ringProcesses.size()];
		for (int i = 0; i < ringProcesses.size(); i++) {
			showRing[i] = ringProcesses.get(i).name();
		}
		
		monitor.ring(showRing);
		monitor.startzahlen(startZahlen);
		
		//3 Prozesse mit der kleinsten Zahl starten
		List sortList = new ArrayList(newList.keySet());
		Collections.sort(sortList);
		newList.get(sortList.get(0)).startCalulation();
		newList.get(sortList.get(1)).startCalulation();
		newList.get(sortList.get(2)).startCalulation();
		
		
		//------------------------------- hier muss der termination complete code rein
		
		while(notTerminated){
			
			
			
		}
		//-------------------------------
		
	}

	@Override
	public synchronized Starter[] getStarterListe() {
		Starter[] tmp = new Starter[starters.size()];
		int i = 0;
		for (Starter e : starters.keySet()) {
			tmp[i] = e;
			i++;
		}
		
		return tmp;
	}

	@Override
	public synchronized void quit() {
		// TODO Auto-generated method stub
		
	}

}
