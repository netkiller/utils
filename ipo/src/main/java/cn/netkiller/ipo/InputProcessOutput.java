/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.netkiller.ipo;

import cn.netkiller.ipo.input.Input;
import cn.netkiller.ipo.output.Output;

/**
 *
 * @author neoch
 */
public class InputProcessOutput {
    private Input input;
    private Output output;
    private Process process;
    public void setInput(Input input){
        this.input = input;
    }
    public void setOutput(Output output){
        this.output = output;
    }
    public void setProcess(Process process){
        this.process = process;
    }
    public void launch(){
        String in = input.read();
        this.output.write(in);
        this.output.write("Helloword");
    }
}
