package org.example;

import javax.swing.*;

public class Karakter extends JFrame {

    private String Ad;
    private int ID;
    Lokasyon konum;
    private int scanningRange;

    public Karakter()
    {
        this.konum = new Lokasyon(100,100);
    }
    public Karakter(String Ad , int ID , int x ,int y)
    {
        this.Ad = Ad;
        this.ID = ID;
        this.konum = new Lokasyon(x,y);
    }

    public String getAd()
    {
        return Ad;
    }
    public int getID()
    {
        return ID;
    }
    public void setID(int ID)
    {
        this.ID = ID;
    }
    public void setAd(String Ad)
    {
        this.Ad = Ad;
    }

    public void increaseScanningRange(int multiplier) {
        this.scanningRange *= multiplier;
    }

    public int getX() {
        if (konum != null) {
            return konum.getX();
        } else {
            System.out.println("Hata: konum nesnesi null. getX metodu çağrılamıyor.");
            return -1; // veya başka bir hata değeri döndürebilirsiniz.
        }
    }
    public int getY() {
        if (konum != null) {
            return konum.getY();
        } else {
            System.out.println("Hata: konum nesnesi null. getY metodu çağrılamıyor.");
            return -1; // veya başka bir hata değeri döndürebilirsiniz.
        }
    }

    public void setX(int x)
    {
        konum.setX(x);
    }
    public void setY(int y)
    {
        konum.setY(y);
    }

    public int getScanningRange() {
        return scanningRange;
    }

    public void setScanningRange(int scanningRange) {
        this.scanningRange = scanningRange;
    }




}
