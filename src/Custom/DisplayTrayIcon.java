/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Custom;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author lahcen
 */
public class DisplayTrayIcon {
    public static TrayIcon trayIcon;
    public DisplayTrayIcon(){
        ShowTrayIcon();
    }
    
    public static void ShowTrayIcon(){
        if(!SystemTray.isSupported()){
            System.out.println("Error , your pc");
            System.exit(0);
            return;
        }
        
        final PopupMenu popup = new PopupMenu();
       // trayIcon = new TrayIcon(CreateIcon("iconetray.png", "Gestion stock"));
        final SystemTray tray = SystemTray.getSystemTray();
        trayIcon.setToolTip("Version 1.0 \n Gestion De Stock");
        Menu DisplayMenu = new Menu("Menu");
        MenuItem ErrorItem = new MenuItem("Error");
        MenuItem WarningItem = new MenuItem("Warning");
        MenuItem NormalItem = new MenuItem("Normal");
        MenuItem InfoItem = new MenuItem("Info");
        
        MenuItem about =new MenuItem("About");
        MenuItem exit = new MenuItem("Exit");
        DisplayMenu.add(ErrorItem);
        about.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        popup.add(about);
        popup.addSeparator();
        popup.add(exit);
        trayIcon.setPopupMenu(popup);
        
        
        try{
            tray.add(trayIcon);
        }catch(Exception ex){
            
        }
    }
    /*protected static Image CreateIcon(String path,String desc){
     //   URL ImageURL = Main.class.getClassLoader().getResource("img/"+path);
        return (new ImageIcon(ImageURL,desc)).getImage();
    }*/
}