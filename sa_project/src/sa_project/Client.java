package sa_project;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Client extends Thread {

    //����һ��Socket����
    Socket socket = null;
    String result=null;
    String year,month,day;
    JTextArea text;
    String state;
    public Client(String host, int port,String s,JTextArea text) {
            //��Ҫ��������IP��ַ�Ͷ˿ںţ����ܻ����ȷ��Socket����
				try {
					socket = new Socket(host, port);
					this.state=s.split("-")[0];
					this.year=s.split("-")[1];
					this.month=s.split("-")[2];
					this.day=s.split("-")[3];
					this.text=text;
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        }


    @Override
    public void run() {
        //�ͻ���һ���ӾͿ���д���ݸ���������
        new sendMessThread().start();
        super.run();
        try {
            // ��Sock���������
            InputStream s = socket.getInputStream();
            byte[] buf = new byte[14400];
            int len = s.read(buf);    
            if(len!=-1)
            {
            	System.out.println("  ������˵��  "+new String(buf, 0, len,"UTF-8"));
            	result=new String(buf, 0, len,"UTF-8");
            	System.out.println("���ؽ��"+result);
            	if(Integer.parseInt(result.split("-")[0])==1)
            		output_text(result);
            	if(Integer.parseInt(result.split("-")[0])==2)
            	{
            		System.out.println();
            		output_picture(year,month,result);
            	}
            }          
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //��Socket����д���ݣ���Ҫ�¿�һ���߳�
    class sendMessThread extends Thread{
        @Override
        public void run() {
            super.run();
            //д����
            
            OutputStream os= null;
            String in=state+"-"+year+"-"+month+"-"+day;
            System.out.println("������������"+in);
            try { 
            	while(true) {
                	if(result!=null)
                		break;
                	os= socket.getOutputStream();
                    //in+=sc.next();
                	os.write((in).getBytes());
                	os.flush();
              }
            }     
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    public void output_text(String s)
    {
    	String result[]=s.split("-");
    	text.append("�ɽ��"+result[1]+'\n');
    	text.append("ת���ʣ�"+result[2]+'\n');
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
 	    		year+"��"+month+"��ת��������ͼ", 
 	    		"��",
 	    		"ת����",
 	    		dateset,
 	    		PlotOrientation.VERTICAL, //ͼ�����ģʽˮƽ/��ֱ 
 	    		true, //��ʾlable
 	    		false, //��ʾ��ʾ
 	    		false //��ʾurls
 	    		);
 	    chart.getTitle().setFont(new Font("����", Font.BOLD,12));
 	    CategoryPlot plot=chart.getCategoryPlot();//��ȡͼ���������
         CategoryAxis domainAxis=plot.getDomainAxis();//ˮƽ�ײ��б�
         domainAxis.setLabelFont(new Font("����",Font.BOLD,14));         //ˮƽ�ײ�����
         domainAxis.setTickLabelFont(new Font("����",Font.BOLD,12));  //��ֱ����
         ValueAxis rangeAxis=plot.getRangeAxis();//��ȡ��״
         rangeAxis.setLabelFont(new Font("����",Font.BOLD,15));

         
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
