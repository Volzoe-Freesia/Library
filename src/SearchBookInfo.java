

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


public class SearchBookInfo{
	String[] title= {"编号","书架号","书名","价格","作者"};
	Object[][] data=null;
	
	JFrame frame = new JFrame("图书查询");
	
	JPanel pnlCenter = new JPanel();
	final JTextField txtBookShelfID;
	
	
	private JTable table;
	private final JLabel lblNewLabel = new JLabel("\u4E66\u67B6\u53F7");
	private final JTextField txtBookName = new JTextField();
	private final JLabel lblNewLabel_1 = new JLabel("\u4E66\u540D");
	private final JTextField txtAuthor = new JTextField();
	private final JLabel lblNewLabel_2 = new JLabel("\u4F5C\u8005");
	private final JButton btnClear = new JButton("\u6E05\u7A7A");
	private final JButton btnBack = new JButton("\u8FD4\u56DE");
	private final JButton btnSearch = new JButton("\u641C\u7D22");
	private final JButton btnShowAll = new JButton("\u663E\u793A\u5168\u90E8");
	
	public static void main(String[] args) {
		new SearchBookInfo();
	}
	
	public SearchBookInfo() {
		txtAuthor.setText("");
		txtAuthor.setColumns(10);
		txtBookName.setColumns(10);
		
		pnlCenter.add(lblNewLabel);
		
		
		txtBookShelfID = new JTextField(10);
		pnlCenter.add(txtBookShelfID);
		
		pnlCenter.add(lblNewLabel_1);
		
		pnlCenter.add(txtBookName);
		
		pnlCenter.add(lblNewLabel_2);
		
		pnlCenter.add(txtAuthor);
		
		frame.getContentPane().add("North",pnlCenter);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtAuthor.setText(null);
				txtBookName.setText(null);
				txtBookShelfID.setText(null);
			}
		});
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  java.sql.Statement statement=null;
				  ResultSet rs=null; 
				  Connection connection=ConnDB.getConnection(); 
				  String bookname=txtBookName.getText(); 
				  String bookauthor=txtAuthor.getText(); 
				  String bookshelf=txtBookShelfID.getText(); 
				  try {
					  statement=connection.createStatement();
					  String sqlString="select * from Books where 1=1";
					  if(bookname !=null && !"".equals(bookname)) {
						  sqlString+= " and b_Name='"+bookname+"'";
					  }
					  if(bookshelf !=null && !"".equals(bookshelf)) {
						  sqlString+= " and shelf_Id='"+bookshelf+"'";
					  }
					  if(bookauthor !=null && !"".equals(bookauthor)) {
						  sqlString+= " and b_Author='"+bookauthor+"'";
					  }
//					  System.out.println(sqlString); 
					  rs=statement.executeQuery(sqlString);
					  List <Book> list=new ArrayList<Book>(); 
					  while(rs.next()) { 
						  Book book=new Book();
						  book.setB_Id(rs.getInt("b_Id"));
						  book.setB_Name(rs.getString("b_Name"));
						  book.setShelf_Id(rs.getInt("shelf_Id"));
						  book.setB_Author(rs.getString("b_Author"));
						  book.setB_Price(rs.getFloat("b_Price"));
						  list.add(book); 
						  } 
					  data=new Object[list.size()][title.length];
					  for(int i=0;i<list.size();i++) {
						  data[i][0]=list.get(i).getB_Id(); 
						  data[i][1]=list.get(i).getShelf_Id();
						  data[i][2]=list.get(i).getB_Name(); 
						  data[i][3]=list.get(i).getB_Price();
						  data[i][4]=list.get(i).getB_Author(); 
						  } 
					  table=new JTable(data, title);
					  JScrollPane scroll = new JScrollPane(table);
						frame.getContentPane().add(scroll);

						frame.setSize(1031, 696);
						frame.setLocationRelativeTo(null);
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frame.setVisible(true);
				  }catch (Exception e1) { 
					  // TODO: handle exception } } });
				  }
			}
		});
		
		pnlCenter.add(btnSearch);
		
		pnlCenter.add(btnClear);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.hide();
				Login window=new Login();
				window.frame.setVisible(true);
			}
		});
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new SearchBookInfo();
			}
		});
		
		pnlCenter.add(btnShowAll);
		
		pnlCenter.add(btnBack);
		
		//初始化数据
		java.sql.Statement statement=null;
		ResultSet rs=null;
		Connection connection=ConnDB.getConnection();
		try {
			statement=connection.createStatement();
			String sqlString="select * from Books";
			rs=statement.executeQuery(sqlString);
			 List <Book> list=new ArrayList<Book>(); 
			  while(rs.next()) { 
				  Book book=new Book();
				  book.setB_Id(rs.getInt("b_Id"));
				  book.setB_Name(rs.getString("b_Name"));
				  book.setShelf_Id(rs.getInt("shelf_Id"));
				  book.setB_Author(rs.getString("b_Author"));
				  book.setB_Price(rs.getFloat("b_Price"));
				  list.add(book); 
				  } 
			  data=new Object[list.size()][title.length];
			  for(int i=0;i<list.size();i++) {
				  data[i][0]=list.get(i).getB_Id(); 
				  data[i][1]=list.get(i).getShelf_Id();
				  data[i][2]=list.get(i).getB_Name(); 
				  data[i][3]=list.get(i).getB_Price();
				  data[i][4]=list.get(i).getB_Author(); 
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

		frame.setSize(1031, 696);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		
	}	
}
