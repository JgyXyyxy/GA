import com.sdu.com.sdu.ga.Chromosome;
import com.sdu.com.sdu.ga.Fitness;
import com.sdu.com.sdu.ga.GeneticAlgorithm;
import com.sdu.com.sdu.ga.Population;
import com.sdu.pic.ColorFormat;
import com.sdu.pic.PicAtri;
import com.sdu.swing.ChooseImage;
import com.sdu.swing.Drawing;
import com.sdu.swing.ShowImage;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * Created by J on 2017/5/17.
 */
public class RecoverPic {

    public static void main(String[] args) throws Exception {
         new ChooseImage();
    }

//    public static void recoverPoint(String imageSrc) throws Exception {
//
//        PicAtri pic = new PicAtri(imageSrc);
//        int popsize = pic.getHigh()*pic.getWidth();
//        Color[] colors = pic.getColor();
//        ArrayList<Chromosome> chromosomes = new ArrayList<Chromosome>();
//        for (int i=0;i<popsize;i++){
//            Chromosome chromosome = new Chromosome(colors[i]);
//            chromosomes.add(chromosome);
//        }
//        Population population = new Population(chromosomes);
//        population.setPopSize(popsize);
//        population.init();
////        Drawing drawing = new Drawing(imageSrc);
//        ShowImage showImage = new ShowImage(pic);
//        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(population,showImage);
//
//        int maxIterNum = 5000;
//        while (geneticAlgorithm.getGeneration() < maxIterNum) {
//            //种群遗传
////            geneticAlgorithm.evolve(pic);
//            geneticAlgorithm.print();
//            geneticAlgorithm.setGeneration(geneticAlgorithm.getGeneration()+1);;
//        }
//    }






}
