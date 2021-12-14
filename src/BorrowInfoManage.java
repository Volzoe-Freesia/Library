
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class BorrowInfoManage{

	Object[][] data=null;
	String[] title = { "读者编号", "图书编号", "借阅日期"};
	
	JFrame frame = new JFrame("借阅信息管理");
	
	JPanel pnlCenter = new JPanel();
	
	JLabel lblName = new JLabel("\u8BFB\u8005\u7F16\u53F7\uFF1A");
	final JTextField txtReaderID;
	
	
	private JTable table;
	private final JLabel label = new JLabel("\u56FE\u4E66\u7F16\u53F7\uFF1A");
	private final JTextField txtBookID = new JTextField(10);
	private final JLabel label_1 = new JLabel("\u501F\u9605\u65E5\u671F\uFF1A");
	private final JTextField txtBorrowDate = new JTextField(10);
	private final JButton btnBack = new JButton("\u8FD4\u56DE");
	private final JButton btnDelete = new JButton("\u5220\u9664");
	private final JButton btnUpdate = new JButton("\u4FEE\u6539");
	
	public static void main(String[] args) {
		new BorrowInfoManage();
	}
	
	public BorrowInfoManage() {
		
		txtReaderID = new JTextField(10);
		pnlCenter.add(lblName);
		pnlCenter.add(txtReaderID);
		
		pnlCenter.add(label);
		
		pnlCenter.add(txtBookID);
		
		pnlCenter.add(label_1);
		
		pnlCenter.add(txtBorrowDate);
		
		
		JButton btnAddBorrowInfo=new JButton("\u65B0\u589E");
		btnAddBorrowInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.sql.Statement statement=null;
				Connection connection=ConnDB.getConnection();
				try {
					statement=connection.createStatement();
					String readerId,BookId,BorrowDate;
					readerId=txtReaderID.getText();
					BookId=txtBookID.getText();
					BorrowDate=txtBorrowDate.getText();
					String sqlString="insert into Borrow values('"+readerId+"','"+BookId+"','"+BorrowDate+"')";
//					System.out.println(sqlString);
					int non=statement.executeUpdate(sqlString);
					if(non>0) {
						JOptionPane.showMessageDialog(frame, "新增成功","提示",JOptionPane.DEFAULT_OPTION);
						frame.dispose();
						new BorrowInfoManage();
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});

		
		pnlCenter.add(btnAddBorrowInfo);
		
		frame.getContentPane().add("North",pnlCenter);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.hide();
				ManageMain window=new ManageMain();
				window.frame.setVisible(true);
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				System.out.println(table.getValueAt(table.getSelectedRow(),1));
				Object id=table.getValueAt(table.getSelectedRow(),1);
				java.sql.Statement statement=null;
				ResultSet rs=null;
				Connection connection=ConnDB.getConnection();
				try {
					statement=connection.createStatement();
					String sqlString="delete from Borrow where b_Id='"+id+"'";
//					System.out.println(sqlString);
					int non=statement.executeUpdate(sqlString);
					if(non>0) {
						JOptionPane.showMessageDialog(frame, "删除成功","提示",JOptionPane.DEFAULT_OPTION);
						frame.dispose();
						new BorrowInfoManage();
					}
//					System.out.println(statement.executeUpdate(sqlString));
				} catch (Exception e2) {
					// TODO: handle exception
//				}
			}
		}
			});
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				System.out.println(table.getValueAt(table.getSelectedRow(),0));
				Object rid=table.getValueAt(table.getSelectedRow(),0);
				Object bid=table.getValueAt(table.getSelectedRow(),1);
				Object borrowdate=table.getValueAt(table.getSelectedRow(),2); 
				java.sql.Statement statement=null;
				ResultSet rs=null;
				Connection connection=ConnDB.getConnection();
				try {
					statement=connection.createStatement();
					String sqlString="update Borrow set b_Id='"+bid+"',borrow_Date='"+borrowdate+"' where r_Id='"+rid+"'";
//					System.out.println(sqlString);
					int non=statement.executeUpdate(sqlString);
					if(non>0) {
						JOptionPane.showMessageDialog(frame, "修改成功","提示",JOptionPane.DEFAULT_OPTION);
						frame.dispose();
						new BorrowInfoManage();
					}
//					System.out.println(statement.executeUpdate(sqlString));
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		
		pnlCenter.add(btnUpdate);
		
		pnlCenter.add(btnDelete);
		
		pnlCenter.add(btnBack);
		
		
		java.sql.Statement statement=null;
		ResultSet rs=null;
		Connection connection=ConnDB.getConnection();
		try {
			statement=connection.createStatement();
			String sqlString="select * from Borrow";
			rs=statement.executeQuery(sqlString);
			 List <Borrow> list=new ArrayList<Borrow>(); 
			  while(rs.next()) { 
				  Borrow borrow=new Borrow();
				  borrow.setR_Id(rs.getInt("r_Id"));
				  borrow.setB_Id(rs.getInt("b_Id"));
				  borrow.setBorrow_Date(rs.getDate("borrow_Date"));
				  list.add(borrow); 
				  } 
			  data=new Object[list.size()][title.length];
			  for(int i=0;i<list.size();i++) {
				  data[i][0]=list.get(i).getR_Id(); 
				  data[i][1]=list.get(i).getB_Id();
				  data[i][2]=list.get(i).getBorrow_Date(); 
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

		frame.setSize(885, 471);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		
	}
}
