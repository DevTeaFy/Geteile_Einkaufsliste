package de.ge.utils;

import java.awt.Font;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Utils {
	
	public static boolean debug = true;
	public static String FontName = "Comic Sans MS";
	public static Font NormalFont = new Font(FontName, Font.PLAIN, 14);
	public static Font SecondHeaderFont = new Font(FontName, Font.BOLD, 18);
	public static Font HeaderFont = new Font(FontName, Font.BOLD, 20);

	public static boolean hasInternetConnection() {
		try {
		    InetAddress.getByName("test.abendspieler.de");
		    if(Utils.debug)
		    	System.out.println("User hat Internet."); 
		    
		    return true;
		} catch(UnknownHostException e) {
			if(Utils.debug) {
				System.out.println("User has KEIN Internet."); 
		    	e.printStackTrace();
			}
			return false;
		}
	}
	
}
