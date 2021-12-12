package de.ge.gui.screens;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;

import de.ge.gui.listener.ListListener;
import de.ge.main.Geteilte_Einkaufsliste;
import de.ge.mysql.MySQL;
import de.ge.user.User;
import de.ge.utils.PrettyColor;

public class MainScreen {

	private JFrame frame = new JFrame();
	private JLabel lblListen = new JLabel();
	private JComboBox<String> cbbListen = new JComboBox<String>();
	private JButton btnListeerstellen = new JButton();
	private DefaultListModel listModel = new DefaultListModel();
	private JList lListen = new JList();
	private JScrollPane lListenScrollPane = new JScrollPane(getlListen());
	private JTextField tfListenName = new JTextField();
	private JButton btnListeLoeschen = new JButton();
	private JButton btnEinstellung = new JButton();
	private JButton btnHinzufuegen = new JButton();
	private JTable jTable = new JTable(0, 5);
	private DefaultTableModel jTableModel = (DefaultTableModel) jTable.getModel();
	private JScrollPane jTableScrollPane = new JScrollPane(jTable);
	private JButton btnOefnnen = new JButton();
	private JButton btnZurück = new JButton();
	private User u = Geteilte_Einkaufsliste.getUser();
	private MySQL mysql = Geteilte_Einkaufsliste.getMySQL();

	@SuppressWarnings("unchecked")
	public MainScreen() {

		this.frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		int frameWidth = 948;
		int frameHeight = 647;
		this.frame.setSize(frameWidth, frameHeight);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - this.frame.getSize().width) / 2;
		int y = (d.height - this.frame.getSize().height) / 2;
		this.frame.setLocation(x, y);
		this.frame.setTitle(Geteilte_Einkaufsliste.getUser().getBenutzername()+" -> Hallo, "+Geteilte_Einkaufsliste.getUser().getVorname());
		this.frame.setResizable(false);
		Container cp = this.frame.getContentPane();
		cp.setBackground(PrettyColor.BLUE);
		cp.setLayout(null);

		lblListen.setBounds(0, 0, 246, 80);
		lblListen.setText("Listen:");
		lblListen.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
		lblListen.setHorizontalTextPosition(SwingConstants.CENTER);
		lblListen.setHorizontalAlignment(SwingConstants.CENTER);
		lblListen.setBackground(PrettyColor.LITHEBLUE);
		lblListen.setForeground(PrettyColor.WHITE);
		lblListen.setOpaque(true);
		cp.add(lblListen);
		
		cbbListen.setBounds((int)(lblListen.getBounds().getX()), (int)(lblListen.getBounds().getY()+lblListen.getBounds().getHeight()), (int)lblListen.getBounds().getWidth(), 40);
		cbbListen.setBackground(PrettyColor.NICE);
		cbbListen.setForeground(PrettyColor.BLACK);
		cbbListen.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		cbbListen.addItem("Eigenelisten");
		cbbListen.addItem("Gruppenlisten");
		cbbListen.addItem("Eigenelisten & Gruppenlisten");
		cbbListen.setPreferredSize(new Dimension(30,200));
		cbbListen.addPopupMenuListener(new PopupMenuListener() {
			
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				cbbListen.setBounds((int)(lblListen.getBounds().getX()), (int)(lblListen.getBounds().getY()+lblListen.getBounds().getHeight()), 400, 40);
				
			}
			
			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				cbbListen.setBounds((int)(lblListen.getBounds().getX()), (int)(lblListen.getBounds().getY()+lblListen.getBounds().getHeight()), (int)lblListen.getBounds().getWidth(), 40);
				
			}
			
			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
				cbbListen.setBounds((int)(lblListen.getBounds().getX()), (int)(lblListen.getBounds().getY()+lblListen.getBounds().getHeight()), (int)lblListen.getBounds().getWidth(), 40);
				
			}
		});
		cbbListen.setBorder(BorderFactory.createEmptyBorder());
		cbbListen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String toswitch = cbbListen.getSelectedItem().toString();
				ArrayList<String> listen = u.getListenname();
				ArrayList<String> gruppenlisten = u.getGruppenListenname();
				switch (toswitch) {
				case "Eigenelisten":
						for(int i = listModel.getSize();i > 0;i--) {
							listModel.remove(i-1);
						}
						for (int i = 0; i < listen.size(); i++) {
							listModel.add(i, listen.get(i));
						}
					break;
				case "Gruppenlisten":
					for(int i = listModel.getSize();i > 0;i--) {
						listModel.remove(i-1);
					}
					for (int i = 0; i < gruppenlisten.size(); i++) {
						listModel.add(i, gruppenlisten.get(i));
					}
					break;
				case "Eigenelisten & Gruppenlisten":
					for(int i = listModel.getSize();i > 0;i--) {
					listModel.remove(i-1);
					}	
					for (int i = 0; i < listen.size(); i++) {
						listModel.add(i, listen.get(i));
					}
					for (int i = 0; i < gruppenlisten.size(); i++) {
						listModel.add(i, gruppenlisten.get(i));
					}
					break;

				default:
					break;
				}
				
			}
		});
		cbbListen.setSelectedIndex(0);
		cp.add(cbbListen);
		
		ArrayList<String> listen = u.getListenname();
		for (int i = 0; i < listen.size(); i++) {
			listModel.add(i, listen.get(i));
		}
		this.lListen.addListSelectionListener(new ListListener(this));
		this.lListen.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		this.lListen.setBackground(PrettyColor.LITHEBLUE);
		this.lListen.setForeground(PrettyColor.WHITE);
		this.lListen.setModel(listModel);
		lListenScrollPane.setBounds((int)(lblListen.getBounds().getX()), (int)(cbbListen.getBounds().getY()+cbbListen.getBounds().getHeight()), (int)lblListen.getBounds().getWidth(), 340);
		lListenScrollPane.setBorder(BorderFactory.createEmptyBorder());
		cp.add(lListenScrollPane);
		tfListenName.setBounds((int)(lblListen.getBounds().getX()), (int)(lListenScrollPane.getBounds().getY()+lListenScrollPane.getBounds().getHeight()), 246, 60);
		tfListenName.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		tfListenName.setBackground(PrettyColor.LITHEBLUE);
		tfListenName.setForeground(PrettyColor.WHITE);
		tfListenName.setBorder(BorderFactory.createEmptyBorder());
		cp.add(tfListenName);
		
		
		btnListeerstellen.setBounds((int)(lblListen.getBounds().getX()+5), (int)(tfListenName.getBounds().getY()+tfListenName.getBounds().getHeight()+5), 115, 33);
		btnListeerstellen.setText("Liste erstellen");
		btnListeerstellen.setMargin(new Insets(2, 2, 2, 2));
		btnListeerstellen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnListeerstellen_ActionPerformed(evt);
			}
		});
		btnListeerstellen.setHorizontalTextPosition(SwingConstants.CENTER);
		btnListeerstellen.setBackground(PrettyColor.GREEN);
		btnListeerstellen.setForeground(PrettyColor.WHITE);
		btnListeerstellen.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		cp.add(btnListeerstellen);
		
		btnListeLoeschen.setBounds((int)(btnListeerstellen.getBounds().getX()+btnListeerstellen.getBounds().getWidth()+5), (int)(tfListenName.getBounds().getY()+tfListenName.getBounds().getHeight()+5), 115, 33);
		btnListeLoeschen.setText("Liste Löschen");
		btnListeLoeschen.setMargin(new Insets(2, 2, 2, 2));
		btnListeLoeschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnListeLoeschen_ActionPerformed(evt);
			}
		});
		btnListeLoeschen.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnListeLoeschen.setBackground(PrettyColor.RED);
		btnListeLoeschen.setForeground(PrettyColor.WHITE);
		cp.add(btnListeLoeschen);
		
		btnOefnnen.setBounds((int)(btnListeerstellen.getBounds().getX()), (int)(btnListeLoeschen.getBounds().getY()+btnListeLoeschen.getBounds().getHeight()+5), 115, 33);
		btnOefnnen.setText("Öfnnen");
		btnOefnnen.setMargin(new Insets(2, 2, 2, 2));
		btnOefnnen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnOefnnen_ActionPerformed(evt);
			}
		});
		btnOefnnen.setBackground(PrettyColor.LITHEBLUE);
		btnOefnnen.setForeground(PrettyColor.WHITE);
		btnOefnnen.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		cp.add(btnOefnnen);
		
		
		btnEinstellung.setBounds((int)(btnOefnnen.getBounds().getX()+btnOefnnen.getBounds().getWidth()+5), (int)(btnListeerstellen.getBounds().getY()+btnListeerstellen.getBounds().getHeight()+5), 115, 33);
		btnEinstellung.setText("Einstellung");
		btnEinstellung.setMargin(new Insets(2, 2, 2, 2));
		btnEinstellung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnEinstellung_ActionPerformed(evt);
			}
		});
		btnEinstellung.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnEinstellung.setBackground(PrettyColor.LITHEBLUE);
		btnEinstellung.setForeground(PrettyColor.WHITE);
		cp.add(btnEinstellung);
		
		
		jTableScrollPane.setBounds((int)(lblListen.getBounds().getX()+lblListen.getBounds().getWidth()+10), 10, 644, 502);
		jTable.setRowHeight(30);
		jTable.setRowSelectionAllowed(false);
		jTable.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		jTable.setAutoCreateRowSorter(false);
		jTable.getColumnModel().getColumn(0).setHeaderValue("Artikel");
		jTable.getColumnModel().getColumn(1).setHeaderValue("Bezeichnung");
		jTable.getColumnModel().getColumn(2).setHeaderValue("Stk. Zahl");
		jTable.getColumnModel().getColumn(3).setHeaderValue("Preis je. 1");
		jTable.getColumnModel().getColumn(4).setHeaderValue("URL-Link");
		cp.add(jTableScrollPane);

		btnHinzufuegen.setBounds(330, (int)(btnEinstellung.getBounds().getY()), 115, 33);
		btnHinzufuegen.setText("Hinzufügen");
		btnHinzufuegen.setMargin(new Insets(2, 2, 2, 2));
		btnHinzufuegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnHinzufuegen_ActionPerformed(e);
			}
		});
		btnHinzufuegen.setBackground(PrettyColor.LITHEBLUE);
		btnHinzufuegen.setForeground(PrettyColor.WHITE);
		btnHinzufuegen.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		cp.add(btnHinzufuegen);
		
		
		btnZurück.setBounds(660, (int)(btnEinstellung.getBounds().getY()), 115, 33);
		btnZurück.setText("Logout");
		btnZurück.setMargin(new Insets(2, 2, 2, 2));
		btnZurück.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnZurück_ActionPerformed(e);
			}
		});
		btnZurück.setBackground(PrettyColor.LITHEBLUE);
		btnZurück.setForeground(PrettyColor.WHITE);
		btnZurück.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		cp.add(btnZurück);

		this.frame.setVisible(true);
	}
	public JTable getjTable1() {
		return jTable;
	}
	public void setjTable1(JTable jTable1) {
		this.jTable = jTable1;
	}
	public DefaultTableModel getjTable1Mode() {
		return jTableModel;
	}
	
	public void btnZurück_ActionPerformed(ActionEvent e) {
		if (e.getSource() == btnZurück) {
			frame.dispose();
			new LoginScreen();
		}
	}

	public void btnHinzufuegen_ActionPerformed(ActionEvent e) {
		if (e.getSource() == btnHinzufuegen) {
			if(getlListen().getSelectedValue() != null) {
				frame.dispose();
				new ArtikelScreen(getlListen().getSelectedValue().toString(),this);
			}else {
				//Frag ihn ob er eine Liste anlegen möchte :P
			}
		}
	}

	public void btnListeerstellen_ActionPerformed(ActionEvent e) {
		if (e.getSource() == btnListeerstellen) {
			String listenname = tfListenName.getText();
			if(this.cbbListen.getSelectedItem().toString().equals("Eigenelisten")) {
				if(!u.hasListname(listenname)) {
					u.createEinkaufsliste(listenname);
					listModel.addElement(listenname);
				}
			}else if(this.cbbListen.getSelectedItem().toString().equals("Gruppenlisten")) {
//				if(!u.hasListname(listenname)) {
//					u.createEinkaufsliste(listenname);
//					listModel.addElement(listenname);
//				}
			}
		}
	}

	public void btnListeLoeschen_ActionPerformed(ActionEvent e) {
		if (e.getSource() == btnListeLoeschen) {

			listModel.removeElement(tfListenName.getText());
			// listModel.removeElementAt(Integer.valueOf(tfListenName.getText()));
			// listModel.remove(index);

		}

	}

	public void btnEinstellung_ActionPerformed(ActionEvent e) {
		if (e.getSource() == btnEinstellung) {
			this.frame.dispose();
			new InviteScreen();
		}
	}

	public void btnOefnnen_ActionPerformed(ActionEvent e) {
		if (e.getSource() == btnOefnnen) {
			ListListener.setArtikelOutOfListInTabel(this.lListen.getSelectedValue().toString());
		}

	}
	public JList getlListen() {
		return lListen;
	}
	public void setlListen(JList lListen) {
		this.lListen = lListen;
	}

}
