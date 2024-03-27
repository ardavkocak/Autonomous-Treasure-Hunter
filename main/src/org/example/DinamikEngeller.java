package org.example;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public abstract class DinamikEngeller extends Engel {

    private int hareketAlani;
    private boolean hareketYonu; // False ise sağa-sola , True ise yukarı-aşağı


    public int getHareketAlani() {
        return hareketAlani;
    }

    public void setHareketAlani(int hareketAlani) {
        this.hareketAlani = hareketAlani;
    }

    public boolean isHareketYonu() {
        return hareketYonu;
    }

    public void setHareketYonu(boolean hareketYonu) {
        this.hareketYonu = hareketYonu;
    }
}
