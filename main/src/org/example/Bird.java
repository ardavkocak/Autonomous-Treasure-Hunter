package org.example;

public class Bird extends DinamikEngeller{

    public Bird(int boyut)
    {
        super();
        this.setBoyut(boyut);
        this.setHareketAlani(20);
        this.setHareketYonu(true);
    }

    public void move() {
        Lokasyon konum = this.getKonum();
        int yeniY = konum.getY();

        if (this.isHareketYonu()) {
            // Hareket yönüne göre yeni konumu belirle
            yeniY += this.getHareketAlani();
            if (yeniY >= getKonum().getY() + 20) {
                // Belirlenen konumdan 3 birim sağa gidildiyse hareket yönünü tersine çevir
                this.setHareketYonu(false);
            }
        } else {
            yeniY -= this.getHareketAlani();
            if (yeniY <= getKonum().getY() - 20) {
                // Belirlenen konumdan 3 birim sola gidildiyse hareket yönünü tekrar sağa doğru çevir
                this.setHareketYonu(true);
            }
        }

        // Yeni konumu ayarla
        konum.setY(yeniY);
        this.setKonum(konum);
    }

}
