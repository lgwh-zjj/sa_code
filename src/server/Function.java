package server;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.Axis;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Function {
	public JTextArea text;
	public Connection ct = null;
	public ResultSet re=null;
	public CallableStatement st=null;
	public Function(Connection ct)
	{
		this.ct=ct;
	}
	
	public String func1(int year,int month,int day)
	{
		String s="";
	    System.out.println("调用存储过程");
		String sql="{call dbo.mypro(?,?,?)}";  
	    try {
			this.st=this.ct.prepareCall(sql);
			this.st.setInt(1, year);
			this.st.setInt(2, month);
			this.st.setInt(3, day);
			re=st.executeQuery();
		    while(this.re.next())
		    	{
		    	System.out.println("成交额："+re.getString(1));
		    	System.out.println("转手率："+re.getString(2));
		    	s+=(re.getString(1)+"-"+re.getString(2)+"-");
		    	} 
		    this.re.close();
		    this.st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  //调用
	    System.out.println("返回结果为"+s);
	    return s;
	}
	
	public String func2(int year,int month) throws IOException
	{
		String s="";
	    System.out.println("调用存储过程");
		String sql="{call dbo.mypro1(?,?)}";  
		//DefaultCategoryDataset dateset = new DefaultCategoryDataset();
	    try {
			this.st=this.ct.prepareCall(sql);
			this.st.setInt(1, year);
			this.st.setInt(2, month);
			re=st.executeQuery();
		    while(this.re.next())
		    	{
		    	System.out.println("转手率："+re.getString(1));
		    	System.out.println("day："+re.getString(2));
		    	//dateset.setValue(Double.parseDouble(re.getString(1)), "a", re.getString(2));
		    	s+=(re.getString(1)+"-"+re.getString(2)+"-");
		    	} 
		    this.re.close();
		    this.st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  //调用
        return s;
	    
	}
	
	
}
