package com.sdu.swing;

import com.sdu.pic.PicAtri;

import javax.swing.*;

/**
 * Created by J on 2017/5/21.
 */
public class Drawing extends JFrame{

    private JLabel imageLabel;
    private  Icon icon;
    private PicAtri picAtri;
    private JFileChooser chooser;
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 260;

    public Drawing(PicAtri atri)  {
        this.picAtri = atri;
        icon = new ImageIcon(atri.getImageSrc());
        setTitle("Show Image");
        setSize(picAtri.getWidth()+120,picAtri.getHigh()+120);
        imageLabel = new JLabel(icon);
        add(imageLabel);
        setVisible(true);
    }
}
