/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.netkiller.ipo.test;

import cn.netkiller.ipo.Input;
import cn.netkiller.ipo.InputProcessOutput;
import cn.netkiller.ipo.Output;
import cn.netkiller.ipo.Process;
import cn.netkiller.ipo.input.FileInput;
import cn.netkiller.ipo.output.StdoutOutput;
import cn.netkiller.ipo.process.Replace;

/**
 *
 * @author neoch
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Starting...");
        
        FileInput fi = new FileInput("/tmp/access.log");
//        System.out.print(fi.readLine());
        
        Input input = new Input();
        input.add(fi);
//        input.read();

        Output output = new Output();
        output.add(new StdoutOutput());
        
        Process process = new Process();
        process.add(new Replace("Hello","Netkiller "));
        process.add(new Replace("Neo","<Neo>"));
        process.add(new Replace("Tom","[Tom]"));
        
        InputProcessOutput ipo = new InputProcessOutput();
        ipo.setInput(input);
        ipo.setProcess(process);
        ipo.setOutput(output);
        ipo.launch();
    }
    
}
