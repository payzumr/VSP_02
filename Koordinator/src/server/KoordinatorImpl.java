package server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

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
	public void registerProcess(Process process) throws exAlreadyExists {
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
	
	public synchronized void registerMonitor(Monitor monitor) {
		this.monitor = monitor;
	}
	
	public synchronized void deleteMonitor(){
		
	}

	@Override //timeout refactor 
	public synchronized void startCalculation(int minProcesses, int maxProcesses, int minDelay, int maxDelay, int timeout, int ggt) {
		KoordinatorImpl tmp = this;
		try {
			koord = KoordinatorHelper.narrow(poa.servant_to_reference(tmp));
		} catch (ServantNotActive | WrongPolicy e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int numOfProcess = (int)Math.round(Math.random()*(maxProcesses-minProcesses))+minProcesses;
		
		System.out.println("starters: " + starters.size());
		for (Starter e : starters.keySet()) {
			try{
				e.createProcess(numOfProcess);
			}catch(exInvalidCount ex){
				System.out.println(ex.s);
			}
		}
		
		System.out.println("huhu1");
		Map<Integer, Process> newList = new HashMap<Integer, Process>();
		int length = processes.size();
		for(int j = 0; j < length; j++)
		{
			int randomPlace = (int)Math.round(Math.random()*(processes.size()-1));
			ringProcesses.add(processes.get(randomPlace));
			processes.remove(randomPlace);
		}
		System.out.println("huhu2");
		System.out.println("ring size. " + ringProcesses.size());
		int [] startZahlen = new int[ringProcesses.size()];
		for (int i = 0; i < ringProcesses.size(); i++){
			
			int startGGT = ggt*((int)Math.round(Math.random()*100)+1)*((int)Math.round(Math.random()*100)+1);
			startZahlen[i] = startGGT;
			int delay = (int)Math.round(Math.random()*minDelay)+maxDelay;
			
			if(i == 0){
//				System.out.println("huhu3");
//				System.out.println(ringProcesses.get(0).name());
				ringProcesses.get(i).init(ringProcesses.get(i+1), ringProcesses.get(ringProcesses.size()-1), startGGT , delay, timeout, monitor, koord);
			}
			else if(i == ringProcesses.size()-1){
				ringProcesses.get(i).init(ringProcesses.get(0), ringProcesses.get(i-1), startGGT , delay, timeout, monitor, koord);
			}
			else{
				ringProcesses.get(i).init(ringProcesses.get(i+1), ringProcesses.get(i-1), startGGT , delay, timeout, monitor, koord);
			}	
			newList.put(startGGT,ringProcesses.get(i) );
		}
		String[] showRing = new String[ringProcesses.size()];
		System.out.println("huhu3");
		for (int i = 0; i < ringProcesses.size(); i++) {
			showRing[i] = ringProcesses.get(i).name();
		}
		
//		monitor.ring(showRing);
//		monitor.startzahlen(startZahlen);
		
		//3 Prozesse mit der kleinsten Zahl starten
		System.out.println("Prozesse: "+newList.size());
		List<Integer> sortList = new ArrayList<Integer>(newList.keySet());
		Collections.sort(sortList);
		newList.get(sortList.get(0)).startCalulation();
		newList.get(sortList.get(1)).startCalulation();
		newList.get(sortList.get(2)).startCalulation();
		
		
		//------------------------------- hier muss der termination complete code rein
		// soll der client solange offen bleiben oder schon beenden?
		
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
