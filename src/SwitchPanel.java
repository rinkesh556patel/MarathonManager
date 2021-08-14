import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.clou.uhf.G3Lib.CLReader;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionEvent;


public class SwitchPanel extends JFrame {

	private JPanel contentPane;
	public static JTextField txtConnId;
	static SwitchPanel frame;
	static public RaceHome raceHome ;
	static public JComboBox cmbTotAnt ;
	int xx,xy;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	static JTextField txtFilterTime;
	static AdminHome adminFrame;
	public static boolean status = false;
	/**
	 * Create the frame.
	 */
	
	
	public SwitchPanel() {
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
				SwitchPanel.this.setLocation(x - xx , y - xy);
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 120, 1050, 650);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(139,157,195));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtConnId = new JTextField();
		txtConnId.setFont(new Font("Gadugi", Font.PLAIN, 16));
		txtConnId.setText("192.168.1.116:9090");
		txtConnId.setBounds(791, 122, 203, 30);
		txtConnId.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtConnId);
		
	    cmbTotAnt = new JComboBox();
		cmbTotAnt.setFont(new Font("Gadugi", Font.PLAIN, 16));
		cmbTotAnt.setBackground(Color.WHITE);
		cmbTotAnt.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		cmbTotAnt.setMaximumRowCount(10);
		cmbTotAnt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		cmbTotAnt.setBounds(791, 234, 203, 30);
		contentPane.add(cmbTotAnt);
		
		Font f = new Font("Yu Gothic UI",Font.PLAIN,20);
		ImageIcon admin = new ImageIcon("./images/icon_admin.png");
		ImageIcon race = new ImageIcon("./images/RaceFlag.png");
		ImageIcon icon = new ImageIcon("./images/g1.jpg");
		
		JLabel lblAntenna = new JLabel("Total Antenna");
		lblAntenna.setForeground(Color.WHITE);
		lblAntenna.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		lblAntenna.setBounds(563, 234, 132, 30);
		contentPane.add(lblAntenna);
		
		JLabel lblConnectionAddress = new JLabel("Connection Address");
		lblConnectionAddress.setForeground(Color.WHITE);
		lblConnectionAddress.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		lblConnectionAddress.setBounds(563, 122, 167, 30);
		contentPane.add(lblConnectionAddress);
		
		JLabel lblFilterTime = new JLabel("Repeat Filter Time(In Second)");
		lblFilterTime.setForeground(Color.WHITE);
		lblFilterTime.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		lblFilterTime.setBounds(563, 176, 203, 30);
		contentPane.add(lblFilterTime);
		
		txtFilterTime = new JTextField();
		txtFilterTime.setFont(new Font("Gadugi", Font.PLAIN, 16));
		txtFilterTime.setText("5");
		txtFilterTime.setBounds(791, 178, 203, 30);
		txtFilterTime.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtFilterTime.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtFilterTime);
		
		JLabel lblBg = new JLabel("");
		lblBg.setIcon(new ImageIcon("./images/switchPane TST.png"));
		lblBg.setBounds(10, 120, 525, 440);
		contentPane.add(lblBg);
		
		JLabel lblMin = new JLabel("");
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
		
		JLabel lblCl = new JLabel("");
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
		
		JPanel pnlRaceBtn = new JPanel();
		pnlRaceBtn.setBounds(794, 307, 200, 164);
		contentPane.add(pnlRaceBtn);
		pnlRaceBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				raceHome = new RaceHome();
				raceHome.setUndecorated(true);
				raceHome.setLocationRelativeTo(null);
				raceHome.setVisible(true);
				dispose();
			}
		});
		
		pnlRaceBtn.setLayout(null);
		pnlRaceBtn.setBackground(new Color(59,89,152));
		pnlRaceBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("./images/RaceIco.png"));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 22, 200, 73);
		pnlRaceBtn.add(label);
		
		JLabel lblRace = new JLabel("Race");
		lblRace.setHorizontalAlignment(SwingConstants.CENTER);
		lblRace.setForeground(Color.WHITE);
		lblRace.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		lblRace.setBounds(0, 106, 190, 36);
		pnlRaceBtn.add(lblRace);
		
		JPanel pnlAdminBtn = new JPanel();
		pnlAdminBtn.setBounds(563, 307, 200, 164);
		contentPane.add(pnlAdminBtn);
		pnlAdminBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		pnlAdminBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					SampleCode2.makeConn();
					status = CLReader.CheckConnect(SwitchPanel.txtConnId.getText());
					if(!status) {
						JOptionPane.showMessageDialog(null, "Connection Failure!!! Try again" , "Connection Failure", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (InterruptedException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				adminFrame = new AdminHome();
				adminFrame.setUndecorated(true);
				adminFrame.setLocationRelativeTo(null);
				adminFrame.setVisible(true);
				dispose();
			}
		});
		pnlAdminBtn.setBackground(new Color(59,89,152));
		pnlAdminBtn.setLayout(null);
		
		JLabel lblImgAdmin = new JLabel("");
		lblImgAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblImgAdmin.setIcon(new ImageIcon("./images/adminIco.png"));
		lblImgAdmin.setBounds(0, 22, 200, 73);
		pnlAdminBtn.add(lblImgAdmin);
		
		JLabel lblAdmin = new JLabel("Admin");
		lblAdmin.setForeground(Color.WHITE);
		lblAdmin.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		lblAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdmin.setBounds(0, 106, 200, 36);
		pnlAdminBtn.add(lblAdmin);
		
		
		JLabel bg = new JLabel(".");
		bg.setBounds(0, 0, 1050, 650);
		//contentPane.add(bg);
		bg.setIcon(icon);
	}
}