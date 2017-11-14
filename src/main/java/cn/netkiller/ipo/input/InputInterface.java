/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.netkiller.ipo.input;

/**
 *
 * @author neoch
 */
public interface InputInterface {
    public void open();
    public String readLine();
    public void close();
}
