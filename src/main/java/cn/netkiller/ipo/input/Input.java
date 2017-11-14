/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.netkiller.ipo.input;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author neoch
 */
public class Input {

    private InputInterface input;

    public Input() {

    }

    public Input add() {
        return this;
    }

    public Input add(FileInput input) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.input = input;
        return this;
    }

    public String read() {
//        this.input.read();

        return this.input.readLine();

    }
}
