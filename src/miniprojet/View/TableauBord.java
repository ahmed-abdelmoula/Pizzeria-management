/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniprojet.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.LineBorder;
import Custom.ChartPanel;
import Custom.CustomPanel;
import Custom.MyScrollBarUI;
import com.sun.imageio.plugins.jpeg.JPEG;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.*;
import javax.swing.border.LineBorder;



/**
 *
 * @author ahmed
 */
public class TableauBord  extends JPanel {
    
    
     JPanel profil = new JPanel(null);
    JPanel statistique = new JPanel(null);
    JPanel numbers = new JPanel(null);
    JPanel time = new JPanel(null);
    JPanel datte = new JPanel(null);
    JPanel historique = new JPanel(null);
    JScrollPane scroll = new JScrollPane(historique);
    JPanel panUser = new JPanel(null);
    JPanel panProduit = new JPanel(null);
    JPanel panClient = new JPanel(null);
    JPanel panFournisseur = new JPanel(null);
    JLabel imgUser = new JLabel();
    JLabel imgProduit = new JLabel();
    JLabel imgClient = new JLabel();
    JLabel imgFournisseur = new JLabel();
    JLabel titreUser = new JLabel();
    JLabel titreProduit = new JLabel();
    JLabel titreClient = new JLabel();
    JLabel titreFournisseur = new JLabel();
    CustomPanel userCircle = new CustomPanel(12, 18, Color.decode("#ff5454"), Color.white, Color.decode("#fe7d7d"), 4, 25);
    CustomPanel produitCircle = new CustomPanel(12, 18, Color.decode("#c767ef"), Color.white, Color.decode("#db87fe"), 4, 83);
    CustomPanel clientCircle = new CustomPanel(12, 18, Color.decode("#fabb3c"), Color.white, Color.decode("#fccc6d"), 4, 67);
    CustomPanel fournisseurCircle = new CustomPanel(12, 18, Color.decode("#79c447"), Color.white, Color.decode("#95da67"), 4, 41);
    JLabel clock = new JLabel();
    Date aujourdhui = new Date();
          SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
          SimpleDateFormat formater2 = new SimpleDateFormat("HH:MM");
    JLabel sousClock = new JLabel(""+formater2.format(aujourdhui));
    JLabel cal = new JLabel();
    
 
    JLabel sousCal = new JLabel(""+formater.format(aujourdhui));
    JLabel titreStat = new JLabel("Les bénéfices");
    JLabel borderBottom = new JLabel();
    JPanel titreHistory = new JPanel(null);
    JLabel textHistory = new JLabel("Historique");
    JLabel iconProfil = new JLabel();
    JLabel textProfil = new JLabel();
    public TableauBord()
    {
        
         setBounds(-10, -30, 881, 500);
        setBackground(Color.decode("#f2f3f8"));
        this.setOpaque(false);
        setLayout(null);
        

        /**
         * ************** profil ************
         */
        profil.setBounds(20, 20, 395, 90);
        profil.setBackground(Color.white);
        profil.setBorder(new LineBorder(Color.decode("#e5e5e5"), 1));


        iconProfil.setBounds(10, 10, 42, 70);
       // Frame.Icon(iconProfil, "profil.png", 42, 70);

        textProfil.setText("<html><head><style>"
                + "*{margin:0;padding:0px;}"
                + "h2{color:#7f7f7f;}"
                + "p{color:#a09f9f;}"
                + "span{color:#f77d34;}"
                + "</style></head><boyd><h2>Tableau De Bord</h2>"
                + "<p>Bienvenu <span>Hanane Amzil</span> . pour deconnecter clique </p></body></html>");
        textProfil.setBounds(62, 10, 320, 60);
        profil.add(textProfil);

        profil.add(iconProfil);



        /**
         * *********** statistique **********
         */
        statistique.setBackground(Color.white);
        statistique.setBorder(new LineBorder(Color.decode("#e5e5e5"), 1));
        statistique.setBounds(225, 125, 410, 235);
        titreStat.setBounds(10, 0, 385, 30);
        titreStat.setFont(new Font("Tahoma", Font.BOLD, 12));
        titreStat.setForeground(Color.gray);
       // Frame.Icon(titreStat, "stat.png", 20, 20);
        borderBottom.setBounds(0, 30, 410, 3);
        borderBottom.setBackground(Color.decode("#e5e5e5"));
        borderBottom.setOpaque(true);
        double[] values = {2000, 35000, 61000, 54000, 21000, 95200, 54000, 12580, 100589, 39000, 54892, 75336};
        String[] names = {"janv", "févr", "mars", "avr", "mai", "juin", "juill", "août", "sept", "oct", "nov", "déc"};
        ChartPanel aa = new ChartPanel(values, names, "");
        aa.setBounds(10, 40, 390, 170);
        aa.setBackground(Color.white);
        statistique.add(titreStat);
        statistique.add(borderBottom);
        statistique.add(aa);




        /**
         * ************** time ************
         */
        time.setBackground(Color.white);
        time.setBounds(430, 20, 95, 90);
        time.setBorder(new LineBorder(Color.decode("#e5e5e5"), 1));

        clock.setBackground(Color.decode("#da534f"));
        //Frame.Icon(clock, "clock.png", 40, 40);
        clock.setHorizontalAlignment(JLabel.CENTER);
        clock.setVerticalAlignment(JLabel.CENTER);
        clock.setOpaque(true);
        clock.setBounds(0, 0, 95, 60);
        sousClock.setForeground(Color.decode("#7f7f7f"));
        sousClock.setBounds(0, 60, 95, 30);
        sousClock.setHorizontalAlignment(JLabel.CENTER);
        sousClock.setVerticalAlignment(JLabel.CENTER);
        sousClock.setFont(new Font("Tahoma", Font.PLAIN, 12));


        /**
         * ************ date ****************
         */
        datte.setBackground(Color.white);
        datte.setBounds(540, 20, 95, 90);
        datte.setBorder(new LineBorder(Color.decode("#e5e5e5"), 1));


        cal.setBackground(Color.decode("#efad4d"));
       // Frame.Icon(cal, "cal.png", 40, 40);
        cal.setHorizontalAlignment(JLabel.CENTER);
        cal.setVerticalAlignment(JLabel.CENTER);
        cal.setOpaque(true);
        cal.setBounds(0, 0, 95, 60);
        sousCal.setForeground(Color.decode("#7f7f7f"));
        sousCal.setBounds(0, 60, 95, 30);
        sousCal.setHorizontalAlignment(JLabel.CENTER);
        sousCal.setVerticalAlignment(JLabel.CENTER);
        sousCal.setFont(new Font("Tahoma", Font.PLAIN, 12));



        /**
         * *********** historique *********
         */
        titreHistory.setBounds(648, 20, 203, 33);
        titreHistory.setBackground(Color.decode("#ffffff"));
        titreHistory.setBorder(new LineBorder(Color.decode("#e5e5e5"), 1));

        textHistory.setBounds(10, 0, 150, 30);
        textHistory.setFont(new Font("Tahoma", Font.BOLD, 12));
        textHistory.setForeground(Color.gray);

       // Frame.Icon(textHistory, "iconHis.png", 15, 20);
        historique.setBackground(Color.decode("#ffffff"));
        historiqueTable();


        scroll.setBounds(648, 53, 203, 307);
        scroll.setOpaque(false);
        scroll.setBorder(new LineBorder(Color.decode("#e5e5e5"), 1));
        scroll.setLayout(new ScrollPaneLayout());
        scroll.getViewport().setOpaque(false);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(10, 10));
        scroll.getVerticalScrollBar().setUI(new MyScrollBarUI());

        /**
         * *********** numbers *************
         */
        numbers.setBackground(Color.decode("#f2f3f8"));
        numbers.setBounds(20, 370, 830, 80);

        //**************** pan user ************//
        panUser.setBackground(Color.decode("#ff5454"));
        panUser.setBounds(0, 0, 195, 80);
        panUser.setBorder(new LineBorder(Color.white, 1));
        imgUser.setBounds(10, 10, 60, 60);

       // Frame.Icon(imgUser, "pan1.png", 60, 60);
        titreUser.setBounds(80, 50, 100, 30);
        titreUser.setVerticalAlignment(JLabel.TOP);
        titreUser.setForeground(Color.decode("#ffffff"));
        titreUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
        titreUser.setText("Utilisateurs");
        userCircle.setBounds(80, 10, 40, 40);
        //userCircle.setBoucle(nbrEnreg("membre",false,""));

        //**************** pan produit ************//
        panProduit.setBackground(Color.decode("#c767ef"));
        panProduit.setBounds(205, 0, 200, 80);
        panProduit.setBorder(new LineBorder(Color.white, 1));
        imgProduit.setBounds(10, 10, 60, 60);
  //      Frame.Icon(imgProduit, "pan2.png", 60, 60);
        titreProduit.setBounds(80, 50, 100, 30);
        titreProduit.setVerticalAlignment(JLabel.TOP);
        titreProduit.setForeground(Color.decode("#ffffff"));
        titreProduit.setFont(new Font("Tahoma", Font.PLAIN, 15));
        titreProduit.setText("Produits");
        produitCircle.setBounds(80, 10, 40, 40);
        //produitCircle.setBoucle(nbrEnreg("produits",false,""));

        //**************** pan client ************//
        panClient.setBackground(Color.decode("#fabb3c"));
        panClient.setBounds(415, 0, 202, 80);
        panClient.setBorder(new LineBorder(Color.white, 1));
        imgClient.setBounds(10, 10, 60, 60);
       // Frame.Icon(imgClient, "pan3.png", 60, 60);
        titreClient.setBounds(80, 50, 100, 30);
        titreClient.setVerticalAlignment(JLabel.TOP);
        titreClient.setForeground(Color.decode("#ffffff"));
        titreClient.setFont(new Font("Tahoma", Font.PLAIN, 15));
        titreClient.setText("Clients");
        clientCircle.setBounds(80, 10, 40, 40);
        //clientCircle.setBoucle(nbrEnreg("personne",true,"client"));

        //**************** pan fournisseur ************//
        panFournisseur.setBackground(Color.decode("#79c447"));
        panFournisseur.setBounds(627, 0, 203, 80);
        panFournisseur.setBorder(new LineBorder(Color.white, 1));
        imgFournisseur.setBounds(10, 10, 60, 60);
        //Frame.Icon(imgFournisseur, "pan4.png", 60, 60);
        titreFournisseur.setBounds(80, 50, 100, 30);
        titreFournisseur.setVerticalAlignment(JLabel.TOP);
        titreFournisseur.setForeground(Color.decode("#ffffff"));
        titreFournisseur.setFont(new Font("Tahoma", Font.PLAIN, 15));
        titreFournisseur.setText("Fournisseurs");
        fournisseurCircle.setBounds(80, 10, 40, 40);
        //fournisseurCircle.setBoucle(nbrEnreg("personne",true,"fournisseur"));



        time.add(sousClock);
        time.add(clock);

        datte.add(sousCal);
        datte.add(cal);


        panUser.add(imgUser);
        panUser.add(titreUser);
        panUser.add(userCircle);
        userCircle.runCircle();

        panProduit.add(imgProduit);
        panProduit.add(titreProduit);
        panProduit.add(produitCircle);
        produitCircle.runCircle();

        panClient.add(imgClient);
        panClient.add(titreClient);
        panClient.add(clientCircle);
        clientCircle.runCircle();

        panFournisseur.add(imgFournisseur);
        panFournisseur.add(titreFournisseur);
        panFournisseur.add(fournisseurCircle);
        fournisseurCircle.runCircle();

        numbers.add(panUser);
        numbers.add(panProduit);
        numbers.add(panClient);
        numbers.add(panFournisseur);

        titreHistory.add(textHistory);

        add(profil);
        add(titreHistory);
        add(statistique);
        add(time);
        add(datte);
        add(scroll);
        add(numbers);
        setVisible(true);

    }

    public void historiqueTable() {
        int y = 5;
        int ps = 0;
        String imgStr = "";
        Color color = Color.decode("#00acf4");
        for (int i = 0; i < 5; i++) {
            JPanel p = new JPanel(null);
            p.setBackground(Color.decode("#ffffff"));
            p.setBounds(0, y, 200, 50);
            JLabel line = new JLabel();
            line.setBounds(10, 0, 1, 50);
            line.setOpaque(true);
            line.setBackground(Color.decode("#e5e5e5"));
            JLabel circle = new JLabel();
            circle.setBounds(6, 20, 10, 10);
            if (ps == 0) {
                imgStr = "circle1.png";
                color = Color.decode("#00acf4");
                ps = 1;
            } else {
                imgStr = "circle2.png";
                color = Color.decode("#fec305");
                ps = 0;
            }
            Frame.Icon(circle, imgStr, 10, 10);


            JPanel pContent = new JPanel(null);
            pContent.setBackground(Color.white);
            pContent.setBounds(25, 0, 168, 50);
            JLabel pTop = new JLabel("11");
            pTop.setFont(new Font("Tahoma", Font.PLAIN, 12));
            pTop.setVerticalAlignment(JLabel.CENTER);
            pTop.setHorizontalAlignment(JLabel.CENTER);
            pTop.setForeground(Color.white);
            pTop.setBackground(color);
            pTop.setBounds(1, 0, 23, 23);
            pTop.setOpaque(true);
            JLabel pBottom = new JLabel("14");
            pBottom.setFont(new Font("Tahoma", Font.PLAIN, 12));
            pBottom.setVerticalAlignment(JLabel.CENTER);
            pBottom.setHorizontalAlignment(JLabel.CENTER);
            pBottom.setForeground(Color.white);
            pBottom.setBackground(color);
            pBottom.setBounds(1, 26, 23, 23);
            pBottom.setOpaque(true);
            JLabel pText = new JLabel("Reference site about lorem");
            pText.setFont(new Font("Tahoma", Font.PLAIN, 11));
            pText.setForeground(Color.decode("#7f7f7f"));
            pText.setBounds(30, 8, 140, 20);
            JLabel pSousText = new JLabel("09h30'");
            pSousText.setFont(new Font("Tahoma", Font.PLAIN, 9));
            pSousText.setForeground(Color.decode("#9a9999"));
            pSousText.setBounds(30, 30, 140, 10);

            y += 60;
            pContent.add(pTop);
            pContent.add(pBottom);
            pContent.add(pText);
            pContent.add(pSousText);
            p.add(circle);
            p.add(line);
            p.add(pContent);

            historique.add(p);
        }
        y -= 8;
        historique.setPreferredSize(new Dimension(203, y));
    }

    public int nbrEnreg(String nameTable, boolean where, String type) {
        int n = 0;
      /*  try {
            String sql = "select * from " + nameTable;
            if (where == true) {
                sql = sql + " where type='" + type + "'";
            }
            Connexion.rs = Connexion.stmt.executeQuery(sql);
           while (Connexion.rs.next()) {
                n++;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }*/
        return n;

    }
    
    }
      

