import java.security.*;
import java.util.*;
import java.math.*;

class MD5{
	public static void main(String args[]) throws Exception{
		Scanner sc = new Scanner(System.in);
		String msg = sc.nextLine();		
		
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(msg.getBytes(),0,msg.length());
		System.out.println("MD5 : " + (new BigInteger(1,md.digest()).toString(16)));
	}
}

// MessageDigest md=MessageDigest.getDigest("MD5");
// md.update(msg.getBytes(),0,msg.length());
// SOp("md5 : "+ new BigInteger(1, md.digest()).toString(16));