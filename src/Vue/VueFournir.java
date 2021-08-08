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
import java.awt.print.*;
import java.text.*;

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
import Modele.ModeleFournir;
import Modele.ModeleProduit;

@SuppressWarnings({ "serial", "unused" })
public class VueFournir extends JFrame {

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


	public static void main(String[] args) {

					VueFournir frame = new VueFournir();
					frame.setVisible(true);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public VueFournir() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		conn=Connexion.connecter();
		setBounds(100, 100, 598, 465);
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
		
		JLabel lblDateVente = new JLabel("Date Fournissement");
		lblDateVente.setBounds(250, 63, 118, 14);
		contentPane.add(lblDateVente);
		
		tfDate = new JTextField();
		tfDate.setEnabled(false);
		tfDate.setBounds(378, 60, 168, 20);
		contentPane.add(tfDate);
		tfDate.setColumns(10);
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		String d=sdf.format(new Date());
		tfDate.setText(d);
		
		JLabel lblLesArticles = new JLabel("Les Produit:");
		lblLesArticles.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLesArticles.setBounds(26, 94, 106, 28);
		contentPane.add(lblLesArticles);
		
		JLabel lblIdArticle = new JLabel("Produit:");
		lblIdArticle.setBounds(26, 133, 91, 14);
		contentPane.add(lblIdArticle);
		
		JLabel lblQuantit = new JLabel("Quantit\u00E9:");
		lblQuantit.setBounds(250, 133, 74, 14);
		contentPane.add(lblQuantit);
		
		tfQte = new JTextField();
		tfQte.setBounds(344, 130, 86, 20);
		contentPane.add(tfQte);
		tfQte.setColumns(10);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				afficher();
				updateQte();
				preparerEnregistrer();

			}
		});
		btnAjouter.setIcon(new ImageIcon("D:\\Stoufa\\ISET\\Java\\Java\\Projet\\img\\Plus.png"));
		btnAjouter.setBounds(440, 124, 106, 33);
		contentPane.add(btnAjouter);
		
		table = new JTable();
		table.setBounds(113, 158, 255, 0);
		contentPane.add(table);
		tm.setColumnIdentifiers(new String[]{"Id Article","Designation","Quantité ","Point de Vente"});
		table.setModel(tm);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(45, 168, 501, 154);
		contentPane.add(scrollPane);
		
		JButton btnImprimer = new JButton("Imprimer");
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imprimer();

			}
		});
		btnImprimer.setIcon(new ImageIcon("D:\\Stoufa\\ISET\\Java\\Java\\Projet\\img\\prin.png"));
		btnImprimer.setBounds(354, 359, 121, 32);
		contentPane.add(btnImprimer);
		
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
		button.setBounds(89, 359, 121, 32);
		contentPane.add(button);
		
		cbxPV = new JComboBox();
		cbxPV.setBounds(111, 60, 118, 20);
		contentPane.add(cbxPV);
		mPV=ModeleFournir.remplirPV(conn, mPV);
		cbxPV.setModel(mPV);
		
		JLabel lblFournissement = new JLabel("Approvisionnement");
		lblFournissement.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblFournissement.setBounds(150, 11, 280, 35);
		contentPane.add(lblFournissement);
	
	}
	
	protected void imprimer() {
		MessageFormat header=new MessageFormat("Approvisionnement");
		MessageFormat footer=new MessageFormat(tfDate.getText());
		try{
			table.print(JTable.PrintMode.NORMAL,header,footer);
		}catch(java.awt.print.PrinterException ex){
			ex.getMessage();
		}		
	}

	protected void preparerEnregistrer() {
		ControlleFournir.insertion(conn, tfDate.getText()
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

	@SuppressWarnings("static-access")
	protected void updateQte() {
			
				try {
					String codeC=cbxId.getSelectedItem().toString();
					int qte1=Integer.parseInt(tfQte.getText());
					String sql="update produit set qteStock=qteStock-'"+qte1+"' where codeProd='"+codeC+"'";
					PreparedStatement pst=conn.prepareStatement(sql);
					pst.executeUpdate();
					pst.close();
					
				} catch (SQLException ex) {
					ex.printStackTrace();
				}		
				JOptionPane op=new JOptionPane();
				op.setLocation(new Point(20, 20));
				op.showMessageDialog(this,"Quantité a été mise à jour","OK",JOptionPane.OK_CANCEL_OPTION);
		}		



	public void close(){
		WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
	    Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
	}
}
