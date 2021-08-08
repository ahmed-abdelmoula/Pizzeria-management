package Vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JMenu;

import Controleur.Connexion;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JButton;
@SuppressWarnings({ "serial", "unused" })
public class Accueil extends JFrame {

	private JPanel contentPane;
	private Connection conn;

	public static void main(String[] args) {

					Accueil frame = new Accueil();
					frame.setVisible(true);

	}

	public Accueil() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\MUSTAFA BA\\Desktop\\shop.png"));
		setTitle("Accueil");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 642, 648);
		setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenuItem mntmProduit = new JMenuItem("Produit");
		mntmProduit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VueProduit p=new VueProduit();
				p.setVisible(true);
			}
		});
		menuBar.add(mntmProduit);
		
		JMenuItem mntmCatgorie = new JMenuItem("Cat\u00E9gorie");
		mntmCatgorie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VueCategorie c=new VueCategorie();
				c.setVisible(true);
			}
		});
		menuBar.add(mntmCatgorie);
		
		JMenuItem mntmVente = new JMenuItem("Fournir");
		mntmVente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VueFournir v=new VueFournir();
				v.setVisible(true);
			}
		});
		menuBar.add(mntmVente);
		
		JMenuItem menuItem = new JMenuItem("Quitter");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		JMenuItem mntmRecette = new JMenuItem("Recette");
		mntmRecette.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VueAjoutRecette r=null;
				try {
					r = new VueAjoutRecette();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				r.setVisible(true);
			}
		});
		menuBar.add(mntmRecette);

		menuBar.add(menuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCalRec = new JButton("");
		btnCalRec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RechRecette r=new RechRecette();
				r.setVisible(true);
			}
		});
		btnCalRec.setIcon(new ImageIcon("D:\\Stoufa\\ISET\\Java\\Java\\MiniProjet\\img\\calculator.png"));
		btnCalRec.setBounds(147, 117, 92, 67);
		contentPane.add(btnCalRec);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EnvEmail ee;
				try {
					ee = new EnvEmail();
					ee.setVisible(true);

				} catch (AddressException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (MessagingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setIcon(new ImageIcon("D:\\Stoufa\\ISET\\Java\\Java\\MiniProjet\\img\\email.png"));
		button.setBounds(388, 117, 92, 67);
		contentPane.add(button);
		
		
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 626, 626);
		contentPane.add(label);
		Image img1=new ImageIcon(this.getClass().getResource("/aaa.png")).getImage();
		label.setIcon(new ImageIcon(img1));
		
		
	}
}
