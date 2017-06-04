package com.sdu.swing;

import com.sdu.pic.PicAtri;

import javax.swing.*;
import java.awt.*;

/**
 * Created by J on 2017/5/21.
 */
public class Drawing22 extends JFrame{

    private JLabel imageLabel;
    private  Icon icon;
    private PicAtri picAtri;

    public Drawing22(PicAtri atri)  {
        this.picAtri = atri;
        icon = new ImageIcon(atri.getImageSrc());
        setTitle("Show Image");
        setSize(picAtri.getWidth()+120,picAtri.getHigh()+180);
        imageLabel = new JLabel(icon);
        add(imageLabel, BorderLayout.CENTER);
        JPanel jPanel = new JPanel();
        JLabel jLabel = new JLabel("迭代次数");
        JButton jButton = new JButton("GO");
        JTextField jTextField = new JTextField("10000");
        jPanel.add(jLabel);
        jPanel.add(jTextField);
        jPanel.add(jButton);
        add(jPanel,BorderLayout.SOUTH);
        setVisible(true);
    }
}
