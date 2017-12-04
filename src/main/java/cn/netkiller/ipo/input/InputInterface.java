/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.netkiller.ipo.input;

import java.util.List;

import cn.netkiller.ipo.Input;

/**
 *
 * @author neoch
 */
public interface InputInterface {
    public Input open();
    public String readLine();
    public Input close();
	public boolean hasNextLine();
	public List<String> readLines();
}
