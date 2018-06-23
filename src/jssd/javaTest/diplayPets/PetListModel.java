package jssd.javaTest.diplayPets;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

import jssd.javaTest.dataManipulation.DBOperate;

public class PetListModel extends AbstractListModel<String>{

	private static final long serialVersionUID = 1L;
	private List<String> petList = null;
	private PreparedStatement psta = null;
	private ResultSet rs = null;
	
	public PetListModel() {
		String sql = "select id, name, type from petinfo";
		try {
			psta = DBOperate.conn.prepareStatement(sql);
			rs = psta.executeQuery();
			petList = new ArrayList<String>();
			
			while(rs.next()) {
				petList.add(rs.getInt(1) + "." + rs.getString(3) + ": " + rs.getString(2));
			}
			
			if(petList.isEmpty()) {
				petList.add("暂时没有宠物");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("数据库操作失败");
		} finally {
			try {
				rs.close();
				psta.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	
	public String getElementAt(int i) {
		if(i < petList.size()) {
			return petList.get(i);
		} else {
			return null;
		}
	}

	public int getSize() {
		return petList.size();
	}
	

}
