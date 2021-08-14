import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;

import com.clou.uhf.G3Lib.CLReader;
import com.clou.uhf.G3Lib.Tag6C;
import com.clou.uhf.G3Lib.Enumeration.eAntennaNo;
import com.clou.uhf.G3Lib.Enumeration.eReadType;
import com.jamierf.rxtx.RXTXLoader;

import jxl.Sheet;
import jxl.Workbook;
import net.proteanit.sql.DbUtils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.awt.image.RescaleOp;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.AbstractButton;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableCellRenderer;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.MouseAdapter;

/**
 *
 * @author Rogue Gamer
 */

public class RaceHome extends javax.swing.JFrame {

    /**
     * Creates new form RaceHome
     */
	int mousePx;
    int mousePy;
    int xx,xy;
	public static String tid = "";
	public static String selectedLap = "1";
	public static TImeFrame timeFrame;
	
	
    public RaceHome() { 
    	addWindowStateListener(new WindowStateListener() {

			@Override
			public void windowStateChanged(WindowEvent e) {
				if((e.getNewState() & SwitchPanel.raceHome.ICONIFIED)== SwitchPanel.raceHome.ICONIFIED ) {
					setState(timeFrame.ICONIFIED);
				}
				else if((e.getNewState() & SwitchPanel.raceHome.MAXIMIZED_BOTH)== SwitchPanel.raceHome.MAXIMIZED_BOTH ) {
					timeFrame.setVisible(true);
				}
			}
			
    		
    	});
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
				RaceHome.this.setLocation(x - xx , y - xy);
			}
			@Override
			public void mouseMoved(MouseEvent e) {
				timeFrame.setVisible(true);
			}
		});
        initComponents();
        showData("1");
        changeSelected("1");
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }
                jTable1.setDefaultRenderer(Object.class, new MonCellRenderer());
            }
        });
    }
    public static void showData(String lap) {
		Connection con = null;
    	Statement stmt;
		try{
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:marathon.db");
    		PreparedStatement pstmt=con.prepareStatement("select rc.tid,at.bib, at.athletename,rc.timestamp,rc.Time,at.age,at.gender,at.category,at.color from tblrace rc, tbltagdata at where rc.tid = at.tid and lap = ? order by timestamp");
    		pstmt.setString(1, lap);
			ResultSet res=pstmt.executeQuery();
			boolean found=false;
			jTable1.setModel(DbUtils.resultSetToTableModel(res));
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

    
    public static void changeSelected(String lap) {
    	pl1.setBackground(new Color(223, 227, 238));
    	pl2.setBackground(new Color(223, 227, 238));
    	pl3.setBackground(new Color(223, 227, 238));
    	pl4.setBackground(new Color(223, 227, 238));
    	pl5.setBackground(new Color(223, 227, 238));
    	pl6.setBackground(new Color(223, 227, 238));
    	pl7.setBackground(new Color(223, 227, 238));
    	pl8.setBackground(new Color(223, 227, 238));
    	pl9.setBackground(new Color(223, 227, 238));
    	pl10.setBackground(new Color(223, 227, 238));
    	pl11.setBackground(new Color(223, 227, 238));
    	pl12.setBackground(new Color(223, 227, 238));
    	pl13.setBackground(new Color(223, 227, 238));
    	pl14.setBackground(new Color(223, 227, 238));
    	pl15.setBackground(new Color(223, 227, 238));
    	pl16.setBackground(new Color(223, 227, 238));
    	pl17.setBackground(new Color(223, 227, 238));
    	pl18.setBackground(new Color(223, 227, 238));
    	pl19.setBackground(new Color(223, 227, 238));
    	pl20.setBackground(new Color(223, 227, 238));
    	
    	
    	ll1.setForeground(new Color(54, 33, 89));
    	ll2.setForeground(new Color(54, 33, 89));
    	ll3.setForeground(new Color(54, 33, 89));
    	ll4.setForeground(new Color(54, 33, 89));
    	ll5.setForeground(new Color(54, 33, 89));
    	ll6.setForeground(new Color(54, 33, 89));
    	ll7.setForeground(new Color(54, 33, 89));
    	ll8.setForeground(new Color(54, 33, 89));
    	ll9.setForeground(new Color(54, 33, 89));
    	ll10.setForeground(new Color(54, 33, 89));
    	ll11.setForeground(new Color(54, 33, 89));
    	ll12.setForeground(new Color(54, 33, 89));
    	ll13.setForeground(new Color(54, 33, 89));
    	ll14.setForeground(new Color(54, 33, 89));
    	ll15.setForeground(new Color(54, 33, 89));
    	ll16.setForeground(new Color(54, 33, 89));
    	ll17.setForeground(new Color(54, 33, 89));
    	ll18.setForeground(new Color(54, 33, 89));
    	ll19.setForeground(new Color(54, 33, 89));
    	ll20.setForeground(new Color(54, 33, 89));
    	
    	
    	if(lap.equals("1")) {
    		ll1.setForeground(Color.white);
        	pl1.setBackground(new Color(59, 89, 152));
        	selectedLap = "1";
    	}
    	else if(lap.equals("2")) {
    		ll2.setForeground(Color.white);
        	pl2.setBackground(new Color(59, 89, 152));
        	selectedLap = "2";
    	}else if(lap.equals("3")) {
    		ll3.setForeground(Color.white);
        	pl3.setBackground(new Color(59, 89, 152));
        	selectedLap = "3";
    	}else if(lap.equals("4")) {
    		ll4.setForeground(Color.white);
        	pl4.setBackground(new Color(59, 89, 152));
        	selectedLap = "4";
    	}else if(lap.equals("5")) {
    		ll5.setForeground(Color.white);
        	pl5.setBackground(new Color(59, 89, 152));
        	selectedLap = "5";
    	}else if(lap.equals("6")) {
    		ll6.setForeground(Color.white);
        	pl6.setBackground(new Color(59, 89, 152));
        	selectedLap = "6";
    	}else if(lap.equals("7")) {
    		ll7.setForeground(Color.white);
        	pl7.setBackground(new Color(59, 89, 152));
        	selectedLap = "7";
    	}else if(lap.equals("8")) {
    		ll8.setForeground(Color.white);
        	pl8.setBackground(new Color(59, 89, 152));
        	selectedLap = "8";
    	}else if(lap.equals("9")) {
    		ll9.setForeground(Color.white);
        	pl9.setBackground(new Color(59, 89, 152));
        	selectedLap = "9";
    	}else if(lap.equals("10")) {
    		ll10.setForeground(Color.white);
        	pl10.setBackground(new Color(59, 89, 152));
        	selectedLap = "10";
    	}else if(lap.equals("11")) {
    		ll11.setForeground(Color.white);
        	pl11.setBackground(new Color(59, 89, 152));
        	selectedLap = "11";
    	}
    	else if(lap.equals("12")) {
    		ll12.setForeground(Color.white);
        	pl12.setBackground(new Color(59, 89, 152));
        	selectedLap = "12";
    	}
    	else if(lap.equals("13")) {
    		ll13.setForeground(Color.white);
        	pl13.setBackground(new Color(59, 89, 152));
        	selectedLap = "13";
    	}
    	else if(lap.equals("14")) {
    		ll14.setForeground(Color.white);
        	pl14.setBackground(new Color(59, 89, 152));
        	selectedLap = "14";
    	}
    	else if(lap.equals("15")) {
    		ll15.setForeground(Color.white);
        	pl15.setBackground(new Color(59, 89, 152));
        	selectedLap = "15";
    	}
    	else if(lap.equals("16")) {
    		ll16.setForeground(Color.white);
        	pl16.setBackground(new Color(59, 89, 152));
        	selectedLap = "16";
    	}
    	else if(lap.equals("17")) {
    		ll17.setForeground(Color.white);
        	pl17.setBackground(new Color(59, 89, 152));
        	selectedLap = "17";
    	}
    	else if(lap.equals("18")) {
    		ll18.setForeground(Color.white);
        	pl18.setBackground(new Color(59, 89, 152));
        	selectedLap = "18";
    	}
    	else if(lap.equals("19")) {
    		ll19.setForeground(Color.white);
        	pl19.setBackground(new Color(59, 89, 152));
        	selectedLap = "19";
    	}
    	else if(lap.equals("20")) {
    		ll20.setForeground(Color.white);
        	pl20.setBackground(new Color(59, 89, 152));
        	selectedLap = "20";
    	}
    	
    }
    
    
    public static void writeToFile(JTable table, File file,String type) throws Exception {
    	//JOptionPane.showMessageDialog(null, "Type: "+type  , "Record Not Exist", JOptionPane.INFORMATION_MESSAGE);
    	int ctr = 1;
    	FileWriter out = new FileWriter(file);
    	BufferedWriter bw = new BufferedWriter(out);
    	for(int x=1;x<=20;x++) {
    		showData(ctr + "");
    		bw.write("Lap: "+ctr+"\n");
    		ctr++;
    		TableModel model = table.getModel();
        	
        	
        	
        	for(int i=0;i<model.getColumnCount();i++) {
        		if(type.equals("excel"))
        			bw.write(model.getColumnName(i)+"\t");
        		else
        			bw.write(model.getColumnName(i)+",");
        	}
        	bw.write("\n");
        	
        	for(int i=0;i<model.getRowCount();i++) {
        		for(int j=0;j<model.getColumnCount();j++) {
        			if(type.equals("excel"))
        				bw.write(model.getValueAt(i,j).toString()+"\t");
        			else {
        				bw.write(model.getValueAt(i,j).toString()+",");
        			}
        				
        		}
        		bw.write("\n");	
        	}
    		
        	
        	bw.write("\n\n\n\n\n\n\n\n\n");
    	}
    	
    	bw.close();
    	showData(selectedLap);
    	//System.out.println("_____Done Writing to file: "+file );
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

    	jPanelAll = new javax.swing.JPanel();
        jPanelLeftPane = new javax.swing.JPanel();
        jPaneHeader = new javax.swing.JPanel();
        jPaneHeader.addMouseMotionListener(new MouseMotionAdapter() {
        	@Override
        	public void mouseDragged(MouseEvent e) {
        		//this.setX(e.getXOnScreen()-mousePx);
        		//Stage s = (Stage) ((Node)e.getSource()).getScene().getWindow();
        	}
        });
        lblCl = new javax.swing.JLabel();
        lblCl.setIcon(new ImageIcon("./images/closeIco2.png"));
        
        
        jLabel4 = new javax.swing.JLabel();
        jPanelLeftHeader = new javax.swing.JPanel();
        jPanelRight = new javax.swing.JPanel();
        jPanelRight.addMouseMotionListener(new MouseMotionAdapter() {
        	@Override
        	public void mouseMoved(MouseEvent e) {
        		timeFrame.setVisible(true);
        	}
        });
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane1.addMouseMotionListener(new MouseMotionAdapter() {
        	@Override
        	public void mouseMoved(MouseEvent e) {
        		timeFrame.setVisible(true);
        	}
        });
        jTable1 = new javax.swing.JTable() {
        	 public boolean isCellEditable(int row, int column) {                
                 return false;               
         }
        };
        jTable1.addMouseMotionListener(new MouseMotionAdapter() {
        	@Override
        	public void mouseMoved(MouseEvent arg0) {
        		timeFrame.setVisible(true);
        	}
        });
        jTable1.setOpaque(false);        
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1024, 720));
        setResizable(false);
        
        
        
        jPanelAll.setBackground(new Color(59,89,152));
        jPanelLeftPane.setBackground(new java.awt.Color(139,157,195));
       
        
        jPanelLeftPane.setBackground(new java.awt.Color(139,157,195));
        
        pl9 = new JPanel();
        pl9.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		showData("9");
        		changeSelected("9");
        		timeFrame.setVisible(true);
        	}
        });
        pl9.setLayout(null);
        pl9.setBackground(new Color(223, 227, 238));
        
        ll9 = new JLabel("Lap 9");
        ll9.setHorizontalAlignment(SwingConstants.CENTER);
        ll9.setForeground(new Color(54, 33, 89));
        ll9.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
        ll9.setBounds(10, 0, 96, 33);
        pl9.add(ll9);
        
        pl10 = new JPanel();
        pl10.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		showData("10");
        		changeSelected("10");
        		timeFrame.setVisible(true);
        	}
        });
        pl10.setLayout(null);
        pl10.setBackground(new Color(223, 227, 238));
        
        ll10 = new JLabel("Lap 10");
        ll10.setHorizontalAlignment(SwingConstants.CENTER);
        ll10.setForeground(new Color(54, 33, 89));
        ll10.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
        ll10.setBounds(10, 0, 96, 33);
        pl10.add(ll10);
        
        pl11 = new JPanel();
        pl11.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		showData("11");
        		changeSelected("11");
        		timeFrame.setVisible(true);
        	}
        });
        pl11.setLayout(null);
        pl11.setBackground(new Color(223, 227, 238));
        
        ll11 = new JLabel("Lap 11");
        ll11.setHorizontalAlignment(SwingConstants.CENTER);
        ll11.setForeground(new Color(54, 33, 89));
        ll11.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
        ll11.setBounds(10, 0, 96, 33);
        pl11.add(ll11);
        
        pl12 = new JPanel();
        pl12.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		showData("12");
        		changeSelected("12");
        		timeFrame.setVisible(true);
        	}
        });
        pl12.setLayout(null);
        pl12.setBackground(new Color(223, 227, 238));
        
        ll12 = new JLabel("Lap 12");
        ll12.setHorizontalAlignment(SwingConstants.CENTER);
        ll12.setForeground(new Color(54, 33, 89));
        ll12.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
        ll12.setBounds(10, 0, 96, 33);
        pl12.add(ll12);
        
        pl13 = new JPanel();
        pl13.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		showData("13");
        		changeSelected("13");
        		timeFrame.setVisible(true);
        	}
        });
        pl13.setLayout(null);
        pl13.setBackground(new Color(223, 227, 238));
        
        ll13 = new JLabel("Lap 13");
        ll13.setHorizontalAlignment(SwingConstants.CENTER);
        ll13.setForeground(new Color(54, 33, 89));
        ll13.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
        ll13.setBounds(10, 0, 96, 33);
        pl13.add(ll13);
        
        pl14 = new JPanel();
        pl14.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		showData("14");
        		changeSelected("14");
        		timeFrame.setVisible(true);
        	}
        });
        pl14.setLayout(null);
        pl14.setBackground(new Color(223, 227, 238));
        
        ll14 = new JLabel("Lap 14");
        ll14.setHorizontalAlignment(SwingConstants.CENTER);
        ll14.setForeground(new Color(54, 33, 89));
        ll14.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
        ll14.setBounds(10, 0, 96, 33);
        pl14.add(ll14);
        
        pl15 = new JPanel();
        pl15.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		showData("15");
        		changeSelected("15");
        		timeFrame.setVisible(true);
        	}
        });
        pl15.setLayout(null);
        pl15.setBackground(new Color(223, 227, 238));
        
        ll15 = new JLabel("Lap 15");
        ll15.setHorizontalAlignment(SwingConstants.CENTER);
        ll15.setForeground(new Color(54, 33, 89));
        ll15.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
        ll15.setBounds(10, 0, 96, 33);
        pl15.add(ll15);
        
        pl16 = new JPanel();
        pl16.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		showData("16");
        		changeSelected("16");
        		timeFrame.setVisible(true);
        	}
        });
        pl16.setLayout(null);
        pl16.setBackground(new Color(223, 227, 238));
        
        ll16 = new JLabel("Lap 16");
        ll16.setHorizontalAlignment(SwingConstants.CENTER);
        ll16.setForeground(new Color(54, 33, 89));
        ll16.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
        ll16.setBounds(10, 0, 96, 33);
        pl16.add(ll16);
        
        pl17 = new JPanel();
        pl17.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		showData("17");
        		changeSelected("17");
        		timeFrame.setVisible(true);
        	}
        });
        pl17.setLayout(null);
        pl17.setBackground(new Color(223, 227, 238));
        
        ll17 = new JLabel("Lap 17");
        ll17.setHorizontalAlignment(SwingConstants.CENTER);
        ll17.setForeground(new Color(54, 33, 89));
        ll17.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
        ll17.setBounds(10, 0, 96, 33);
        pl17.add(ll17);
        
        pl18 = new JPanel();
        pl18.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		showData("18");
        		changeSelected("18");
        		timeFrame.setVisible(true);
        	}
        });
        pl18.setLayout(null);
        pl18.setBackground(new Color(223, 227, 238));
        
        ll18 = new JLabel("Lap 18");
        ll18.setHorizontalAlignment(SwingConstants.CENTER);
        ll18.setForeground(new Color(54, 33, 89));
        ll18.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
        ll18.setBounds(10, 0, 96, 33);
        pl18.add(ll18);
        
        pl7 = new JPanel();
        pl7.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		showData("7");
        		changeSelected("7");
        		timeFrame.setVisible(true);
        	}
        });
        pl7.setLayout(null);
        pl7.setBackground(new Color(223, 227, 238));
        
        ll7 = new JLabel("Lap 7");
        ll7.setHorizontalAlignment(SwingConstants.CENTER);
        ll7.setForeground(new Color(54, 33, 89));
        ll7.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
        ll7.setBounds(10, 0, 96, 33);
        pl7.add(ll7);
        
        pl8 = new JPanel();
        pl8.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		showData("8");
        		changeSelected("8");
        		timeFrame.setVisible(true);
        	}
        });
        pl8.setLayout(null);
        pl8.setBackground(new Color(223, 227, 238));
        
        ll8 = new JLabel("Lap 8");
        ll8.setHorizontalAlignment(SwingConstants.CENTER);
        ll8.setForeground(new Color(54, 33, 89));
        ll8.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
        ll8.setBounds(10, 0, 96, 33);
        pl8.add(ll8);
        
        pl5 = new JPanel();
        pl5.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		showData("5");
        		changeSelected("5");
        		timeFrame.setVisible(true);
        	}
        });
        pl5.setLayout(null);
        pl5.setBackground(new Color(223,227,238));
        
        ll5 = new JLabel("Lap 5");
        ll5.setHorizontalAlignment(SwingConstants.CENTER);
        ll5.setForeground(new Color(54,33,89));
        ll5.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
        ll5.setBounds(10, 0, 96, 33);
        pl5.add(ll5);
        
        pl6 = new JPanel();
        pl6.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		showData("6");
        		changeSelected("6");
        		timeFrame.setVisible(true);
        	}
        });
        pl6.setLayout(null);
        pl6.setBackground(new Color(223, 227, 238));
        
        ll6 = new JLabel("Lap 6");
        ll6.setHorizontalAlignment(SwingConstants.CENTER);
        ll6.setForeground(new Color(54, 33, 89));
        ll6.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
        ll6.setBounds(10, 0, 96, 33);
        pl6.add(ll6);
        
        pl3 = new JPanel();
        pl3.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		changeSelected("3");
        		showData("3");
        		timeFrame.setVisible(true);
        	}
        });
        
        pl3.setLayout(null);
        pl3.setBackground(new Color(223, 227, 238));
        
        ll3 = new JLabel("Lap 3");
        ll3.setHorizontalAlignment(SwingConstants.CENTER);
        ll3.setForeground(new Color(54, 33, 89));
        ll3.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
        ll3.setBounds(10, 0, 96, 33);
        pl3.add(ll3);
        
        pl4 = new JPanel();
        pl4.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		showData("4");
        		changeSelected("4");
        		timeFrame.setVisible(true);
        	}
        });
        pl4.setLayout(null);
        pl4.setBackground(new Color(223, 227, 238));
        
        ll4 = new JLabel("Lap 4");
        ll4.setHorizontalAlignment(SwingConstants.CENTER);
        ll4.setForeground(new Color(54, 33, 89));
        ll4.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
        ll4.setBounds(10, 0, 96, 33);
        pl4.add(ll4);
        
        pl1 = new JPanel();
        pl1.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		showData("1");
        		changeSelected("1");
        		timeFrame.setVisible(true);
        	}
        });
        pl1.setLayout(null);
        pl1.setBackground(new Color(223, 227, 238));
        
        ll1 = new JLabel("Lap 1");
        ll1.setHorizontalAlignment(SwingConstants.CENTER);
        ll1.setForeground(new Color(54, 33, 89));
        ll1.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
        ll1.setBounds(10, 0, 96, 33);
        pl1.add(ll1);
        
        pl2 = new JPanel();
        pl2.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		showData("2");
        		changeSelected("2");
        		timeFrame.setVisible(true);
        	}
        });
        pl2.setLayout(null);
        pl2.setBackground(new Color(223, 227, 238));
        
        ll2 = new JLabel("Lap 2");
        ll2.setHorizontalAlignment(SwingConstants.CENTER);
        ll2.setForeground(new Color(54, 33, 89));
        ll2.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
        ll2.setBounds(10, 0, 96, 33);
        pl2.add(ll2);
        
        pnlExc = new JPanel();
        pnlExc.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		try {
        			JFileChooser fs = new JFileChooser(new File("E:/"));
        			fs.setDialogTitle("Save File");
        			//fs.setFileFilter(new FileTypeFilter(".xls","Excel File"));
        			int result = fs.showSaveDialog(null);
        			if(result == JFileChooser.APPROVE_OPTION) {
        				File fi = fs.getSelectedFile();
        				writeToFile(jTable1,fi,"excel");
                		JOptionPane.showMessageDialog(null, "Data exported to excel successfully"  , "Record Not Exist", JOptionPane.INFORMATION_MESSAGE);
        			}					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Error in data export"  , "Record Not Exist", JOptionPane.INFORMATION_MESSAGE);
					//e1.printStackTrace();					
				}
        	}
        });
        pnlExc.setBackground(new Color(223, 227, 238));
        pnlExc.setLayout(null);
        
        label_21 = new JLabel("Export To Excel");
        label_21.setBounds(80, 8, 103, 22);
        label_21.setForeground(new Color(54, 33, 89));
        label_21.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
        pnlExc.add(label_21);
        
        pnlCSV = new JPanel();
        pnlCSV.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		try {
        			JFileChooser fs = new JFileChooser(new File("E:/"));
        			fs.setDialogTitle("Save File");
        			int result = fs.showSaveDialog(null);
        			if(result == JFileChooser.APPROVE_OPTION) {
        				File fi = fs.getSelectedFile();
        				writeToFile(jTable1,fi,"csv");
                		JOptionPane.showMessageDialog(null, "Data exported to csv file successfully"  , "Success", JOptionPane.INFORMATION_MESSAGE);
        			}					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Error in data export"  , "Error!", JOptionPane.INFORMATION_MESSAGE);
					//e1.printStackTrace();					
				}
        	}
        });
        pnlCSV.setBackground(new Color(223, 227, 238));
        pnlCSV.setLayout(null);
        
        label_22 = new JLabel("Export To CSV");
        label_22.setBounds(83, 7, 98, 22);
        label_22.setForeground(new Color(54, 33, 89));
        label_22.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
        pnlCSV.add(label_22);
        
        pl19 = new JPanel();
        pl19.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		showData("19");
        		changeSelected("19");
        		timeFrame.setVisible(true);
        	}
        });
        pl19.setLayout(null);
        pl19.setBackground(new Color(223, 227, 238));
        
        ll19 = new JLabel("Lap 19");
        ll19.setHorizontalAlignment(SwingConstants.CENTER);
        ll19.setForeground(new Color(54, 33, 89));
        ll19.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
        ll19.setBounds(10, 0, 96, 33);
        pl19.add(ll19);
        
        pl20 = new JPanel();
        pl20.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		showData("20");
        		changeSelected("20");
        		timeFrame.setVisible(true);
        	}
        });
        pl20.setLayout(null);
        pl20.setBackground(new Color(223, 227, 238));
        
        ll20 = new JLabel("Lap 20");
        ll20.setHorizontalAlignment(SwingConstants.CENTER);
        ll20.setForeground(new Color(54, 33, 89));
        ll20.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
        ll20.setBounds(10, 0, 96, 33);
        pl20.add(ll20);
        
        JPanel pnlDeleteRace = new JPanel();
        pnlDeleteRace.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		Connection con;
		    	try{
		    		Class.forName("org.sqlite.JDBC");
					con = DriverManager.getConnection("jdbc:sqlite:marathon.db");
		    		int result = JOptionPane.showConfirmDialog (null, "Are you sure you want to delete records ?" , "Warning", JOptionPane.INFORMATION_MESSAGE);
					if(result == JOptionPane.YES_OPTION){
						PreparedStatement pstmt=con.prepareStatement("delete from tblrace");
						int cnt=pstmt.executeUpdate();
						if(cnt>0) {
							JOptionPane.showMessageDialog(null, "Athlete data deleted!"  , "Deleted successfully ", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(null, "No records found!"  , "Error", JOptionPane.INFORMATION_MESSAGE);
						}
					}
		    	}
				catch(Exception e2){
					//e2.printStackTrace();
				}
        	}
        });
        pnlDeleteRace.setLayout(null);
        pnlDeleteRace.setBackground(new Color(223, 227, 238));
        
        JLabel lblDeleteRace = new JLabel("Delete All Race Records");
        lblDeleteRace.setForeground(new Color(54, 33, 89));
        lblDeleteRace.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
        lblDeleteRace.setBounds(51, 6, 172, 22);
        pnlDeleteRace.add(lblDeleteRace);
        
        javax.swing.GroupLayout jPanelLeftPaneLayout = new javax.swing.GroupLayout(jPanelLeftPane);
        jPanelLeftPaneLayout.setHorizontalGroup(
        	jPanelLeftPaneLayout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(jPanelLeftPaneLayout.createSequentialGroup()
        			.addGroup(jPanelLeftPaneLayout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(Alignment.LEADING, jPanelLeftPaneLayout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(pnlCSV, GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE))
        				.addGroup(Alignment.LEADING, jPanelLeftPaneLayout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(pnlExc, GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE))
        				.addGroup(Alignment.LEADING, jPanelLeftPaneLayout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(pl1, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(pl2, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
        				.addGroup(Alignment.LEADING, jPanelLeftPaneLayout.createSequentialGroup()
        					.addGap(11)
        					.addGroup(jPanelLeftPaneLayout.createParallelGroup(Alignment.LEADING)
        						.addGroup(jPanelLeftPaneLayout.createSequentialGroup()
        							.addComponent(pl9, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(pl10, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
        						.addGroup(jPanelLeftPaneLayout.createSequentialGroup()
        							.addComponent(pl11, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(pl12, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))))
        				.addGroup(Alignment.LEADING, jPanelLeftPaneLayout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(pl3, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(pl4, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
        				.addGroup(Alignment.LEADING, jPanelLeftPaneLayout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(pl5, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(pl6, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
        				.addGroup(Alignment.LEADING, jPanelLeftPaneLayout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(pl7, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(pl8, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
        				.addGroup(Alignment.LEADING, jPanelLeftPaneLayout.createSequentialGroup()
        					.addContainerGap()
        					.addGroup(jPanelLeftPaneLayout.createParallelGroup(Alignment.TRAILING)
        						.addComponent(pl13, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
        						.addComponent(pl15, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(jPanelLeftPaneLayout.createParallelGroup(Alignment.LEADING)
        						.addComponent(pl16, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
        						.addComponent(pl14, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)))
        				.addGroup(Alignment.LEADING, jPanelLeftPaneLayout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(pl17, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(pl18, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
        				.addGroup(Alignment.LEADING, jPanelLeftPaneLayout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(pl19, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(pl20, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)))
        			.addGap(21))
        		.addGroup(Alignment.LEADING, jPanelLeftPaneLayout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(pnlDeleteRace, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(21, Short.MAX_VALUE))
        );
        jPanelLeftPaneLayout.setVerticalGroup(
        	jPanelLeftPaneLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanelLeftPaneLayout.createSequentialGroup()
        			.addGap(12)
        			.addGroup(jPanelLeftPaneLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(pl1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
        				.addComponent(pl2, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(jPanelLeftPaneLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(pl3, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
        				.addComponent(pl4, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
        			.addGap(11)
        			.addGroup(jPanelLeftPaneLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(pl5, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
        				.addComponent(pl6, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(jPanelLeftPaneLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(pl7, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
        				.addComponent(pl8, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(jPanelLeftPaneLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(pl10, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
        				.addComponent(pl9, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(jPanelLeftPaneLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(pl12, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
        				.addComponent(pl11, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(jPanelLeftPaneLayout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(pl13, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
        				.addComponent(pl14, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(jPanelLeftPaneLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(pl16, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
        				.addComponent(pl15, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(jPanelLeftPaneLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(pl17, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
        				.addComponent(pl18, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(jPanelLeftPaneLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(pl19, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
        				.addComponent(pl20, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(pnlExc, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(pnlCSV, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(pnlDeleteRace, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        
        jPanelLeftPane.setLayout(jPanelLeftPaneLayout);

        jPaneHeader.setBackground(new java.awt.Color(255, 255, 255));
        jPaneHeader.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));


        lblCl.setFont(new java.awt.Font("Tahoma", 1, 18));
        lblCl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		lblCl.setIcon(new ImageIcon("./images/closeIcoRed.png"));
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		lblCl.setIcon(new ImageIcon("./images/closeIco2.png"));
        	}
        });

        
        jLabel4.setFont(new java.awt.Font("Bookman Old Style", 2, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(54, 33, 89));
        jLabel4.setText("Race Home");
        
        lblMin = new JLabel("");
        lblMin.setIcon(new ImageIcon("./images/minIco2.png"));
        lblMin.setHorizontalAlignment(SwingConstants.CENTER);
        lblMin.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblMin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblMin.setIcon(new ImageIcon("./images/minIcoRed.png"));
			}
			public void mouseExited(MouseEvent e) {
				lblMin.setIcon(new ImageIcon("./images/minIco2.png"));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(Frame.ICONIFIED);
				timeFrame.setState(ICONIFIED);
			}
		});
        
        lbltime = new JLabel("");
        lbltime.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
        lbltime.setHorizontalAlignment(SwingConstants.CENTER);
        javax.swing.GroupLayout jPaneHeaderLayout = new javax.swing.GroupLayout(jPaneHeader);
        jPaneHeaderLayout.setHorizontalGroup(
        	jPaneHeaderLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPaneHeaderLayout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
        			.addGap(513)
        			.addComponent(lbltime)
        			.addPreferredGap(ComponentPlacement.RELATED, 541, Short.MAX_VALUE)
        			.addComponent(lblMin, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(lblCl, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
        );
        jPaneHeaderLayout.setVerticalGroup(
        	jPaneHeaderLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPaneHeaderLayout.createSequentialGroup()
        			.addGap(0, 0, Short.MAX_VALUE)
        			.addGroup(jPaneHeaderLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblCl, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jLabel4)
        				.addComponent(lblMin, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lbltime)))
        );
        jPaneHeader.setLayout(jPaneHeaderLayout);

        jPanelLeftHeader.setBackground(new Color(59,89,152));
        
        pnlStart = new JPanel();
        pnlStart.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		try {
					RaceHandler.makeConn();
					lblStopRace.setForeground(new Color(54,33,89));
	        		lblStartRace.setForeground(Color.GREEN);
	        	    timeFrame = new TImeFrame();
	        		timeFrame.setUndecorated(true);
	        		timeFrame.setVisible(true);
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					//e2.printStackTrace();
				}
        	
        		
        		
        		try {        			
					RaceHandler.mainSample();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
        	}
        });
        pnlStart.setLayout(null);
        pnlStart.setBackground(new Color(223,227,238));
        
        lblStartRace = new JLabel("Start Race");
        lblStartRace.setHorizontalAlignment(SwingConstants.CENTER);
        lblStartRace.setForeground(new Color(54,33,89));
        lblStartRace.setFont(new Font("Yu Gothic Light", Font.BOLD, 16));
        lblStartRace.setBounds(0, 48, 140, 31);
        pnlStart.add(lblStartRace);
        pnlStart.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon("./images/start.png"));
        lblNewLabel_1.setBounds(43, 0, 55, 55);
        pnlStart.add(lblNewLabel_1);
        
        pnlStop = new JPanel();
        pnlStop.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		lblStopRace.setForeground(Color.GREEN);
        		lblStartRace.setForeground(new Color(54,33,89));
        		try {
					CLReader._Config.Stop(SwitchPanel.txtConnId.getText());
					timeFrame.clock.interrupt();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
        	}
        });
        pnlStop.setLayout(null);
        pnlStop.setBackground(new Color(223,227,238));
        
        label_6 = new JLabel("");
        label_6.setIcon(new ImageIcon("./images/stop2.png"));
        label_6.setBounds(40, 0, 74, 49);
        pnlStop.add(label_6);
        pnlStop.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        lblStopRace = new JLabel("Stop Race");
        lblStopRace.setBounds(0, 47, 153, 32);
        pnlStop.add(lblStopRace);
        lblStopRace.setHorizontalAlignment(SwingConstants.CENTER);
        lblStopRace.setForeground(new Color(54,33,89));
        lblStopRace.setFont(new Font("Yu Gothic Light", Font.BOLD, 16));

        javax.swing.GroupLayout jPanelLeftHeaderLayout = new javax.swing.GroupLayout(jPanelLeftHeader);
        jPanelLeftHeaderLayout.setHorizontalGroup(
        	jPanelLeftHeaderLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanelLeftHeaderLayout.createSequentialGroup()
        			.addComponent(pnlStart, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(pnlStop, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
        );
        jPanelLeftHeaderLayout.setVerticalGroup(
        	jPanelLeftHeaderLayout.createParallelGroup(Alignment.TRAILING)
        		.addComponent(pnlStop, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        		.addComponent(pnlStart, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanelLeftHeader.setLayout(jPanelLeftHeaderLayout);

        jTable1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16)); // NOI18N
        
        jTable1.setGridColor(new java.awt.Color(230, 255, 255));
        jTable1.setIntercellSpacing(new java.awt.Dimension(5, 5));
        jTable1.setRowHeight(40);
        jTable1.setBackground(Color.WHITE);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setHeaderValue("TID");
            jTable1.getColumnModel().getColumn(1).setHeaderValue("Name");
            jTable1.getColumnModel().getColumn(2).setHeaderValue("Age");
            jTable1.getColumnModel().getColumn(3).setHeaderValue("Time");
        }
       
        javax.swing.GroupLayout jPanelRightLayout = new javax.swing.GroupLayout(jPanelRight);
        jPanelRightLayout.setHorizontalGroup(
        	jPanelRightLayout.createParallelGroup(Alignment.LEADING)
        		.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 946, Short.MAX_VALUE)
        );
        jPanelRightLayout.setVerticalGroup(
        	jPanelRightLayout.createParallelGroup(Alignment.LEADING)
        		.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
        );
        jPanelRight.setLayout(jPanelRightLayout);
        javax.swing.GroupLayout jPanelAllLayout = new javax.swing.GroupLayout(jPanelAll);
        jPanelAllLayout.setHorizontalGroup(
        	jPanelAllLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanelAllLayout.createSequentialGroup()
        			.addGroup(jPanelAllLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(jPanelAllLayout.createSequentialGroup()
        					.addContainerGap()
        					.addGroup(jPanelAllLayout.createParallelGroup(Alignment.TRAILING)
        						.addComponent(jPanelLeftPane, 0, 0, Short.MAX_VALUE)
        						.addComponent(jPanelLeftHeader, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(jPanelRight, GroupLayout.PREFERRED_SIZE, 946, GroupLayout.PREFERRED_SIZE)
        					.addGap(114))
        				.addComponent(jPaneHeader, GroupLayout.PREFERRED_SIZE, 1280, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap())
        );
        jPanelAllLayout.setVerticalGroup(
        	jPanelAllLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanelAllLayout.createSequentialGroup()
        			.addComponent(jPaneHeader, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(jPanelAllLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(jPanelAllLayout.createSequentialGroup()
        					.addComponent(jPanelLeftHeader, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(jPanelLeftPane, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        				.addComponent(jPanelRight, GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE))
        			.addContainerGap())
        );
        jPanelAll.setLayout(jPanelAllLayout);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addComponent(jPanelAll, GroupLayout.PREFERRED_SIZE, 1280, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addComponent(jPanelAll, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        getContentPane().setLayout(layout);

        setBounds(50, 150, 1280, 713);
    }// </editor-fold>                        
                   

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {                                     
        // TODO add your handling code here:
        try {
			CLReader._Config.Stop(SwitchPanel.txtConnId.getText());
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
    	Login.switchPanelFrame.setVisible(true);
    	timeFrame.dispose();
    	dispose();
    	
    }                                    

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RaceHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RaceHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RaceHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RaceHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RaceHome().setVisible(true);
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
    private javax.swing.JLabel lblCl;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPaneHeader;
    private javax.swing.JPanel jPanelAll;
    private javax.swing.JPanel jPanelLeftHeader;
    private javax.swing.JPanel jPanelLeftPane;
    private javax.swing.JPanel jPanelRight;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTable jTable1;
    private JPanel pnlStart;
    private JLabel lblStartRace;
    private JPanel pnlStop;
    private JLabel lblStopRace;
    private JLabel label_6;
    private JLabel lblNewLabel_1;
    private static JPanel pl9;
    private static JLabel ll9;
    private static JPanel pl10;
    private static JLabel ll10;
    private static JPanel pl11;
    private static JLabel ll11;
    private static JPanel pl12;
    private static JLabel ll12;
    private static JPanel pl13;
    private static JLabel ll13;
    private static JPanel pl14;
    private static JLabel ll14;
    private static JPanel pl15;
    private static JLabel ll15;
    private static JPanel pl16;
    private static JLabel ll16;
    private static JPanel pl17;
    private static JLabel ll17;
    private static JPanel pl18;
    private static JLabel ll18;
    private static JPanel pl7;
    private static JLabel ll7;
    private static JPanel pl8;
    private static JLabel ll8;
    private static JPanel pl5;
    private static JLabel ll5;
    private static JPanel pl6;
    private static JLabel ll6;
    private static JPanel pl3;
    private static JLabel ll3;
    private static JPanel pl4;
    private static JLabel ll4;
    private static JPanel pl1;
    private static JLabel ll1;
    private static JPanel pl2;
    private static JLabel ll2;
    private JPanel pnlExc;
    private JLabel label_21;
    private JPanel pnlCSV;
    private JLabel label_22;
    private static JPanel pl19;
    private static JLabel ll19;
    private static JPanel pl20;
    private static JLabel ll20;
    private JLabel lblMin;
    private JLabel lbltime;
}
