package de.ge.gui.listener;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import de.ge.main.Geteilte_Einkaufsliste;
import de.ge.utils.Utils;

public class CloseWindowListener implements WindowListener {

	
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

	@Override
	public void windowActivated(WindowEvent e) {
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		
	}
	

}
