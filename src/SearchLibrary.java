
import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


public class SearchLibrary{
	Object[][] data=null;
	String[] title= {"书库号","地址","面积","电话"};
	JFrame frame = new JFrame("书库查询");
	
	JPanel pnlCenter = new JPanel();
	
	JLabel lblName = new JLabel("\u4E66\u5E93ID\uFF1A");
	
	
	private JTable table;
	private final JComboBox comboBox_shelfID = new JComboBox();
	private final JButton btn_Search = new JButton("\u641C\u7D22");
	private final JButton btn_Back = new JButton("\u8FD4\u56DE");
	private final JButton btnShowAll = new JButton("\u663E\u793A\u5168\u90E8");
	
	public static void main(String[] args) {
		new SearchLibrary();
	}
	
	public SearchLibrary() {
		pnlCenter.add(lblName);
		comboBox_shelfID.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6"}));
		comboBox_shelfID.setToolTipText("");
		
		pnlCenter.add(comboBox_shelfID);
		btn_Search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.sql.Statement statement=null;
				  ResultSet rs=null; 
				  Connection connection=ConnDB.getConnection(); 
				  Object id=comboBox_shelfID.getSelectedIndex()+1;
				  try {
					  statement=connection.createStatement();
					  String sqlString="select * from Libraries where l_Id='"+id+"'";
					  rs=statement.executeQuery(sqlString);
					  List <Library> list=new ArrayList<Library>(); 
					  while(rs.next()) { 
						  Library library=new Library();
						  library.setL_Id(rs.getInt("l_Id"));
						  library.setL_Address(rs.getString("l_Address"));
						  library.setL_Area(rs.getString("l_Area"));
						  library.setL_Tel(rs.getString("l_Tel"));
						  list.add(library); 
						  } 
					  data=new Object[list.size()][title.length];
					  for(int i=0;i<list.size();i++) {
						  data[i][0]=list.get(i).getL_Id(); 
						  data[i][1]=list.get(i).getL_Address();
						  data[i][2]=list.get(i).getL_Area(); 
						  data[i][3]=list.get(i).getL_Tel();
						  } 
					  table=new JTable(data, title);
					  JScrollPane scroll = new JScrollPane(table);
						frame.getContentPane().add(scroll);

						frame.setSize(655, 471);
						frame.setLocationRelativeTo(null);
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frame.setVisible(true);
				  }catch (Exception e1) { 
					  // TODO: handle exception } } });
				  }
			}
		});
		
		pnlCenter.add(btn_Search);
		
		frame.getContentPane().add("North",pnlCenter);
		btn_Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.hide();
				Login window=new Login();
				window.frame.setVisible(true);
			}
		});
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				new SearchLibrary();
			}
		});
		
		pnlCenter.add(btnShowAll);
		
		pnlCenter.add(btn_Back);
		
		
		//初始化数据
				java.sql.Statement statement=null;
				ResultSet rs=null;
				Connection connection=ConnDB.getConnection();
				try {
					statement=connection.createStatement();
					String sqlString="select * from Libraries";
					rs=statement.executeQuery(sqlString);
					 List <Library> list=new ArrayList<Library>(); 
					  while(rs.next()) { 
						  Library library=new Library();
						  library.setL_Id(rs.getInt("l_Id"));
						  library.setL_Address(rs.getString("l_Address"));
						  library.setL_Area(rs.getString("l_Area"));
						  library.setL_Tel(rs.getString("l_Tel"));
						  list.add(library);
						  } 
					  data=new Object[list.size()][title.length];
					  for(int i=0;i<list.size();i++) {
						  data[i][0]=list.get(i).getL_Id(); 
						  data[i][1]=list.get(i).getL_Address();
						  data[i][2]=list.get(i).getL_Area(); 
						  data[i][3]=list.get(i).getL_Tel();
						  } 			  
					table.setBounds(14, 92, 695, 427);
					frame.getContentPane().add(table);
				} catch (Exception e) {
					// TODO: handle exception
				}finally {
					ConnDB.closeAll(connection, statement, rs);
				}
				table = new JTable(data, title);
		
		
		JScrollPane scroll = new JScrollPane(table);
		frame.getContentPane().add(scroll);

		frame.setSize(655, 471);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		
	}
}
