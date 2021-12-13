package de.ge.gui.screens;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.text.MaskFormatter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import de.ge.gui.listener.FeldFoucosListener;
import de.ge.main.Geteilte_Einkaufsliste;
import de.ge.user.User;
import de.ge.utils.DateLabelFormatter;
import de.ge.utils.PrettyColor;
import de.ge.utils.Utils;

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
	private JButton btnZurück = new JButton();
	private MaskFormatter jFormattedTextField1MaskFormatter = new MaskFormatter();
	private JDatePickerImpl datepickerGeburtsdatum;
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
		tfBenutzerName.setForeground(PrettyColor.WHITE);
		tfBenutzerName.setBorder(BorderFactory.createEmptyBorder());
		tfBenutzerName.addFocusListener(new FeldFoucosListener(this));
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
		tfName.setForeground(PrettyColor.WHITE);
		tfName.addFocusListener(new FeldFoucosListener(this));
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
		tfNachname.setForeground(PrettyColor.WHITE);
		tfNachname.addFocusListener(new FeldFoucosListener(this));
		cp.add(tfNachname);
		
		

		lblGeburtsdatum.setBounds((int)((frameWidth/2)-(120/2)), (tfNachname.getBounds().y+tfNachname.getBounds().height+(abstand)), 120, 30);
		lblGeburtsdatum.setText("Geburtsdatum:");
		lblGeburtsdatum.setHorizontalAlignment(SwingConstants.CENTER);
		lblGeburtsdatum.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblGeburtsdatum.setForeground(PrettyColor.GREEN);
		cp.add(lblGeburtsdatum);

		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Heute");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model,p);
		this.datepickerGeburtsdatum = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		this.datepickerGeburtsdatum.setBounds((int)((frameWidth/2)-(160/2)), (lblGeburtsdatum.getBounds().y+lblGeburtsdatum.getBounds().height+(abstand)), 160, 30);
		this.datepickerGeburtsdatum.setBorder(BorderFactory.createEmptyBorder());
		cp.add(datepickerGeburtsdatum);
		
		
//		ftfGeburtsdatum.setBounds((int)((frameWidth/2)-(150/2)), (lblGeburtsdatum.getBounds().y+lblGeburtsdatum.getBounds().height+(abstand/2)), 150, 20);
//		ftfGeburtsdatum.setBorder(BorderFactory.createEmptyBorder());
//		ftfGeburtsdatum.setBackground(PrettyColor.LITHEBLUE);
//		ftfGeburtsdatum.setForeground(PrettyColor.WHITE);
//		cp.add(ftfGeburtsdatum);
		
		lblPasswort.setBounds((int)((frameWidth/2)-(110/2)), (datepickerGeburtsdatum.getBounds().y+datepickerGeburtsdatum.getBounds().height+(abstand)), 110, 30);
		lblPasswort.setText("Passwort:");
		lblPasswort.setHorizontalAlignment(SwingConstants.CENTER);
		lblPasswort.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblPasswort.setForeground(PrettyColor.GREEN);
		cp.add(lblPasswort);
		
		tfPasswort.setBounds((int)((frameWidth/2)-(150/2)), (lblPasswort.getBounds().y+lblPasswort.getBounds().height+(abstand/2)), 150, 20);
		tfPasswort.setBorder(BorderFactory.createEmptyBorder());
		tfPasswort.setBackground(PrettyColor.LITHEBLUE);
		tfPasswort.setForeground(PrettyColor.WHITE);
		tfPasswort.addFocusListener(new FeldFoucosListener(this));
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
		tfPasswortw.setForeground(PrettyColor.WHITE);
		tfPasswortw.addFocusListener(new FeldFoucosListener(this));
		cp.add(tfPasswortw);
		
		
		
		btnRegistrieren.setBounds((int)((frameWidth/2)-(130/2)-(115/2)), (tfPasswortw.getBounds().y+tfPasswortw.getBounds().height+(abstand)), 130, 40);
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
				boolean runokay = true;
				if(e.getSource() == btnRegistrieren) {
					if(tfBenutzerName.getText().isEmpty()) {
						tfBenutzerName.setBackground(PrettyColor.RED);
						runokay = false;
					}
					if(tfName.getText().isEmpty()) {
						tfName.setBackground(PrettyColor.RED);
						runokay = false;
					}
					if(tfNachname.getText().isEmpty()) {
						tfNachname.setBackground(PrettyColor.RED);
						runokay = false;
					}
					if(tfPasswort.getText().isEmpty()) {
						tfPasswort.setBackground(PrettyColor.RED);
						runokay = false;
					}
					if(tfPasswortw.getText().isEmpty()) {
						tfPasswortw.setBackground(PrettyColor.RED);
						runokay = false;
					}
					
					if(!tfPasswort.getText().equals(tfPasswortw.getText())) {
						tfPasswortw.setBackground(PrettyColor.RED);
						runokay = false;
						return;
					}
					if(!runokay) {
						return;
					}
					
					if(Geteilte_Einkaufsliste.getMySQL().benutzerNameExists(tfBenutzerName.getText())) {
						tfBenutzerName.setBackground(PrettyColor.RED);
						if(Utils.debug)System.out.println("Der Benutzername: "+tfBenutzerName.getText()+" ist Vergeben!");
						return;
					}else {
						tfBenutzerName.setBackground(PrettyColor.LITHEBLUE);
						User.createUser(tfBenutzerName.getText(), tfNachname.getText(), tfName.getText(), ((Date)datepickerGeburtsdatum.getJDateInstantPanel().getModel().getValue()).getTime(), tfPasswort.getText());
						frame.dispose();
						new LoginScreen(tfBenutzerName.getText());
					}
					
					
					
				}
			}
		});
		cp.add(btnRegistrieren);
		
		btnZurück.setBounds((int) ((int)((frameWidth/2)+(115/2/2))), (btnRegistrieren.getBounds().y), 115, 40);
		btnZurück.setText("Zurück");
		btnZurück.setMargin(new Insets(2, 2, 2, 2));
		btnZurück.setFocusable(false);
		btnZurück.setBorderPainted(false);
		btnZurück.setContentAreaFilled(false);
		btnZurück.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		btnZurück.setForeground(PrettyColor.GREEN);
		btnZurück.setBackground(PrettyColor.BLUE);
		btnZurück.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new LoginScreen();
			}
		
		});
		cp.add(btnZurück);
		

		this.frame.setVisible(true);
	}
	

	
	
	public JFrame getFrame() {
		return frame;
	}

	public JLabel getLblBenutzerName() {
		return lblBenutzerName;
	}

	public JTextField getTfBenutzerName() {
		return tfBenutzerName;
	}

	public JLabel getLblName() {
		return lblName;
	}

	public JTextField getTfName() {
		return tfName;
	}

	public JLabel getLblNachname() {
		return lblNachname;
	}

	public JTextField getTfNachname() {
		return tfNachname;
	}

	public JPasswordField getTfPasswort() {
		return tfPasswort;
	}

	public JPasswordField getTfPasswortw() {
		return tfPasswortw;
	}

	public JLabel getLblPasswort() {
		return lblPasswort;
	}

	public JLabel getLblPasswortwiederholen() {
		return lblPasswortwiederholen;
	}

	public JLabel getLblRegestrierung() {
		return lblRegestrierung;
	}

	public JButton getBtnRegistrieren() {
		return btnRegistrieren;
	}

	public MaskFormatter getjFormattedTextField1MaskFormatter() {
		return jFormattedTextField1MaskFormatter;
	}

	public JDatePickerImpl getFtfGeburtsdatum() {
		return datepickerGeburtsdatum;
	}

	public JLabel getLblGeburtsdatum() {
		return lblGeburtsdatum;
	}

}
