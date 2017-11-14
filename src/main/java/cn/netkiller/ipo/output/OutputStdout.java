/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.netkiller.ipo.output;

/**
 *
 * @author neoch
 */
public class OutputStdout implements OutputInterface {

    public void open() {

    }

    public void write(String output) {
        System.out.println(output);
    }

    public void close() {

    }

//    @Override
//    public void write() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
