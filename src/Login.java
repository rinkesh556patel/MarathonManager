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
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtusername;
	private JTextField txtpassword;
	private JLabel label;
	private JLabel lblPassword;
	private JLabel lblclose;
	private JPasswordField txtpwd;
	static Login frame;
	private JLabel lblLogIn;
	String macDatabase;
	static SwitchPanel switchPanelFrame ;
	/**
	 * Launch the application.
	 */
	int xx,xy;
	private JLabel lblMin;
	private JLabel lblCl;
	private static JLabel lblActivate;
	Connection con = null;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Login();
					frame.setUndecorated(true);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					activationCheck();
				} catch (Exception e) {
					//e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public static void addSwitchPanel() {
		switchPanelFrame = new SwitchPanel();
		switchPanelFrame.setUndecorated(true);
		switchPanelFrame.setLocationRelativeTo(null);
		switchPanelFrame.setVisible(true);
		
	}
	public void loginFun() throws UnknownHostException, SocketException, ClassNotFoundException {
		
		InetAddress ip = InetAddress.getLocalHost();
        NetworkInterface n = NetworkInterface.getByInetAddress(ip);
     	byte[] mac = n.getHardwareAddress();
     	StringBuilder sb = new StringBuilder();
     	for(int i=0;i<mac.length;i++) {
     		 sb.append(String.format("%02X%s", mac[i], (i<mac.length-1)?"-":""));
     	}
     	String macAdd=sb.toString();
     	try {
     		Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:marathon.db");
			String query = "select macadd from activator";
		    PreparedStatement pstmt2 = con.prepareStatement(query);
	        ResultSet rs2 = pstmt2.executeQuery();
            if (rs2.next()){
            	macDatabase = rs2.getString(1);
            }else {
            	JOptionPane.showMessageDialog(null, ">> Invalid username or password or system" , "Login failed", JOptionPane.INFORMATION_MESSAGE);
            }
     	}catch (SQLException e1) {
		//	e1.printStackTrace();
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
		String username = txtusername.getText();
    	String pwd = String.valueOf(txtpwd.getPassword());
    	if( username.equals("admin") &&  pwd.equals("admin")  && macAdd.equals(macDatabase)) {
    		Statement st;
			try {
				Class.forName("org.sqlite.JDBC");
				con = DriverManager.getConnection("jdbc:sqlite:marathon.db");
				String qr = "create table if not exists [tbltagdata]( tid varchar2(50),athletename varchar2(50),distance varchar2(15),contact varchar2(15), gender varchar2(10),age varchar2(5),category varchar2(20),color varchar2(10),bib varchar2(50));";
				//String qr = "drop table tbltagdata";
				
				
				st = con.createStatement();
				st.executeUpdate(qr);
				String qr2 = "create table if not exists [tblrace]( tid varchar2(50),timestamp varchar2(50),time varchar2(20),lap varchar2(5));";
				st.executeUpdate(qr2);
				
				
			} catch (SQLException e1) {
				//	e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage()  , "Invalid Key", JOptionPane.INFORMATION_MESSAGE);
			}
			finally {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			}
			addSwitchPanel();	    		
    		dispose();
    		
    	}else {
    		JOptionPane.showMessageDialog(null, "!!! Enter valid username and password !!! " , "Invalid User", JOptionPane.INFORMATION_MESSAGE);
    	}
	}
	public static void activationCheck(){
		Connection con = null;
    	try{		    		
    		Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:marathon.db");
			PreparedStatement pstmt1=con.prepareStatement("Create table if not exists [activator](akey varchar2(100),macAdd varchar2(50),status varchar2(5))");
			int res1=pstmt1.executeUpdate();
			
			PreparedStatement pstmt=con.prepareStatement("select akey,macAdd,status from Activator");
			ResultSet res=pstmt.executeQuery();
			if(!res.next()){
				JOptionPane.showMessageDialog(null, "Not found" , "Invalid User", JOptionPane.INFORMATION_MESSAGE);
				Activator activator= new Activator();
				activator.setUndecorated(true);
				activator.setLocationRelativeTo(null);
				frame.setVisible(false);
				activator.setVisible(true);
			}else {
				lblActivate.setText("Activated");
			}
    	}
    	catch(Exception e2){
    		JOptionPane.showMessageDialog(null, "Not found"+e2.getMessage() , "error", JOptionPane.INFORMATION_MESSAGE);
			//e2.printStackTrace();
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
	}
	public Login() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xx = e.getX();
				xy = e.getY();
			}
		});
		
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				Login.this.setLocation(x - xx , y - xy);
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 120, 1050, 650);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(139,157,195));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnlogin = new JButton("Log In");
		btnlogin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					loginFun();
				} catch (UnknownHostException | SocketException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
			}
		});
		btnlogin.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnlogin.setForeground(Color.WHITE);
		btnlogin.setBounds(699, 401, 300, 40);
		btnlogin.setBackground(new Color(59,89,152));
		btnlogin.setBorder(null);
		contentPane.add(btnlogin);
		
		btnlogin.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		    	try {
					loginFun();
				} catch (UnknownHostException | SocketException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage()  , "Invalid Key", JOptionPane.INFORMATION_MESSAGE);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
		    }
		});
		
		
		txtusername = new JTextField();
		txtusername.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		txtusername.setBounds(699, 221, 300, 40);
		txtusername.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtusername);
		txtusername.setColumns(10);
		
		txtpwd = new JPasswordField();
		txtpwd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						loginFun();
					} catch (UnknownHostException | SocketException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
					}
				}
			}
		});
		txtpwd.setColumns(10);
		txtpwd.setBounds(699, 321, 300, 40);
		txtpwd.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtpwd);
		
		JLabel lblUserName = new JLabel("Username");
		lblUserName.setForeground(Color.WHITE);
		lblUserName.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		lblUserName.setBounds(699, 171, 300, 40);
		contentPane.add(lblUserName);
		
		lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		lblPassword.setBounds(699, 271, 300, 40);
		contentPane.add(lblPassword);
		
		lblLogIn = new JLabel("Log In");
		lblLogIn.setForeground(Color.WHITE);
		lblLogIn.setFont(new Font("Yu Gothic UI", Font.PLAIN, 25));
		lblLogIn.setBounds(799, 117, 136, 43);
		contentPane.add(lblLogIn);
		
		ImageIcon icon = new ImageIcon("./images/g3.png"); 		
		JLabel bg = new JLabel(".");
		bg.setBounds(0, 0, 1050, 650);
		//contentPane.add(bg);
		bg.setIcon(icon);
		
		ImageIcon logoicon = new ImageIcon("./images/TST3.png"); 
		JLabel lg = new JLabel("");
		contentPane.add(lg);
		lg.setBounds(0, 90, 619, 542);
		lg.setIcon(new ImageIcon("./images/login TST.png"));
		
		lblMin = new JLabel("");
		lblMin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblMin.setIcon(new ImageIcon("./images/minIcoRed.png"));
			}
			public void mouseExited(MouseEvent e) {
				lblMin.setIcon(new ImageIcon("./images/minIco.png"));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(Frame.ICONIFIED);
			}
		});
		lblMin.setForeground(Color.MAGENTA);
		lblMin.setIcon(new ImageIcon("./images/minIco.png"));
		lblMin.setBounds(995, 2, 25, 25);
		contentPane.add(lblMin);
		
		lblCl = new JLabel("");
		lblCl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblCl.setIcon(new ImageIcon("./images/closeIcoRed.png"));
			}
			public void mouseExited(MouseEvent e) {
				lblCl.setIcon(new ImageIcon("./images/closeIco.png"));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
				
			}
		});
		lblCl.setIcon(new ImageIcon("./images/closeIco.png"));
		lblCl.setForeground(Color.MAGENTA);
		lblCl.setBounds(1021, 2, 25, 25);
		contentPane.add(lblCl);
		
		lblActivate = new JLabel("");
		lblActivate.setForeground(Color.WHITE);
		lblActivate.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		lblActivate.setBounds(925, 548, 74, 40);
		contentPane.add(lblActivate);
	}
}
