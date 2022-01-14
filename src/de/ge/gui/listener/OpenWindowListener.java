package de.ge.gui.listener;

import java.awt.Cursor;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import de.ge.gui.screens.LoginScreen;
import de.ge.main.Geteilte_Einkaufsliste;
import de.ge.user.User;
import de.ge.utils.Utils;

public class OpenWindowListener implements WindowListener {

	private LoginScreen ls;
	public OpenWindowListener(LoginScreen ls) {
		this.ls = ls;
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		if(!Utils.hasInternetConnection()) {
			return;
		}
		if(Geteilte_Einkaufsliste.getMySQL().getCon() != null) {
			Geteilte_Einkaufsliste.getMySQL().closeMySQL();
		}
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		
	}
	private int count = 0;
	@Override
	public void windowActivated(WindowEvent e) {
		if(count >= 1) {
			return;
		}
		if(Utils.hasInternetConnection()) {
			count++;
			ls.getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			Geteilte_Einkaufsliste.getMySQL().connectMySQL();
			ls.getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			if(Utils.debug)
			User.listIDS();
		}
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		
	}

}
