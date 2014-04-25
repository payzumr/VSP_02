package ggt;


/**
* ggt/KoordinatorOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Aufgabe2.idl
* Freitag, 25. April 2014 11:01 Uhr MESZ
*/

public interface KoordinatorOperations 
{

  // Methods for the Process
  void registerProcess (ggt.Process process) throws ggt.KoordinatorPackage.exAlreadyExists;
  void terminationCcheck (ggt.Process terminator, int seqN, boolean status);

  // Methods for the Starter
  void activateStarter (ggt.Starter starter, String starterName) throws ggt.KoordinatorPackage.exAlreadyExists;
  void deleteStarter (ggt.Starter starter);

  // Methods for the Monitor
  void registerMonitor (monitor.Monitor monitor);
  void deleteMonitor ();

  // Methods for the Client
  void startCalculation (int minProcesses, int maxProcesses, int minDelay, int maxDelay, int timeout, int ggt);
  ggt.Starter[] getStarterListe ();
  void exit ();
} // interface KoordinatorOperations
