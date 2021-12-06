package de.ge.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import de.ge.main.Geteilte_Einkaufsliste;
import de.ge.utils.PrettyColor;
import de.ge.utils.Tabellen;
import de.ge.utils.Utils;

public class LoginScreen {

	private JFrame frame = new JFrame();
	private JLabel lblUserID = new JLabel();
	private JButton btnLogin = new JButton();
	private JPasswordField pwfield = new JPasswordField();
	private JLabel lblPasswort = new JLabel();
	private JButton btnRegistrieren = new JButton();
	private JLabel lblueberschrift = new JLabel();
	private JTextField useridfield = new JTextField();

	public LoginScreen() { 
	    this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    this.frame.addWindowListener(new CloseWindowListener());
	    int frameWidth = 527; 
	    int frameHeight = 290;
	    this.frame.setSize(frameWidth, frameHeight);
	    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (d.width - this.frame.getSize().width) / 2;
	    int y = (d.height - this.frame.getSize().height) / 2;
	    this.frame.setLocation(x, y);
	    this.frame.setTitle("Login -> Geteilte Einkaufsliste");
	    this.frame. setResizable(false);
	    Container cp = this.frame.getContentPane();
	    cp.setLayout(null);
	    
	    lblueberschrift.setBounds(206, 21, 110, 29);
	    lblueberschrift.setText("Login");
	    lblueberschrift.setBackground(PrettyColor.BLUE);
	    lblueberschrift.setOpaque(true);
	    lblueberschrift.setFont(Utils.HeaderFont);
	    lblueberschrift.setForeground(PrettyColor.WHITE);
	    cp.add(lblueberschrift);
	    
	    lblUserID.setBounds(115, 65, 100, 20);
	    lblUserID.setText("UserID:");
	    lblUserID.setFont(Utils.NormalFont);
	    lblUserID.setForeground(PrettyColor.GREEN);
	    
	    useridfield.setBounds(215, 65, 150, 20);
	    useridfield.setText("");
	    useridfield.setToolTipText("Bitte nur Zahlen eingeben");
	 
	    cp.add(useridfield);
	    
	    cp.add(lblUserID);
	    btnLogin.setBounds(100, 160, 100, 25);
	    btnLogin.setText("Login");
	    btnLogin.setMargin(new Insets(2, 2, 2, 2));
	    btnLogin.setBorderPainted(false);
	    btnLogin.setContentAreaFilled(false);
	    btnLogin.setFocusable(false);
	    btnLogin.setBackground(PrettyColor.BLUE);
	    btnLogin.setFont(Utils.NormalFont);
	    btnLogin.setForeground(Color.GREEN);
	    btnLogin.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnLogin) {
					if(Utils.debug)
						System.out.println("------------- Login versuch -------------");
						
					if(!Utils.hasInternetConnection()) {
						JOptionPane.showMessageDialog(cp,"Du hast kein Internet.","Internet",JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					if(Geteilte_Einkaufsliste.getMySQL().getCon() == null) {
						JOptionPane.showMessageDialog(cp,"Du hast keine verbindung zur Datenbank","Datenbank",JOptionPane.ERROR_MESSAGE);
						return;
					}
					int id = 0;
					try {
						id = Integer.valueOf(useridfield.getText());
					} catch (NumberFormatException e1) {
						if(Utils.debug) {
							System.out.println("User hat Buchstaben statt Zahl eingegeben.");
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(cp,"Die UserID kann nur Zahlen enthalten","Hilfe",JOptionPane.ERROR_MESSAGE);
						System.out.println("------------- Login Ende -------------");
						return;
					}
					String pw = pwfield.getText();
					String datenbankpw = Geteilte_Einkaufsliste.getMySQL().getString("*", Tabellen.USER, "ID", id, "Password");
					
					if(pw.equals(datenbankpw)) {
						System.out.println("Login erfolgreich");
					}else {
						System.out.println("Pw falsch!!!");
					}
					
					if(Utils.debug)
						System.out.println("------------- Login Ende -------------");
				}
				
			}
		});
	    cp.add(btnLogin);
	    
	    pwfield.setBounds(215, 115, 150, 20);
	    cp.add(pwfield);
	    lblPasswort.setBounds(115, 115, 150, 20);
	    lblPasswort.setText("Passwort:");
	    lblPasswort.setFont(Utils.NormalFont);
	    lblPasswort.setForeground(PrettyColor.GREEN);
	    cp.add(lblPasswort);
	    btnRegistrieren.setBounds(225, 160, 115, 25);
	    btnRegistrieren.setText("Registrieren");
	    btnRegistrieren.setMargin(new Insets(2, 2, 2, 2));
	    btnRegistrieren.setBackground(PrettyColor.BLUE);
	    btnRegistrieren.setFocusable(false);
	    btnRegistrieren.setBorderPainted(false);
	    btnRegistrieren.setContentAreaFilled(false);
	    btnRegistrieren.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnRegistrieren) {
					frame.dispose();
					new RegisterScreen();
				}
			}
		});
	    btnRegistrieren.setFont(Utils.NormalFont);
	    btnRegistrieren.setForeground(Color.GREEN);
	    cp.add(btnRegistrieren);
	    
	    cp.setBackground(PrettyColor.BLUE);
	    
	    
	    this.frame.setVisible(true);
	  }
	
	public JFrame getFrame() {
		return frame;
	}


}
