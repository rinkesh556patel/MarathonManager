import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.clou.uhf.G3Lib.CLReader;
import com.clou.uhf.G3Lib.SampleCode;
import com.clou.uhf.G3Lib.ClouInterface.IAsynchronousMessage;
import com.clou.uhf.G3Lib.ClouInterface.ISearchDevice;
import com.clou.uhf.G3Lib.Enumeration.eAntennaNo;
import com.clou.uhf.G3Lib.Enumeration.eReadType;
import com.clou.uhf.G3Lib.Models.Device_Model;
import com.clou.uhf.G3Lib.Models.GPI_Model;
import com.clou.uhf.G3Lib.Models.Tag_Model;
import com.jamierf.rxtx.RXTXLoader;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.TextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JSlider;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.Frame;

import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class UpdateForm extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public static JTextField txtname;
	public static JTextField txttid;
	private static JTextField txtdistance;
	private static JTextField txtcno;
	private static JTextField txtCategory;
	private static JTextField txtbib;
	static boolean found=false;
	int xx,xy;
	boolean status;

	/**
	 * Create the frame.
	 */
	public UpdateForm() {
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
				UpdateForm.this.setLocation(x - xx , y - xy);
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 120, 1050, 650);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(59,89,152));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setBounds(250, 95, 795, 550);
		panel.setBackground(new Color(223,227,238));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tag ID");
		lblNewLabel.setForeground(new Color(59,89,152));
		lblNewLabel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		lblNewLabel.setBounds(40, 40, 46, 30);
		panel.add(lblNewLabel);

		txttid = new JTextField();
		txttid.setEditable(false);
		txttid.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		txttid.setBounds(129, 40, 339, 30);
		txttid.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		panel.add(txttid);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(new Color(59,89,152));
		lblName.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		lblName.setBounds(40, 152, 46, 14);
		panel.add(lblName);
		
		txtname = new JTextField();
		txtname.setBounds(129, 140, 339, 30);
		txtname.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		panel.add(txtname);
		
		JLabel lblBib = new JLabel("BIB");
		lblBib.setBounds(40, 90, 72, 30);
		lblBib.setForeground(new Color(59, 89, 152));
		lblBib.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		panel.add(lblBib);
		
		txtbib = new JTextField();
		txtbib.setBounds(129, 90, 339, 30);
		txtbib.setColumns(10);
		txtbib.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		txtbib.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtbib.setColumns(10);
		panel.add(txtbib);
		txtbib.setColumns(10);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setForeground(new Color(59,89,152));
		lblAge.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		lblAge.setBounds(40, 187, 46, 30);
		panel.add(lblAge);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setForeground(new Color(59,89,152));
		lblGender.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		lblGender.setBounds(40, 231, 72, 30);
		panel.add(lblGender);
		
		JRadioButton rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnMale.setSelected(true);
		rdbtnMale.setBounds(129, 231, 72, 30);
		rdbtnMale.setForeground(new Color(59,89,152));
		rdbtnMale.setBackground(new Color(223,227,238));
		rdbtnMale.setSelected(true);
		buttonGroup.add(rdbtnMale);
		panel.add(rdbtnMale);
		
		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnFemale.setBounds(210, 231, 79, 30);
		rdbtnFemale.setForeground(new Color(59,89,152));
		rdbtnFemale.setBackground(new Color(223,227,238));
		buttonGroup.add(rdbtnFemale);
		panel.add(rdbtnFemale);
		
		JRadioButton rdbtnOther = new JRadioButton("Other");
		rdbtnOther.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnOther.setBounds(291, 231, 79, 30);
		rdbtnOther.setForeground(new Color(59,89,152));
		rdbtnOther.setBackground(new Color(223,227,238));
		buttonGroup.add(rdbtnOther);
		panel.add(rdbtnOther);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(new Color(255,255,255));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1","2","3","4","5","6","7","8","9","10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56","57","58","59","60","61","62","63","64","65","66","67","68","69","70","71","72","73","74","75","75","76","77","78","79","80","81","82","83","84","85","86","87","88","89","90","91","92","93","94","95","96","97","98","99","100"}));
		comboBox.setMaximumRowCount(10);
		comboBox.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		comboBox.setBounds(129, 190, 339, 30);
		panel.add(comboBox);
		
		
		JLabel lbldistance = new JLabel("Distance");
		lbldistance.setForeground(new Color(59,89,152));
		lbldistance.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		lbldistance.setBounds(40, 282, 72, 30);
		panel.add(lbldistance);
		
		JLabel lblcno = new JLabel("Contact No.");
		lblcno.setForeground(new Color(59,89,152));
		lblcno.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		lblcno.setBounds(40, 333, 88, 30);
		panel.add(lblcno);
		
		txtdistance = new JTextField();
		txtdistance.setBounds(129, 283, 339, 30);
		txtdistance.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		txtdistance.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		panel.add(txtdistance);
		txtdistance.setColumns(10);
		
		txtcno = new JTextField();
		txtcno.setColumns(10);
		txtcno.setBounds(129, 333, 339, 30);
		txtcno.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		txtcno.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		panel.add(txtcno);
		
		JComboBox cmbColor = new JComboBox();
		cmbColor.setBounds(129, 430, 339, 30);
		cmbColor.setBackground(new Color(255,255,255));
		cmbColor.setModel(new DefaultComboBoxModel(new String[] {"RED", "GREEN", "BLUE", "YELLOW", "PINK", "BLACK"}));
		cmbColor.setMaximumRowCount(50);
		cmbColor.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		panel.add(cmbColor);
		
		JLabel lblcategory = new JLabel("Category");
		lblcategory.setForeground(new Color(59,89,152));
		lblcategory.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		lblcategory.setBounds(40, 379, 72, 30);
		panel.add(lblcategory);
		
		JLabel lblColor = new JLabel("Color");
		lblColor.setForeground(new Color(59, 89, 152));
		lblColor.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		lblColor.setBounds(40, 430, 72, 30);
		panel.add(lblColor);
		
		txtCategory = new JTextField();
		txtCategory.setColumns(10);
		txtCategory.setBounds(129, 384, 339, 30);
		txtCategory.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		txtCategory.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtCategory.setColumns(10);
		panel.add(txtCategory);
		
		
		JPanel pnlScanTag = new JPanel();
		pnlScanTag.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		pnlScanTag.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//if(CLReader.CheckConnect(SwitchPanel.txtConnId.getText())) {
				try {
					status = CLReader.CheckConnect(SwitchPanel.txtConnId.getText());
					if(status) {
						SampleCode2.mainSample("update");
						Connection con = null;
				    	try{		    		
				    		Class.forName("org.sqlite.JDBC");
							con = DriverManager.getConnection("jdbc:sqlite:marathon.db");
				    		PreparedStatement pstmt=con.prepareStatement("select tid tbltagdata where tid=?");
				    		
							pstmt.setString(1,txttid.getText());
							ResultSet res=pstmt.executeQuery();
							if(!res.next()){
								JOptionPane.showMessageDialog(null, "Record not exist with given Tag Id"  , "Record Not Exist", JOptionPane.INFORMATION_MESSAGE);
								txttid.setText("");
								txtname.setText("");
								txtdistance.setText("");
								txtcno.setText("");
								txtCategory.setText("");
								txtbib.setText("");
							}else {
								pstmt=con.prepareStatement("select athleteName, gender, age, distance, contact, category, color, bib from tbltagdata where tid=?");
								pstmt.setString(1,txttid.getText());
							    res=pstmt.executeQuery();
									
								UpdateForm.txtname.setText(res.getString(1));
								String gen = res.getString(2);
								comboBox.setSelectedItem(res.getString(3));					
								UpdateForm.txtdistance.setText(res.getString(4));
								UpdateForm.txtcno.setText(res.getString(5));
								UpdateForm.txtCategory.setText(res.getString(6));
								cmbColor.setSelectedItem(res.getString(7));
								UpdateForm.txtbib.setText(res.getString(8));
								
								if(gen.equals("Male"))
									rdbtnMale.setSelected(true);
								else if(gen.equals("Female"))
									rdbtnFemale.setSelected(true);
								else
									rdbtnOther.setSelected(true);
								found=true;
							}
				    	}
				    	catch(Exception e2){
							//e2.printStackTrace();
						}
						finally {
							try {
								con.close();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								//e1.printStackTrace();
							}
						}
			    	}
					else {
						JOptionPane.showMessageDialog(null, "Connection Failure! please start again" , "Error", JOptionPane.INFORMATION_MESSAGE);
					}
					
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				pnlScanTag.setBackground(new Color(84,55,152));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pnlScanTag.setBackground(new Color(59,89,152));
			}
		});
		pnlScanTag.setBounds(490, 40, 100, 30);
		panel.add(pnlScanTag);
		pnlScanTag.setLayout(null);
		pnlScanTag.setBackground(new Color(59,89,152));
		pnlScanTag.setForeground(Color.WHITE);
		
		JLabel lblScan = new JLabel("Scan Tag");
		lblScan.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		lblScan.setForeground(Color.WHITE);
		lblScan.setIcon(new ImageIcon("./images/scanIco.png"));
		lblScan.setHorizontalAlignment(SwingConstants.CENTER);
		lblScan.setBounds(0, 0, 100, 25);
		pnlScanTag.add(lblScan);
		
		JPanel pnlUpdate = new JPanel();
		pnlUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String athleteName = UpdateForm.txtname.getText();
				if(athleteName.equals(""))
					JOptionPane.showMessageDialog(null, "please enter athlete name"  , "Please enter name  ", JOptionPane.INFORMATION_MESSAGE);
				else if(txttid.getText().equals(""))
					JOptionPane.showMessageDialog(null, "please scan the tag id"  , "Tag Id not available" , JOptionPane.INFORMATION_MESSAGE);
				else {
					Connection con = null;
			        PreparedStatement pstmt = null;
		    		try{
		    			String tid = txttid.getText();
		    			athleteName = txtname.getText(); 
		    			Class.forName("org.sqlite.JDBC");
						con = DriverManager.getConnection("jdbc:sqlite:marathon.db");
			    		if(found) {
							//txtname.setText(athleteName);							
							int result2 = JOptionPane.showConfirmDialog(null, "Are you sure you want to update data ?"  , "Record Update", JOptionPane.INFORMATION_MESSAGE);
							if(result2 == JOptionPane.YES_OPTION){
								String gender = "";
								if(rdbtnMale.isSelected())
									gender = "Male";
								else if(rdbtnFemale.isSelected())
									gender = "Female";
								else
									gender = "Other";
								pstmt=con.prepareStatement("update TblTagData set athleteName=?, age=?, gender=?, distance=?, category=?, contact=?, color=?, bib=?  where tid=?");
								pstmt.setString(9, tid);
								pstmt.setString(1, txtname.getText());
								pstmt.setString(2, comboBox.getSelectedItem().toString());
								pstmt.setString(3, gender);
								pstmt.setString(4, txtdistance.getText());
								pstmt.setString(5, txtCategory.getText());
								pstmt.setString(6, txtcno.getText());
								pstmt.setString(7, cmbColor.getSelectedItem().toString());
								pstmt.setString(8, txtbib.getText());
								int cnt=pstmt.executeUpdate();
								if(cnt>0) {
									JOptionPane.showMessageDialog(null, "Update Successfull!"  , "update success ", JOptionPane.INFORMATION_MESSAGE);
									txttid.setText("");
									txtname.setText("");
									txtdistance.setText("");
									txtcno.setText("");
									txtCategory.setText("");
									txtbib.setText("");
									AdminHome.showData();
								}else {
									JOptionPane.showMessageDialog(null, "Error in update, Please retry!"  , "", JOptionPane.INFORMATION_MESSAGE);
								}
							}
							else {
								txttid.setText("");
								txtname.setText("");
								txtdistance.setText("");
								txtcno.setText("");
								txtCategory.setText("");
								txtbib.setText("");
							}
						}
						else { // When no record is found from the database.
							JOptionPane.showMessageDialog(null, "No data exist under this tag id!"  , "", JOptionPane.INFORMATION_MESSAGE);
						}
			    	}
					catch(Exception e2){
						JOptionPane.showMessageDialog(null, e2.getMessage() , "", JOptionPane.INFORMATION_MESSAGE);
						//e2.printStackTrace();
					}
					finally {
						try {
							con.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							//e1.printStackTrace();
						}
					}
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				pnlUpdate.setBackground(new Color(84,55,152));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pnlUpdate.setBackground(new Color(59,89,152));
			}
		});
		pnlUpdate.setBackground(new Color(59,89,152));
		pnlUpdate.setBounds(129, 490, 164, 30);
		panel.add(pnlUpdate);
		pnlUpdate.setLayout(null);
		pnlUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		JLabel lblUpdateRecord = new JLabel("Update Record");
		lblUpdateRecord.setForeground(Color.WHITE);
		lblUpdateRecord.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		lblUpdateRecord.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdateRecord.setBounds(10, 0, 144, 25);
		pnlUpdate.add(lblUpdateRecord);
		
		JPanel pnlCancel = new JPanel();
		pnlCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txttid.setText("");
				txtname.setText("");
				txtdistance.setText("");
				txtcno.setText("");
				txtCategory.setText("");
				txtbib.setText("");
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				pnlCancel.setBackground(new Color(84,55,152));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pnlCancel.setBackground(new Color(59,89,152));
			}
		});
		pnlCancel.setLayout(null);
		pnlCancel.setBackground(new Color(59, 89, 152));
		pnlCancel.setBounds(305, 490, 164, 30);
		panel.add(pnlCancel);
		
		pnlCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		JLabel lblCancel = new JLabel("Cancel");
		lblCancel.setHorizontalAlignment(SwingConstants.CENTER);
		lblCancel.setForeground(Color.WHITE);
		lblCancel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		lblCancel.setBounds(10, 0, 144, 25);
		pnlCancel.add(lblCancel);
		
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
				Login.switchPanelFrame.setVisible(true);
				dispose();
				
			}
		});
		lblCl.setIcon(new ImageIcon("./images/closeIco.png"));
		lblCl.setForeground(Color.MAGENTA);
		lblCl.setBounds(1021, 2, 25, 25);
		contentPane.add(lblCl);
		
		JLabel lblupdatepage = new JLabel("Update Athelete Information");
		lblupdatepage.setIcon(null);
		lblupdatepage.setFont(new Font("Yu Gothic UI", Font.PLAIN, 25));
		lblupdatepage.setBounds(410, 11, 427, 61);
		lblupdatepage.setForeground(new Color(247,247,247));
		contentPane.add(lblupdatepage);		
		
		JPanel pnlDisp = new JPanel();
		pnlDisp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AdminHome adminHome = new AdminHome();
				adminHome.setUndecorated(true);
				adminHome.setLocationRelativeTo(null);
				adminHome.setVisible(true);
				dispose();
			}
		});
		pnlDisp.setBounds(5, 95, 240, 50);
		pnlDisp.setLayout(null);
		JLabel lblDisp = new JLabel("Display");
		lblDisp.setIcon(new ImageIcon("./images/showIco.png"));
		lblDisp.setBounds(88, 11, 87, 22);
		pnlDisp.add(lblDisp);
		lblDisp.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		lblDisp.setForeground(new Color(59,89,152));
		pnlDisp.setBackground(new Color(139,157,195));
		contentPane.add(pnlDisp);
		
		JPanel pnlIns = new JPanel();
		pnlIns.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				InsertForm insertForm = new InsertForm();
				insertForm.setUndecorated(true);
				insertForm.setLocationRelativeTo(null);
				insertForm.setVisible(true);
	    		dispose();
			}
		});
		pnlIns.setBounds(5, 150, 240, 50);
		pnlIns.setLayout(null);
		JLabel lblIns = new JLabel("Insert");
		lblIns.setIcon(new ImageIcon("./images/inIco.png"));
		lblIns.setBounds(88, 11, 87, 22);
		pnlIns.add(lblIns);
		lblIns.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		lblIns.setForeground(new Color(59,89,152));
		pnlIns.setBackground(new Color(139,157,195));
		contentPane.add(pnlIns);
		
		JPanel pnlUpd = new JPanel();
		pnlUpd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		pnlUpd.setBounds(5, 205, 245, 50);
		pnlUpd.setLayout(null);
		JLabel lblUpd = new JLabel("Update");
		lblUpd.setIcon(new ImageIcon("./images/upIco.png"));
		lblUpd.setBounds(88, 11, 87, 22);
		pnlUpd.add(lblUpd);
		lblUpd.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		lblUpd.setForeground(new Color(59,89,152));
		pnlUpd.setBackground(new Color(223,227,238));
		contentPane.add(pnlUpd);
		
		JPanel pnlDel = new JPanel();
		pnlDel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DeleteForm deleteForm = new DeleteForm();
				deleteForm.setUndecorated(true);
				deleteForm.setLocationRelativeTo(null);
				deleteForm.setVisible(true);
	    		dispose();
			}
		});
		pnlDel.setBounds(5, 260, 240, 50);
		pnlDel.setLayout(null);
		JLabel lblDel = new JLabel("Delete");
		lblDel.setIcon(new ImageIcon("./images/delIco.png"));
		lblDel.setBounds(88, 11, 87, 22);
		pnlDel.add(lblDel);
		lblDel.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		lblDel.setForeground(new Color(59,89,152));
		pnlDel.setBackground(new Color(139,157,195));
		contentPane.add(pnlDel);
		pnlDisp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		pnlIns.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		pnlUpd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		pnlDel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
	}
}