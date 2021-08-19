package presentatie;

import data.DataLayerJDBC;
import logica.Stad;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;

public class Kaart {
    private JPanel stadPanel;
    private JLabel lblStad;
    private BufferedImage foto;
    private final DataLayerJDBC dl = new DataLayerJDBC("pandemie",true);
    Stad s = new Stad();

    public JPanel getStadPanel(String naam) throws SQLException {

        //kleur van de stad (voor de border)
        Color kleur = stadPanel.getBackground();
        s = dl.getStadMetNaam(naam);
        switch (s.getKleur()) {
            case GEEL -> kleur = Color.YELLOW;
            case ROOD -> kleur = Color.RED;
            case BLAUW -> kleur = Color.BLUE;
            case ZWART -> kleur = Color.BLACK;
        }

        //border met naam en kleur
        TitledBorder border = new TitledBorder(BorderFactory.createTitledBorder(new LineBorder(kleur), naam));
        border.setTitleJustification(TitledBorder.CENTER); //dit werkt niet?
        stadPanel.setBorder(border);


        //DEEL VAN DE KAART TOEVOEGEN
        //bolletje op de kaart zetten
        Graphics2D g2d = getFoto().createGraphics();
        switch (s.getKleur()) {
            case GEEL -> g2d.setColor(Color.YELLOW);
            case ROOD -> g2d.setColor(Color.RED);
            case BLAUW -> g2d.setColor(Color.BLUE);
            case ZWART -> g2d.setColor(Color.BLACK);
        }
        g2d.fillOval(55, 55, 10, 10);
        g2d.dispose();

        //foto als icon zetten
        lblStad.setIcon(new ImageIcon(getFoto()));
        return stadPanel;
    }

    public BufferedImage getFoto() {
        if (foto == null) {
            try {
                foto = ImageIO.read(PandemieGui.class.getResource("/europe.png"));
                try{
                    foto = foto.getSubimage(s.getX()-60, s.getY()-60,120,120);
                }catch (Exception e){
                    int h = 120;
                    while(s.getY()-60+h>896){
                        h-=10;
                    }
                    foto = foto.getSubimage(s.getX()-60, s.getY()-60,120,h);
                }
            } catch (IOException e) {
                e.printStackTrace();
                foto = null;
            }
        }
        return foto;
    }

}