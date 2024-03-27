package org.example;

import java.util.*;



public class Uygulama {

    public int hareketSayisi = 0;
    //Karakterin bütün gemleri kaç adımda elde ettiğini tutacağız.
    public int Step = 0;

    public void artirHareketSayisi() {
        hareketSayisi++;
    }

    public void setStep(int playerSpeed , int blok)
    {
        Step = (hareketSayisi * playerSpeed) / blok;
    }


}
