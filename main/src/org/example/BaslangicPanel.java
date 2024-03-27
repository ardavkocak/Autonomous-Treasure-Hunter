package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BaslangicPanel extends JPanel {

    private JButton yeniHaritaButton;
    private JButton baslatButton;

    public BaslangicPanel() {

        setLayout(new BorderLayout());


        yeniHaritaButton = new JButton("Yeni Harita Oluştur");
        baslatButton = new JButton("Başlat");


        JPanel buttonPanel = new JPanel();
        buttonPanel.add(yeniHaritaButton);
        buttonPanel.add(baslatButton);
        add(buttonPanel, BorderLayout.NORTH);


        baslatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Çerçeve yarattık.
                JFrame frame = new JFrame("GAME SCREEN");
                frame.setLocation(10,10);
                Panel panel = new Panel();
                panel.requestFocus();
                panel.setFocusTraversalKeysEnabled(false);
                frame.add(panel);
                frame.pack();
                frame.setVisible(true);

            }
        });
        yeniHaritaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Çerçeve yarattık.
                JFrame frame = new JFrame("MAP SCREEN");
                frame.setLocation(10,10);
                YeniHaritaPanel YeniHaritaPanel = new YeniHaritaPanel();
                YeniHaritaPanel.requestFocus();
                YeniHaritaPanel.setFocusTraversalKeysEnabled(false);
                frame.add(YeniHaritaPanel);
                frame.pack();
                frame.setVisible(true);

            }
        });

    }
}