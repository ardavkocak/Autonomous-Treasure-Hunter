package org.example;

import java.util.Random;

public abstract class Engel {
    Main main = new Main();

    private boolean isPassable;
    private int boyut;
    Lokasyon konum;

    public Engel() {
        konum = generateRandomLocation();
    }


    private Lokasyon generateRandomLocation() {
        Random random = new Random();

        Lokasyon yeniKonum = new Lokasyon();
        yeniKonum.setX(random.nextInt(1000 - boyut));  // Genişlik değeri uygun bir şekilde güncellenmeli.
        yeniKonum.setY(random.nextInt(1000 - boyut));  // Yükseklik değeri uygun bir şekilde güncellenmeli.

        // Oluşturulan yeni konumun mevcut engellerle çakışıp çakışmadığını kontrol et
        while (checkCollisionWithExistingLocations(yeniKonum)) {
            yeniKonum.setX(random.nextInt(1000 - boyut));
            yeniKonum.setY(random.nextInt(1000 - boyut));
        }

        main.occupiedLocations.add(yeniKonum);
        return yeniKonum;
    }

    // Mevcut engellerle çakışmayı kontrol eden yardımcı metod
    private boolean checkCollisionWithExistingLocations(Lokasyon yeniKonum) {
        int minDistance = 200; // Minimum mesafe, engellerin yarıçaplarının toplamı olabilir
        for (Lokasyon existingLocation : main.occupiedLocations) {
            double distance = Math.sqrt(Math.pow(existingLocation.getX() - yeniKonum.getX(), 2) + Math.pow(existingLocation.getY() - yeniKonum.getY(), 2));
            if (distance < minDistance) {
                return true; // Çakışma var
            }
        }
        return false; // Çakışma yok
    }




    public Lokasyon getKonum() {
        return konum;
    }

    public void setKonum(Lokasyon konum) {
        this.konum = konum;
    }

    public boolean isPassable() {
        return isPassable;
    }

    public void setPassable(boolean passable) {
        isPassable = passable;
    }

    public int getBoyut() {
        return boyut;
    }

    public void setBoyut(int boyut) {
        this.boyut = boyut;
    }

    public int getX()
    {
        return konum.getX();
    }
    public int getY()
    {
        return konum.getY();
    }
    public void setX(int x)
    {
        konum.setX(x);
    }
    public void setY(int y)
    {
        konum.setY(y);
    }


}
