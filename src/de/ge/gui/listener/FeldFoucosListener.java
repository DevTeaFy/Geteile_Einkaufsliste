package de.ge.gui.listener;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import de.ge.gui.screens.RegisterScreen;
import de.ge.user.User;
import de.ge.utils.PrettyColor;
import de.ge.utils.Utils;

public class FeldFoucosListener implements FocusListener{
	
	private RegisterScreen rs;
	public FeldFoucosListener(RegisterScreen rs) {
		this.rs = rs;
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		if(e.getSource() == rs.getTfBenutzerName()) {
			rs.getTfBenutzerName().setBackground(PrettyColor.LITHEBLUE);
		}else if(e.getSource() == rs.getTfNachname()) {
			rs.getTfNachname().setBackground(PrettyColor.LITHEBLUE);
		}else if(e.getSource() == rs.getTfName()) {
			rs.getTfName().setBackground(PrettyColor.LITHEBLUE);
		}else if(e.getSource() == rs.getTfPasswort()) {
			rs.getTfPasswort().setBackground(PrettyColor.LITHEBLUE);
		}else if(e.getSource() == rs.getTfPasswortw()) {
			rs.getTfPasswortw().setBackground(PrettyColor.LITHEBLUE);
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if(e.getSource() == rs.getTfBenutzerName()) {
			
			if(Utils.debug) {System.out.println("Benutzername Feld verlassen check ob name exestiert!");}
			
			if(User.benutzerNameExists(rs.getTfBenutzerName().getText())) {
				rs.getTfBenutzerName().setBackground(PrettyColor.RED);
				
				if(Utils.debug) {System.out.println("Benutzername "+rs.getTfBenutzerName().getText()+" existiert bereits.");}
				
				return;
			}else {
				if(Utils.debug) {System.out.println("Benutzername "+rs.getTfBenutzerName().getText()+" gibt es noch nicht.");}
			}
		}
	}

}
