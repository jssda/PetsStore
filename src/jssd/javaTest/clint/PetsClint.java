package jssd.javaTest.clint;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.*;

import jssd.javaTest.dataManipulation.DBOperate;

@SuppressWarnings("serial")
public class PetsClint extends JFrame{
	
	private JButton jbLogin = null;
	private JButton jbLogon	= null;
	
	public PetsClint(String title) {
		super(title);
		new DBOperate();
		init();
	}
	

	//���ڳ�ʼ��
	private void init() {
		this.setSize(750, 600);
		
		this.setLayout(new BorderLayout());
		JPanel jpOperate = new JPanel();
		ImageIcon image = new ImageIcon("." + File.separator + 
				"resource" + File.separator + "1.jpg", "Display the main interface");
		JLabel jl1 = new JLabel(image);
		jbLogon = new JButton("ע��");
		jbLogon.addActionListener(new MyActionListener());
		jbLogin	= new JButton("��½");
		jbLogin.addActionListener(new MyActionListener());
		
		jpOperate.add(jbLogon);
		jpOperate.add(jbLogin);
		
		jl1.setSize(800, 800);
		this.add(jl1, BorderLayout.CENTER);
		this.add(jpOperate, BorderLayout.SOUTH);
		
		//������Ϊ��Ļ����
		Toolkit tool = Toolkit.getDefaultToolkit();
		int x, y;
		x = (int) (tool.getScreenSize().getWidth() - 750) / 2;
		y = (int) (tool.getScreenSize().getHeight() - 600) / 2;
		this.setLocation(x, y);
		this.setVisible(true);
		
		this.addWindowListener(new WindowAdapter() { 	//�رմ��ڼ���
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				DBOperate.disConnectDB();
				System.exit(0);
			}
		});
	}

	private class MyActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == jbLogon) {
				System.out.println("ע��");
				new LogonModel(PetsClint.this);
			} else if(e.getSource() == jbLogin) {
				System.out.println("��½");
				new LoginModel(PetsClint.this);
			}
		}
		
	}
}