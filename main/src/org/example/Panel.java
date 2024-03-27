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


public class Panel extends JPanel implements ActionListener {

    Uygulama uygulama = new Uygulama();

    private int izgaraSayi = Main.sayi1;
    public final int screenWidth = 1000;
    public final int screenHeight = 1000;
    public int playerSpeed = 5;

    private BufferedImage characterImage, treeImage, winterTreeImage , rockImage , winterRockImage ,wallImage , mountImage , winterMountImage ,
            birdImage , beeImage ,copperChestImage , emeraldChestImage , silverChestImage , goldChestImage , summerImage , winterImage;


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
    public static int ProduceNumber(int lowerLimit , int upperLimit)
    {
        if (lowerLimit > upperLimit) {
            throw new IllegalArgumentException("Alt sınır üst sınırdan büyük olamaz.");
        }

        Random random = new Random();
        return random.nextInt(upperLimit - lowerLimit + 1) + lowerLimit;
    }



    //Her bir nesneden kaç tane üretileceğine dair atamalar.
    public int treeCount = ProduceNumber(4, 7);
    public int rockCount = ProduceNumber(4,7);
    public int wallCount = ProduceNumber(4,7);
    public int mountCount = ProduceNumber(4,7);
    public int birdCount = 3;
    public int beeCount = 3;
    public int copperChestCount = ProduceNumber(7,12);
    public int emeraldChestCount = ProduceNumber(7,12);
    public int silverChestCount = ProduceNumber(7,12);
    public int goldChestCount = ProduceNumber(7,12);


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


    public Panel() {
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



        timer = new Timer(400,this);
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

        //Karakterin arkasından çizilecek olan çizgileri tutacak olan nesneleri tutan ArrayList
        Lines = new ArrayList<>();

        collectedChests = new ArrayList<Chest>();

        chestStack = new Stack<>();




        Steve = new Karakter("Steve" , 1 , random.nextInt(500) , random.nextInt(500));




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



    public int blok = screenWidth / izgaraSayi;

    public void generateTrees() {
        for (int i = 0; i < treeCount; i++) {
            treeBlockCount = ProduceNumber(2,5);
            Tree tree = new Tree(blok * treeBlockCount);
            Trees.add(tree);
        }
    }

    public void generateRocks() {
        for (int i = 0; i < rockCount; i++) {
            rockBlockCount = ProduceNumber(2,3);
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

    public void generateBees()
    {
        for (int i = 0; i < beeCount; i++)
        {
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
            EmeraldChest  emeraldChest = new EmeraldChest(blok * emeraldChestBlockCount);
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


    private void checkChestCollision() {
        Rectangle playerRect = new Rectangle(Steve.getX(), Steve.getY(), blok * 2, blok * 2); // Karakterin sınırlarını temsil eden bir dikdörtgen oluştur

        // Copper Chestlerin çakışıp çakışmadığını kontrol et.
        for (CopperChest copperChest : CopperChests) {
            Rectangle chestRect = new Rectangle(copperChest.getKonum().getX(), copperChest.getKonum().getY(), copperChest.getBoyut(), copperChest.getBoyut());

            if (playerRect.intersects(chestRect)) {
                System.out.println("Copper Chest  " + "X :" + copperChest.getX() + "  " + "Y :" + copperChest.getY());
                collectedChests.add(copperChest);
            }
        }

        // Emerald Chestlerin çakışıp çakışmadığını kontrol et.
        for (EmeraldChest emeraldChest : EmeraldChests) {
            Rectangle chestRect = new Rectangle(emeraldChest.getKonum().getX(), emeraldChest.getKonum().getY(), emeraldChest.getBoyut(), emeraldChest.getBoyut());

            if (playerRect.intersects(chestRect)) {
                System.out.println("Emerald Chest  " + "X :" + emeraldChest.getX() + "  " + "Y :" + emeraldChest.getY());
                collectedChests.add(emeraldChest);
            }
        }

        // Silver Chestlerin çakışıp çakışmadığını kontrol et
        for (SilverChest silverChest : SilverChests) {
            Rectangle chestRect = new Rectangle(silverChest.getKonum().getX(), silverChest.getKonum().getY(), silverChest.getBoyut(), silverChest.getBoyut());

            if (playerRect.intersects(chestRect)) {
                System.out.println("Silver Chest  " + "X :" + silverChest.getX() + "  " + "Y :" + silverChest.getY());
                collectedChests.add(silverChest);
            }
        }
        // Gold Chestlerin kontrolu
        for (GoldChest goldChest : GoldChests) {
            Rectangle chestRect = new Rectangle(goldChest.getKonum().getX(), goldChest.getKonum().getY(), goldChest.getBoyut(), goldChest.getBoyut());

            if (playerRect.intersects(chestRect)) {
                System.out.println("Gold Chest  " + "X :" + goldChest.getX() + "  " + "Y :" + goldChest.getY());
                collectedChests.add(goldChest);
            }
        }
        for (GoldChest goldChest : GoldChests) {
            Rectangle chestRect = new Rectangle(goldChest.getKonum().getX(), goldChest.getKonum().getY(), goldChest.getBoyut(), goldChest.getBoyut());

            for (EmeraldChest emeraldChest : EmeraldChests) {
                Rectangle otherChestRect = new Rectangle(emeraldChest.getKonum().getX(), emeraldChest.getKonum().getY(), emeraldChest.getBoyut(), emeraldChest.getBoyut());

                if (chestRect.intersects(otherChestRect)) {
                    goldChest.setX(-goldChest.getX());
                    goldChest.setY(-goldChest.getY());

                }
            }
        }
        for (GoldChest goldChest : GoldChests) {
            Rectangle chestRect = new Rectangle(goldChest.getKonum().getX(), goldChest.getKonum().getY(), goldChest.getBoyut(), goldChest.getBoyut());

            for (SilverChest silverChests : SilverChests) {
                Rectangle otherChestRect = new Rectangle(silverChests.getKonum().getX(), silverChests.getKonum().getY(), silverChests.getBoyut(), silverChests.getBoyut());

                if (chestRect.intersects(otherChestRect)) {

                    goldChest.setX(-goldChest.getX());
                    goldChest.setY(-goldChest.getY());

                }
            }
        }
        for (GoldChest goldChest : GoldChests) {
            Rectangle chestRect = new Rectangle(goldChest.getKonum().getX(), goldChest.getKonum().getY(), goldChest.getBoyut(), goldChest.getBoyut());

            for (CopperChest copperChest : CopperChests) {
                Rectangle otherChestRect = new Rectangle(copperChest.getKonum().getX(), copperChest.getKonum().getY(), copperChest.getBoyut(), copperChest.getBoyut());

                if (chestRect.intersects(otherChestRect)) {
                    goldChest.setX(-goldChest.getX());
                    goldChest.setY(-goldChest.getY());

                }
            }
        }
        for (CopperChest copperChest : CopperChests) {
            Rectangle chestRect = new Rectangle(copperChest.getKonum().getX(), copperChest.getKonum().getY(), copperChest.getBoyut(), copperChest.getBoyut());

            for (EmeraldChest emeraldChest : EmeraldChests) {
                Rectangle otherChestRect = new Rectangle(emeraldChest.getKonum().getX(), emeraldChest.getKonum().getY(), emeraldChest.getBoyut(), emeraldChest.getBoyut());

                if (chestRect.intersects(otherChestRect)) {

                    copperChest.setX(-copperChest.getX());
                    copperChest.setY(-copperChest.getY());

                }
            }
        }
        for (CopperChest copperChest : CopperChests) {
            Rectangle chestRect = new Rectangle(copperChest.getKonum().getX(), copperChest.getKonum().getY(), copperChest.getBoyut(), copperChest.getBoyut());

            for (SilverChest silverChests : SilverChests) {
                Rectangle otherChestRect = new Rectangle(silverChests.getKonum().getX(), silverChests.getKonum().getY(), silverChests.getBoyut(), silverChests.getBoyut());

                if (chestRect.intersects(otherChestRect)) {

                    copperChest.setX(-copperChest.getX());
                    copperChest.setY(-copperChest.getY());

                }
            }
        }
        for (EmeraldChest emeraldChest : EmeraldChests) {
            Rectangle chestRect = new Rectangle(emeraldChest.getKonum().getX(), emeraldChest.getKonum().getY(), emeraldChest.getBoyut(), emeraldChest.getBoyut());

            for (SilverChest silverChests : SilverChests) {
                Rectangle otherChestRect = new Rectangle(silverChests.getKonum().getX(), silverChests.getKonum().getY(), silverChests.getBoyut(), silverChests.getBoyut());

                if (chestRect.intersects(otherChestRect)) {
                    emeraldChest.setX(-emeraldChest.getX());
                    emeraldChest.setY(-emeraldChest.getY());

                }
            }
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

        Scanning();
        checkChestCollision();


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
    private void sortChestStack() {
        // Karakterin konumunu al
        int characterX = Steve.getX();
        int characterY = Steve.getY();

        Stack<Chest> tempStack = new Stack<>();
        while (!chestStack.isEmpty()) {
            Chest tempChest = chestStack.pop(); // Stack'ten eleman çıkar
            // Karaktere olan Manhattan uzaklığını hesapla
            int distanceToCharacter = Math.abs(tempChest.getKonum().getX() - characterX) + Math.abs(tempChest.getKonum().getY() - characterY);
            // Küçükten büyüğe doğru sıralı bir şekilde tempStack'e ekleyin
            while (!tempStack.isEmpty() && getDistanceToCharacter(tempStack.peek(), characterX, characterY) > distanceToCharacter) {
                chestStack.push(tempStack.pop()); // Geçici stack'i geri doldur
            }
            tempStack.push(tempChest); // Küçükten büyüğe doğru sıralı bir şekilde ekle
        }
        // Sıralanmış elemanları tekrar chestStack'e geri ekleyin
        while (!tempStack.isEmpty()) {
            chestStack.push(tempStack.pop());
        }
    }

    // Bir Chest nesnesinin karaktere olan Manhattan uzaklığını hesaplar
    private int getDistanceToCharacter(Chest chest, int characterX, int characterY) {
        return Math.abs(chest.getKonum().getX() - characterX) + Math.abs(chest.getKonum().getY() - characterY);
    }





    private boolean isGameOver = false; // Bayrak tanımı, başlangıçta oyunun bitmediği varsayılır


    private void Scanning() {
        // Karakterin etrafındaki sandıkları tespit etmek için bir tarama mesafesi.
        Steve.setScanningRange(500);
        while (!chestStack.isEmpty()) {
            System.out.println(chestStack.pop());
        }
        // Karakterin konumunu alın
        int characterX = Steve.getX();
        int characterY = Steve.getY();
        // Karakterin konumuna göre bir tarama alanı oluşturun
        Rectangle scanArea = new Rectangle (0, 0,
                2 * Steve.getScanningRange(), 2 * Steve.getScanningRange());

        // Karakterin etrafındaki sandıkları bulmak için bir tarama yapın
        for (CopperChest copperChest : CopperChests) {
            if (scanArea.contains(copperChest.getKonum().getX(), copperChest.getKonum().getY())) {
                if (!collectedChests.contains(copperChest)) {
                    chestStack.push(copperChest);
                }
            }
        }



        for (EmeraldChest emeraldChest : EmeraldChests) {
            if (scanArea.contains(emeraldChest.getKonum().getX(), emeraldChest.getKonum().getY())) {
                if (!collectedChests.contains(emeraldChest)) {
                    chestStack.push(emeraldChest);
                }
            }
        }


        for (SilverChest silverChest : SilverChests) {
            if (scanArea.contains(silverChest.getKonum().getX(), silverChest.getKonum().getY())) {
                if (!collectedChests.contains(silverChest)) {
                    chestStack.push(silverChest);
                }
            }
        }

        for (GoldChest goldChest : GoldChests) {
            if (scanArea.contains(goldChest.getKonum().getX(), goldChest.getKonum().getY())) {
                if (!collectedChests.contains(goldChest)) {
                    chestStack.push(goldChest);
                }
            }
        }
        sortChestStack();

        try{ //Try-Catch bloğu içinde kullanmadan da çalışıyor kod.
            // Stack yapısındaki en üstteki sandığa git
            if (!chestStack.isEmpty()) {

                Chest targetChest = chestStack.peek(); // En üstteki sandığı al
                int targetX = targetChest.getKonum().getX();
                int targetY = targetChest.getKonum().getY();


                if (characterX < targetX) {
                    moveRight();
                    System.out.println("Sağa hareket etti");
                } else if (characterX > targetX) {
                    moveLeft();
                    System.out.println("Sola hareket etti");
                }
                if (characterY < targetY) {
                    moveDown();
                    System.out.println("Aşağı hareket etti");
                } else if (characterY > targetY) {
                    moveUp();
                    System.out.println("Yukarı hareket etti");
                }
                // Stack'ten en üstteki sandığı çıkar
                chestStack.pop();

            }
            if (chestStack.isEmpty() && !isGameOver) {
                isGameOver = true; // Oyun bitti bayrağını işaretle
                System.out.println("Oyun bitti!");
                JFrame gameOverFrame = new JFrame("Oyun Bitti");
                gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gameOverFrame.setSize(300, 200);

                GameOverPanel gameOverPanel = new GameOverPanel();
                JLabel stepYazisi = new JLabel("Bütün Sandıların Toplanması için Atılan Adım Sayısı" + ": " + uygulama.Step);
                gameOverPanel.add(stepYazisi);

                gameOverFrame.add(gameOverPanel);

                gameOverFrame.setVisible(true);
            }
            else {
                while (Steve.getScanningRange() <= 1000) {
                    Steve.setScanningRange(Steve.getScanningRange() * 2);
                    System.out.println(Steve.getScanningRange());

                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void moveLeft() {
        int oldX = Steve.getX();
        Steve.setX(Steve.getX() - playerSpeed);
        int newX = Steve.getX();
        Lines.add(new Line(oldX, Steve.getY(), newX, Steve.getY(), Color.BLUE)); // Yeşil çizgiyi koleksiyona ekle
        uygulama.artirHareketSayisi();
        uygulama.setStep(playerSpeed , blok);
        repaint();
    }

    private void moveRight() {
        int oldX = Steve.getX();
        Steve.setX(Steve.getX() + playerSpeed);
        int newX = Steve.getX();
        Lines.add(new Line(oldX, Steve.getY(), newX, Steve.getY(), Color.BLUE)); // Yeşil çizgiyi koleksiyona ekle
        uygulama.artirHareketSayisi();
        uygulama.setStep(playerSpeed , blok);
        repaint();
    }

    private void moveUp() {
        int oldY = Steve.getY();
        Steve.setY(Steve.getY() - playerSpeed);
        int newY = Steve.getY();
        Lines.add(new Line(Steve.getX(), oldY, Steve.getX(), newY, Color.BLUE)); // Yeşil çizgiyi koleksiyona ekle
        uygulama.artirHareketSayisi();
        uygulama.setStep(playerSpeed , blok);
        repaint();
    }

    private void moveDown() {
        int oldY = Steve.getY();
        Steve.setY(Steve.getY() + playerSpeed);
        int newY = Steve.getY();
        Lines.add(new Line(Steve.getX(), oldY, Steve.getX(), newY, Color.BLUE)); // Mavi çizgiyi koleksiyona ekle
        uygulama.artirHareketSayisi();
        uygulama.setStep(playerSpeed , blok);
        repaint();
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