/**
 * E_mail.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package sa_project3;

public interface E_mail extends java.rmi.Remote {
    public boolean validateEmailAddress_SOAP(java.lang.String _url) throws java.rmi.RemoteException;
    public boolean sendEmail_RECT(java.lang.String _url, java.lang.String _payload) throws java.rmi.RemoteException;
    public boolean sendEmail_SOAP(java.lang.String _url, java.lang.String _payload) throws java.rmi.RemoteException;
}
