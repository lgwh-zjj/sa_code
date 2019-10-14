
import plugin.*;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AAPFrame extends JFrame{
	File filename;
	IPlayPlugin pPlugin;
	public AAPFrame()
	{
		JFileChooser jfc=new JFileChooser();//文件选择器
		JFrame frame=new JFrame("音乐播放器");
		JButton button1=new JButton("播放");
		JButton button2=new JButton("结束");
		JButton button3=new JButton("暂停");
		JButton button4=new JButton("选择");
		JPanel panel1=new JPanel();
		JPanel panel2=new JPanel();
		//JPanel panel3=new JPanel();
		JTextField text=new JTextField(20);
		panel1.add(text);
		panel1.add(button4);
		panel2.add(button1);
		panel2.add(button2);
		panel2.add(button3);
		frame.setLayout(new GridLayout(2, 1, 10, 10));
		frame.add(panel1);
		frame.add(panel2);
		frame.setSize(400, 300);
		frame.show();
		button1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				playClicked(e);
			}
		});
		
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				stopClicked(e);
			}
		});
		
		button3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pauseClicked(e);
			}
		});
		
		button4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int i = jfc.showOpenDialog(getContentPane());// 显示文件选择对话框								// 判断用户单击的是否为“打开”按钮				if (i == JFileChooser.APPROVE_OPTION) {										File selectedFile = fileChooser.getSelectedFile();// 获得选中的文件对象					textField.setText(selectedFile.getName());// 显示选中文件的名称				}
				if(i == JFileChooser.APPROVE_OPTION)
				{
					File selectedFile = jfc.getSelectedFile();// 获得选中的文件对象
					filename=selectedFile;
					text.setText(selectedFile.getName());// 显示选中文件的名称
				}
				if(filename.toString().contains("mp3"))
					pPlugin=new MP3();
				if(filename.toString().contains("OGG"))
					pPlugin=new OGG();
				if(filename.toString().contains("MAV"))
					pPlugin=new MAV();
			}
		});
	
	}
	
	void playClicked(ActionEvent e)
	{
		System.out.println("加载音乐");
		pPlugin.loadFile(filename.toString());
	}
	
	void stopClicked (ActionEvent e)
	{
		pPlugin.stop();
	}
	
	void pauseClicked(ActionEvent e)
	{
		pPlugin.pause();
	}
}


