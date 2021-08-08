package Vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import javax.swing.JScrollPane;

import Controleur.Connexion;
import Controleur.ControlleCategorie;
import Controleur.ControlleProduit;
import Modele.ModeleProduit;
import Modele.ModeleCategorie;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings({ "serial", "unused" })
public class VueCategorie extends JFrame {

	private JPanel contentPane;
	private JTextField tfNom;
	private JTextField tfDesc;
	private static Connection conn;
	private DefaultTableModel tm=new DefaultTableModel();
	private JTable table;
	private JTextField tfId;


	public static void main(String[] args) {

					VueCategorie frame = new VueCategorie();
					frame.setVisible(true);

	}

	public VueCategorie() {
		conn=Connexion.connecter();
		setTitle("Cat\u00E9gorie");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\MUSTAFA BA\\Desktop\\category.png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 561, 318);
		setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenuItem mntmProduit = new JMenuItem("Produit");
		mntmProduit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VueProduit p=new VueProduit();
				p.setVisible(true);
				close();

			}
		});
		
		JMenuItem mntmAccueil = new JMenuItem("Accueil");
		mntmAccueil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Accueil p=new Accueil();
				p.setVisible(true);
				close();

			}
		});
		menuBar.add(mntmAccueil);
		menuBar.add(mntmProduit);
		
		JMenuItem mntmVente = new JMenuItem("Fournir");
		mntmVente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VueFournir v=new VueFournir();
				v.setVisible(true);
				close();

			}
		});
		menuBar.add(mntmVente);
		
		JMenuItem menuItem = new JMenuItem("Quitter");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		menuBar.add(menuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(panel, BorderLayout.CENTER);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				preparerAjout();
			}
		});
		btnAjouter.setIcon(new ImageIcon("D:\\Stoufa\\ISET\\Java\\Java\\Projet\\img\\Plus.png"));
		btnAjouter.setBounds(10, 11, 128, 32);
		panel.add(btnAjouter);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				preparerModif();
			}
		});
		btnModifier.setIcon(new ImageIcon("D:\\Stoufa\\ISET\\Java\\Java\\Projet\\img\\modify.png"));
		btnModifier.setBounds(10, 45, 128, 32);
		panel.add(btnModifier);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prepareSupp();
			}
		});
		btnSupprimer.setIcon(new ImageIcon("D:\\Stoufa\\ISET\\Java\\Java\\Projet\\img\\delete.png"));
		btnSupprimer.setBounds(10, 79, 128, 32);
		panel.add(btnSupprimer);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(187, 11, 256, 32);
		panel.add(lblNom);
		
		tfNom = new JTextField();
		tfNom.setColumns(10);
		tfNom.setBounds(257, 17, 186, 20);
		panel.add(tfNom);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(187, 36, 256, 50);
		panel.add(lblDescription);
		
		tfDesc = new JTextField();
		tfDesc.setColumns(10);
		tfDesc.setBounds(257, 51, 186, 20);
		panel.add(tfDesc);

		
		JButton button_3 = new JButton("Annuler");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		button_3.setIcon(new ImageIcon("D:\\Stoufa\\ISET\\Java\\Java\\Projet\\img\\back.png"));
		button_3.setBounds(422, 213, 110, 32);
		panel.add(button_3);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				afficherLigne();
			}
		});
		table.setBounds(0, 0, 515, 1);
		panel.add(table);
		ResultSet rs=ModeleCategorie.afficher(conn);
		table.setModel(DbUtils.resultSetToTableModel(rs));		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(148, 97, 378, 105);
		panel.add(scrollPane);
		
		tfId = new JTextField();
		tfId.setCaretColor(Color.WHITE);
		tfId.setEnabled(false);
		tfId.setEditable(false);
		tfId.setBounds(453, 65, -10, 6);
		panel.add(tfId);
		tfId.setColumns(10);
		

	}
	
	protected void prepareSupp() {
		ControlleCategorie.supression((com.mysql.jdbc.Connection) conn,Integer.parseInt(tfId.getText()));
		tfId.setText("");
		tfNom.setText("");
		tfDesc.setText("");
		ResultSet rs=ModeleCategorie.afficher(conn);
		table.setModel(DbUtils.resultSetToTableModel(rs));		
	}

	protected void preparerModif() {
			ControlleCategorie.modification((com.mysql.jdbc.Connection) conn,Integer.parseInt(tfId.getText()),
					tfNom.getText(),tfDesc.getText());
			tfId.setText("");
			tfNom.setText("");
			tfDesc.setText("");
			ResultSet rs=ModeleCategorie.afficher(conn);
			table.setModel(DbUtils.resultSetToTableModel(rs));
			/*JOptionPane op=new JOptionPane();
			op.setLocation(new Point(20, 20));
			op.showMessageDialog(this,"Insertion avec succee","OK",JOptionPane.OK_CANCEL_OPTION);*/			
	}

	protected void preparerAjout() {
		ControlleCategorie.insertion(conn,
				tfNom.getText(),
				tfDesc.getText());
		tfNom.setText("");
		tfDesc.setText("");

		ResultSet rs=ModeleCategorie.afficher(conn);
		table.setModel(DbUtils.resultSetToTableModel(rs));
		/*JOptionPane op=new JOptionPane();
		op.setLocation(new Point(20, 20));
		op.showMessageDialog(this,"Insertion avec succee","OK",JOptionPane.OK_CANCEL_OPTION);	*/		
	}

	protected void afficherLigne() {
		int n=table.getSelectedRow();
		tfId.setText(table.getValueAt(n, 0).toString());
		tfNom.setText(table.getValueAt(n, 1).toString());
		tfDesc.setText(table.getValueAt(n, 2).toString());	
	}

	public void close(){
		WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
	    Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
	}
}
