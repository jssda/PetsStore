package jssd.javaTest.diplayPets;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.event.*;


import jssd.javaTest.clint.PetsClint;
import jssd.javaTest.dataManipulation.DBOperate;

public class Sales extends JFrame{

	private static final long serialVersionUID = 1L;	
	
	public Sales(PetsClint parents) {
		super("销售展示");
		init();
	}
	
	private void init() {
		setSize(1000, 800);
		this.setLayout(null);
		setResizable(false);
		
		display();
		
		//窗口置为屏幕正中
		Toolkit tool = Toolkit.getDefaultToolkit();
		int x, y;
		x = (int) (tool.getScreenSize().getWidth() - 1000) / 2;
		y = (int) (tool.getScreenSize().getHeight() - 800) / 2;
		this.setLocation(x, y);
		setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void display() {
		
		JPanel jp1 = new JPanel(new BorderLayout());
		
		//JList列表选项
		JList<String> jlist1 = new JList<String>(new PetListModel()); 
		jlist1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//jp1布局
		jp1.setBorder(BorderFactory.createTitledBorder("宠物概览, 点击即可显示详细信息"));
		JScrollPane jsp = new JScrollPane(jlist1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jp1.add(jsp, BorderLayout.CENTER);
		
		jp1.setBounds(0, 0, 300, 770);
		this.add(jp1);	
		
		//jp2布局
		JPanel jp2 = new JPanel(new GridLayout(7, 2));
		jp2.setBorder(BorderFactory.createTitledBorder("详细信息"));
		
		JTextArea jta1 = new JTextArea();
		jta1.setBorder(BorderFactory.createTitledBorder("宠物id"));
		jta1.setEditable(false);
		jta1.setLineWrap(true);
		JTextArea jta2 = new JTextArea();
		jta2.setBorder(BorderFactory.createTitledBorder("宠物类型"));
		jta2.setEditable(false);
		jta2.setLineWrap(true);
		JTextArea jta3 = new JTextArea();
		jta3.setBorder(BorderFactory.createTitledBorder("宠物姓名"));
		jta3.setEditable(false);
		jta3.setLineWrap(true);
		JTextArea jta4 = new JTextArea();
		jta4.setBorder(BorderFactory.createTitledBorder("食量"));
		jta4.setEditable(false);
		jta4.setLineWrap(true);
		JTextArea jta5 = new JTextArea();
		jta5.setBorder(BorderFactory.createTitledBorder("能力"));
		jta5.setEditable(false);
		jta5.setLineWrap(true);
		JTextArea jta6 = new JTextArea();
		jta6.setBorder(BorderFactory.createTitledBorder("习惯"));
		jta6.setEditable(false);
		jta6.setLineWrap(true);
		JTextArea jta7 = new JTextArea();
		jta7.setBorder(BorderFactory.createTitledBorder("注意事项"));
		jta7.setEditable(false);
		jta7.setLineWrap(true);
		JTextArea jta8 = new JTextArea();
		jta8.setBorder(BorderFactory.createTitledBorder("体型"));
		jta8.setEditable(false);
		jta8.setLineWrap(true);
		JTextArea jta9 = new JTextArea();
		jta9.setBorder(BorderFactory.createTitledBorder("体重"));
		jta9.setEditable(false);
		jta9.setLineWrap(true);
		JTextArea jta10 = new JTextArea();
		jta10.setBorder(BorderFactory.createTitledBorder("颜色"));
		jta10.setEditable(false);
		jta10.setLineWrap(true);
		JTextArea jta11 = new JTextArea();
		jta11.setBorder(BorderFactory.createTitledBorder("生日"));
		jta11.setEditable(false);
		jta11.setLineWrap(true);
		JTextArea jta12 = new JTextArea();
		jta12.setBorder(BorderFactory.createTitledBorder("价钱"));
		jta12.setEditable(false);
		jta12.setLineWrap(true);
		
		JButton jb1 = new JButton("联系客服");
		JButton jb2 = new JButton("购买");
		
		jp2.add(jta1);
		jp2.add(jta2);
		jp2.add(jta3);
		jp2.add(jta4);
		jp2.add(jta5);
		jp2.add(jta6);
		jp2.add(jta7);
		jp2.add(jta8);
		jp2.add(jta9);
		jp2.add(jta10);
		jp2.add(jta11);
		jp2.add(jta12);
		jp2.add(jb1);
		jp2.add(jb2);
		
		
		//列表监听
		jlist1.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()) {
					String s = jlist1.getModel().getElementAt(jlist1.getSelectedIndex());
					String[] str = s.split("\\.", 2);
					String sql = "select * from petinfo where id = " + str[0];
					PreparedStatement pst = null;
					ResultSet rs = null;
					try {
						pst = DBOperate.conn.prepareStatement(sql);
						rs = pst.executeQuery();
						if(rs == null) {
							System.out.println("内部错误");
						} else {
							while(rs.next()) {
								jta1.setText("" + rs.getInt("id"));
								jta2.setText(rs.getString("type"));
								jta3.setText(rs.getString("name"));
								jta4.setText(rs.getString("capacityOfEat"));
								jta5.setText(rs.getString("ability"));
								jta6.setText(rs.getString("habit"));
								jta7.setText(rs.getString("attention"));
								jta8.setText(rs.getString("body"));
								jta9.setText(rs.getString("weight"));
								jta10.setText(rs.getString("color"));
								jta11.setText(rs.getDate("birthday") + "");
								jta12.setText((rs.getFloat("bid") * 1.4) + "");
							}
						}
						
					} catch (SQLException e1) {
						e1.printStackTrace();
						System.out.println("详细信息查询失败");
					} finally {
						try {
							rs.close();
							pst.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
					
				}
			}
		});
		
		jp2.setBounds(300, 0, 700, 770);
		this.add(jp2);

	}
}
