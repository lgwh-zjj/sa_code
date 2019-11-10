package sa_project3;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import java.util.regex.Pattern;


import java.util.regex.Matcher;

@Path("/E_mailService")
public class E_mail {
		@GET
		@Path("/sendemail")
		@Produces(MediaType.TEXT_PLAIN)
		public boolean sendEmail_RECT(@QueryParam("url") String _url,@QueryParam("payload") String _payload) {
			String accesskey="LTAI4FvPgqwYvN7gN42cTac2";
			String accesspassword="B3ushgLp6FZ8rtuwWaKp7qEzcF6iL1";
		    //建立与远程账号的链接
		    IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accesskey,accesspassword);
		    IAcsClient client = new DefaultAcsClient(profile);
		    SingleSendMailRequest request = new SingleSendMailRequest();
		    //发送账号
		    try {
		        request.setAccountName("admin@email.cugsericky.top");
		        request.setFromAlias("Email Server");
		        request.setAddressType(1);
		        request.setReplyToAddress(true);
		        request.setToAddress(_url);
		        request.setSubject("TestMessage");
		        request.setHtmlBody(_payload);
		        SingleSendMailResponse httpResponse = client.getAcsResponse(request);
		    }
		    catch (Exception e) {
		        System.out.println(e.toString());
		        return false;
		    }
		    return true;
			}  
	
		
	public boolean sendEmail_SOAP(String _url,String _payload) {
		String accesskey="LTAI4FvPgqwYvN7gN42cTac2";
		String accesspassword="B3ushgLp6FZ8rtuwWaKp7qEzcF6iL1";
		//建立与远程账号的链接
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accesskey,accesspassword);
		IAcsClient client = new DefaultAcsClient(profile);
		SingleSendMailRequest request = new SingleSendMailRequest();
		//发送账号
		try {
			request.setAccountName("admin@email.cugsericky.top");
			request.setFromAlias("Email Server");
			request.setAddressType(1);
			request.setReplyToAddress(true);
			request.setToAddress(_url);
			request.setSubject("TestMessage");
			request.setHtmlBody(_payload);
			SingleSendMailResponse httpResponse = client.getAcsResponse(request);
		}
		catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
		return true;
	}
	
	public boolean validateEmailAddress_SOAP(String _url)
	{
		String pat="\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}";
		Pattern p=Pattern.compile(pat);
		Matcher m=p.matcher(_url);
		if(m.matches())
		{
			//System.out.println("邮箱正确！");
			return true;
		}
		else
		{
			//System.out.println("邮箱不正确！");}
			return false;
		}

	}
	
}
