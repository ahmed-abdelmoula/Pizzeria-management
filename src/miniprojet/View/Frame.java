/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miniprojet.View;

import Custom.DisplayTrayIcon;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 *
 * @author lahcen
 */
public class Frame extends JFrame {

    int xMouse;
    int yMouse;
    DisplayTrayIcon DTI = new DisplayTrayIcon();
    JPanel pan = new JPanel(null);
    static JPanel header = new JPanel(null);
    static JPanel outils = new JPanel(null);
    JLabel bg_header = new JLabel();
    JLabel txt_header = new JLabel("Gestion De Stock v 1.0 By");
    final JLabel reduire = new JLabel("-");
    final JLabel fermer = new JLabel("x");
    JPanel content = new JPanel(null);
    JLabel separator = new JLabel();
    JLabel logo = new JLabel();
    JDesktopPane desktop = new JDesktopPane();
    JLabel tableBtn = new JLabel();
    JLabel info = new JLabel();
    JLabel param = new JLabel();
    public static JLabel back = new JLabel();

    public Frame() {
        setSize(new Dimension(865, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setTitle("Gestion De Stock v 1.0");
       // ImageIcon iconFrame = new ImageIcon(Main.class.getClassLoader().getResource("img/icone.png"));
      //  Image imgFrame = iconFrame.getImage();
       // this.setIconImage(imgFrame);

        // panel content
        pan.setOpaque(true);
        pan.setBackground(Color.decode("#043d5d"));
        // panel header
        header.setBounds(2, 2, 861, 30);

        Icon(back, "back.png", 865, 600);
        back.setBounds(0, 0, 865, 600);
        
        pan.add(back);

        //Icon(bg_header, "header.png", 865, 50);
        bg_header.setOpaque(true);
        bg_header.setBackground(Color.decode("#043d5d"));
        bg_header.setCursor(new Cursor(Cursor.MOVE_CURSOR));
        bg_header.addMouseMotionListener(new MouseMotionAdapter() {

            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                setLocation(x - xMouse, y - yMouse);
            }
        });
        bg_header.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                xMouse = e.getX();
                yMouse = e.getY();
            }
        });
        separator.setBounds(2, 32, 861, 2);
        separator.setOpaque(true);
        separator.setBackground(Color.decode("#09537d"));

        bg_header.setBounds(0, 0, 861, 30);
        txt_header.setBounds(10, 8, 150, 15);

        txt_header.setForeground(Color.decode("#ffffff"));
        logo.setBounds(160, 8, 30, 15);
        Icon(logo, "logo.png", 30, 15);

        // icon header ( info , accueil , langue .. )
        info.setText("Information");
        info.setForeground(Color.white);
        info.setBounds(720, 6, 100, 16);
        Icon(info, "info.png", 16, 16);
        info.setCursor(new Cursor(Cursor.HAND_CURSOR));
        info.addMouseListener(new MouseAdapter() {

            public void mouseEntered(MouseEvent e) {
                info.setForeground(Color.decode("#7aceff"));
            }

            public void mouseExited(MouseEvent e) {
                info.setForeground(Color.white);
            }

            public void mouseClicked(MouseEvent e) {
           /*     Frame.Icon(Main.fr.back, "backWhite.png", 865, 600);
                Main.fr.back.setBounds(0, 0, 865, 600);
                Main.fr.pan.repaint();
                Main.fr.pan.revalidate();*/
               // Informations info = new Informations(Main.fr, true);

            }
        });
        header.add(info);
        tableBtn.setText("Tableau de bord");
        tableBtn.setForeground(Color.white);
        tableBtn.setBounds(500, 6, 120, 16);
        tableBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Icon(tableBtn, "accueil.png", 16, 16);
        tableBtn.addMouseListener(new MouseAdapter() {

            public void mouseEntered(MouseEvent e) {
                tableBtn.setForeground(Color.decode("#7aceff"));
            }

            public void mouseExited(MouseEvent e) {
                tableBtn.setForeground(Color.white);
            }

            public void mouseClicked(MouseEvent e) {
             //   open("100");
            }
        });
        header.add(tableBtn);

        param.setText("Paramétres");
        param.setForeground(Color.white);
        param.setBounds(620, 6, 100, 16);
        param.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Icon(param, "param.png", 16, 16);
        param.addMouseListener(new MouseAdapter() {

            public void mouseEntered(MouseEvent e) {
                param.setForeground(Color.decode("#7aceff"));
            }

            public void mouseExited(MouseEvent e) {
                param.setForeground(Color.white);
            }

            public void mouseClicked(MouseEvent e) {
              //  open("200");
            }
        });
        header.add(param);

        // end icon header

        //****** Button reduire ***********
        reduire.setOpaque(true);
        reduire.setBackground(Color.gray);
        reduire.setBounds(821, 6, 16, 16);
        reduire.setHorizontalAlignment(JLabel.CENTER);
        reduire.setVerticalAlignment(JLabel.CENTER);
        reduire.setFont(new Font("Tahoma", Font.PLAIN, 16));
        reduire.setCursor(new Cursor(Cursor.HAND_CURSOR));
        reduire.setForeground(Color.white);
        reduire.addMouseListener(new MouseAdapter() {

            public void mouseEntered(MouseEvent e) {
                reduire.setBackground(Color.decode("#ed3955"));
            }

            public void mouseExited(MouseEvent e) {
                reduire.setBackground(Color.gray);
            }

            public void mouseClicked(MouseEvent e) {
                setExtendedState(ICONIFIED);
            }
        });
        //****** Button fermer ***********
        fermer.setOpaque(true);
        fermer.setBackground(Color.gray);
        fermer.setForeground(Color.white);
        fermer.setBounds(840, 6, 16, 16);
        fermer.setHorizontalAlignment(JLabel.CENTER);
        fermer.setVerticalAlignment(JLabel.CENTER);
        fermer.setFont(new Font("Tahoma", Font.PLAIN, 16));
        fermer.setCursor(new Cursor(Cursor.HAND_CURSOR));
        fermer.addMouseListener(new MouseAdapter() {

            public void mouseEntered(MouseEvent e) {
                fermer.setBackground(Color.decode("#ed3955"));
            }

            public void mouseExited(MouseEvent e) {
                fermer.setBackground(Color.gray);
            }

            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });
        // outils 
        outils.setBounds(2, 34, 861, 100);
        outils.setBackground(Color.decode("#3dc1d6"));
        //content
        content.setBounds(2, 134, 861, 464);
        content.setBackground(Color.decode("#ffffff"));
        //open("100");
        // menu
        int step = 2;
        String[] tb = {"PRODUITS", "CLIENTS", "FOURNISSEURS", "ACHAT", "VENTE", "STOCK", "STATISTIQUES", "HISTORIQUE"};
        final String[] im = {"001.png", "007.png", "003.png", "002.png", "008.png", "005.png", "004.png", "006.png"};
        final String[] imH = {"001-001.png", "007-007.png", "003-003.png", "002-002.png", "008-008.png", "005-005.png", "004-004.png", "006-006.png"};
        for (int i = 0; i < 8; i++) {
            final JLabel lab = new JLabel();
            final JLabel text = new JLabel(tb[i]);
            lab.setBackground(Color.red);
            Icon(lab, im[i], 107, 60);
            lab.setCursor(new Cursor(Cursor.HAND_CURSOR));
            lab.setName("" + i);
            lab.addMouseListener(new MouseAdapter() {

                public void mouseEntered(MouseEvent e) {
                    if (lab.getBackground() == Color.red) {
                        Icon(lab, imH[Integer.parseInt(lab.getName())], 107, 60);
                        lab.setBackground(Color.gray);
                        text.setForeground(Color.decode("#fdcd11"));
                    }
                }

                public void mouseExited(MouseEvent e) {
                    if (lab.getBackground() == Color.gray) {
                        Icon(lab, im[Integer.parseInt(lab.getName())], 107, 60);
                        lab.setBackground(Color.red);
                        text.setForeground(Color.decode("#ffffff"));
                    }
                }

                public void mouseClicked(MouseEvent e) {
              //      open(lab.getName());
                }
            });
            lab.setBounds(step, 10, 107, 60);

            text.setForeground(Color.white);
            text.setHorizontalAlignment(JLabel.CENTER);
            text.setVerticalAlignment(JLabel.CENTER);
            text.setBounds(step, 75, 107, 15);
            step = step + 107;
            outils.add(lab);
            outils.add(text);
        }
        // end menu   
        // JDesktop Pane

        desktop.setBounds(0, 0, 861, 464);
        desktop.setBackground(Color.WHITE);
        desktop.setOpaque(true);
        desktop.setLayout(null);

        header.add(reduire);
        header.add(fermer);
        header.add(txt_header);
        header.add(logo);
        header.add(bg_header);
        pan.add(separator);
        pan.add(outils);
        content.add(desktop);
        pan.add(content);
        pan.add(header);

        add(pan);
        setVisible(true);
    }

    public static void Icon(JLabel lab, String name, int width, int height) {
       /*  try {
           URL url = Main.class.getClassLoader().getResource("img/" + name);
            BufferedImage img2 = ImageIO.read(url);
            Image dimg = img2.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(dimg);
            lab.setIcon(icon);
        } catch (IOException e) {
        }*/
    }

  /*  public void open(String id) {
        desktop.removeAll();
        desktop.repaint();
        desktop.revalidate();
        if (Integer.parseInt(id) == 100) {
            Main.tableauForm = new TableauBord();
            desktop.add(Main.tableauForm);
        } // Table de bord
        if (Integer.parseInt(id) == 0) {
            Main.produitForm = new Produits();
            desktop.add(Main.produitForm);
        } // produits
        if (Integer.parseInt(id) == 1) {
            Main.clientForm = new Client();
            desktop.add(Main.clientForm);
        } // Client
        if (Integer.parseInt(id) == 2) {
            Main.fournisseurForm = new Fournisseur();
            desktop.add(Main.fournisseurForm);
        } // Fournisseur
        if (Integer.parseInt(id) == 3) {
            desktop.add(new Achat());
        } // Achat
        if (Integer.parseInt(id) == 4) {
            desktop.add(new Vente());
        } // Achat

        System.out.println(id);
    }*/
}