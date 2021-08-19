package presentatie;

import data.DataLayerJDBC;
import logica.Stad;

import java.sql.SQLException;
import java.util.ArrayList;

public class Spelverloop {
    private ArrayList<Stad> aftrekstapel;
    private ArrayList<Stad> aflegstapel;
    DataLayerJDBC datalayer = new DataLayerJDBC("pandemie",true);


    public Spelverloop() throws SQLException {
        this.aftrekstapel = (ArrayList<Stad>) datalayer.getStedenList();
        this.aflegstapel = new ArrayList<Stad>();
    }


    public ArrayList<Stad> getAflegstapel() {
        return aflegstapel;
    }

    public ArrayList<Stad> getAftrekstapel() {
        return aftrekstapel;
    }

    public void addToAflegstapel(Stad stad) {
        aflegstapel.add(stad);
    }

    public void removeFromAftrekstapel(Stad stad) {
        aftrekstapel.remove(stad);
    }

    public void setAftrekstapel(ArrayList<Stad>stad) {
        this.aftrekstapel = stad;
    }

    public void setAflegstapel(ArrayList<Stad>stad) {
        this.aflegstapel = stad;
    }

    public  Stad trekStad() {
        ArrayList<Stad> aflegstapel = getAflegstapel();
        ArrayList<Stad> aftrekstapel = getAftrekstapel();
        if (aftrekstapel.size()>0) {
            int getrokkenKaart = (int) (Math.random() * 25 + 1);
            for (Stad stad : aftrekstapel) {
                if (getrokkenKaart != stad.getId()) {
                    addToAflegstapel(stad);
                    removeFromAftrekstapel(stad);
                    return stad;
                }
            }
        }
        shuffle();
        throw new IllegalArgumentException("aftrekstapel is op aflegstapel gelegd & geschudt");


    }

    public void shuffle() {
        setAftrekstapel(getAflegstapel());
        this.aflegstapel.clear();
    }
//Hier worden de kaarten getrokken
    public int[] beurtTrek1 () {
        int [] trekking = new int[2];
        trekking[0] = trekStad().getId();
        trekking[1] = 1;
        return trekking;
    }

    public int[] beurtTrek2 () {
        int [] trekking = new int[2];
        trekking[0] = trekStad().getId();
        trekking[1] = 2;
        return trekking;
    }

    public int[] beurtTrek3 () {
        int [] trekking = new int[2];
        trekking[0] = trekStad().getId();
        trekking[1] = 3;
        return trekking;
    }

    public int[] beurtTrek4 () {
        int [] trekking = new int[2];
        trekking[0] = trekStad().getId();
        trekking[1] = 4;
        return trekking;
    }



    public void volgendeBeurt() {

    }

}
