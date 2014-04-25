package starter;

import java.util.*;

import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import ggt.Koordinator;
import ggt.Process;
import ggt.ProcessHelper;
import ggt.StarterPOA;
import ggt.KoordinatorPackage.exAlreadyExists;
import ggt.StarterPackage.exInvalidCount;

public class StarterImpl extends StarterPOA {
	private String name;
	private Koordinator koor;
	private List<ProcessImpl> processList = new ArrayList<ProcessImpl>();
	private POA poa;

	public StarterImpl(String name, Koordinator koor, POA poa) {
		super();
		this.name = name;
		this.koor = koor;
		this.poa = poa;
	}

	@Override
	public synchronized String name() {
		return this.name;
	}

	@Override
	public synchronized void createProcess(int count) throws exInvalidCount {
		System.out.println("huhu1");
		int numberOfProcesses = count;
		if (numberOfProcesses < 1) {
			System.out.println("huhu2");
			throw new exInvalidCount();
		} else {
			System.out.println("huhu3");
			for (int i = 0; i < numberOfProcesses; i++) {
				String pName = name + i;// make name
				ProcessImpl newP = new ProcessImpl(pName, poa);// generate new
																// Process
				processList.add(newP);// add to process list
				newP.getThread().start();
				System.out.println("huhu4");
				try {
					Process pro = ProcessHelper.narrow(poa
							.servant_to_reference(newP));// narrow ProcessImpl
															// to Process
					koor.registerProcess(pro);// register process at Koordinator
					System.out.println("huhu5");
				} catch (ServantNotActive | WrongPolicy e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (exAlreadyExists e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public synchronized void exit() {
		try {
			new Thread() {
				public void run() {
					System.exit(0);
				}
			}.start();
		} catch (Exception e) {
			System.err.println("Fehler: " + e);
			e.printStackTrace(System.out);
		}
	}
	
	public synchronized void processExit(){
		for (ProcessImpl e : processList) {
			e.quit(name);
		}
	}
}
