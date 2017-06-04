package com.sdu.pic;

import com.sdu.tool.Numeration;

import java.awt.*;

/**
 * Created by J on 2017/5/17.
 */
public class ColorFormat {

    public static String toHexFromColor(Color color){
        String r,g,b;
        StringBuilder hex = new StringBuilder();
        r = Integer.toHexString(color.getRed());
        g = Integer.toHexString(color.getGreen());
        b = Integer.toHexString(color.getBlue());
        r = r.length() == 1 ? "0" + r : r;
        g = g.length() ==1 ? "0" +g : g;
        b = b.length() == 1 ? "0" + b : b;
        r = r.toUpperCase();
        g = g.toUpperCase();
        b = b.toUpperCase();
        hex.append(r);
        hex.append(g);
        hex.append(b);
        return hex.toString();

    }

    public static boolean[] toBinaryGene(Color color) throws Exception {

        boolean[] binaryGene = new boolean[24];
        String binaryString = Numeration.hexString2binaryString(toHexFromColor(color));
        char[] chars = binaryString.toCharArray();
        for (int i = 0 ; i < 24 ; i++){
            if (Character.getNumericValue(chars[i]) == 1){
                binaryGene[i] = true;
            } else if (Character.getNumericValue(chars[i]) == 0){
                binaryGene[i] = false;
                }else {
                throw new Exception("Convert false");
                }
        }
        return binaryGene;
    }

    public static Color binary2Color(boolean[] binary){

        int red = 0;
        int green = 0;
        int blue =0;
        int i = 0;
        int[] binaryInt = new int[24];
        for (int j = 0;j< 24;j++) {
            if (binary[j]) {
                binaryInt[j] = 1;
            } else {
                binaryInt[j] = 0;
            }
        }

        for(int mutiple = 128;i<8;i++){
            red = red + binaryInt[i]*mutiple;
            mutiple = mutiple/2;
        }
        for(int mutiple = 128;i<16;i++){
            green= green + binaryInt[i]*mutiple;
            mutiple = mutiple/2;
        }
        for(int mutiple = 128;i<24;i++){
            blue = blue + binaryInt[i]*mutiple;
            mutiple = mutiple/2;
        }

        return  new Color(red, green, blue);
    }


}
