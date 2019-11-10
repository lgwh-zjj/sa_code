package sa_project3;

import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
public class client extends JFrame{
	private JPanel  contentPane;
	private JTextField text1;
	private JTextArea text2;
	private JButton button1,button2;
	private JLabel label1,label2;
	
	public client()
	{
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        setTitle("客户端");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        
        label1=new JLabel("邮箱");
        label1.setBounds(50,10,40,40);
        contentPane.add(label1);
        
        text1=new JTextField();
        text1.setBounds(100, 15, 200, 30);
        contentPane.add(text1);
        
        label2=new JLabel("内容");
        label2.setBounds(50,60,40,40);
        contentPane.add(label2);
        
        text2=new JTextArea();
        text2.setBounds(100, 60, 200, 150);
        contentPane.add(text2);
        
        button1=new JButton("SOAP");
        button1.setBounds(320, 10, 100, 80);
        contentPane.add(button1);
        button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				E_mailProxy proxy = new E_mailProxy();
				String _url=text1.getText();
				String _payload=text2.getText();
				try {
					if(!proxy.validateEmailAddress_SOAP(_url))
					{
						JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
								"邮箱有误!\n请重新输入！", "系统信息", JOptionPane.INFORMATION_MESSAGE); 
					}
					else {
						if(proxy.sendEmail_SOAP(_url, _payload))
						{
							JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
									"发送成功！", "系统信息", JOptionPane.INFORMATION_MESSAGE); 
						}
						else {
							{
								JOptionPane.showMessageDialog(new JFrame().getContentPane(), 
										"发送失败！", "系统信息", JOptionPane.INFORMATION_MESSAGE); 
							}
						}
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
        
        button2=new JButton("REST");
        button2.setBounds(320, 120, 100, 80);
        contentPane.add(button2);
        button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String _url=text1.getText();
				String _payload=text2.getText();
				String result="";
			    ClientConfig config = new ClientConfig();
			    Client client = ClientBuilder.newClient(config);	
			    WebTarget target = client.target("http://localhost:8080/sa_project3");
			    result=target.path("rest").path("/E_mailService").path("sendemail").queryParam("url", _url).queryParam("payload", _payload).request().accept(MediaType.TEXT_PLAIN).get(String.class);	    
			    System.out.println("Output from REST Server ......");
			    System.out.println("运算结果 = "+ result);

				
			}
		});
	}
	
	
	public static void main(String args[])
	{
		client myClient =new client();
		myClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myClient.setVisible(true);
	}

}
