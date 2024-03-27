package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.awt.Rectangle;
import java.util.Stack;


public class YeniHaritaPanel extends JPanel implements ActionListener {

    int a = 0;

    Main main = new Main();

    private int izgaraSayi = Main.sayi1;
    final int screenWidth = 1000;
    final int screenHeight = 1000;
    public int playerSpeed = 5;

    private BufferedImage characterImage, treeImage, winterTreeImage, rockImage, winterRockImage, wallImage, mountImage, winterMountImage,
            birdImage, beeImage, copperChestImage, emeraldChestImage, silverChestImage, goldChestImage, summerImage, winterImage;


    Random random = new Random();

    private Karakter Steve;

    ArrayList<Tree> Trees;
    ArrayList<Rock> Rocks;
    ArrayList<Wall> Walls;
    ArrayList<Mountain> Mountains;
    ArrayList<Bee> Bees;
    ArrayList<Bird> Birds;
    ArrayList<CopperChest> CopperChests;
    ArrayList<EmeraldChest> EmeraldChests;
    ArrayList<SilverChest> SilverChests;
    ArrayList<GoldChest> GoldChests;

    ArrayList<Line> Lines;

    ArrayList<Chest> collectedChests;

    Stack<Chest> chestStack;

    Timer timer;


    //Belirli iki sayı arasında rastegel bir int değer üretmek için method. (Örneğin 2-7 arası sayı üretmek için).
    public static int ProduceNumber(int lowerLimit, int upperLimit) {
        if (lowerLimit > upperLimit) {
            throw new IllegalArgumentException("Alt sınır üst sınırdan büyük olamaz.");
        }

        Random random = new Random();
        return random.nextInt(upperLimit - lowerLimit + 1) + lowerLimit;
    }


    //Her bir nesneden kaç tane üretileceğine dair atamalar.
    public int treeCount = ProduceNumber(4, 7);
    public int rockCount = ProduceNumber(4, 7);
    public int wallCount = ProduceNumber(4, 7);
    public int mountCount = ProduceNumber(4, 7);
    public int birdCount = 3;
    public int beeCount = 3;
    public int copperChestCount = ProduceNumber(7, 12);
    public int emeraldChestCount = ProduceNumber(7, 12);
    public int silverChestCount = ProduceNumber(7, 12);
    public int goldChestCount = ProduceNumber(7, 12);


    //Her bir nesnenin kaça kaç kare boyutunda olacağına dair atama yapılması için değişken instantiation başlatması.
    public int treeBlockCount;
    public int rockBlockCount;
    //public int wallBlockCount = ProduceNumber() zaten 10x1 olacak bir değer atamaya gerek yok. Wall classında değer atamasını yaptım.
    public int mountBlockCount = 8; //Normalde 15'e eşit olmalı denemek için 8 yaptım.
    public int birdBlockCount = 2;
    public int beeBlockCount = 2;
    public int copperChestBlockCount = 3;
    public int emeraldChestBlockCount = 3;
    public int silverChestBlockCount = 3;
    public int goldChestBlockCount = 3;


    public YeniHaritaPanel() {
        try {


            // Karakter görüntüsünü ekler.
            this.characterImage = ImageIO.read(new File("Images/Character.png"));
            //Yaz Teması için Ağaç görüntüsünü ekler.
            this.treeImage = ImageIO.read(new File("Images/Tree.png"));
            //Kış Teması için Ağaç görüntüsü ekler.
            this.winterTreeImage = ImageIO.read(new File("Images/WinterTree.png"));
            //Yaz Teması için Kaya görüntüsü ekler.
            this.rockImage = ImageIO.read(new File("Images/Rock.png"));
            //Kış Teması için Kaya görüntüsü ekler.
            this.winterRockImage = ImageIO.read(new File("Images/winterRock.png"));
            //Yaz Teması için Dağ görüntüsü ekler.
            this.mountImage = ImageIO.read(new File("Images/Mountain.png"));
            //Kış Teması için Dağ görüntüsü ekler.
            this.winterMountImage = ImageIO.read(new File("Images/WinterMountain.png"));
            //Duvar görüntüsü ekler.
            this.wallImage = ImageIO.read(new File("Images/Wall.png"));
            //Arı görüntüsü ekler.
            this.beeImage = ImageIO.read(new File("Images/Bee.png"));
            //Kuş görüntüsü ekler.
            this.birdImage = ImageIO.read(new File("Images/Bird.png"));
            //Copper Chest görüntüsü ekler.
            this.copperChestImage = ImageIO.read(new File("Images/copperChest.png"));
            //Emerald Chest görüntüsü ekler.
            this.emeraldChestImage = ImageIO.read(new File("Images/emeraldChest.png"));
            //Silver Chest görüntüsü ekler.
            this.silverChestImage = ImageIO.read(new File("Images/silverChest.png"));
            //Gold Chest görüntüsü ekler.
            this.goldChestImage = ImageIO.read(new File("Images/goldChest.png"));
            //WinterImage görüntüsünü ekler.
            this.winterImage = ImageIO.read(new File("Images/Winter.png"));
            //SumemrImage görüntüsünü ekler.
            this.summerImage = ImageIO.read(new File("Images/Summer.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }


        timer = new Timer(400, this);
        timer.start();


        Trees = new ArrayList<Tree>();
        Rocks = new ArrayList<Rock>();
        Walls = new ArrayList<Wall>();
        Mountains = new ArrayList<Mountain>();
        Bees = new ArrayList<Bee>();
        Birds = new ArrayList<Bird>();
        CopperChests = new ArrayList<CopperChest>();
        EmeraldChests = new ArrayList<EmeraldChest>();
        SilverChests = new ArrayList<SilverChest>();
        GoldChests = new ArrayList<GoldChest>();

        Lines = new ArrayList<>();

        collectedChests = new ArrayList<Chest>();

        chestStack = new Stack<>();




        Steve = new Karakter("Steve", 1, random.nextInt(500), random.nextInt(500));


        generateTrees();
        generateRocks();
        generateWalls();
        generateMountains();
        generateBees();
        generateBirds();
        generateCopperChest();
        generateEmeraldChest();
        generateSilverChest();
        generateGoldChest();


        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);


    }

    private void moveBees() {
        for (Bee bee : Bees) {
            bee.move(); // Her bir arıyı hareket ettir
        }
        repaint();
    }

    private void moveBirds() {
        for (Bird bird : Birds) {
            bird.move(); // Her bir kuşu hareket ettir
        }
        repaint();
    }


    int blok = screenWidth / izgaraSayi;

    public void generateTrees() {
        for (int i = 0; i < treeCount; i++) {
            treeBlockCount = ProduceNumber(2, 5);
            Tree tree = new Tree(blok * treeBlockCount);
            Trees.add(tree);
        }
    }

    public void generateRocks() {
        for (int i = 0; i < rockCount; i++) {
            rockBlockCount = ProduceNumber(2, 3);
            Rock rock = new Rock(blok * rockBlockCount);
            Rocks.add(rock);
        }
    }

    public void generateWalls() {
        for (int i = 0; i < wallCount; i++) {
            //Boyut her Duvar şeklinde sabit 10x1 olacağı için random değer atamaya gerek yok. Wall sınıfında değer consturctor içinde atandı.
            Wall wall = new Wall();
            Walls.add(wall);
        }
    }

    public void generateMountains() {
        for (int i = 0; i < mountCount; i++) {
            Mountain mountain = new Mountain(blok * mountBlockCount);
            Mountains.add(mountain);
        }
    }

    public void generateBirds() {
        for (int i = 0; i < birdCount; i++) {
            Bird bird = new Bird(blok * birdBlockCount);
            Birds.add(bird);
        }
    }

    public void generateBees() {
        for (int i = 0; i < beeCount; i++) {
            Bee bee = new Bee(blok * beeBlockCount);
            Bees.add(bee);
        }
    }

    public void generateCopperChest() {
        for (int i = 0; i < copperChestCount; i++) {
            CopperChest copperChest = new CopperChest(blok * copperChestBlockCount);
            CopperChests.add(copperChest);
        }
    }

    public void generateEmeraldChest() {
        for (int i = 0; i < emeraldChestCount; i++) {
            EmeraldChest emeraldChest = new EmeraldChest(blok * emeraldChestBlockCount);
            EmeraldChests.add(emeraldChest);
        }
    }

    public void generateSilverChest() {
        for (int i = 0; i < silverChestCount; i++) {
            SilverChest silverChest = new SilverChest(blok * silverChestBlockCount);
            SilverChests.add(silverChest);
        }
    }

    public void generateGoldChest() {
        for (int i = 0; i < goldChestCount; i++) {
            GoldChest goldChest = new GoldChest(blok * goldChestBlockCount);
            GoldChests.add(goldChest);
        }
    }





    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(summerImage,0,0,screenWidth/2,screenHeight,null);
        g.drawImage(winterImage, screenWidth/2,0,screenWidth/2,screenHeight,null);

        //Izgara Çizer.
        for (int i = 0; i < izgaraSayi; i++) {
            //Yatay çizgileri çizer
            g.drawLine(0, blok * i, screenWidth, blok * i);

            //Dikey çizgileri çizer.
            g.drawLine(blok * i, 0, blok * i, screenHeight);
        }




        for (Line line : Lines) {
            g.setColor(line.color);
            g.drawLine(line.getX1(), line.getY1(), line.getX2(), line.getY2());
        }

        // Karakter görüntüsünü çiz
        g.drawImage(characterImage, Steve.getX(), Steve.getY(),  blok * 4, blok * 4 ,  null);

        //Ağaç görüntüsünü çiz
        for (Tree tree : Trees) {
            if(tree.getKonum().getX() < screenWidth / 2)
            {
                g.drawImage(treeImage ,tree.getKonum().getX(), tree.getKonum().getY(), tree.getBoyut() ,tree.getBoyut(),null);
            }
            else {
                g.drawImage(winterTreeImage ,tree.getKonum().getX(), tree.getKonum().getY(), tree.getBoyut() ,tree.getBoyut(),null);
            }
        }
        //Kaya çizdirir.
        for (Rock rock : Rocks){
            if(rock.getKonum().getX() < screenWidth / 2)
            {
                g.drawImage(rockImage ,rock.getKonum().getX() ,rock.getKonum().getY() ,rock.getBoyut() ,rock.getBoyut() ,null);
            }
            else{
                g.drawImage(winterRockImage ,rock.getKonum().getX() ,rock.getKonum().getY() ,rock.getBoyut() ,rock.getBoyut() ,null);
            }
        }

        //Dağ çizdirir.
        for (Mountain mountain : Mountains){
            if(mountain.getKonum().getX() < screenWidth / 2)
            {
                g.drawImage(mountImage ,mountain.getKonum().getX() ,mountain.getKonum().getY() ,mountain.getBoyut() ,mountain.getBoyut() ,null);
            }
            else{
                g.drawImage(winterMountImage ,mountain.getKonum().getX() ,mountain.getKonum().getY() ,mountain.getBoyut() ,mountain.getBoyut() ,null);
            }
        }

        //Duvar çizdirir.
        for (Wall wall : Walls){
            g.drawImage(wallImage ,wall.getKonum().getX() ,wall.getKonum().getY() ,blok*10 ,blok ,null);
        }

        //Kuş çizdirir.
        for (Bird bird : Birds){
            g.drawImage(birdImage ,bird.getKonum().getX() ,bird.getKonum().getY() ,bird.getBoyut() ,bird.getBoyut() ,null);
        }

        //Arı çizdirir.
        for (Bee bee : Bees){
            g.drawImage(beeImage ,bee.getKonum().getX() ,bee.getKonum().getY() , bee.getBoyut() , bee.getBoyut() ,null);
        }

        //Copper Chest Çizdirir.
        for (CopperChest copperChest : CopperChests){
            if(!collectedChests.contains(copperChest))
            {
                g.drawImage(copperChestImage ,copperChest.getKonum().getX() ,copperChest.getKonum().getY() , copperChest.getBoyut() , copperChest.getBoyut() ,null);
            }
        }

        //Emerald Chest Çizdirir.
        for (EmeraldChest emeraldChest : EmeraldChests){
            if(!collectedChests.contains(emeraldChest))
            {
                g.drawImage(emeraldChestImage ,emeraldChest.getKonum().getX() ,emeraldChest.getKonum().getY() , emeraldChest.getBoyut() , emeraldChest.getBoyut() ,null);
            }
        }

        //Silver Chest Çizdirir.
        for (SilverChest silverChest : SilverChests){
            if(!collectedChests.contains(silverChest))
            {
                g.drawImage(silverChestImage ,silverChest.getKonum().getX() ,silverChest.getKonum().getY() , silverChest.getBoyut() , silverChest.getBoyut() ,null);
            }
        }

        //Gold Chest Çizdirir.
        for (GoldChest goldChest : GoldChests){
            if(!collectedChests.contains(goldChest))
            {
                g.drawImage(goldChestImage ,goldChest.getKonum().getX() ,goldChest.getKonum().getY() , goldChest.getBoyut() , goldChest.getBoyut() ,null);
            }
        }



    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // Her timer tetiklendiğinde oyun güncellenir
        updateGame(); // Oyunu güncelle
        repaint(); // Yeniden çiz
    }

    private void updateGame() {
        moveBees(); // Arıları hareket ettir
        moveBirds(); // Kuşları hareket ettir.

    }



}