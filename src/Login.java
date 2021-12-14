
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("图书馆管理系统");
		frame.setBounds(100, 100, 379, 392);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		int windowWidth = frame.getWidth(); //获得窗口宽
		int windowHeight = frame.getHeight(); //获得窗口高
		Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包
		Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸
		int screenWidth = screenSize.width; //获取屏幕的宽
		int screenHeight = screenSize.height; //获取屏幕的高
		frame.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//设置窗口居中显示 
		 
		
		JLabel lblNewLabel = new JLabel("\u6B22\u8FCE\u6765\u5230\u56FE\u4E66\u9986\u7BA1\u7406\u7CFB\u7EDF");
		lblNewLabel.setBounds(92, 13, 170, 18);
		frame.getContentPane().add(lblNewLabel);
		
		JButton button = new JButton("\u67E5\u8BE2\u56FE\u4E66\u4FE1\u606F");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.hide();
				SearchBookInfo window=new SearchBookInfo();
				window.frame.setVisible(true);
			}
		});
		button.setBounds(111, 78, 123, 27);
		frame.getContentPane().add(button);
		
		JButton button_2 = new JButton("\u4E66\u5E93\u4FE1\u606F\u67E5\u8BE2");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.hide();
				SearchLibrary window=new SearchLibrary();
				window.frame.setVisible(true);
			}
		});
		button_2.setBounds(111, 139, 123, 27);
		frame.getContentPane().add(button_2);
		
		JButton button_1 = new JButton("\u7BA1\u7406\u5458\u767B\u9646");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.hide();
				ManageLogin window = new ManageLogin();
				window.frame.setVisible(true);
			};
		});
		button_1.setBounds(111, 199, 123, 27);
		frame.getContentPane().add(button_1);
	}

}
