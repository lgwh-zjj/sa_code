package sa_project;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Server extends Thread {
    ServerSocket server = null;
    Socket socket = null;
    String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
    String dbURL="jdbc:sqlserver://database-1.ce6lobyfxsbk.us-east-1.rds.amazonaws.com:1433;DatabaseName=test";
    String userName="admin";
    String userPwd="lgwh0830qie";
    Connection ct;
    String get_message;
    public Server(int port) {
        try {
            server = new ServerSocket(port);
            try {
				Class.forName(driverName);
				System.out.println("���������ɹ���");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            try {
				ct=DriverManager.getConnection(dbURL,userName,userPwd);
				 System.out.println("�������ݿ�ɹ���");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        super.run();
        try {
            System.out.println("  �ȴ��ͻ�������...");
            socket = server.accept();
            System.out.println("  �ͻ��� ��" + socket.getInetAddress().getHostAddress() + "�� ���ӳɹ�...");
            InputStream in = socket.getInputStream();
            int len = 0;
            byte[] buf = new byte[14400];
            if((len = in.read(buf)) != -1) {
                System.out.println("  �ͻ���: ��"
                        + socket.getInetAddress().getHostAddress() + "��˵��"
                        + new String(buf, 0, len, "UTF-8"));
                get_message=new String(buf,0,len,"UTF-8"); 
            }
            new sendMessThread().start();// ���Ӳ�����socket�������÷�����Ϣ�߳�
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class sendMessThread extends Thread {
        @Override
        public void run() {
            super.run();
            OutputStream out = null;
            String s = null;
            try {
                if (socket != null) {
                	System.out.println("get_message="+get_message);
                	function f=new function(ct);
                	String value[]=get_message.split("-");          
                    if(Integer.parseInt(value[0])==1)
                    {                  	
                    	s="1-";
                    	s=s+f.func1(Integer.parseInt(value[1]),Integer.parseInt(value[2]),Integer.parseInt(value[3]));
                    }
                    if(Integer.parseInt(value[0])==2)
                    {
                    	s="2-";
                    	s=s+f.func2(Integer.parseInt(value[1]),Integer.parseInt(value[2]));
                    }
                    out = socket.getOutputStream();
                    System.out.println("д������:"+s);
                    out.write(("" +s).getBytes("UTF-8"));
                    out.flush();// ��ջ�����������
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // �������
    public static void main(String[] args) {
        Server server = new Server(6666);
        server.start();
    }
}