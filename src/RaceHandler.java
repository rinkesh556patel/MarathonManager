

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.apache.commons.dbcp2.BasicDataSource;

import com.clou.uhf.G3Lib.CLReader;
import com.clou.uhf.G3Lib.Tag6C;
import com.clou.uhf.G3Lib.Enumeration.eAntennaNo;
import com.clou.uhf.G3Lib.Enumeration.eReadType;
import com.clou.uhf.G3Lib.ClouInterface.IAsynchronousMessage;
import com.clou.uhf.G3Lib.ClouInterface.ISearchDevice;
import com.clou.uhf.G3Lib.Models.GPI_Model;
import com.clou.uhf.G3Lib.Models.Tag_Model;
import com.jamierf.rxtx.RXTXLoader;

import net.proteanit.sql.DbUtils;

public class RaceHandler implements IAsynchronousMessage,ISearchDevice {
	static eReadType readType;
	static public long seconds=0;
	static public long minutes = 0;
	static public long millis = 0;
	static public long hours = 0;
	static int antNum;
	static String ConnID = "";
	static int ano = SwitchPanel.cmbTotAnt.getSelectedIndex();
	
	static int antennaNo = 0;
	static int duplicateFilterTime = Integer.parseInt(SwitchPanel.txtFilterTime.getText()) * 100;
	static String formType1="";
	int ctr=0;
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date startTime = new Date();
	long startNanoTime = System.nanoTime();
	//long tt = System.miliTime();
	public static void makeConn() throws InterruptedException{
		try{
    		RXTXLoader.load();		//add library about rxtx
    	}catch(Exception ex){}

		RaceHandler example = new RaceHandler();
		ConnID = SwitchPanel.txtConnId.getText();
		
		if(CLReader.CreateTcpConn(ConnID, example)){
			try {
				System.out.println(CLReader._Config.Stop(ConnID));
				System.out.println(CLReader._Config.SetTagUpdateParam(ConnID, duplicateFilterTime, 60));
				if(ano==0)
					antennaNo+=eAntennaNo._1.GetNum();
				else{
					antennaNo+=eAntennaNo._1.GetNum();
					antennaNo+=eAntennaNo._2.GetNum();
				}
				if(CLReader._Config.SetReaderANT(ConnID, antennaNo) == 0){
					JOptionPane.showMessageDialog(null, "success antenna setting : GetReaderANT= " + CLReader._Config.GetReaderANT(ConnID) , "Suucessfull", JOptionPane.INFORMATION_MESSAGE);
		    	}else {
		    		JOptionPane.showMessageDialog(null, "failed antenna setting : GetReaderANT= " + CLReader._Config.GetReaderANT(ConnID) , "Exception", JOptionPane.INFORMATION_MESSAGE);
		    	}
			} catch (InterruptedException e) {
				//e.printStackTrace();
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Connection failure, Try again"  , "Connection failure ", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public static void mainSample() throws InterruptedException{
		
			try{
				Tag6C.GetEPC_TID(ConnID, antennaNo,  eReadType.Inventory);
	    	}
	    	catch(Exception ex){
	    		JOptionPane.showMessageDialog(null, "Exception : "+ex.getMessage()  , "Exception", JOptionPane.INFORMATION_MESSAGE);
	    	}
    }
	
	public void DebugMsg(String arg0) {
		// TODO Auto-generated method stub
		
	}

	public void DeviceInfo(com.clou.uhf.G3Lib.Models.Device_Model arg0) {
		// TODO Auto-generated method stub
		
	}

	public void GPIControlMsg(GPI_Model gpi_model) {
		// TODO Auto-generated method stub	
	}
	public void OutPutTags(Tag_Model arg0){
		Date now = new Date();
		long diff = now.getTime() - startTime.getTime();
		long diffSecond = diff / 1000 % 60;
		long time1 = now. getTime();
		Timestamp ts = new Timestamp(time1);
		//System.out.println(arg0._TID + " " + ts);
		int occur = 0;
		Connection con = null;
    	try{
    		Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:marathon.db");
    		PreparedStatement stmt=con.prepareStatement("select count(*) from tblrace where tid = ?");
    		stmt.setString(1, arg0._TID);
    		ResultSet res=stmt.executeQuery();
			
    		res.next();
			occur = res.getInt(1)+1;
			millis = (System.nanoTime()-startNanoTime)/1000000;
			minutes = (millis /1000) / 60;
			seconds = (millis /1000) % 60;
			hours = (millis /1000) / 60;
			//System.out.println("occur: "+occur+"__________________"+arg0._ANT_NUM+" : ANt"+"____Time: "+minutes+":"+seconds+"");
			
			
				
			//arg0
			stmt=con.prepareStatement("insert into tblrace values(?,?,?,?)");
    		stmt.setString(1, arg0._TID);
			stmt.setString(2, ts.toString());
			stmt.setString(3, minutes+":"+seconds+"");
			stmt.setString(4, occur+"");
			int cnt=stmt.executeUpdate();
			RaceHome.showData(RaceHome.selectedLap);
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

	public void OutPutTagsOver() {
		// TODO Auto-generated method stub
		//System.out.println("OutPutTagsOver");
	}

	public void PortClosing(String arg0) {
		// TODO Auto-generated method stub
		
	}

	public void PortConnecting(String connID) {
		// TODO Auto-generated method stub
		//System.out.println(connID);
        if (CLReader.GetServerStartUp())
        {
        	//System.out.println("A reader connected to this server: " + connID);
            ConnID = connID;
        }
	}

	public void WriteDebugMsg(String arg0) {
		// TODO Auto-generated method stub
		
	}

	public void WriteLog(String arg0) {
		// TODO Auto-generated method stub
		
	}

}