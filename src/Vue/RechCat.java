package Vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import Controleur.Connexion;
import Modele.ModeleProduit;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RechCat extends JFrame {

	private JPanel contentPane;
	private static Connection conn;
	private JTable table;
	private JTextField tfCat;
	private JLabel lblRechrecheParCatgorie;
	private JButton button;



	public static void main(String[] args) {

					RechCat frame = new RechCat();
					frame.setVisible(true);

	}

	public RechCat() {
		conn=Connexion.connecter();
		setBounds(100, 100, 529, 288);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tfCat = new JTextField();
		tfCat.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				ResultSet rs=ModeleProduit.afficherCat(conn,tfCat.getText());
				table.setModel(DbUtils.resultSetToTableModel(rs));

			}
		});
		
		lblRechrecheParCatgorie = new JLabel("Rechreche par Cat\u00E9gorie");
		lblRechrecheParCatgorie.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblRechrecheParCatgorie.setBounds(31, 11, 305, 35);
		contentPane.add(lblRechrecheParCatgorie);
		tfCat.setBounds(329, 24, 149, 20);
		contentPane.add(tfCat);
		tfCat.setColumns(10);
		
		table = new JTable();
		table.setBounds(10, 28, 422, 64);
		contentPane.add(table);
		ResultSet rs=ModeleProduit.afficher(conn);
		table.setModel(DbUtils.resultSetToTableModel(rs));
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(36, 91, 442, 91);
		contentPane.add(scrollPane);
		
		button = new JButton("Annuler");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		button.setIcon(new ImageIcon("D:\\Stoufa\\ISET\\Java\\Java\\MiniProjet\\img\\back.png"));
		button.setBounds(368, 203, 110, 32);
		contentPane.add(button);
	}
	public void close(){
		WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
	    Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
	}
}
