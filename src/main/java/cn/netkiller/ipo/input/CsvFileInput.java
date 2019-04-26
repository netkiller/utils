/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.netkiller.ipo.input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author neoch
 */
public class CsvFileInput extends FileInput {

	private String filename;
	private BufferedReader in;

	public CsvFileInput(String filename) {
		super(filename);
		this.filename = filename;
	}

	public String getColume() throws FileNotFoundException, IOException {
		final File namefile = new File(this.filename);
		final FileReader namereader = new FileReader(namefile);
		in = new BufferedReader(namereader);
		return in.readLine();
	}

	public boolean close() {
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.close();

	}
}
