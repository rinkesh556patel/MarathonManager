import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TimeZone;

import javax.swing.JOptionPane;

import com.clou.uhf.G3Lib.CLReader;
import com.clou.uhf.G3Lib.SampleCode;
import com.clou.uhf.G3Lib.Tag6C;
import com.clou.uhf.G3Lib.Enumeration.eAntennaNo;
import com.clou.uhf.G3Lib.Enumeration.eBaudrate;
import com.clou.uhf.G3Lib.Enumeration.eGPI;
import com.clou.uhf.G3Lib.Enumeration.eGPO;
import com.clou.uhf.G3Lib.Enumeration.eGPOState;
import com.clou.uhf.G3Lib.Enumeration.eLockArea;
import com.clou.uhf.G3Lib.Enumeration.eLockType;
import com.clou.uhf.G3Lib.Enumeration.eRF_Range;
import com.clou.uhf.G3Lib.Enumeration.eReadType;
import com.clou.uhf.G3Lib.Enumeration.eTriggerCode;
import com.clou.uhf.G3Lib.Enumeration.eTriggerStart;
import com.clou.uhf.G3Lib.Enumeration.eTriggerStop;
import com.clou.uhf.G3Lib.Enumeration.eWiegandDetails;
import com.clou.uhf.G3Lib.Enumeration.eWiegandFormat;
import com.clou.uhf.G3Lib.Enumeration.eWiegandSwitch;
import com.clou.uhf.G3Lib.Enumeration.eWorkMode;
import com.clou.uhf.G3Lib.ClouInterface.IAsynchronousMessage;
import com.clou.uhf.G3Lib.ClouInterface.ISearchDevice;
import com.clou.uhf.G3Lib.Helper.Helper_String;
import com.clou.uhf.G3Lib.Models.Device_Model;
import com.clou.uhf.G3Lib.Models.GPI_Model;
import com.clou.uhf.G3Lib.Models.Tag_Model;
import com.jamierf.rxtx.RXTXLoader;

public class SampleCode2 implements IAsynchronousMessage,ISearchDevice {
	static eReadType readType;
	static String ConnID = "";
	static int antennaNo = eAntennaNo._1.GetNum();
	static String formType1="";
	public static void makeConn() throws InterruptedException{
		try{
    		RXTXLoader.load();		//add library about rxtx
    	}catch(Exception ex){}

		SampleCode2 example = new SampleCode2();
		ConnID = SwitchPanel.txtConnId.getText();
		//ConnID = "192.168.1.116:9090";
		if(CLReader.CreateTcpConn(ConnID, example)){
			
		}
		else{
			//JOptionPane.showMessageDialog(null, "Connection to reader failed, Try again"  , "Connection failure ", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	public static void mainSample(String formType) throws InterruptedException{
		try{
			formType1 = formType;
			
			if(Tag6C.GetEPC_TID(ConnID, antennaNo,  eReadType.Single)==0){
				
			}
			else{
				//JOptionPane.showMessageDialog(null, "Connection failure, Try again"  , "Connection failure ", JOptionPane.INFORMATION_MESSAGE);
			}
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
	public synchronized void OutPutTags(Tag_Model arg0){
		if(!arg0._TID.equals("")) {
			if(formType1.equals("insert") && InsertForm.txttid.getText().equals("")) 
				InsertForm.txttid.setText(arg0._TID);
			else if(formType1.equals("update")  && UpdateForm.txttid.getText().equals(""))
				UpdateForm.txttid.setText(arg0._TID);
			else if(formType1.equals("delete")  && DeleteForm.txttid.getText().equals(""))
				DeleteForm.txttid.setText(arg0._TID);
		}
    }

	public void OutPutTagsOver() {
		// TODO Auto-generated method stub
		System.out.println("OutPutTagsOver");
	}

	public void PortClosing(String arg0) {
		// TODO Auto-generated method stub
		
	}

	public void PortConnecting(String connID) {
		// TODO Auto-generated method stub
		System.out.println(connID);
        if (CLReader.GetServerStartUp())
        {
        	System.out.println("A reader connected to this server: " + connID);
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