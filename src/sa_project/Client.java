package sa_project;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends JFrame implements  Runnable{

    private JPanel contentPane;
    private JTextField textField1,textField2,textField3;
    private JButton button1,button2,button3;
    private JLabel label1,label2,label3;
    private  static JTextArea BtextArea;
    public static ServerSocket serverSocket=null;
    public static Socket clientSocket=null;
    public  Socket client=null;
    public DataOutputStream out=null;
    public static DataInputStream in=null;
    private Thread thread;
    private String year,month;
    public void run()
        {
            try
            {
                while(true)
                {        
                    
                    if(in!=null)
                        {
                            String tmp=in.readUTF();
                        	if(Integer.parseInt(tmp.split("-")[0])==1)
                        		output_text(tmp);
                        	if(Integer.parseInt(tmp.split("-")[0])==2)
                        	{
                        		System.out.println();
                        		output_picture(year,month,tmp);
                        	}
                            //BtextArea.append("\n服务端:\n"+tmp);
                        }
                }
            
            }catch (Exception e) {
                e.printStackTrace();
            }    
        }
    

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Client frame = new Client();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    //frame.setTitle("客户端");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void  connect()
    {
            InetAddress localIP=null;
            try {
                if(client==null)
                {
                    localIP = InetAddress.getLocalHost();
                    //client=new Socket("127.0.0.1",8080);
                    client=new Socket("54.89.84.78",8080);
                    out=new DataOutputStream(client.getOutputStream());
                    in=new DataInputStream(client.getInputStream());
                    if(!(thread.isAlive()))
                    {
                        thread=new Thread(this);
                    }
                    thread.start();
                }
            } catch (UnknownHostException   e2) {
                // TODO 自动生成的 catch 块
                e2.printStackTrace();
            }
            catch(IOException e2)
            {
                e2.printStackTrace();
            }
    }

    public Client() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        setTitle("客户端");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        JTextArea textArea = new JTextArea();
        textArea.setBounds(0, 0, 299, 132);
        BtextArea=textArea;
        JScrollPane jsp = new JScrollPane(textArea); //在文本框上添加滚动条
        jsp.setBounds(0, 15, 350, 100);
        //默认的设置是超过文本框才会显示滚动条，以下设置让滚动条一直显示
         jsp.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
         
        contentPane.add(jsp);
        
        textField1 = new JTextField();
        textField1.setBounds(5, 165, 50, 26);
        contentPane.add(textField1);
        textField1.setColumns(10);
        label1=new JLabel("年");
        label1.setBounds(60,165,20,26);
        contentPane.add(label1);
        
        textField2 = new JTextField();
        textField2.setBounds(80, 165, 50, 26);
        contentPane.add(textField2);
        textField2.setColumns(10);
        label2=new JLabel("月");
        label2.setBounds(140,165,50,26);
        contentPane.add(label2);

        textField3 = new JTextField();
        textField3.setBounds(155, 165, 50, 26);
        contentPane.add(textField3);
        textField3.setColumns(10);
        JLabel label3=new JLabel("日");
        label3.setBounds(220,165,50,26);
        contentPane.add(label3);
        
        button1 = new JButton("查询某日转手率");
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	year=textField1.getText();
            	month=textField2.getText();
            	String tmp="1-"+textField1.getText()+"-"+textField2.getText()+"-"+textField3.getText();
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                //BtextArea.append("\n客户端查询某日转手率："+tmp);
                try
                {
                	out.writeUTF(tmp);
                }
                catch(Exception e1)
                {
                    e1.printStackTrace();
                }
            }
        });
        button1.setBounds(282, 170, 140, 29);
        contentPane.add(button1);
        
        button2 = new JButton("查询某月换手率");
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	year=textField1.getText();
            	month=textField2.getText();
                 String tmp="2-"+textField1.getText()+"-"+textField2.getText()+"-"+textField3.getText();
                 textField1.setText("");
                 textField2.setText("");
                 textField3.setText("");
                 //BtextArea.append("\n客户端查询某月换手率："+tmp);
                 try
                 {
                     out.writeUTF(tmp);
                 }
                 catch(Exception e1)
                 {
                     e1.printStackTrace();
                 }
            }
        });
        button2.setBounds(282, 210, 140, 29);
        contentPane.add(button2);
        
        JButton button3 = new JButton("连接服务器");
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                connect();
            }
        });
        button3.setBounds(282, 130, 140, 29);
        contentPane.add(button3);
        
        
        thread=new Thread(this);
    }
    
    public void output_text(String s)
    {
    	String result[]=s.split("-");
    	BtextArea.append("成交额："+result[1]+'\n');
    	BtextArea.append("转手率："+result[2]+'\n');
    }
    
    public void output_picture(String year,String month,String s)
    {
    	String result[]=s.split("-");
    	DefaultCategoryDataset dateset = new DefaultCategoryDataset();
    	for(int i=0;i<result.length/2;i++)
    	{
    		dateset.setValue(Double.parseDouble(result[2*i+1]), "a",result[2*(i+1)]);
    	}
    	 JFreeChart chart=ChartFactory.createLineChart(
 	    		year+"年"+month+"月转手率折线图", 
 	    		"日",
 	    		"转手率",
 	    		dateset,
 	    		PlotOrientation.VERTICAL, //图表放置模式水平/垂直 
 	    		true, //显示lable
 	    		false, //显示提示
 	    		false //显示urls
 	    		);
 	    chart.getTitle().setFont(new Font("宋体", Font.BOLD,12));
 	    CategoryPlot plot=chart.getCategoryPlot();//获取图表区域对象
         CategoryAxis domainAxis=plot.getDomainAxis();//水平底部列表
         domainAxis.setLabelFont(new Font("黑体",Font.BOLD,14));         //水平底部标题
         domainAxis.setTickLabelFont(new Font("宋体",Font.BOLD,12));  //垂直标题
         ValueAxis rangeAxis=plot.getRangeAxis();//获取柱状
         rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));

         
 	    OutputStream os = null;
		try {
			os = new FileOutputStream("f:\\test.jpg");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	    try {
			ChartUtilities.writeChartAsJPEG(os, chart, 500, 500);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	    ChartPanel frame1=new ChartPanel(chart,true);
 	    JFrame frame2=new JFrame();
 	    frame2.add(frame1);
		frame2.setSize(500, 500);
		frame2.setVisible(true);
	    frame2.show();
    }
}
