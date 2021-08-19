package presentatie;

import data.DataLayerJDBC;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Dit is een Gui voor het kaartspel Pandemie, een opdracht voor het vak Object Orientation & Database Concepts - 2020-2021 EP3
 * @author
 */
public class PandemieGui {

    private JPanel mainPanel;
    private JPanel spelersPanel;
    private JPanel infoPanel;
    private JButton infectieTrekButton;
    private JList stedenList;
    private JButton handTrekButton;
    private JTabbedPane infoTabbedPane;
    private JTextArea logTextArea;
    private List<SpelerPanel> spelerPanels;


    public JButton getInfectieTrekButton() {
        return infectieTrekButton;

    }


    public PandemieGui() throws SQLException {
        spelersPanel.setLayout(new BoxLayout(spelersPanel,BoxLayout.Y_AXIS));
        Spelverloop spelverloop = new Spelverloop();
        infectieTrekButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String getrokkenNaam = spelverloop.trekStad().getNaam();


            }
        });


    }

    private void createUIComponents() throws SQLException {
        int aantalSpelers =0;
        DataLayerJDBC dataLayer = new DataLayerJDBC("pandemie", true);
        try {
            aantalSpelers =  dataLayer.getNewSpelers().toArray().length;
        } catch (SQLException ex) {
            Logger.getLogger(DataLayerJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        spelerPanels=new ArrayList<>(3);
        spelersPanel=new JPanel();

        for(int i=1;i<=aantalSpelers;i++){//pas deze lijn aan

            //Overloop in deze lus je spelers. Let er ook op dat er ook minder dan 3 spelers kunnen meespelen
            SpelerPanel sp = new SpelerPanel(i);
            spelersPanel.add(sp.getSpelerPanel());
            spelerPanels.add(sp);
        }
    }





    public static void main(String[] args) {
        JFrame myFrame = new JFrame();
        myFrame.setTitle("Pandemie kaartspel - <>");
        while(myFrame.getTitle().contains("student"))
            JOptionPane.showMessageDialog(myFrame,"Voeg in de titel van het frame je eigen naam toe \nom deze melding te vermijden.","Titel nog niet aangepast", JOptionPane.WARNING_MESSAGE);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setContentPane(new StartGUI(myFrame).getPanelMain());
        //myFrame.pack();
        myFrame.setSize(800, 800);
        myFrame.setVisible(true);
    }
    public JPanel getMainPanel (){
        return this.mainPanel;
    }
}
