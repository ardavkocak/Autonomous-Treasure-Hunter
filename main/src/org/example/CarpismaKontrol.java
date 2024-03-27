package org.example;

import javax.swing.text.html.parser.Entity;
import java.awt.*;

public class CarpismaKontrol {
    public class Entity {
        protected int x;
        protected int y;
        protected int width;
        protected int height;

        public Rectangle getBounds() {
            return new Rectangle(x, y, width, height);
        }
    }


    public static boolean checkCollision(Entity entity1, Entity entity2) {
            Rectangle rect1 = entity1.getBounds();
            Rectangle rect2 = entity2.getBounds();
            return rect1.intersects(rect2);
        }



}


