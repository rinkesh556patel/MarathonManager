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
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Activator extends JFrame {
	
	private JPanel contentPane;
	private JTextField txtkey;
	static Activator frame;
	static Login frame1;
	private JLabel lblkey;
	static SwitchPanel switchPanelFrame ;
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
					frame = new Activator();
					frame.setUndecorated(true);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					//e.printStackTrace();
				}
			}
		});
	}

	
	public Activator() {
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
				Activator.this.setLocation(x - xx , y - xy);
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 120, 650, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(139,157,195));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnActivate = new JButton("Activate");
		btnActivate.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
		btnActivate.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnActivate.setForeground(Color.WHITE);
		btnActivate.setBounds(180, 199, 394, 40);
		btnActivate.setBackground(new Color(59,89,152));
		btnActivate.setBorder(null);
		contentPane.add(btnActivate);
		
		btnActivate.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		    	Connection connection=null;
		    	Connection conLocal = null;
		        try {
		        	Class.forName("com.mysql.jdbc.Driver").newInstance(); 
		            connection=DriverManager.getConnection("jdbc:mysql://db4free.net:3306/marathon","rfid_marathon","@a3rink#");
		            Class.forName("org.sqlite.JDBC");
		            conLocal = DriverManager.getConnection("jdbc:sqlite:marathon.db");
		            
		            PreparedStatement pstmt1=conLocal.prepareStatement("Create table if not exists [activator](akey varchar2(100),macAdd varchar2(50),status varchar2(5))");
					int res1=pstmt1.executeUpdate();
		        } catch (SQLException ex) {
		            //Logger.getLogger(Activation_Page.class.getName()).log(Level.SEVERE, null, ex);
		            JOptionPane.showMessageDialog(null, ex.getMessage()  , "Invalid Key", JOptionPane.INFORMATION_MESSAGE);
		        } catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
		        	JOptionPane.showMessageDialog(null, e1.getMessage()  , "Invalid Key", JOptionPane.INFORMATION_MESSAGE);
				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        
		        String inputData = txtkey.getText();
		        String query = "select active_key, status from activation_check where active_key='"+inputData+"'";
		        Statement st = null;
		        try {
		            st = connection.createStatement();
		            ResultSet rs = null;
		            rs = st.executeQuery(query);
		            if (rs.next())
		            {
		            	if(rs.getString("status").equals("yes")) {
		            		JOptionPane.showMessageDialog(null, "Please enter valid activation key"  , "Invalid Key", JOptionPane.INFORMATION_MESSAGE);
		            	}else {
		            		/*--------Getting Mac----------------------------*/
		                    InetAddress ip = InetAddress.getLocalHost();
		                    NetworkInterface n = NetworkInterface.getByInetAddress(ip);
			             	byte[] mac = n.getHardwareAddress();
			             	StringBuilder sb = new StringBuilder();
			             	for(int i=0;i<mac.length;i++) {
			             		 sb.append(String.format("%02X%s", mac[i], (i<mac.length-1)?"-":""));
			             	}
			             	String macAdd=sb.toString();

			             	
			             	/*-------------------Entry in localDB-----------------*/
			             	PreparedStatement pstmt = conLocal.prepareStatement("insert into activator values(?, ?, ?)");
			             	pstmt.setString(1, inputData);
			             	pstmt.setString(2, macAdd);
			             	pstmt.setString(3, "yes");
			             	int cnt=pstmt.executeUpdate();
							if(cnt>0) {
								JOptionPane.showMessageDialog(null, "Local entry created"  , "Insertion Successfull ", JOptionPane.INFORMATION_MESSAGE);
							}else {
								JOptionPane.showMessageDialog(null, "Local entry creation failed"  , "Insertion failed", JOptionPane.INFORMATION_MESSAGE);
							}
							
							
			             	/*------------ updating Server entry------------------------*/
							
							PreparedStatement pstmt2 = connection.prepareStatement("update activation_check set macAddress=? , status=? where active_key=?");
							pstmt2.setString(1, macAdd);
							pstmt2.setString(2, "yes");
							pstmt2.setString(3, inputData);
							cnt=pstmt2.executeUpdate();
							if(cnt>0) {
								JOptionPane.showMessageDialog(null, "Remote entry created"  , "Insertion Successfull ", JOptionPane.INFORMATION_MESSAGE);
								frame1 = new Login();
								frame1.setUndecorated(true);
								frame1.setLocationRelativeTo(null);
								frame1.setVisible(true);
								dispose();
							}else {
								JOptionPane.showMessageDialog(null, "Remote entry creation failed"  , "Insertion failed", JOptionPane.INFORMATION_MESSAGE);
							}
							/****************************************/
			             	JOptionPane.showMessageDialog(null, "Activation successfull "+ macAdd , "Activated", JOptionPane.INFORMATION_MESSAGE);
		            	}		             	
		            }else{
	                	JOptionPane.showMessageDialog(null, "Please enter valid activation key !!! " , "Activation failed", JOptionPane.INFORMATION_MESSAGE);
	                } 
		        } catch (SQLException ex) {
		            //Logger.getLogger(Activation_Page.class.getName()).log(Level.SEVERE, null, ex);
					JOptionPane.showMessageDialog(null, "Please enter valid activation key !!! " , " failure SQL", JOptionPane.INFORMATION_MESSAGE);
		        } catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Please enter valid activation key !!! " , "HOST failure1", JOptionPane.INFORMATION_MESSAGE);
				} catch (SocketException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Please enter valid activation key !!! " , " SOCKET failure2", JOptionPane.INFORMATION_MESSAGE);
				}
		    }
		});
		
		
		txtkey = new JTextField();
		txtkey.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		txtkey.setBounds(180, 128, 394, 40);
		txtkey.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtkey);
		txtkey.setColumns(10);
		
		lblkey = new JLabel("Enter Key");
		lblkey.setForeground(Color.WHITE);
		lblkey.setFont(new Font("Yu Gothic UI", Font.PLAIN, 25));
		lblkey.setBounds(34, 124, 136, 43);
		contentPane.add(lblkey);
		
		ImageIcon icon = new ImageIcon("./images/g3.png"); 		
		JLabel bg = new JLabel(".");
		bg.setBounds(0, 0, 1050, 650);
		//contentPane.add(bg);
		bg.setIcon(icon);
		
		ImageIcon logoicon = new ImageIcon("./images/TST3.png");
		
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
		lblMin.setBounds(595, 2, 25, 25);
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
		lblCl.setBounds(622, 2, 25, 25);
		contentPane.add(lblCl);
	}
}
