package de.ge.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.text.MaskFormatter;

import de.ge.utils.PrettyColor;

public class RegisterScreen {
	private JFrame frame = new JFrame();

	private JLabel lblName = new JLabel();
	private JTextField tfName = new JTextField();
	private JLabel lblNachname1 = new JLabel();
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
		int frameHeight = 483;
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
		lblRegestrierung.setText("Regestrierung");
		lblRegestrierung.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		lblRegestrierung.setOpaque(false);
		lblRegestrierung.setHorizontalTextPosition(SwingConstants.CENTER);
		lblRegestrierung.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegestrierung.setBounds((int)((frameWidth/2)-(175/2)), 32, 175, 33);
		lblRegestrierung.setForeground(PrettyColor.WHITE);
		cp.add(lblRegestrierung);

		tfName.setBounds(104, 112, 150, 20);
		tfName.setText("");
		cp.add(tfName);
		lblName.setBounds(120, 80, 110, 27);
		lblName.setText("Name:");
		lblName.setOpaque(false);
		lblName.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblName.setHorizontalTextPosition(SwingConstants.CENTER);
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setForeground(PrettyColor.GREEN);
		cp.add(lblName);
		lblNachname1.setBounds(120, 136, 110, 27);
		lblNachname1.setText("Nachname:");
		lblNachname1.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblNachname1.setForeground(PrettyColor.GREEN);
		lblNachname1.setHorizontalAlignment(SwingConstants.CENTER);
		cp.add(lblNachname1);
		tfNachname.setBounds(104, 168, 150, 20);
		cp.add(tfNachname);
		
		ftfGeburtsdatum.setBounds(104, 224, 150, 20);
		cp.add(ftfGeburtsdatum);
		
		lblGeburtsdatum.setBounds(120, 192, 119, 27);
		lblGeburtsdatum.setText("Geburtsdatum:");
		lblGeburtsdatum.setHorizontalAlignment(SwingConstants.CENTER);
		lblGeburtsdatum.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblGeburtsdatum.setForeground(PrettyColor.GREEN);
		cp.add(lblGeburtsdatum);
		
		
		tfPasswort.setBounds(104, 288, 150, 20);
		cp.add(tfPasswort);
		tfPasswortw.setBounds(104, 344, 150, 20);
		cp.add(tfPasswortw);
		lblPasswort.setBounds(120, 248, 110, 27);
		lblPasswort.setText("Passwort:");
		lblPasswort.setHorizontalAlignment(SwingConstants.CENTER);
		lblPasswort.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblPasswort.setForeground(PrettyColor.GREEN);
		cp.add(lblPasswort);
		lblPasswortwiederholen.setBounds((int)((frameWidth/2)-(175/2)), 312, 175, 27);
		lblPasswortwiederholen.setText("Passwort wiederholen:");
		lblPasswortwiederholen.setHorizontalAlignment(SwingConstants.CENTER);
		lblPasswortwiederholen.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblPasswortwiederholen.setForeground(PrettyColor.GREEN);
		cp.add(lblPasswortwiederholen);
		btnRegistrieren.setBounds(120, 376, 131, 41);
		btnRegistrieren.setText("Registrieren");
		btnRegistrieren.setMargin(new Insets(2, 2, 2, 2));
		btnRegistrieren.setFocusable(false);
		btnRegistrieren.setBorderPainted(false);
		btnRegistrieren.setContentAreaFilled(false);
		btnRegistrieren.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		btnRegistrieren.setForeground(PrettyColor.GREEN);
		btnRegistrieren.setBackground(PrettyColor.BLUE);
		cp.add(btnRegistrieren);

		

		this.frame.setVisible(true);
	}

}