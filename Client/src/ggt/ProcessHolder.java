package ggt;

/**
* ggt/ProcessHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Aufgabe2.idl
* Freitag, 25. April 2014 12:15 Uhr MESZ
*/

public final class ProcessHolder implements org.omg.CORBA.portable.Streamable
{
  public ggt.Process value = null;

  public ProcessHolder ()
  {
  }

  public ProcessHolder (ggt.Process initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = ggt.ProcessHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    ggt.ProcessHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return ggt.ProcessHelper.type ();
  }

}
