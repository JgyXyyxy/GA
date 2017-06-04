package com.sdu.swing;

import com.sdu.com.sdu.ga.Fitness;
import com.sdu.com.sdu.ga.GeneticAlgorithm;
import com.sdu.com.sdu.ga.Population;
import com.sdu.pic.PicAtri;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by J on 2017/6/3.
 */
public class ChooseImage extends JFrame {
    private JLabel label;
    private JFileChooser chooser;
    private JTextField textfield;
    private JPanel jPanel;
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 260;
    private PicAtri picAtri;
    private  Icon icon;
    private int count;

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public JFileChooser getChooser() {
        return chooser;
    }

    public void setChooser(JFileChooser chooser) {
        this.chooser = chooser;
    }

    public JTextField getTextfield() {
        return textfield;
    }

    public void setTextfield(JTextField textfield) {
        this.textfield = textfield;
    }

    public JPanel getjPanel() {
        return jPanel;
    }

    public void setjPanel(JPanel jPanel) {
        this.jPanel = jPanel;
    }

    public PicAtri getPicAtri() {
        return picAtri;
    }

    public void setPicAtri(PicAtri picAtri) {
        this.picAtri = picAtri;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }


    public ChooseImage() {
        setTitle("ImageViewer");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        label = new JLabel();
        add(label);
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        jPanel = new JPanel();
        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);
        JMenu menu = new JMenu("File");
        menubar.add(menu);
        JMenuItem openItem = new JMenuItem("Open");
        menu.add(openItem);
        JMenuItem exitItem = new JMenuItem("Close");
        menu.add(exitItem);
        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                int result = chooser.showOpenDialog(null);
                if(result == JFileChooser.APPROVE_OPTION){
                    String name = chooser.getSelectedFile().getPath();
                    icon = new ImageIcon(name);
                    picAtri = new PicAtri(name);
                    remove(jPanel);
                    label.setIcon(icon);
                    label.setBorder(new EmptyBorder(20,60,20,60));
                    int width = picAtri.getWidth()+120>240?picAtri.getWidth()+120:240;
                    setSize(width,picAtri.getHigh()+160);
                    add(label,BorderLayout.CENTER);
                    JLabel jLabel = new JLabel("迭代次数");
                    JButton jButton = new JButton("GO");
                    textfield = new JTextField("10000");
                    jPanel = new JPanel();
                    jPanel.add(jLabel);
                    jPanel.add(textfield);
                    jPanel.add(jButton);
                    add(jPanel,BorderLayout.SOUTH);
                    jButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        startRecover();
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }
                            }).start();
                        }
                    });
                }
            }
        });
        exitItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                System.exit(0);
            }
        });
        setVisible(true);
    }

    private void startRecover() throws IOException {
        count = Integer.parseInt(textfield.getText());
        int width = picAtri.getWidth();
        int height = picAtri.getHigh();
        int popsize = 16;
        double sum = 0;
        int sumBestFitness = 0;
        Color[] targetColors = picAtri.getColor();
        sum = 24*targetColors.length;
        String image = createBlankImage(width,height);
        PicAtri createdPic = new PicAtri(image);
        ShowImage createdShow = new ShowImage(createdPic);
        GeneticAlgorithm[] createdPionts = new GeneticAlgorithm[width*height];
        int index = 0;
        for (;index<width*height;index++) {
            Fitness fitness = new Fitness(targetColors[index]);
            Population population = new Population(popsize,fitness);
            createdPionts[index] = new GeneticAlgorithm(population,createdShow);
        }

        int maxIterNum = count;
        int generation = 1;
        while (generation <= maxIterNum){
            for (int i = picAtri.getMinY();i<height;i++){
                for (int j = picAtri.getMinX();j<width;j++){
                    index = j + i*width;
                    createdPionts[index].evolve(createdPic,j,i);
                    sumBestFitness +=createdPionts[index].getBestScore();
                }
            }
            createdPic.writePic();
            createdShow.updatePic();
            double percent = sumBestFitness/sum;
            createdShow.setText(generation,sumBestFitness,percent);
            System.out.println("generation: "+generation+"       "+"fitness: "+sumBestFitness +"     "+"percent: "+percent);
            generation++;
            sumBestFitness = 0;
        }
    }

    public String createBlankImage(int width, int height) throws IOException {

        String createdImage = "D://created.png";
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D graphic = bi.createGraphics();
        graphic.setColor(new Color(255,255,255));
        graphic.fillRect(0, 0, width, height);

//        for (int i = 0; i < width; i++) {
//            for (int j = 0; j < height; j++) {
//                //result[i][j] = bi.getRGB(i, j) & 0xFFFFFF;
//                System.out.println(bi.getRGB(i, j));
//                // bi.setRGB(i, j, 0xFFFFFF);
//            }
//        }

        Iterator<ImageWriter> it = ImageIO.getImageWritersByFormatName("png");
        ImageWriter writer = it.next();
        File f = new File(createdImage);
        ImageOutputStream ios = ImageIO.createImageOutputStream(f);
        writer.setOutput(ios);

        writer.write(bi);
        return createdImage;
    }


}

