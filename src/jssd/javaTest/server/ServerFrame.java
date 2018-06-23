package jssd.javaTest.server;

import java.awt.Toolkit;

import javax.swing.JFrame;

public class ServerFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;

	public ServerFrame() {
		super("����˿���̨");
		this.setSize(400, 400);
		
		init();
		
	}
	
	private void init() {
		
		
		
		//������Ϊ��Ļ����
		Toolkit tool = Toolkit.getDefaultToolkit();
		int x, y;
		x = (int) (tool.getScreenSize().getWidth() - 400) / 2;
		y = (int) (tool.getScreenSize().getHeight() - 400) / 2;
		this.setLocation(x, y);
		this.setVisible(true);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
}
