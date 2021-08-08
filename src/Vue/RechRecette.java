package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import Controleur.Connexion;
import Modele.ModeleFournir;
import com.toedter.calendar.JDateChooser;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import javax.swing.JTextField;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class RechRecette extends JFrame {

	private JPanel contentPane;
	private DefaultComboBoxModel<String> mPV;
	private Connection conn;
	private JDateChooser dateDeb;
	private JDateChooser dateFin;
	private JTable table;
	private DefaultTableModel tm=new DefaultTableModel();
	private JComboBox cbxPV;
	private JTextField tfTot;
	private JButton button;
	private JButton btnStatistique;





	public static void main(String[] args) {

					RechRecette frame = new RechRecette();
					frame.setVisible(true);

	}

	@SuppressWarnings("unchecked")
	public RechRecette() {
		conn=Connexion.connecter();
		setBounds(100, 100, 582, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cbxPV = new JComboBox();
		cbxPV.setBounds(117, 39, 44, 20);
		contentPane.add(cbxPV);
		mPV=ModeleFournir.remplirPV(conn, mPV);
		cbxPV.setModel(mPV);
		
		JLabel label = new JLabel("Point de Vente:");
		label.setBounds(13, 42, 94, 14);
		contentPane.add(label);
		
		dateDeb = new JDateChooser();
		dateDeb.setBounds(252, 39, 106, 20);
		contentPane.add(dateDeb);
		
		dateFin = new JDateChooser();
		dateFin.setBounds(435, 42, 100, 20);
		contentPane.add(dateFin);
		
		JLabel lblDateDbut = new JLabel("Date D\u00E9but:");
		lblDateDbut.setBounds(171, 42, 71, 14);
		contentPane.add(lblDateDbut);
		
		JLabel lblDateFin = new JLabel("Date Fin:");
		lblDateFin.setBounds(368, 42, 57, 14);
		contentPane.add(lblDateFin);
		
		table = new JTable();
		table.setBounds(113, 158, 255, 0);
		contentPane.add(table);
		tm.setColumnIdentifiers(new String[]{"Code Produit","Quantité Vendue","Date","Revenue du Jour"});
		table.setModel(tm);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(34, 124, 501, 154);
		contentPane.add(scrollPane);
		
		JButton btnAfficher = new JButton("Afficher");
		btnAfficher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				afficher();
			}
		});
		btnAfficher.setIcon(new ImageIcon("D:\\Stoufa\\ISET\\Java\\Java\\MiniProjet\\img\\ok.png"));
		btnAfficher.setBounds(34, 80, 126, 33);
		contentPane.add(btnAfficher);
		
		JLabel lblNewLabel = new JLabel("Total des Revenues:");
		lblNewLabel.setBounds(34, 289, 118, 14);
		contentPane.add(lblNewLabel);
		
		tfTot = new JTextField();
		tfTot.setEditable(false);
		tfTot.setBounds(158, 286, 100, 20);
		contentPane.add(tfTot);
		tfTot.setColumns(10);
		
		button = new JButton("Annuler");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		button.setIcon(new ImageIcon("D:\\Stoufa\\ISET\\Java\\Java\\MiniProjet\\img\\back.png"));
		button.setBounds(425, 337, 110, 32);
		contentPane.add(button);
		
		btnStatistique = new JButton("Statistique");
		btnStatistique.setVisible(false);
		btnStatistique.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				afficherStat();

			}
		});
		btnStatistique.setIcon(new ImageIcon("D:\\Stoufa\\ISET\\Java\\Java\\MiniProjet\\img\\chart-icon.png"));
		btnStatistique.setBounds(409, 80, 126, 33);
		contentPane.add(btnStatistique);
		

	}

	protected void afficherStat() {
		DefaultCategoryDataset ds=new DefaultCategoryDataset();
		for(int i=0;i<tm.getRowCount();i++)	
			ds.setValue((Number) table.getValueAt(i, 3), "Revenue", (Comparable) table.getValueAt(i, 2));


		
		JFreeChart bar=ChartFactory.createBarChart("Statistique des revenues", "Jours", "Revenues", ds,PlotOrientation.VERTICAL, rootPaneCheckingEnabled, rootPaneCheckingEnabled, rootPaneCheckingEnabled);
		CategoryPlot barchart=bar.getCategoryPlot();
		barchart.setRangeGridlinePaint(Color.RED);
		ChartFrame fram=new ChartFrame("Statistique", bar);
		fram.setVisible(true);
		fram.setSize(500, 500);		
	}

	protected void afficher() {
		for(int i=0;i<tm.getRowCount();i++)
		tm.removeRow(0);
		double tot=0;
		int codePV=Integer.parseInt(cbxPV.getSelectedItem().toString());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try{
		String sql="select r.*,p.prixVente from recette r,produit p where codePV='"+codePV+"' and r.codeProd=p.codeProd and date between '"+sdf.format(dateDeb.getDate())+"' and '"+sdf.format(dateFin.getDate())+"'";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(sql);
		while(rs.next()){
			int qte=rs.getInt(2);
			int codeP=rs.getInt(4);
			double prix=rs.getDouble(6);
			tm.addRow(new Object[]{codeP,qte,rs.getObject(3),(prix*qte)});
			tot+=(prix*qte);
			}
		tfTot.setText(String.valueOf(tot));
		st.close();
		rs.close();

		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		btnStatistique.setVisible(true);
	}
	public void close(){
		WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
	    Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
	}
}
