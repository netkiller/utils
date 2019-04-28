/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.netkiller.ipo.input;

/**
 *
 * @author netkiller
 */
public interface InputInterface {
	public boolean open();

	public Object readLine();

	public boolean close();
	
	public Object getDataType();
	
	public boolean hasNext();
}