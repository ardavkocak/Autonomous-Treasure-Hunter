package org.example;

public class Lokasyon {


    public int x;
    public int y;
    public Lokasyon() {
        this.x = 10;
        this.y = 10;
    }
    public Lokasyon(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void setKoordinatlar(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }

    public void setX(int x)
    {
        this.x = x;
    }
    public void setY(int y)
    {
        this.y = y;
    }
}
