package ggt;


/**
* ggt/StarterOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Aufgabe2.idl
* Freitag, 25. April 2014 10:00 Uhr MESZ
*/

public interface StarterOperations 
{
  String name ();

  //Methods for the Koordinator
  void createProcess (int count) throws ggt.StarterPackage.exInvalidCount;
  void exit ();
} // interface StarterOperations
