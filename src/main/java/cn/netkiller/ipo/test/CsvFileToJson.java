/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.netkiller.ipo.test;

import cn.netkiller.ipo.InputProcessOutput;
import cn.netkiller.ipo.input.CsvFileInput;
import cn.netkiller.ipo.input.Input;
import cn.netkiller.ipo.output.Output;
import cn.netkiller.ipo.output.OutputJson;
import cn.netkiller.ipo.process.Replace;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author neoch
 */
public class CsvFileToJson {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        System.out.println("Starting...");

        CsvFileInput csv = new CsvFileInput("C:\\Users\\neoch\\Documents\\NetBeansProjects\\ipo\\target\\classes\\data.csv");
//        System.out.print(fi.readLine());

        Input input = new Input();
        input.add(csv);
//        input.read();

        Output output = new Output();
        List<String> colume = new ArrayList<String>(Arrays.asList("id","name","age","address"));
//        colume.add("id");
//        colume.add("name");
        OutputJson json = new OutputJson(colume);
        output.add(json);

        cn.netkiller.ipo.process.Process process = new cn.netkiller.ipo.process.Process();
        process.add(new Replace("Hello", "Netkiller "));

        InputProcessOutput ipo = new InputProcessOutput();
        ipo.setInput(input);
        ipo.setProcess(process);
        ipo.setOutput(output);
        ipo.launch();
    }

}
