package jssd.javaTest.clint;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import jssd.javaTest.dataManipulation.DBOperate;

public class LogonModel {

	private JDialog jdLogon = null;
	private JButton jbOK = null;

	public LogonModel(PetsClint jframe) {	
		
		jdLogon = new JDialog(jframe, true);
		jdLogon.setSize(350, 470);
		jdLogon.setLayout(null);
		init();

		// 窗口置为屏幕正中
		Toolkit tool = Toolkit.getDefaultToolkit();
		int x, y;
		x = (int) (tool.getScreenSize().getWidth() - 350) / 2;
		y = (int) (tool.getScreenSize().getHeight() - 470) / 2;
		jdLogon.setLocation(x, y);

		jdLogon.setVisible(true);
	}

	private JTextField jtName = null;
	private JTextField jtNickName = null;
	private JTextField jtPassWord = null;
	private JPasswordField jtPassConfirm = null;
	private JTextField jtTelephone = null;
	private JTextArea jtAdress = null;
	private JTextField jtAge = null;
	private JTextArea jtEducation = null;
//	private JRadioButton jrb1 = null;
//	private JRadioButton jrb2 = null;
	private JRadioButton jrb3 = null;
	private JRadioButton jrb4 = null;

	private void init() {
		JLabel jlName = new JLabel("       姓名:");
		jlName.setBounds(5, 5, 80, 20);
		JLabel jlNickName = new JLabel("      昵称:");
		jlNickName.setBounds(5, 35, 80, 20);
		JLabel jlPassWord = new JLabel("      密码:");
		jlPassWord.setBounds(5, 65, 80, 20);
		JLabel jlPassConfirm = new JLabel("   确认密码:");
		jlPassConfirm.setBounds(5, 95, 80, 20);
		JLabel jlTelephone = new JLabel("   联系电话:");
		jlTelephone.setBounds(5, 125, 80, 20);
		JLabel jlAdress = new JLabel("   联系地址:");
		jlAdress.setBounds(5, 155, 80, 20);
		JLabel jlAge = new JLabel("        年龄:");
		jlAge.setBounds(5, 205, 80, 20);
		JLabel jlSex = new JLabel("        性别:");
		jlSex.setBounds(5, 235, 80, 20);
		JLabel jlType = new JLabel("    注册类型");
		jlType.setBounds(5, 265, 80, 20);
		JLabel jlEducation = new JLabel("   文化程度:");
		jlEducation.setBounds(5, 295, 80, 20);
		jbOK = new JButton("注册");
		jbOK.setBounds(100, 355, 100, 50);
		jbOK.addActionListener(new myActionLisener());
		
		
		jtName = new JTextField(20);
		jtName.setBounds(90, 5, 200, 20);
		jtNickName = new JTextField(20);
		jtNickName.setBounds(90, 35, 200, 20);
		jtPassWord = new JTextField(20);
		jtPassWord.setBounds(90, 65, 200, 20);
		jtPassConfirm = new JPasswordField(20);
		jtPassConfirm.setBounds(90, 95, 200, 20);
		jtTelephone = new JTextField(20);
		jtTelephone.setBounds(90, 125, 200, 20);
		jtAdress = new JTextArea(20, 40);
		jtAdress.setBounds(90, 155, 200, 40);
		jtAdress.setLineWrap(true); // 自动换行
		jtAge = new JTextField(20);
		jtAge.setBounds(90, 205, 200, 20);
		//性别选择按钮
		JPanel jpSex = new JPanel(new GridLayout(1, 2));
		jpSex.setBounds(90, 235, 150, 20);
		jrb3 = new JRadioButton("男",false);
		jrb3.addItemListener(new myItemListener());
		jrb4 = new JRadioButton("女",false);
		jrb4.addItemListener(new myItemListener());
		jpSex.add(jrb3);
		jpSex.add(jrb4);
		ButtonGroup bug2 = new ButtonGroup();
		bug2.add(jrb3);
		bug2.add(jrb4);
//		//注册者类别
//		JPanel jpType = new JPanel(new GridLayout(1, 2));
//		jpType.setBounds(90, 265, 150, 20);
//		jrb1 = new JRadioButton("顾客");
//		jrb2 = new JRadioButton("志愿");
//		ButtonGroup bug1 = new ButtonGroup();
//		bug1.add(jrb1);
//		bug1.add(jrb2);
//		jpType.add(jrb1);
//		jpType.add(jrb2);
		//教育程度
		jtEducation = new JTextArea(4, 20);
		jtEducation.setLineWrap(true);
		jtEducation.setBounds(90, 265, 200, 80);

		
		jdLogon.add(jlName);
		jdLogon.add(jtName);
		jdLogon.add(jlNickName);
		jdLogon.add(jtNickName);
		jdLogon.add(jlPassWord);
		jdLogon.add(jtPassWord);
		jdLogon.add(jlPassConfirm);
		jdLogon.add(jtPassConfirm);
		jdLogon.add(jlTelephone);
		jdLogon.add(jtTelephone);
		jdLogon.add(jlAdress);
		jdLogon.add(jtAdress);
		jdLogon.add(jlAge);
		jdLogon.add(jtAge);
		jdLogon.add(jlSex);
		jdLogon.add(jpSex);
//		jdLogon.add(jpType);
		jdLogon.add(jlType);
		jdLogon.add(jlEducation);
		jdLogon.add(jtEducation);
		jdLogon.add(jbOK);
	}

	private String txtSex = null;
	// 检查输入, 同步数据库模块
	public boolean check() {
		String txtName = jtName.getText().trim();
		if (txtName.equals("")) {
			jtName.setText("");
			JOptionPane.showMessageDialog(jdLogon, "请输入姓名", "警告",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		String txtNickName = jtNickName.getText();
		if (txtNickName.equals("")) {
			jtNickName.setText("");
			JOptionPane.showMessageDialog(jdLogon, "请输入昵称", "警告",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		String txtPassWord = jtPassWord.getText();
		if (txtPassWord.equals("")) {
			jtPassWord.setText("");
			JOptionPane.showMessageDialog(jdLogon, "请输入密码", "警告",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		String txtPassConfirm = String.valueOf(jtPassConfirm.getPassword());
		if (txtPassConfirm.equals("") || !txtPassConfirm.equals(txtPassWord)) {
			jtPassConfirm.setText("");
			JOptionPane.showMessageDialog(jdLogon, "请检查密码", "警告",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		String txtTelephon = jtTelephone.getText();
		if (txtTelephon.equals("")) {
			jtTelephone.setText("");
			JOptionPane.showMessageDialog(jdLogon, "请检查手机号", "警告",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		String txtAdress = jtAdress.getText();
		if (txtAdress.equals("")) {
			jtAdress.setText("");
			JOptionPane.showMessageDialog(jdLogon, "请检查收货地址", "警告",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		String txtAge = jtAge.getText();
		int iAge = 0;
		if (txtAge.equals("")) {
			jtAge.setText("");
			JOptionPane.showMessageDialog(jdLogon, "请检查年龄", "警告",
					JOptionPane.WARNING_MESSAGE);
			return false;
		} else {
			try {
				iAge = Integer.parseInt(txtAge);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				jtAge.setText("");
				JOptionPane.showMessageDialog(jdLogon, "请检查年龄", "警告",
						JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}
		String txtEducation = jtEducation.getText();
		String sql = "insert into customer(name, nickName, telephone, "
				+ "adress, age, sex, degreeOfEducation, passWord, LoginTime)"
				+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement pStat = null;
		try {
			pStat = DBOperate.conn.prepareStatement(sql);
			pStat.setString(1, txtName);
			pStat.setString(2, txtNickName);
			pStat.setString(3, txtTelephon);
			pStat.setString(4, txtAdress);
			pStat.setInt(5, iAge);
			pStat.setString(6, txtSex);
			pStat.setString(7, txtEducation);
			pStat.setString(8, txtPassWord);
			java.util.Date date = new java.util.Date();
			java.sql.Timestamp sql_date = new java.sql.Timestamp(date.getTime());
			pStat.setTimestamp(9, sql_date);

			pStat.execute();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println("数据插入失败");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据库操作失败");
			DBOperate.disConnectDB();
		} finally {
			try {
				pStat.close();
				System.out.println("数据库操作关闭");
			} catch (SQLException e) {
				System.out.println("数据库关闭失败");
				e.printStackTrace();
			}
		}

		return true;
	}

	//按钮事件监听
	class myActionLisener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == jbOK) {
				if (!check())
					return;
				else {
					System.out.println("注册完毕");
					jdLogon.setVisible(false);
				}
			}
		}

	}
	
	class myItemListener implements ItemListener {

		public void itemStateChanged(ItemEvent e) {
			if(e.getSource() == jrb3) {
				txtSex = "男";
			} else if (e.getSource() == jrb4) {
				txtSex = "女";
			}
		}
	}

}

