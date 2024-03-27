package org.example;


public class Bee extends DinamikEngeller{

    public Bee(int boyut)
    {
        super();
        this.setBoyut(boyut);
        this.setHareketAlani(12);
        this.setHareketYonu(false);
    }

    public void move() {
        Lokasyon konum = this.getKonum();
        int yeniX = konum.getX();

        if (this.isHareketYonu()) {
            // Hareket yönüne göre yeni konumu belirle
            yeniX += this.getHareketAlani();
            if (yeniX >= getKonum().getX() + 12) {
                // Belirlenen konumdan 3 birim sağa gidildiyse hareket yönünü tersine çevir
                this.setHareketYonu(false);
            }
        } else {
            yeniX -= this.getHareketAlani();
            if (yeniX <= getKonum().getX() - 12) {
                // Belirlenen konumdan 3 birim sola gidildiyse hareket yönünü tekrar sağa doğru çevir
                this.setHareketYonu(true);
            }
        }

        // Yeni konumu ayarla
        konum.setX(yeniX);
        this.setKonum(konum);
    }





    private boolean isValidMove(int x, int y) {
        // Hareketin geçerli olup olmadığını kontrol et (örneğin, oyun alanı sınırları içinde mi?)
        // Bu metodun gerçek işlevselliğine göre uyarlayabilirsiniz
        return x >= 0 && x <= (Main.sayi1 - this.getBoyut()); // Ekran genişliği içinde hareket kontrolü
    }


}


