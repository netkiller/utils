/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.netkiller.ipo.input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author neoch
 */
public class FileInput {

    private File file;
    private FileInputStream fileInputStream;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;

    public FileInput(String filename) {
        this.open(filename);
    }

    public void open(String filename) {

        try {
            file = new File(filename);
            fileInputStream = new FileInputStream(file);
            inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            bufferedReader = new BufferedReader(inputStreamReader);

        } catch (FileNotFoundException ex) {
             Logger.getLogger(FileInput.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String read() {
        String str;
        str = bufferedReader.toString();

        return str;
    }
    public String readLine() {
        try {
            return bufferedReader.readLine();
        } catch (IOException ex) {
            Logger.getLogger(FileInput.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public void close() {
        try {
            bufferedReader.close();
            inputStreamReader.close();
            fileInputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(FileInput.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
