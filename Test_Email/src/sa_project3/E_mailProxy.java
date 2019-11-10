package sa_project3;

public class E_mailProxy implements sa_project3.E_mail {
  private String _endpoint = null;
  private sa_project3.E_mail e_mail = null;
  
  public E_mailProxy() {
    _initE_mailProxy();
  }
  
  public E_mailProxy(String endpoint) {
    _endpoint = endpoint;
    _initE_mailProxy();
  }
  
  private void _initE_mailProxy() {
    try {
      e_mail = (new sa_project3.E_mailServiceLocator()).getE_mail();
      if (e_mail != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)e_mail)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)e_mail)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (e_mail != null)
      ((javax.xml.rpc.Stub)e_mail)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public sa_project3.E_mail getE_mail() {
    if (e_mail == null)
      _initE_mailProxy();
    return e_mail;
  }
  
  public boolean validateEmailAddress_SOAP(java.lang.String _url) throws java.rmi.RemoteException{
    if (e_mail == null)
      _initE_mailProxy();
    return e_mail.validateEmailAddress_SOAP(_url);
  }
  
  public boolean sendEmail_RECT(java.lang.String _url, java.lang.String _payload) throws java.rmi.RemoteException{
    if (e_mail == null)
      _initE_mailProxy();
    return e_mail.sendEmail_RECT(_url, _payload);
  }
  
  public boolean sendEmail_SOAP(java.lang.String _url, java.lang.String _payload) throws java.rmi.RemoteException{
    if (e_mail == null)
      _initE_mailProxy();
    return e_mail.sendEmail_SOAP(_url, _payload);
  }
  
  
}