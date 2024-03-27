package org.example;

public class Wall extends HareketsizEngel{

    private int boyutX;
    private int boyutY;

    public Wall()
    {
        super();
        this.boyutX = 10;
        this.boyutY = 1;
        this.setPassable(false);
    }

    public int getBoyutX()
    {
        return boyutX;
    }
    public int getBoyutY()
    {
        return boyutY;
    }
}
