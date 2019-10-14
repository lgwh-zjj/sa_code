package sa_project;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;



public class test {
 public static void main(String [] args)throws SQLException
 {
	JFrame frame=new JFrame();
    JPanel panel1=new JPanel();
    JPanel panel2=new JPanel();
    JPanel panel3=new JPanel();
    JButton button1=new JButton("查询某日转手率");
    JButton button2=new JButton("查询某月换手率");
    JLabel label1=new JLabel("年");
    JLabel label2=new JLabel("月");
    JLabel label3=new JLabel("日");
    
    JTextField text1=new JTextField(4);
    JTextField text2=new JTextField(2);
    JTextField text3=new JTextField(2);
    JTextArea text4=new JTextArea(30, 20);
    
    panel1.add(text1);
    panel1.add(label1);

    panel1.add(text2);
    panel1.add(label2);
    

    panel1.add(text3);
    panel1.add(label3);
    
    panel2.add(text4);
    
    panel3.add(button1);
    panel3.add(button2);
    
    frame.setLayout(new GridLayout(3, 1, 10, 10));
    frame.add(panel1);
    frame.add(panel2);
    frame.add(panel3);

    
    frame.setSize(400, 300);
    frame.show();
    button1.addActionListener(new  ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String year=(text1.getText());
			String month=(text2.getText());
			String day=(text3.getText());
			System.out.println(year);
			System.out.println(month);
			System.out.println(day);
			System.out.println("1-"+year+"-"+month+"-"+day);
			Client clientTest=new Client("127.0.0.1", 6666,"1-"+year+"-"+month+"-"+day,text4);
		    clientTest.start();
		}    
		});
		
		button2.addActionListener(new  ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String year=(text1.getText());
				String month=(text2.getText());
				System.out.println(year);
				System.out.println(month);
				Client clientTest=new Client("127.0.0.1",6666, "2-"+year+"-"+month+"-"+"32",text4);
			    clientTest.start();
			}
    	
    });      
}

}
