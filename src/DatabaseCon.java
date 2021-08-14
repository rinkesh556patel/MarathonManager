import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseCon extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DatabaseCon frame = new DatabaseCon();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DatabaseCon() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Connection con;
		    	try{		    		
		    		Class.forName("org.sqlite.JDBC");
					con = DriverManager.getConnection("jdbc:sqlite:marathon.db");
		    		//PreparedStatement pstmt=con.prepareStatement("Create table activator(akey varchar2(100),macAdd varchar2(50),status varchar2(5))");
		    		PreparedStatement pstmt=con.prepareStatement("drop table activator");
		    		
		    		int res=pstmt.executeUpdate();
					if(res>0){
						JOptionPane.showMessageDialog(null, "done" , "created", JOptionPane.INFORMATION_MESSAGE);
						
					}else {
						JOptionPane.showMessageDialog(null, "failed" , "not created", JOptionPane.INFORMATION_MESSAGE);
						
					}
					con.close();
		    	}
		    	catch(Exception e2){
		    		JOptionPane.showMessageDialog(null, "Not found" , "error", JOptionPane.INFORMATION_MESSAGE);
					e2.printStackTrace();
				}
				/*Connection con;
		    	try{
		    		Class.forName("oracle.jdbc.driver.OracleDriver");
		    		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","admin");
		    		Statement stmnt = con.createStatement();
		    		//String qr = "Create table tblrace2(tid varchar2(50),timestamp varchar2(50),time varchar2(20), lap varchar2(5))";
		    		String qr = "Create table activator(key varchar2(100),macAdd varchar2(50),status varchar2(5))";
		    		stmnt.executeUpdate(qr);
		    		String qr2 = "Create table tbltagdata2(tid varchar2(50),athletename varchar2(50),distance varchar2(15),contact varchar2(15),gender varchar2(10),age varchar2(5),category varchar2(20),color varchar2(10))";
		    		stmnt.executeUpdate(qr2);
		    		JOptionPane.showMessageDialog(null, "Table created"  , "Already exist ", JOptionPane.INFORMATION_MESSAGE);
		    		Login lg = new Login();
		    		lg.setVisible(true);
		    		dispose();
		    	}
		    	catch(Exception e2) {
		    		JOptionPane.showMessageDialog(null, "Error"  , "Already exist ", JOptionPane.INFORMATION_MESSAGE);
		    		e2.printStackTrace();
		    	}*/
			}
		});
		btnConnect.setFont(new Font("Tahoma", Font.PLAIN, 37));
		contentPane.add(btnConnect, BorderLayout.CENTER);
	}

}
