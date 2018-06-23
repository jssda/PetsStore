package jssd.javaTest.clint;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import jssd.javaTest.dataManipulation.DBOperate;
import jssd.javaTest.diplayPets.Sales;

public class LoginModel {
	
	private PetsClint parents = null;
	private JDialog jdLogin = null;
	
	public LoginModel(PetsClint jframe) {
		this.parents = jframe;
//		System.out.println(jframe);
		
		jdLogin = new JDialog(parents, "��ע��", true);
		jdLogin.setLayout(null);
		jdLogin.setSize(350, 250);
		init();
		
		Toolkit tool = Toolkit.getDefaultToolkit();
		int x, y;
		x = (int) (tool.getScreenSize().getWidth() - 350) / 2;
		y = (int) (tool.getScreenSize().getHeight() - 250) / 2;
		jdLogin.setLocation(x, y);
		
		jdLogin.setVisible(true);
	}
	
	private JTextField jtNickName = null;
	private JPasswordField jtPassWord = null;
	private JButton jb1 = null;
	private JButton jb2 = null;
	private JRadioButton jrb1 = null;
	private JRadioButton jrb2 = null;
	
	private void init() {
		
		JLabel jlNickName = new JLabel("�ǳ�");
		jlNickName.setBounds(10, 10, 40, 20);
		JLabel jlPassWord = new JLabel("����");
		jlPassWord.setBounds(10, 40, 40, 20);
		
		jtNickName = new JTextField();
		jtNickName.setBounds(60, 10, 250, 20);
		jtPassWord = new JPasswordField(20);
		jtPassWord.setBounds(60, 40, 250, 20);
		
		JPanel jpType = new JPanel(new GridLayout(1, 2));
		jpType.setBounds(60, 70, 250, 50);
		jpType.setBorder(BorderFactory.createTitledBorder(null, "��½����", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION ));
		jrb1 = new JRadioButton("�˿�");
		jrb1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getSource() == jrb1) {
					LoginType = "�˿�";
				}
			}
		});
		jrb2 = new JRadioButton("ְԱ");
		jrb2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getSource() == jrb2) {
					LoginType = "ְԱ";
				}
			}
		});
		jpType.add(jrb1);
		jpType.add(jrb2);
		ButtonGroup bug = new ButtonGroup();
		bug.add(jrb1);
		bug.add(jrb2);		
		
		jb1 = new JButton("����");
		jb1.setBounds(60, 130, 80, 40);
		jb1.addActionListener(new myActionListener());
		jb2 = new JButton("��½");
		jb2.setBounds(190, 130, 80, 40);
		jb2.addActionListener(new myActionListener());
		
		jdLogin.add(jlNickName);
		jdLogin.add(jtNickName);
		jdLogin.add(jlPassWord);
		jdLogin.add(jtPassWord);
		jdLogin.add(jb1);
		jdLogin.add(jb2);
		jdLogin.add(jpType);
		
	}
	
	private String LoginType = null;
	
	private boolean check() {
		String txtNickName = jtNickName.getText();
		String txtPassWord = String.valueOf(jtPassWord.getPassword());
		String sql = null;
		PreparedStatement pstat = null;
		ResultSet rSet = null;
		boolean flag = true;
		
		if("�˿�".equals(LoginType)) {
			sql = "select passWord from customer where nickName = '" + txtNickName + "'";
		} else if("ְԱ".equals(LoginType)) {
			sql = "select passWord from employee where name = '" + txtNickName + "'";
		} else {
			JOptionPane.showMessageDialog(jdLogin, "��ѡ���½����", "����", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		try {
			pstat = DBOperate.conn.prepareStatement(sql);
			rSet = pstat.executeQuery();
			if(!rSet.next()) {
				JOptionPane.showMessageDialog(jdLogin, "�����ǳ�", "����", JOptionPane.WARNING_MESSAGE);
				jtNickName.setText("");
				jtPassWord.setText("");
				flag = false;
			} else {
				String severPass = rSet.getString(1);
				if(severPass.equals(txtPassWord)) {
					jdLogin.setVisible(false);
					System.out.println("��½�ɹ�");
					this.parents.setVisible(false);
					if("�˿�".equals(LoginType)) {
						new Sales(parents);
					} else if("ְԱ".equals(LoginType)) {
						System.out.println("ְ����½");
					}
				} else {
					JOptionPane.showMessageDialog(jdLogin, "������������", "����", JOptionPane.WARNING_MESSAGE);
					jtPassWord.setText("");
					flag = false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("���ݿ����ʧ��");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���ݿ������������");
		} finally {
			try {
				rSet.close();
				pstat.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("�����ر�ʧ��");
			}
		}		
		
		return flag;
	}
	
	
	class myActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == jb1) {
				System.out.println("���ð���");
				jtNickName.setText("");
				jtPassWord.setText("");
			} else if(e.getSource() == jb2) {
				System.out.println("��½����");
				if(!check())
					return;
			}
		}
	}
}

