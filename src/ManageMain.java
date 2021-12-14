import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManageMain {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageMain window = new ManageMain();
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
	public ManageMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("管理员功能选择");
		frame.setBounds(100, 100, 360, 482);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		int windowWidth = frame.getWidth(); //获得窗口宽
		int windowHeight = frame.getHeight(); //获得窗口高
		Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包
		Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸
		int screenWidth = screenSize.width; //获取屏幕的宽
		int screenHeight = screenSize.height; //获取屏幕的高
		frame.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//设置窗口居中显示 
		 
		
		JButton btnReaderInfo = new JButton("\u8BFB\u8005\u4FE1\u606F\u7BA1\u7406");
		btnReaderInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.hide();
				ReaderInfoManage window=new ReaderInfoManage();
				window.frame.setVisible(true);
			}
		});
		btnReaderInfo.setBounds(103, 53, 135, 27);
		frame.getContentPane().add(btnReaderInfo);
		
		JButton btnLibraryInfo = new JButton("\u4E66\u5E93\u4FE1\u606F\u7BA1\u7406");
		btnLibraryInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.hide();
				LibraryInfoManage window=new LibraryInfoManage();
				window.frame.setVisible(true);
			}
		});
		btnLibraryInfo.setBounds(102, 118, 135, 27);
		frame.getContentPane().add(btnLibraryInfo);
		
		JButton btnExit = new JButton("\u9000\u51FA\u7CFB\u7EDF");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "确认退出?", "确认",JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);
		        if (result == JOptionPane.OK_OPTION) {
			        // 退出
		            System.exit(0);
		        }
			}
		});
		btnExit.setBounds(103, 320, 135, 27);
		frame.getContentPane().add(btnExit);
		
		JLabel lblNewLabel = new JLabel("\u7BA1\u7406\u5458\u529F\u80FD\u9009\u62E9");
		lblNewLabel.setBounds(110, 13, 112, 18);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnBorrowInfo = new JButton("\u501F\u9605\u4FE1\u606F\u7BA1\u7406");
		btnBorrowInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.hide();
				BorrowInfoManage window=new BorrowInfoManage();
				window.frame.setVisible(true);
			}
		});
		btnBorrowInfo.setBounds(103, 184, 135, 27);
		frame.getContentPane().add(btnBorrowInfo);
		
		JButton btnBackLogin = new JButton("\u8FD4\u56DE\u4E3B\u754C\u9762");
		btnBackLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.hide();
				Login window=new Login();
				window.frame.setVisible(true);
			}
		});
		btnBackLogin.setBounds(103, 250, 135, 27);
		frame.getContentPane().add(btnBackLogin);
	}
}
