package Vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;

import net.proteanit.sql.DbUtils;

import Controleur.Connexion;
import Controleur.ControlleFournir;
import Controleur.ControlleRecette;
import Modele.ModeleFournir;
import Modele.ModeleProduit;

@SuppressWarnings({ "serial", "unused" })
public class VueAjoutRecette extends JFrame {

	private JPanel contentPane;
	private JTextField tfDate;
	private JTextField tfQte;
	private JTable table;
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String URL_BD = "jdbc:mysql://localhost/patisserie";
	static final String UTILISATEUR = "root";
	static final String MOT_PASS = "";
	private Connection conn;
	private DefaultComboBoxModel<String> mId;
	private DefaultComboBoxModel<String> mPV;
	@SuppressWarnings("rawtypes")
	private JComboBox cbxId;
	private DefaultTableModel tm=new DefaultTableModel();
	private JComboBox cbxPV;
	SimpleDateFormat sdf;
	java.sql.Date sqlDate;
	Date d;

	public static void main(String[] args) throws ParseException {

					VueAjoutRecette frame = new VueAjoutRecette();
					frame.setVisible(true);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public VueAjoutRecette() throws ParseException {
		conn=Connexion.connecter();
		setBounds(100, 100, 613, 465);
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
		
		JMenuItem mntmCatgorie = new JMenuItem("Cat\u00E9gorie");
		mntmCatgorie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VueCategorie c=new VueCategorie();
				c.setVisible(true);
				close();

			}
		});
		menuBar.add(mntmCatgorie);
		
		JMenuItem menuItem = new JMenuItem("Quitter");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menuBar.add(menuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNvente = new JLabel("Point de Vente:");
		lblNvente.setBounds(26, 63, 106, 14);
		contentPane.add(lblNvente);
		
		JLabel lblDateVente = new JLabel("Date Recette");
		lblDateVente.setBounds(250, 63, 118, 14);
		contentPane.add(lblDateVente);
		
		tfDate = new JTextField();
		tfDate.setEnabled(false);
		tfDate.setBounds(378, 60, 168, 20);
		contentPane.add(tfDate);
		tfDate.setColumns(10);
		d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
		tfDate.setText(sdf.format(d).toString());
		
		JLabel lblLesArticles = new JLabel("Les Produit:");
		lblLesArticles.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLesArticles.setBounds(26, 94, 106, 28);
		contentPane.add(lblLesArticles);
		
		JLabel lblIdArticle = new JLabel("Produit:");
		lblIdArticle.setBounds(26, 133, 91, 14);
		contentPane.add(lblIdArticle);
		
		JLabel lblQuantit = new JLabel("Quantit\u00E9 Vendue:");
		lblQuantit.setBounds(250, 133, 109, 14);
		contentPane.add(lblQuantit);
		
		tfQte = new JTextField();
		tfQte.setBounds(369, 130, 86, 20);
		contentPane.add(tfQte);
		tfQte.setColumns(10);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				afficher();
				try {
					preparerEnregistrer();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnAjouter.setIcon(new ImageIcon("D:\\Stoufa\\ISET\\Java\\Java\\Projet\\img\\Plus.png"));
		btnAjouter.setBounds(465, 124, 106, 33);
		contentPane.add(btnAjouter);
		
		table = new JTable();
		table.setBounds(113, 158, 255, 0);
		contentPane.add(table);
		tm.setColumnIdentifiers(new String[]{"Id Article","Designation","Quantité Vendue","Point de Vente"});
		table.setModel(tm);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(45, 168, 501, 154);
		contentPane.add(scrollPane);
		
		cbxId = new JComboBox();
		cbxId.setBounds(89, 130, 140, 20);
		contentPane.add(cbxId);
		mId=ModeleFournir.remplirCat(conn,mId);
		cbxId.setModel(mId);
		
		JButton button = new JButton("Annuler");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		button.setIcon(new ImageIcon("D:\\Stoufa\\ISET\\Java\\Java\\Projet\\img\\back.png"));
		button.setBounds(425, 359, 121, 32);
		contentPane.add(button);
		
		cbxPV = new JComboBox();
		cbxPV.setBounds(111, 60, 118, 20);
		contentPane.add(cbxPV);
		mPV=ModeleFournir.remplirPV(conn, mPV);
		cbxPV.setModel(mPV);
		
		JLabel lblFournissement = new JLabel("Recette du Jour");
		lblFournissement.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblFournissement.setBounds(181, 11, 238, 35);
		contentPane.add(lblFournissement);
	
	}
	
	protected void preparerEnregistrer() throws ParseException {
		ControlleRecette.insertion(conn, d
				, Integer.parseInt(tfQte.getText())
				, Integer.parseInt((String) cbxId.getSelectedItem())
				, Integer.parseInt((String) cbxPV.getSelectedItem()));
		tfQte.setText("");
		cbxId.setSelectedIndex(0);
		cbxPV.setSelectedIndex(0);
		/*for(int i=0;i<tm.getRowCount();i++)
			tm.removeRow(0);*/
		
	}

	
	protected void afficher() {
		String codeC=cbxId.getSelectedItem().toString();
		String codePV=cbxPV.getSelectedItem().toString();
		int qte=Integer.parseInt(tfQte.getText());
		
		try{
		String sql="Select * From produit where codeProd='"+codeC+"'";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		while(rs.next()){
		String des=rs.getString(2);
		tm.addRow(new Object[]{codeC,des,qte,codePV});
		}
		st.close();
		rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	



	public void close(){
		WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
	    Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
	}
}
