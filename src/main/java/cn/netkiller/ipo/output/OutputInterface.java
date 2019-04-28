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
public interface OutputInterface {

    public void open();

    public void write(Object output);

    public void close();
}
