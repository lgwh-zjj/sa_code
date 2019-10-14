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

    //定义一个Socket对象
    Socket socket = null;
    String result=null;
    String year,month,day;
    JTextArea text;
    String state;
    public Client(String host, int port,String s,JTextArea text) {
            //需要服务器的IP地址和端口号，才能获得正确的Socket对象
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
        //客户端一连接就可以写数据给服务器了
        new sendMessThread().start();
        super.run();
        try {
            // 读Sock里面的数据
            InputStream s = socket.getInputStream();
            byte[] buf = new byte[14400];
            int len = s.read(buf);    
            if(len!=-1)
            {
            	System.out.println("  服务器说：  "+new String(buf, 0, len,"UTF-8"));
            	result=new String(buf, 0, len,"UTF-8");
            	System.out.println("返回结果"+result);
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

    //往Socket里面写数据，需要新开一个线程
    class sendMessThread extends Thread{
        @Override
        public void run() {
            super.run();
            //写操作
            
            OutputStream os= null;
            String in=state+"-"+year+"-"+month+"-"+day;
            System.out.println("传给服务器："+in);
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
    	text.append("成交额："+result[1]+'\n');
    	text.append("转手率："+result[2]+'\n');
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
