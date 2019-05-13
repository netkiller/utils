package cn.netkiller.ipo.position;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FilePosition implements PositionInterface {
	private final static Logger logger = LoggerFactory.getLogger(FilePosition.class);
	private String filename;

	public FilePosition(String filename) {
		this.filename = filename;
		logger.debug("Position file {}", filename);
	}

	@Override
	public boolean set(Object pos) {
		PrintWriter output = null;
		try {
			output = new PrintWriter(this.filename);
			output.print(pos);
			output.flush();
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			output.close();
		}
		return false;
	}

	@Override
	public String get() {
		String pos = null;
		BufferedReader br = null;

		try {

			br = new BufferedReader(new FileReader(this.filename));
			String line;
			while ((line = br.readLine()) != null) {
				pos += line;
				System.out.println(line);
			}

		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				System.err.format("IOException: %s%n", ex);
			}
		}
		return pos;
	}

	@Override
	public void reset() {
		File file = new File(this.filename);
		file.delete();

	}

}
