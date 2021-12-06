package de.ge.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.text.MaskFormatter;

import de.ge.user.User;
import de.ge.utils.PrettyColor;

public class RegisterScreen {
	private JFrame frame = new JFrame();

	private JLabel lblBenutzerName = new JLabel();
	private JTextField tfBenutzerName = new JTextField();
	private JLabel lblName = new JLabel();
	private JTextField tfName = new JTextField();
	private JLabel lblNachname = new JLabel();
	private JTextField tfNachname = new JTextField();
	private JPasswordField tfPasswort = new JPasswordField();
	private JPasswordField tfPasswortw = new JPasswordField();
	private JLabel lblPasswort = new JLabel();
	private JLabel lblPasswortwiederholen = new JLabel();
	private JLabel lblRegestrierung = new JLabel();
	private JButton btnRegistrieren = new JButton();
	private MaskFormatter jFormattedTextField1MaskFormatter = new MaskFormatter();
	private JFormattedTextField ftfGeburtsdatum = new JFormattedTextField(jFormattedTextField1MaskFormatter);
	private JLabel lblGeburtsdatum = new JLabel();

	public RegisterScreen() {
		this.frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		int frameWidth = 365;
		int frameHeight = 510;
		this.frame.setSize(frameWidth, frameHeight);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - this.frame.getSize().width) / 2;
		int y = (d.height - this.frame.getSize().height) / 2;
		this.frame.setLocation(x, y);
		this.frame.setTitle("Register -> Geteilte Einkaufsliste");
		this.frame.setResizable(false);
		Container cp = this.frame.getContentPane();
		cp.setBackground(PrettyColor.BLUE);
		cp.setLayout(null);
		int yanfang = 15;
		int abstand = 7;
		
		
		lblRegestrierung.setText("Regestrierung");
		lblRegestrierung.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		lblRegestrierung.setOpaque(false);
		lblRegestrierung.setHorizontalTextPosition(SwingConstants.CENTER);
		lblRegestrierung.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegestrierung.setBounds((int)((frameWidth/2)-(175/2)), yanfang, 175, 30);
		lblRegestrierung.setForeground(PrettyColor.WHITE);
		cp.add(lblRegestrierung);
		
		
		lblBenutzerName.setBounds((int)((frameWidth/2)-(130/2)), (lblRegestrierung.getBounds().y+lblRegestrierung.getBounds().height+abstand), 130, 30);
		lblBenutzerName.setText("Benutzername:");
		lblBenutzerName.setOpaque(false);
		lblBenutzerName.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblBenutzerName.setHorizontalTextPosition(SwingConstants.CENTER);
		lblBenutzerName.setHorizontalAlignment(SwingConstants.CENTER);
		lblBenutzerName.setForeground(PrettyColor.GREEN);
		cp.add(lblBenutzerName);
		
		tfBenutzerName.setBounds((int)((frameWidth/2)-(150/2)), (lblBenutzerName.getBounds().y+lblBenutzerName.getBounds().height+(abstand/2)), 150, 20);
		tfBenutzerName.setText("");
		tfBenutzerName.setBackground(PrettyColor.LITHEBLUE);
		tfBenutzerName.setBorder(BorderFactory.createEmptyBorder());
		cp.add(tfBenutzerName);
		
		
		lblName.setBounds((int)((frameWidth/2)-(110/2)), (tfBenutzerName.getBounds().y+tfBenutzerName.getBounds().height+abstand), 110, 30);
		lblName.setText("Name:");
		lblName.setOpaque(false);
		lblName.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblName.setHorizontalTextPosition(SwingConstants.CENTER);
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setForeground(PrettyColor.GREEN);
		cp.add(lblName);
		
		tfName.setBounds((int)((frameWidth/2)-(150/2)), (lblName.getBounds().y+lblName.getBounds().height+(abstand/2)), 150, 20);
		tfName.setText("");
		tfName.setBackground(PrettyColor.LITHEBLUE);
		tfName.setBorder(BorderFactory.createEmptyBorder());
		cp.add(tfName);
		
		
		lblNachname.setBounds((int)((frameWidth/2)-(110/2)), (tfName.getBounds().y+tfName.getBounds().height+abstand), 110, 30);
		lblNachname.setText("Nachname:");
		lblNachname.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblNachname.setForeground(PrettyColor.GREEN);
		lblNachname.setHorizontalAlignment(SwingConstants.CENTER);
		cp.add(lblNachname);
		
		tfNachname.setBounds((int)((frameWidth/2)-(150/2)), (lblNachname.getBounds().y+lblNachname.getBounds().height+(abstand/2)), 150, 20);
		tfNachname.setBorder(BorderFactory.createEmptyBorder());
		tfNachname.setBackground(PrettyColor.LITHEBLUE);
		cp.add(tfNachname);
		
		

		lblGeburtsdatum.setBounds((int)((frameWidth/2)-(120/2)), (tfNachname.getBounds().y+tfNachname.getBounds().height+(abstand)), 120, 30);
		lblGeburtsdatum.setText("Geburtsdatum:");
		lblGeburtsdatum.setHorizontalAlignment(SwingConstants.CENTER);
		lblGeburtsdatum.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblGeburtsdatum.setForeground(PrettyColor.GREEN);
		cp.add(lblGeburtsdatum);
		
		ftfGeburtsdatum.setBounds((int)((frameWidth/2)-(150/2)), (lblGeburtsdatum.getBounds().y+lblGeburtsdatum.getBounds().height+(abstand/2)), 150, 20);
		ftfGeburtsdatum.setBorder(BorderFactory.createEmptyBorder());
		ftfGeburtsdatum.setBackground(PrettyColor.LITHEBLUE);
		cp.add(ftfGeburtsdatum);
		
		lblPasswort.setBounds((int)((frameWidth/2)-(110/2)), (ftfGeburtsdatum.getBounds().y+ftfGeburtsdatum.getBounds().height+(abstand)), 110, 30);
		lblPasswort.setText("Passwort:");
		lblPasswort.setHorizontalAlignment(SwingConstants.CENTER);
		lblPasswort.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblPasswort.setForeground(PrettyColor.GREEN);
		cp.add(lblPasswort);
		
		tfPasswort.setBounds((int)((frameWidth/2)-(150/2)), (lblPasswort.getBounds().y+lblPasswort.getBounds().height+(abstand/2)), 150, 20);
		tfPasswort.setBorder(BorderFactory.createEmptyBorder());
		tfPasswort.setBackground(PrettyColor.LITHEBLUE);
		cp.add(tfPasswort);
		
		lblPasswortwiederholen.setBounds((int)((frameWidth/2)-(175/2)), (tfPasswort.getBounds().y+tfPasswort.getBounds().height+(abstand)), 175, 30);
		lblPasswortwiederholen.setText("Passwort wiederholen:");
		lblPasswortwiederholen.setHorizontalAlignment(SwingConstants.CENTER);
		lblPasswortwiederholen.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblPasswortwiederholen.setForeground(PrettyColor.GREEN);
		cp.add(lblPasswortwiederholen);
		
		tfPasswortw.setBounds((int)((frameWidth/2)-(150/2)), (lblPasswortwiederholen.getBounds().y+lblPasswortwiederholen.getBounds().height+(abstand/2)), 150, 20);
		tfPasswortw.setBorder(BorderFactory.createEmptyBorder());
		tfPasswortw.setBackground(PrettyColor.LITHEBLUE);
		cp.add(tfPasswortw);
		
		btnRegistrieren.setBounds((int)((frameWidth/2)-(130/2)), (tfPasswortw.getBounds().y+tfPasswortw.getBounds().height+(abstand)), 130, 40);
		btnRegistrieren.setText("Registrieren");
		btnRegistrieren.setMargin(new Insets(2, 2, 2, 2));
		btnRegistrieren.setFocusable(false);
		btnRegistrieren.setBorderPainted(false);
		btnRegistrieren.setContentAreaFilled(false);
		btnRegistrieren.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		btnRegistrieren.setForeground(PrettyColor.GREEN);
		btnRegistrieren.setBackground(PrettyColor.BLUE);
		btnRegistrieren.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnRegistrieren) {
					User.createUser(tfNachname.getText(), tfName.getText(), new Date().getTime(), tfPasswort.getText());
					
					frame.dispose();
					new LoginScreen();
				}
			}
		});
		cp.add(btnRegistrieren);

		

		this.frame.setVisible(true);
	}

}
