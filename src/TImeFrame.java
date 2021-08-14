import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Button;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.SwingConstants;

public class TImeFrame extends JFrame {

	private JPanel contentPane;
	public static JLabel lblTimer;
	static public TImeFrame frame = null;
	static public long seconds=0;
	static public long minutes = 0;
	static public long millis = 0;
	static public long hours = 0;
	static public Thread clock;
	long startNanoTime = System.nanoTime();
	
	/**
	 * Launch the application.
	 */
	int xx,xy;
	private JLabel lblMin;
	private JLabel lblCl;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new TImeFrame();
					frame.setUndecorated(true);					
					frame.setVisible(true);
					
				} catch (Exception e) {
					//e.printStackTrace();
				}
			}
		});
	}
	public void clock() {
		clock=new Thread() {
			public void run() {
				try {
					while(true) {
						millis = (System.nanoTime()-startNanoTime)/1000000;
						minutes = (millis /1000) / 60;
						minutes = minutes % 60;
						seconds = (millis /1000) % 60;
						hours = (millis /60000) / 60;
						hours = hours % 60;
						
						sleep(1000);
						lblTimer.setText(hours+":"+minutes+":"+seconds);						
					}
				} catch (InterruptedException e) {
					//e.printStackTrace();
				}
			}
		};		
		clock.start();
	}
	
	public TImeFrame() {
		clock();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(650, 13, 70, 20);
		contentPane = new JPanel();
		contentPane.setBackground(Color.white);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTimer = new JLabel("");
		lblTimer.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		lblTimer.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimer.setBounds(0, 0, 70, 20);
		contentPane.add(lblTimer);
		
	}
}
