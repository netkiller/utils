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
public class Output {
    
    private OutputStdout output;
    public void add(OutputStdout output){
        this.output = output;
    }
    public void write(String output){
        this.output.write(output);
    }
}