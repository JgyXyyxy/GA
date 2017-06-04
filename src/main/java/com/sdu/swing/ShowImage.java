package com.sdu.swing;

import com.sdu.pic.PicAtri;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by J on 2017/5/20.
 */
public class ShowImage extends JFrame {

    private JLabel imageLabel;
    private JLabel textArea;
    private  Icon icon;
    private PicAtri picAtri;
    String src ;

    public ShowImage(PicAtri atri) throws HeadlessException {
        this.picAtri = atri;
        src = picAtri.getImageSrc();
        icon = new ImageIcon(atri.getImageSrc());
        setTitle("reappear image");
        int width = 460>(picAtri.getWidth()+100)?460:(picAtri.getWidth()+100);
        setSize(width,picAtri.getHigh()+120);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        imageLabel = new JLabel(icon,JLabel.CENTER);
        imageLabel.setBorder(new EmptyBorder(20,20,20,20));
        textArea = new JLabel();
        add(imageLabel,BorderLayout.NORTH);
        add(textArea,BorderLayout.CENTER);
        setVisible(true);
        imageLabel.setIcon(icon);
        setLocationRelativeTo(null);
    }




    public void updatePic(){
        new Thread(new Runnable() {
            public void run() {
                try {
                    icon = new ImageIcon(ImageIO.read(new File(src)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageLabel.setIcon(icon);

            }
        }).start();

    }

    public void setText(final int generation, final double fitness, final double percent){
        new Thread(new Runnable() {
            public void run() {
                StringBuilder sb= new StringBuilder();
                sb.append("        "+"generation: "+generation+"       "+"fitness: "+fitness +"     "+"percent: "+percent);
                textArea.setText(sb.toString());
            }
        }).start();

    }
}
