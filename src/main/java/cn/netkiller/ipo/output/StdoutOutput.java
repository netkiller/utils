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
public class StdoutOutput implements OutputInterface {

    public boolean open() {
		return true;

    }

    public boolean write(Object output) {
    	
        System.out.println(output);
		return true;
    }

    public boolean close() {
		return true;

    }

//    @Override
//    public void write() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
