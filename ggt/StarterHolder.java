package ggt;

/**
* ggt/StarterHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Aufgabe2.idl
* Freitag, 25. April 2014 11:01 Uhr MESZ
*/

public final class StarterHolder implements org.omg.CORBA.portable.Streamable
{
  public ggt.Starter value = null;

  public StarterHolder ()
  {
  }

  public StarterHolder (ggt.Starter initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = ggt.StarterHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    ggt.StarterHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return ggt.StarterHelper.type ();
  }

}
