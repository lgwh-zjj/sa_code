package server;

import java.awt.EventQueue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Server  implements  Runnable {

    public static ServerSocket serverSocket=null;
    public static Socket clientSocket=null;
    public static DataOutputStream out=null;
    public static DataInputStream in=null;
    public static String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static String dbURL="jdbc:sqlserver://database-1.ce6lobyfxsbk.us-east-1.rds.amazonaws.com:1433;DatabaseName=test";
    public static String userName="admin";
    public static String userPwd="lgwh0830qie";
    public static Connection ct;
    public String get_message;
        public void run()//接收信息
        {
            try
            {
                while(true)
                {
                    if(in!=null)
                        {
                            String tmp=in.readUTF();
                            //BtextArea.append("\n客户端:\n"+tmp);
                            get_message=tmp;
                            send();
                        }
                }
            }catch (Exception e) {
                e.printStackTrace();
            }    
        }
        
    private Thread thread;
    
    public void connect()
    {
   	 try {
  		Class.forName(driverName);
  		System.out.println("加载驱动成功！");   	
				} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				}
           	try {
           		ct=DriverManager.getConnection(dbURL,userName,userPwd);
           		System.out.println("连接数据库成功！");
           	} catch (SQLException e) {
				// TODO Auto-generated catch block
           		e.printStackTrace();
           	}
    	
        try
        {
            serverSocket =new ServerSocket(8080);
            clientSocket=serverSocket.accept();
            in=new DataInputStream(clientSocket.getInputStream());
            out=new DataOutputStream(clientSocket.getOutputStream());
            System.out.println("链接成功");
            if (!(thread.isAlive())) { 
                thread = new Thread(this);
            }
            thread.start();
        }
        catch(Exception e)
        {
            System.out.println("连接失败");
            e.printStackTrace();
        }
        
    }
    public void send()
    {
        try
        {
        	String tmp = null;
        	Function f=new Function(ct);
        	String value[]=get_message.split("-");          
            if(Integer.parseInt(value[0])==1)
            {                  	
            	tmp="1-";
            	tmp=tmp+f.func1(Integer.parseInt(value[1]),Integer.parseInt(value[2]),Integer.parseInt(value[3]));
            }
            if(Integer.parseInt(value[0])==2)
            {
            	tmp="2-";
            	tmp=tmp+f.func2(Integer.parseInt(value[1]),Integer.parseInt(value[2]));
            }
            //BtextArea.append("\n服务端:\n"+tmp);
                 if(out!=null)
                     {
                	 	
                         out.writeUTF(tmp);
                     }
                 else
                 {
                     System.out.println("链接未建立");
                 }
        }
        catch(Exception e1)
        {
            e1.printStackTrace();
        }
    }
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
            EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	Server server = new Server();
             		server.connect();
                  
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public  Server() {
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setTitle("服务端");
//        setBounds(100, 100, 450, 300);
//        contentPane = new JPanel();
//        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//        setContentPane(contentPane);
//        contentPane.setLayout(null);
//        
//        JTextArea textArea = new JTextArea();
//        BtextArea =textArea;
//        textArea.setBounds(0, 15, 300, 137);
//        //contentPane.add(textArea);
//        JScrollPane jsp = new JScrollPane(textArea); //在文本框上添加滚动条
//        jsp.setBounds(0, 15, 350, 100);
//        //默认的设置是超过文本框才会显示滚动条，以下设置让滚动条一直显示
//         jsp.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//         
//        contentPane.add(jsp);
//        textField = new JTextField();
//        textField.setBounds(0, 167, 287, 62);
//        contentPane.add(textField);
//        textField.setColumns(10);
//        
//        JButton button = new JButton("发送");
//        button.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                send();
//            }
//            });
//        button.setBounds(302, 200, 123, 29);
//        contentPane.add(button);
//        
//        JButton button_1 = new JButton("开始服务");
//        button_1.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                connect();        
//            }
//        });
//        button_1.setBounds(302, 147, 123, 29);
//        contentPane.add(button_1);      
        thread=new Thread(this);
        

        //thread.run();
    }
}