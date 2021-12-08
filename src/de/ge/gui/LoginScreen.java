package de.ge.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
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
	    int frameWidth = 400; 
	    int frameHeight = 250;
	    this.frame.setSize(frameWidth, frameHeight);
	    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (d.width - this.frame.getSize().width) / 2;
	    int y = (d.height - this.frame.getSize().height) / 2;
	    this.frame.setLocation(x, y);
	    this.frame.setTitle("Login -> Geteilte Einkaufsliste");
	    this.frame.setResizable(false);
	    Container cp = this.frame.getContentPane();
	    cp.setLayout(null);
		int yanfang = 15;
		int abstand = 20;
	    
	    lblueberschrift.setBounds((int)((frameWidth/2)-(110/2)), yanfang, 110, 30);
	    lblueberschrift.setText("Login");
	    lblueberschrift.setBackground(PrettyColor.BLUE);
	    lblueberschrift.setOpaque(true);
	    lblueberschrift.setFont(Utils.HeaderFont);
	    lblueberschrift.setForeground(PrettyColor.WHITE);
	    cp.add(lblueberschrift);
	    
	    lblUserID.setBounds((int)((frameWidth/2)-(100*1.5)), (lblueberschrift.getBounds().y+lblueberschrift.getBounds().height+abstand), 100, 20);
	    lblUserID.setText("Benutzername:");
	    lblUserID.setFont(Utils.NormalFont);
	    lblUserID.setForeground(PrettyColor.GREEN);
	    cp.add(lblUserID);
	    
	    useridfield.setBounds((int)((frameWidth/2)-(25*1.5)), (lblueberschrift.getBounds().y+lblueberschrift.getBounds().height+abstand), 150, 20);
	    useridfield.setText("");
	    useridfield.setBorder(BorderFactory.createEmptyBorder());
	    useridfield.setBackground(PrettyColor.LITHEBLUE);
	    useridfield.setToolTipText("Bitte nur Zahlen eingeben");
	    cp.add(useridfield);
	   
	    lblPasswort.setBounds((int)((frameWidth/2)-(100*1.5)), (useridfield.getBounds().y+useridfield.getBounds().height+abstand), 100, 20);
	    lblPasswort.setText("Passwort:");
	    lblPasswort.setFont(Utils.NormalFont);
	    lblPasswort.setForeground(PrettyColor.GREEN);
	    cp.add(lblPasswort);

	    pwfield.setBorder(BorderFactory.createEmptyBorder());
	    pwfield.setBackground(PrettyColor.LITHEBLUE);
	    pwfield.setForeground(PrettyColor.WHITE);
	    pwfield.setBounds((int)((frameWidth/2)-(25*1.5)), (useridfield.getBounds().y+useridfield.getBounds().height+abstand), 150, 20);
	    cp.add(pwfield);
	    
	    
	    
	    btnLogin.setBounds((int)((frameWidth/2)-(100*1.5)), (pwfield.getBounds().y+pwfield.getBounds().height+abstand), 100, 25);
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
							System.out.println("------------- Login Ende -------------");
						}
						JOptionPane.showMessageDialog(cp,"Die UserID kann nur Zahlen enthalten","Hilfe",JOptionPane.ERROR_MESSAGE);
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
	    
	    btnRegistrieren.setBounds((int)((frameWidth/2)-(25*1.5)), (pwfield.getBounds().y+pwfield.getBounds().height+abstand), 115, 25);
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
