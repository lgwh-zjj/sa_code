/**
 * E_mailService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package sa_project3;

public interface E_mailService extends javax.xml.rpc.Service {
    public java.lang.String getE_mailAddress();

    public sa_project3.E_mail getE_mail() throws javax.xml.rpc.ServiceException;

    public sa_project3.E_mail getE_mail(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
