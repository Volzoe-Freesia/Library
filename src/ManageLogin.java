import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class ManageLogin {

	public JFrame frame;
	private JTextField txtManageName;
	private JTextField txtManageID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageLogin window = new ManageLogin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ManageLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("管理员登陆");
		frame.setBounds(100, 100, 531, 437);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		int windowWidth = frame.getWidth(); //获得窗口宽
		int windowHeight = frame.getHeight(); //获得窗口高
		Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包
		Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸
		int screenWidth = screenSize.width; //获取屏幕的宽
		int screenHeight = screenSize.height; //获取屏幕的高
		frame.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//设置窗口居中显示 
		 
		
		txtManageName = new JTextField();
		txtManageName.setBounds(207, 132, 157, 24);
		frame.getContentPane().add(txtManageName);
		txtManageName.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u59D3\u540D");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(103, 135, 72, 18);
		frame.getContentPane().add(lblNewLabel);
		
		txtManageID = new JTextField();
		txtManageID.setBounds(207, 180, 157, 24);
		frame.getContentPane().add(txtManageID);
		txtManageID.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u7BA1\u7406\u5458ID");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(103, 183, 72, 18);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnLogin = new JButton("\u767B\u9646");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connection=null;
				java.sql.Statement statement=null;
				ResultSet rs=null;
				String ID=txtManageID.getText();
				String Name=txtManageName.getText();
				connection=ConnDB.getConnection();
				try {
					statement=connection.createStatement();
					String sqlString ="select * from Administrators where a_Id="+ID+" and a_Name='"+Name+"'";
//					System.out.println(sqlString);
					rs=statement.executeQuery(sqlString);
					
					if(rs.next()){
						JOptionPane.showMessageDialog(frame, "欢迎你,"+Name,"登陆成功",JOptionPane.DEFAULT_OPTION);
						frame.hide();
						ManageMain window=new ManageMain();
						window.frame.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(frame, "管理员姓名或ID不正确","登陆失败",JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnLogin.setBounds(87, 268, 113, 27);
		frame.getContentPane().add(btnLogin);
		
		JButton btnBack = new JButton("\u8FD4\u56DE\u4E3B\u83DC\u5355");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.hide();
				Login window=new Login();
				window.frame.setVisible(true);
			}
		});
		btnBack.setBounds(264, 268, 113, 27);
		frame.getContentPane().add(btnBack);
		
		JLabel lblNewLabel_2 = new JLabel("\u7BA1\u7406\u5458\u767B\u9646");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(142, 58, 222, 18);
		frame.getContentPane().add(lblNewLabel_2);
	}

}
