package ggt;


/**
* ggt/StarterListHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Aufgabe2.idl
* Freitag, 25. April 2014 09:09 Uhr MESZ
*/

public final class StarterListHolder implements org.omg.CORBA.portable.Streamable
{
  public ggt.Starter value[] = null;

  public StarterListHolder ()
  {
  }

  public StarterListHolder (ggt.Starter[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = ggt.StarterListHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    ggt.StarterListHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return ggt.StarterListHelper.type ();
  }

}
