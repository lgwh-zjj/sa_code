/**
 * E_mailServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package sa_project3;

public class E_mailServiceLocator extends org.apache.axis.client.Service implements sa_project3.E_mailService {

    public E_mailServiceLocator() {
    }


    public E_mailServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public E_mailServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for E_mail
    private java.lang.String E_mail_address = "http://localhost:8080/sa_project3/services/E_mail";

    public java.lang.String getE_mailAddress() {
        return E_mail_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String E_mailWSDDServiceName = "E_mail";

    public java.lang.String getE_mailWSDDServiceName() {
        return E_mailWSDDServiceName;
    }

    public void setE_mailWSDDServiceName(java.lang.String name) {
        E_mailWSDDServiceName = name;
    }

    public sa_project3.E_mail getE_mail() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(E_mail_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getE_mail(endpoint);
    }

    public sa_project3.E_mail getE_mail(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            sa_project3.E_mailSoapBindingStub _stub = new sa_project3.E_mailSoapBindingStub(portAddress, this);
            _stub.setPortName(getE_mailWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setE_mailEndpointAddress(java.lang.String address) {
        E_mail_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (sa_project3.E_mail.class.isAssignableFrom(serviceEndpointInterface)) {
                sa_project3.E_mailSoapBindingStub _stub = new sa_project3.E_mailSoapBindingStub(new java.net.URL(E_mail_address), this);
                _stub.setPortName(getE_mailWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("E_mail".equals(inputPortName)) {
            return getE_mail();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://sa_project3", "E_mailService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://sa_project3", "E_mail"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("E_mail".equals(portName)) {
            setE_mailEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
