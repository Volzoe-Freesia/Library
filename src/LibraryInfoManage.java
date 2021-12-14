
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
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


public class LibraryInfoManage{
	Object[][] data=null;
	String[] title = { "编号", "地址", "面积","电话"};
//	public String[] libraryID= {"1","2","3","4","5","6"};
	
	
	JFrame frame = new JFrame("书库信息管理");
	JPanel pnlCenter = new JPanel();
	JLabel lblName = new JLabel("\u7F16\u53F7\uFF1A");
	final JTextField txtLibraryID;
	
	
	private JTable table;
	private final JLabel label = new JLabel("\u5730\u5740\uFF1A");
	private final JTextField txtLibraryAddress = new JTextField(10);
	private final JLabel label_1 = new JLabel("\u9762\u79EF\uFF1A");
	private final JTextField txtLibraryArea = new JTextField(10);
	private final JLabel label_2 = new JLabel("\u7535\u8BDD\uFF1A");
	private final JTextField txtLibraryTel = new JTextField(10);
	private final JButton btnBack = new JButton("\u8FD4\u56DE");
	private final JButton btnUpdate = new JButton("\u4FEE\u6539");
	private final JButton btnDelete = new JButton("\u5220\u9664");
	
	public static void main(String[] args) {
		new LibraryInfoManage();
	}
	
	public LibraryInfoManage() {
		
		txtLibraryID = new JTextField(10);
		pnlCenter.add(lblName);
		pnlCenter.add(txtLibraryID);
		
		
		JButton btnAddLibraryInfo=new JButton("\u65B0\u589E");
		btnAddLibraryInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.sql.Statement statement=null;
				Connection connection=ConnDB.getConnection();
				try {
					statement=connection.createStatement();
					String id,address,area,tel;
					id=txtLibraryID.getText();
					address=txtLibraryAddress.getText();
					area=txtLibraryArea.getText();
					tel=txtLibraryTel.getText();
					String sqlString="insert into Libraries values('"+id+"','"+address+"','"+area+"','"+tel+"')";
//					System.out.println(sqlString);
					int non=statement.executeUpdate(sqlString);
					if(non>0) {
						JOptionPane.showMessageDialog(frame, "新增成功","提示",JOptionPane.DEFAULT_OPTION);
						frame.dispose();
						new LibraryInfoManage();
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});

		
		pnlCenter.add(label);
		
		pnlCenter.add(txtLibraryAddress);
		
		pnlCenter.add(label_1);
		
		pnlCenter.add(txtLibraryArea);
		
		pnlCenter.add(label_2);
		
		pnlCenter.add(txtLibraryTel);
		
		pnlCenter.add(btnAddLibraryInfo);
		
		frame.getContentPane().add("North",pnlCenter);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.hide();
				ManageMain window=new ManageMain();
				window.frame.setVisible(true);
			}
		});
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				System.out.println(table.getValueAt(table.getSelectedRow(),0));
				Object lid=table.getValueAt(table.getSelectedRow(),0);
				Object laddress=table.getValueAt(table.getSelectedRow(),1);
				Object area=table.getValueAt(table.getSelectedRow(),2); 
				Object tel=table.getValueAt(table.getSelectedRow(),3); 
				java.sql.Statement statement=null;
				ResultSet rs=null;
				Connection connection=ConnDB.getConnection();
				try {
					statement=connection.createStatement();
					String sqlString="update Libraries set l_Address='"+laddress+"',l_Area='"+area+"',l_Tel='"+tel+"' where l_Id='"+lid+"'";
//					System.out.println(sqlString);
					int non=statement.executeUpdate(sqlString);
					if(non>0) {
						JOptionPane.showMessageDialog(frame, "修改成功","提示",JOptionPane.DEFAULT_OPTION);
						frame.dispose();
						new LibraryInfoManage();
					}
//					System.out.println(statement.executeUpdate(sqlString));
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		
		pnlCenter.add(btnUpdate);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object id=table.getValueAt(table.getSelectedRow(),0);
				java.sql.Statement statement=null;
				ResultSet rs=null;
				Connection connection=ConnDB.getConnection();
				try {
					statement=connection.createStatement();
					String sqlString="delete from Libraries where l_Id='"+id+"'";
//					System.out.println(sqlString);
					int non=statement.executeUpdate(sqlString);
					if(non>0) {
						JOptionPane.showMessageDialog(frame, "删除成功","提示",JOptionPane.DEFAULT_OPTION);
						frame.dispose();
						new LibraryInfoManage();
					}
//					System.out.println(statement.executeUpdate(sqlString));
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		
		pnlCenter.add(btnDelete);
		
		pnlCenter.add(btnBack);
		
		
		
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
			System.out.println(table.getRowCount());
			for(int i=0;i<title.length;i++)
				System.out.print(title[i]);
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

		frame.setSize(1018, 471);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		
	}	
}
