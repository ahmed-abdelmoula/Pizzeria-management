package Vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Point;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import java.awt.Toolkit;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.Rectangle;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import Controleur.*;
import Modele.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings({ "serial", "unused" })
public class VueProduit extends JFrame {

	private JPanel contentPane;
	private JTextField tfDes;
	private JTable table;
	private static Connection conn;
	private BufferedImage img;
	private JFileChooser chooser;
	private File file ; 
    private JLabel label;
    private String path;
    @SuppressWarnings("rawtypes")
	private JComboBox cbxCat;
	private DefaultComboBoxModel<String> mCat;
	private JTextField tfCode;
	private JTextField tfPrixVent;
	private JTextField tfQte;


	public static void main(String[] args) {

					VueProduit frame = new VueProduit();
					frame.setVisible(true);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public VueProduit() {
		conn=Connexion.connecter();
		
		setTitle("Produit");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\MUSTAFA BA\\Desktop\\Box.png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 633, 472);
		setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenuItem mntmProduit = new JMenuItem("Accueil");
		mntmProduit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Accueil p=new Accueil();
				p.setVisible(true);
				close();

			}
		});
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
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setHorizontalAlignment(SwingConstants.LEFT);
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				preparerAjout();

			}
		});
		btnAjouter.setIcon(new ImageIcon("D:\\Stoufa\\ISET\\Java\\Java\\Projet\\img\\Plus.png"));
		btnAjouter.setBounds(10, 21, 124, 32);
		contentPane.add(btnAjouter);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				preparerModif();
			}
		});
		btnModifier.setHorizontalAlignment(SwingConstants.LEFT);
		btnModifier.setIcon(new ImageIcon("D:\\Stoufa\\ISET\\Java\\Java\\Projet\\img\\modify.png"));
		btnModifier.setBounds(10, 64, 124, 32);
		contentPane.add(btnModifier);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				preparerSupp();
			}
		});
		btnSupprimer.setHorizontalAlignment(SwingConstants.LEFT);
		btnSupprimer.setIcon(new ImageIcon("D:\\Stoufa\\ISET\\Java\\Java\\Projet\\img\\delete.png"));
		btnSupprimer.setBounds(10, 107, 124, 32);
		contentPane.add(btnSupprimer);
		
		JLabel lblDesignation = new JLabel("Designation");
		lblDesignation.setBounds(159, 72, 89, 32);
		contentPane.add(lblDesignation);
		
		tfDes = new JTextField();
		tfDes.setBounds(246, 78, 124, 20);
		contentPane.add(tfDes);
		tfDes.setColumns(10);
		
		JLabel lblImage = new JLabel("Image");
		lblImage.setBounds(403, 89, 53, 50);
		contentPane.add(lblImage);
		
		JLabel lblCtgorie = new JLabel("Cat\u00E9gorie");
		lblCtgorie.setBounds(403, 53, 67, 50);
		contentPane.add(lblCtgorie);
		
		cbxCat = new JComboBox();
		cbxCat.setBounds(478, 73, 124, 20);
		contentPane.add(cbxCat);
		mCat=ModeleProduit.remplirCat(conn,mCat);
		cbxCat.setModel(mCat);
		
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				afficherLigne();

			}
		});
		table.setBounds(1, 21, 32, 85);
		contentPane.add(table);
		ResultSet rs=ModeleProduit.afficher(conn);
		table.setModel(DbUtils.resultSetToTableModel(rs));
		
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					close();
			}
		});
		btnAnnuler.setIcon(new ImageIcon("D:\\Stoufa\\ISET\\Java\\Java\\Projet\\img\\back.png"));
		btnAnnuler.setBounds(492, 376, 110, 32);
		contentPane.add(btnAnnuler);
		
		JButton btnImporter = new JButton("");
		btnImporter.setIcon(new ImageIcon("D:\\Stoufa\\ISET\\Java\\Java\\Projet\\img\\upload.png"));
		btnImporter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				importer();
			}
		});
		btnImporter.setBounds(403, 125, 32, 32);
		contentPane.add(btnImporter);
		
		label = new JLabel("");
		label.setBackground(Color.GRAY);
		label.setForeground(Color.BLACK);
		label.setBounds(478, 102, 103, 92);
		contentPane.add(label);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(33, 219, 569, 120);
		contentPane.add(scrollPane);
		
		JLabel lblCode = new JLabel("Code");
		lblCode.setBounds(164, 37, 46, 14);
		contentPane.add(lblCode);
		
		tfCode = new JTextField();
		tfCode.setBounds(246, 35, 124, 20);
		contentPane.add(tfCode);
		tfCode.setColumns(10);
		
		JLabel lblPrixDeVente = new JLabel("Prix de Vente");
		lblPrixDeVente.setBounds(158, 125, 110, 14);
		contentPane.add(lblPrixDeVente);
		
		tfPrixVent = new JTextField();
		tfPrixVent.setBounds(246, 119, 124, 20);
		contentPane.add(tfPrixVent);
		tfPrixVent.setColumns(10);
		
		JLabel lblQuantit = new JLabel("Quantit\u00E9");
		lblQuantit.setBounds(403, 37, 89, 14);
		contentPane.add(lblQuantit);
		
		tfQte = new JTextField();
		tfQte.setBounds(478, 34, 124, 20);
		contentPane.add(tfQte);
		tfQte.setColumns(10);
		
		JButton btnRechercher = new JButton("Rechercher");
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RechCat rc=new RechCat();
				rc.setVisible(true);
			}
		});
		btnRechercher.setIcon(new ImageIcon("D:\\Stoufa\\ISET\\Java\\Java\\MiniProjet\\img\\Zoom.png"));
		btnRechercher.setHorizontalAlignment(SwingConstants.LEFT);
		btnRechercher.setBounds(10, 150, 124, 32);
		contentPane.add(btnRechercher);
	}



	protected void preparerModif() {
		ControlleProduit.modification((com.mysql.jdbc.Connection) conn,Integer.parseInt(tfCode.getText()),
				Integer.parseInt(tfQte.getText()),
				Double.parseDouble(tfPrixVent.getText()),
				tfDes.getText(),
				path,
				cbxCat.getSelectedItem().toString());
		tfCode.setText("");
		tfPrixVent.setText("");
		tfDes.setText("");
		label.setIcon(null);
		cbxCat.setSelectedIndex(0);
		ResultSet rs=ModeleProduit.afficher(conn);
		table.setModel(DbUtils.resultSetToTableModel(rs));
		/*JOptionPane op=new JOptionPane();
		op.setLocation(new Point(20, 20));
		op.showMessageDialog(this,"Insertion avec succee","OK",JOptionPane.OK_CANCEL_OPTION);*/		
	}

	protected void preparerSupp() {
		ControlleProduit.supression((com.mysql.jdbc.Connection) conn,Integer.parseInt(tfCode.getText()));
		tfCode.setText("");
		tfPrixVent.setText("");
		tfDes.setText("");
		tfQte.setText("");
		label.setIcon(null);
		cbxCat.setSelectedIndex(0);
		ResultSet rs=ModeleProduit.afficher(conn);
		table.setModel(DbUtils.resultSetToTableModel(rs));		
	}

	protected void afficherLigne() {
		int n=table.getSelectedRow();
		tfCode.setText(table.getValueAt(n, 0).toString());
		tfDes.setText(table.getValueAt(n, 1).toString());
		tfPrixVent.setText(table.getValueAt(n, 2).toString());
		path=table.getValueAt(n, 3).toString();
		cbxCat.setSelectedItem(table.getValueAt(n, 4));
		tfQte.setText(table.getValueAt(n, 5).toString());		
	}

	protected void preparerAjout() {
		ControlleProduit.insertion(conn,Integer.parseInt(tfCode.getText()),
				Integer.parseInt(tfQte.getText()),
				Double.parseDouble(tfPrixVent.getText()),
				tfDes.getText(),
				path,
				cbxCat.getSelectedItem().toString());
		tfCode.setText("");
		tfPrixVent.setText("");
		tfDes.setText("");
		tfQte.setText("");
		label.setIcon(null);
		cbxCat.setSelectedIndex(0);
		ResultSet rs=ModeleProduit.afficher(conn);
		table.setModel(DbUtils.resultSetToTableModel(rs));
		/*JOptionPane op=new JOptionPane();
		op.setLocation(new Point(20, 20));
		op.showMessageDialog(this,"Insertion avec succee","OK",JOptionPane.OK_CANCEL_OPTION);	*/
	}

	protected void importer() {

		 JFileChooser file = new JFileChooser();
         file.setCurrentDirectory(new File(System.getProperty("user.home")));
         FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
         file.addChoosableFileFilter(filter);
         int result = file.showSaveDialog(null);
         if(result == JFileChooser.APPROVE_OPTION){
             File selectedFile = file.getSelectedFile();
             path = selectedFile.getAbsolutePath();
             label.setIcon(ResizeImage(path));
         }

         else if(result == JFileChooser.CANCEL_OPTION){
             System.out.println("No File Select");
         }
	}
	public ImageIcon ResizeImage(String ImagePath)
    {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }

	public void close(){
		WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
	    Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
	}
}
