
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
		JFileChooser jfc=new JFileChooser();//�ļ�ѡ����
		JFrame frame=new JFrame("���ֲ�����");
		JButton button1=new JButton("����");
		JButton button2=new JButton("����");
		JButton button3=new JButton("��ͣ");
		JButton button4=new JButton("ѡ��");
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
				int i = jfc.showOpenDialog(getContentPane());// ��ʾ�ļ�ѡ��Ի���								// �ж��û��������Ƿ�Ϊ���򿪡���ť				if (i == JFileChooser.APPROVE_OPTION) {										File selectedFile = fileChooser.getSelectedFile();// ���ѡ�е��ļ�����					textField.setText(selectedFile.getName());// ��ʾѡ���ļ�������				}
				if(i == JFileChooser.APPROVE_OPTION)
				{
					File selectedFile = jfc.getSelectedFile();// ���ѡ�е��ļ�����
					filename=selectedFile;
					text.setText(selectedFile.getName());// ��ʾѡ���ļ�������
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
		System.out.println("��������");
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


