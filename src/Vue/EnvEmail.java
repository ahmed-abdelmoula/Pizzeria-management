package Vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import Controleur.Connexion;
import Modele.ModelePV;
import Modele.ModeleProduit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class EnvEmail extends JFrame {

	private JPanel contentPane;
	private JTextField tfSujet;
	private JTextField tfMail;
	private JComboBox cbxCat;
	private DefaultComboBoxModel<String> mCat;
	private static Connection conn;
	private JTextPane body;



	public static void main(String[] args) throws AddressException, MessagingException {

					EnvEmail frame = new EnvEmail();
					frame.setVisible(true);
	}

	public EnvEmail() throws AddressException, MessagingException {
		
		conn=Connexion.connecter();
		setBounds(100, 100, 502, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnvoyerEmail = new JLabel("Envoyer Email:");
		lblEnvoyerEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEnvoyerEmail.setBounds(10, 10, 145, 52);
		contentPane.add(lblEnvoyerEmail);
		
		JLabel lblSujet = new JLabel("Sujet:");
		lblSujet.setBounds(68, 73, 43, 33);
		contentPane.add(lblSujet);
		
		tfSujet = new JTextField();
		tfSujet.setBounds(138, 79, 310, 20);
		contentPane.add(tfSujet);
		tfSujet.setColumns(10);
		
		JLabel label = new JLabel("\u00E0:");
		label.setBounds(68, 112, 43, 33);
		contentPane.add(label);
		
		cbxCat = new JComboBox();
		cbxCat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				affEmail();
			}
		});
		cbxCat.setBounds(138, 118, 100, 20);
		contentPane.add(cbxCat);
		mCat=ModelePV.remplirCat(conn,mCat);
		cbxCat.setModel(mCat);
		
		tfMail = new JTextField();
		tfMail.setEditable(false);
		tfMail.setColumns(10);
		tfMail.setBounds(248, 118, 200, 20);
		contentPane.add(tfMail);
		
		body = new JTextPane();
		body.setBounds(138, 149, 310, 112);
		contentPane.add(body);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(68, 147, 43, 33);
		contentPane.add(lblEmail);
		
		JButton btnEnvoyer = new JButton("Envoyer");
		btnEnvoyer.setIcon(new ImageIcon("D:\\Stoufa\\ISET\\Java\\Java\\MiniProjet\\img\\ok.png"));
		btnEnvoyer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				envoyer();
				close();
			}
		});
		btnEnvoyer.setBounds(335, 290, 113, 33);
		contentPane.add(btnEnvoyer);
		

		
	}
	
	protected void affEmail() {
		try {
			ResultSet rs=ModelePV.afficherEmail(conn,cbxCat.getSelectedItem().toString());
			rs.next();
			tfMail.setText(rs.getString(4));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void envoyer(){
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("benamar.mustafa@gmail.com","159632159632");
				}
			});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("benamar.mustafa@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(tfMail.getText()));
			message.setSubject(tfSujet.getText());
			message.setText(body.getText());

			Transport.send(message);

			JOptionPane.showMessageDialog(null, "Envoyé !");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	public void close(){
		WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
	    Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
	}
}