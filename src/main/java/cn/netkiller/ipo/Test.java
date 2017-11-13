/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.netkiller.ipo;

import cn.netkiller.ipo.input.FileInput;
import cn.netkiller.ipo.input.Input;
import cn.netkiller.ipo.output.Output;
import cn.netkiller.ipo.output.OutputStdout;

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
        
        FileInput fi = new FileInput("C:\\Users\\neoch\\Documents\\NetBeansProjects\\ipo\\target\\classes\\input.txt");
//        System.out.print(fi.readLine());
        
        Input input = new Input();
        input.add(fi);
//        input.read();

        Output output = new Output();
        output.add(new OutputStdout());
        
        InputProcessOutput ipo = new InputProcessOutput();
        ipo.setInput(input);
        ipo.setOutput(output);
        ipo.launch();
    }
    
}
