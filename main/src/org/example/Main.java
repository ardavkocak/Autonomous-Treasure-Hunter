package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;


public class Main extends JComponent{
    ArrayList<Lokasyon> occupiedLocations = new ArrayList<Lokasyon>();
    public static int sayi1;
    public static void main(String[] args) {

        int izgaraSayi = 0;

        Main main = new Main();
        //OptionPane ekranı ile kullanıcıdan int değer alma.
        String girdi = JOptionPane.showInputDialog("Ekran Boyutunu Giriniz :");

        try{
            if(girdi != null)
            {
                //Burada kullanıcı tarafından girilen değer atanıyor sorun yok.
                izgaraSayi = Integer.parseInt(girdi);
                sayi1 = izgaraSayi;

            }
            else
            {
                JOptionPane.showMessageDialog(null,"Hatalı veya boş bir giriş yapıldı.", "Uyarı", JOptionPane.WARNING_MESSAGE);
            }
        }catch(NumberFormatException e) {

            JOptionPane.showMessageDialog(null,"Geçersiz tam sayı girişi" ,"Hata", JOptionPane.ERROR_MESSAGE);

        }
        JFrame frame = new JFrame("Başlangıç Paneli");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BaslangicPanel baslangicPanel = new BaslangicPanel();

        frame.getContentPane().add(baslangicPanel);

        frame.setSize(300, 150);
        frame.setVisible(true);


    }

}