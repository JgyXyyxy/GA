package com.sdu.tool;

import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * Created by J on 2017/5/17.
 */
public class OutInFile {

    public OutInFile(String src) {
        try {
            //文件生成路径
            PrintStream ps=new PrintStream(src);
            System.setOut(ps);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
