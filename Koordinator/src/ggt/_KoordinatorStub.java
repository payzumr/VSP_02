package ggt;


/**
* ggt/_KoordinatorStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Aufgabe2.idl
* Freitag, 25. April 2014 12:15 Uhr MESZ
*/

public class _KoordinatorStub extends org.omg.CORBA.portable.ObjectImpl implements ggt.Koordinator
{


  // Methods for the Process
  public void registerProcess (ggt.Process process) throws ggt.KoordinatorPackage.exAlreadyExists
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("registerProcess", true);
                ggt.ProcessHelper.write ($out, process);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:ggt/Koordinator/exAlreadyExists:1.0"))
                    throw ggt.KoordinatorPackage.exAlreadyExistsHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                registerProcess (process        );
            } finally {
                _releaseReply ($in);
            }
  } // registerProcess

  public void terminationCcheck (ggt.Process terminator, int seqN, boolean status)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("terminationCcheck", true);
                ggt.ProcessHelper.write ($out, terminator);
                $out.write_long (seqN);
                $out.write_boolean (status);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                terminationCcheck (terminator, seqN, status        );
            } finally {
                _releaseReply ($in);
            }
  } // terminationCcheck


  // Methods for the Starter
  public void activateStarter (ggt.Starter starter, String starterName) throws ggt.KoordinatorPackage.exAlreadyExists
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("activateStarter", true);
                ggt.StarterHelper.write ($out, starter);
                $out.write_string (starterName);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:ggt/Koordinator/exAlreadyExists:1.0"))
                    throw ggt.KoordinatorPackage.exAlreadyExistsHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                activateStarter (starter, starterName        );
            } finally {
                _releaseReply ($in);
            }
  } // activateStarter

  public void deleteStarter (ggt.Starter starter)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("deleteStarter", true);
                ggt.StarterHelper.write ($out, starter);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                deleteStarter (starter        );
            } finally {
                _releaseReply ($in);
            }
  } // deleteStarter


  // Methods for the Monitor
  public void registerMonitor (monitor.Monitor monitor)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("registerMonitor", true);
                ggt.MonitorHelper.write ($out, monitor);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                registerMonitor (monitor        );
            } finally {
                _releaseReply ($in);
            }
  } // registerMonitor

  public void deleteMonitor ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("deleteMonitor", true);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                deleteMonitor (        );
            } finally {
                _releaseReply ($in);
            }
  } // deleteMonitor


  // Methods for the Client
  public void startCalculation (int minProcesses, int maxProcesses, int minDelay, int maxDelay, int timeout, int ggt)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("startCalculation", true);
                $out.write_long (minProcesses);
                $out.write_long (maxProcesses);
                $out.write_long (minDelay);
                $out.write_long (maxDelay);
                $out.write_long (timeout);
                $out.write_long (ggt);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                startCalculation (minProcesses, maxProcesses, minDelay, maxDelay, timeout, ggt        );
            } finally {
                _releaseReply ($in);
            }
  } // startCalculation

  public ggt.Starter[] getStarterListe ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getStarterListe", true);
                $in = _invoke ($out);
                ggt.Starter $result[] = ggt.StarterListHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getStarterListe (        );
            } finally {
                _releaseReply ($in);
            }
  } // getStarterListe

  public void exit ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("exit", true);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                exit (        );
            } finally {
                _releaseReply ($in);
            }
  } // exit

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:ggt/Koordinator:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _KoordinatorStub
