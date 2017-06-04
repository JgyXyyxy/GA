package com.sdu.pic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by J on 2017/5/17.
 */
public class PicAtri {

    private String imageSrc;
    private BufferedImage bufferedImage;
    private int height;
    private int width;
    private int minX;
    private int minY;

    public PicAtri(String imageSrc) {
        this.imageSrc = imageSrc;
        File imageFile = new File(imageSrc);
        try {
            bufferedImage = ImageIO.read(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        height = bufferedImage.getHeight();
        width = bufferedImage.getWidth();
        minX = bufferedImage.getMinX();
        minY = bufferedImage.getMinY();
        System.out.println("height: " + height+"  width: " + width);
    }


    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public int getHigh() {
        return height;
    }

    public void setHigh(int height) {
        this.height = height;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getMinX() {
        return minX;
    }

    public void setMinX(int minX) {
        this.minX = minX;
    }

    public int getMinY() {
        return minY;
    }

    public void setMinY(int minY) {
        this.minY = minY;
    }


    public Color[] getColor() {

        int num = height * width;
        int[][] rgb = new int[num][5];

        Color[] colorMap = new Color[num];

        int k = 0;
        for (int i = minY; i < height; i++) {
            for (int j = minX; j < width; j++) {
                int pixel = bufferedImage.getRGB(j, i);
                rgb[k][1] = i;
                rgb[k][2] = j;
                rgb[k][2] = (pixel & 0xff0000) >> 16;
                rgb[k][3] = (pixel & 0xff00) >> 8;
                rgb[k][4] = (pixel & 0xff);
                Color color = new Color(rgb[k][2], rgb[k][3], rgb[k][4]);
                colorMap[k] = color;
                k++;
            }
        }
        return colorMap;
    }

    public void setAlpha(Color[] colors) {
        int count = 0;
        try {
//            ImageIcon imageIcon = new ImageIcon(imageSrc);
//            BufferedImage bufferedImage = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight()
//                    , BufferedImage.TYPE_3BYTE_BGR);
//            Graphics2D g2D = (Graphics2D) bufferedImage.getGraphics();
//            g2D.drawImage(bufferedImage., 0, 0,
//                    imageIcon.getImageObserver());
            //循环每一个像素点，改变像素点的Alpha值
//            int alpha = 100;
            for (int j1 = bufferedImage.getMinY(); j1 < bufferedImage.getHeight(); j1++) {
                for (int j2 = bufferedImage.getMinX(); j2 < bufferedImage.getWidth(); j2++) {
//                    int pixel = bufferedImage.getRGB(j2, j1);
//                    int[]   rgb = new int[3];
//                    rgb[0] = (pixel & 0xff0000) >> 16;
//                    rgb[1] = (pixel & 0xff00) >> 8;
//                    rgb[2] = (pixel & 0xff);
//                    System.out.println("i=" + j1 + ",j=" + j2 + ":(" + rgb[0] + ","
//                            + rgb[1] + "," + rgb[2] + ")");
                    int pixel = colors[count].getRGB();
                    bufferedImage.setRGB(j2, j1, pixel);
                    count++;
                }
            }
//            g2D.drawImage(bufferedImage, 0, 0, imageIcon.getImageObserver());

            //生成图片为PNG
            ImageIO.write(bufferedImage, "png", new File(imageSrc));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPointAlpha(int x, int y, Color color) {
//        ImageIcon imageIcon = new ImageIcon(imageSrc);
//        BufferedImage bufferedImage = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight()
//                , BufferedImage.TYPE_3BYTE_BGR);
//        Graphics2D g2D = (Graphics2D) bufferedImage.getGraphics();
//        g2D.drawImage(imageIcon.getImage(), 0, 0, imageIcon.getImageObserver());
        int pixel = color.getRGB();
        bufferedImage.setRGB(x, y, pixel);
//        g2D.drawImage(bufferedImage, 0, 0, imageIcon.getImageObserver());
    }

    public void writePic() {
        try {
            ImageIO.write(bufferedImage, "png", new File(imageSrc));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
