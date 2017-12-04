/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.netkiller.ipo.input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.netkiller.ipo.Input;

/**
 *
 * @author neoch
 */
public class FileInput implements InputInterface {

	private final static Logger logger = LoggerFactory.getLogger(FileInput.class);
	private File file;
	private FileInputStream fileInputStream;
	private InputStreamReader inputStreamReader;
	private BufferedReader bufferedReader;

	private String filename;

	public FileInput(String filename) {
		this.filename = filename;
		logger.info(filename);
	}

	public Input open() {

		try {
			file = new File(this.filename);
			fileInputStream = new FileInputStream(file);
			inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
			bufferedReader = new BufferedReader(inputStreamReader);

		} catch (FileNotFoundException ex) {
			logger.error(ex.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String read() {
		String str;
		str = bufferedReader.toString();

		return str;
	}

	public String readLine() {
		try {			
			return bufferedReader.readLine();
		} catch (IOException ex) {
			logger.error(ex.getMessage());
		}
		return null;
	}

	public Input close() {
		try {
			bufferedReader.close();
			inputStreamReader.close();
			fileInputStream.close();
		} catch (IOException ex) {
			logger.error(ex.getMessage());
		}
		return null;

	}

	@Override
	public boolean hasNextLine() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<String> readLines() {
		// TODO Auto-generated method stub
		return null;
	}

	// @Override
	// public void open() {
	// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	// }
}
