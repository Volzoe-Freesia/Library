
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ReaderInfoManage{
	Object[][] data=null;
	String[] title = { "编号", "名字", "年龄","职业","部门"};
	
	JFrame frame = new JFrame("读者信息管理");
	
	
	
	private JTable table;
	private JPanel panel;
	private JButton btnBack;
	private JLabel lblNewLabel;
	private JTextField txtReaderName;
	private JLabel label;
	private JTextField txtReaderAge;
	private JLabel label_1;
	private JTextField txtReaderJob;
	private JLabel label_2;
	private JTextField txtReaderDept;
	private JPanel panel_1;
	private JButton btnAddReaderInfo;
	private JButton btnDeleteReaderInfo;
	private JLabel label_3;
	private JTextField txtReaderID;
	private JButton btnUpdate;
	
	public static void main(String[] args) {
		new ReaderInfoManage();
	}
	public void init() {
		java.sql.Statement statement=null;
		ResultSet rs=null;
		Connection connection=ConnDB.getConnection();
		try {
			statement=connection.createStatement();
			String sqlString="select * from Reader";
			rs=statement.executeQuery(sqlString);
			 List <Reader> list=new ArrayList<Reader>(); 
			  while(rs.next()) { 
				  Reader reader=new Reader();
				  reader.setR_Id(rs.getInt("r_Id"));
				  reader.setR_Name(rs.getString("r_Name"));
				  reader.setR_Age(rs.getInt("r_Age"));
				  reader.setR_Job(rs.getString("r_Job"));
				  reader.setR_Dept(rs.getString("r_Dept")); 
				  list.add(reader); 
				  } 
			  data=new Object[list.size()][title.length];
			  for(int i=0;i<list.size();i++) {
				  data[i][0]=list.get(i).getR_Id(); 
				  data[i][1]=list.get(i).getR_Name();
				  data[i][2]=list.get(i).getR_Age(); 
				  data[i][3]=list.get(i).getR_Job();
				  data[i][4]=list.get(i).getR_Dept(); 
				  } 			  
			table.setBounds(14, 92, 695, 427);
			frame.getContentPane().add(table);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			ConnDB.closeAll(connection, statement, rs);
		}
		table = new JTable(data, title);
	}
	public ReaderInfoManage() {
		java.sql.Statement statement=null;
		ResultSet rs=null;
		Connection connection=ConnDB.getConnection();
		try {
			statement=connection.createStatement();
			String sqlString="select * from Reader";
			rs=statement.executeQuery(sqlString);
			 List <Reader> list=new ArrayList<Reader>(); 
			  while(rs.next()) { 
				  Reader reader=new Reader();
				  reader.setR_Id(rs.getInt("r_Id"));
				  reader.setR_Name(rs.getString("r_Name"));
				  reader.setR_Age(rs.getInt("r_Age"));
				  reader.setR_Job(rs.getString("r_Job"));
				  reader.setR_Dept(rs.getString("r_Dept")); 
				  list.add(reader); 
				  } 
			  data=new Object[list.size()][title.length];
			  for(int i=0;i<list.size();i++) {
				  data[i][0]=list.get(i).getR_Id(); 
				  data[i][1]=list.get(i).getR_Name();
				  data[i][2]=list.get(i).getR_Age(); 
				  data[i][3]=list.get(i).getR_Job();
				  data[i][4]=list.get(i).getR_Dept(); 
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
		
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		label_3 = new JLabel("\u7F16\u53F7\uFF1A");
		panel.add(label_3);
		
		txtReaderID = new JTextField();
		txtReaderID.setColumns(10);
		panel.add(txtReaderID);
		
		lblNewLabel = new JLabel("\u540D\u5B57\uFF1A");
		panel.add(lblNewLabel);
		
		txtReaderName = new JTextField();
		panel.add(txtReaderName);
		txtReaderName.setColumns(10);
		
		label = new JLabel("\u5E74\u9F84:");
		panel.add(label);
		
		txtReaderAge = new JTextField();
		txtReaderAge.setColumns(10);
		panel.add(txtReaderAge);
		
		label_1 = new JLabel("\u804C\u4E1A:");
		panel.add(label_1);
		
		txtReaderJob = new JTextField();
		txtReaderJob.setColumns(10);
		panel.add(txtReaderJob);
		
		label_2 = new JLabel("\u90E8\u95E8:");
		panel.add(label_2);
		
		txtReaderDept = new JTextField();
		txtReaderDept.setColumns(10);
		panel.add(txtReaderDept);
		
		panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		btnAddReaderInfo = new JButton("\u65B0\u589E");
		btnAddReaderInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.sql.Statement statement=null;
				Connection connection=ConnDB.getConnection();
				try {
					statement=connection.createStatement();
					String id,name,age,job,dept;
					id=txtReaderID.getText().trim();
					name=txtReaderName.getText().trim();
					age=txtReaderAge.getText().trim();
					job=txtReaderJob.getText().trim();
					dept=txtReaderDept.getText().trim();
					String sqlString="insert into Reader values('"+id+"','"+name+"','"+age+"','"+job+"','"+dept+"')";
					int non=statement.executeUpdate(sqlString);
					if(non>0) {
						JOptionPane.showMessageDialog(frame, "新增成功","提示",JOptionPane.DEFAULT_OPTION);
						frame.dispose();
						new ReaderInfoManage();
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		
		btnUpdate = new JButton("\u4FEE\u6539");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				System.out.println(table.getValueAt(table.getSelectedRow(), 3));
				Object id=table.getValueAt(table.getSelectedRow(),0);
				Object name=table.getValueAt(table.getSelectedRow(),1);
				Object age=table.getValueAt(table.getSelectedRow(),2); 
				Object job=table.getValueAt(table.getSelectedRow(),3);
				Object dept=table.getValueAt(table.getSelectedRow(),4);
				java.sql.Statement statement=null;
				ResultSet rs=null;
				Connection connection=ConnDB.getConnection();
				try {
					statement=connection.createStatement();
					String sqlString="update Reader set r_Name='"+name+"',r_Age='"+age+"',r_Job='"+job+"',r_Dept='"+dept+"' where r_Id='"+id+"'";
//					System.out.println(sqlString);
					int non=statement.executeUpdate(sqlString);
					if(non>0) {
						JOptionPane.showMessageDialog(frame, "修改成功","提示",JOptionPane.DEFAULT_OPTION);
						frame.dispose();
						new ReaderInfoManage();
					}
//					System.out.println(statement.executeUpdate(sqlString));
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		panel_1.add(btnUpdate);
		panel_1.add(btnAddReaderInfo);
		
		btnDeleteReaderInfo = new JButton("\u5220\u9664");
		btnDeleteReaderInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				System.out.println(table.getValueAt(table.getSelectedRow(),0));
				Object id=table.getValueAt(table.getSelectedRow(),0);
				java.sql.Statement statement=null;
				ResultSet rs=null;
				Connection connection=ConnDB.getConnection();
				try {
					statement=connection.createStatement();
					String sqlString="delete from Reader where r_Id='"+id+"'";
//					System.out.println(sqlString);
					int non=statement.executeUpdate(sqlString);
					if(non>0) {
						JOptionPane.showMessageDialog(frame, "删除成功","提示",JOptionPane.DEFAULT_OPTION);
						frame.dispose();
						new ReaderInfoManage();
					}
//					System.out.println(statement.executeUpdate(sqlString));
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		panel_1.add(btnDeleteReaderInfo);
		
		btnBack = new JButton("\u8FD4\u56DE");
		panel_1.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.hide();
				ManageMain window=new ManageMain();
				window.frame.setVisible(true);
			}
		});

		frame.setSize(821, 471);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
}
