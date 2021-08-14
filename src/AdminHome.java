import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import net.proteanit.sql.DbUtils;
import javax.swing.JTabbedPane;

public class AdminHome extends JFrame {
	private JPanel contentPane;
	private static JTable table;
	public static AdminHome AdminFrame;
	int xx,xy;
	
	public static void showData() {
		Connection con = null;
    	Statement stmt;
    	//ResultSet rs;
    	try{
    		Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:marathon.db");
    		PreparedStatement pstmt=con.prepareStatement("select tid, bib, athleteName, age, gender, distance, contact, category, color from tbltagdata");
			ResultSet res=pstmt.executeQuery();
			boolean found=false;
			table.setModel(DbUtils.resultSetToTableModel(res));
			con.close();
    	}
		catch(Exception e){
			//e.printStackTrace();
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
	/**
	 * Create the frame.
	 */
	public AdminHome() {
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
				AdminHome.this.setLocation(x - xx , y - xy);
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
		panel.setLayout(new MigLayout("", "[grow]", "[][grow]"));
		panel.setBackground(new Color(223,227,238));
		panel.setBounds(250, 95, 795, 550);
		contentPane.add(panel);
			
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, "cell 0 1,grow");
		
		table = new javax.swing.JTable() {
       	 public boolean isCellEditable(int row, int column) {                
                return false;               
        }
       };
		table.setFont(new java.awt.Font("Yu Gothic UI", Font.PLAIN, 14)); 
		table.setGridColor(new java.awt.Color(230, 255, 255));
		table.setIntercellSpacing(new java.awt.Dimension(5, 5));
		table.setRowHeight(25);
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
		
		showData();
		
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
		
		JLabel lbladminpage = new JLabel("Athletes Information");
		lbladminpage.setFont(new Font("Yu Gothic UI", Font.PLAIN, 25));
		lbladminpage.setBounds(410, 31, 277, 34);
		lbladminpage.setForeground(new Color(247,247,247));
		contentPane.add(lbladminpage);
		
		
		JPanel pnlDisp = new JPanel();
		pnlDisp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		pnlDisp.setBounds(5, 95, 245, 50);
		pnlDisp.setLayout(null);
		JLabel lblDisp = new JLabel("Display");
		lblDisp.setIcon(new ImageIcon("./images/showIco.png"));
		lblDisp.setBounds(88, 11, 87, 22);
		pnlDisp.add(lblDisp);
		lblDisp.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		lblDisp.setForeground(new Color(59,89,152));
		pnlDisp.setBackground(new Color(223,227,238));
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
				UpdateForm updateForm = new UpdateForm();
				updateForm.setUndecorated(true);
				updateForm.setLocationRelativeTo(null);
				updateForm.setVisible(true);
	    		dispose();
			}
		});
		pnlUpd.setBounds(5, 205, 240, 50);
		pnlUpd.setLayout(null);
		JLabel lblUpd = new JLabel("Update");
		lblUpd.setIcon(new ImageIcon("./images/upIco.png"));
		lblUpd.setBounds(88, 11, 87, 22);
		pnlUpd.add(lblUpd);
		lblUpd.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		lblUpd.setForeground(new Color(59,89,152));
		pnlUpd.setBackground(new Color(139,157,195));
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
		
	
		
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }
                table.setDefaultRenderer(Object.class, new MonCellRenderer());
            }
	    });		 	
	}
	 public class MonCellRenderer extends DefaultTableCellRenderer {

	        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	            	if(value.equals("RED"))
	            		setBackground(Color.RED);
	            	else if(value.equals("GREEN"))
	            		setBackground(Color.GREEN);
	            	else if(value.equals("BLUE"))
	            		setBackground(Color.BLUE);
	            	else if(value.equals("YELLOW"))
	            		setBackground(Color.YELLOW);
	            	else if(value.equals("PINK"))
	            		setBackground(Color.PINK);
	            	else if(value.equals("BLACK"))
	            		setBackground(Color.BLACK);
	            	else 
	            		setBackground(Color.WHITE);
	            return this;
	        }
	       }
}
