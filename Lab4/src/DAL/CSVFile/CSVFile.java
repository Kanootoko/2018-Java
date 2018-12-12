package DAL.CSVFile;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;

public class CSVFile {
	private int columns = -1;
	private int now = 0;
	private final String filename;
	List<String[]> lines;
	private CSVFile(String filename) {
		this.filename = filename;
		lines = new ArrayList<String[]>();
	}
	static public CSVFile fromFile(String filename) throws FileNotFoundException, CSVException {
		CSVFile parser = new CSVFile(filename);
		try (FileReader fr = new FileReader(filename);
		     BufferedReader file = new BufferedReader(fr);
		    ) {
			parser.lines = new ArrayList<String[]>();
			while (true) {
				String[] params = file.readLine().replace("\"", "").split(",");
				if (parser.columns == -1)
					parser.columns = params.length;
				else if (params.length != parser.columns)
					throw new CSVException(String.format("CSV file \"%s\" is broken. Line [%s] has not the same columns than previous (%d and %d)",
					                                     filename, String.join(",", params), params.length, parser.columns));
				parser.lines.add(params);
			}
		} catch (IOException ex) {
			throw new RuntimeException("IOException at CSVFile.fromFile(). This never happens anyway");
		}
	}
	public String getString(int columnNumber) throws CSVException, IndexOutOfBoundsException {
		if (now >= lines.size())
			throw new CSVException("CSVFile is finished");
		if (now == -1)
			now = 0;
		return lines.get(now)[columnNumber + 1];
	}
	public void updateFromFile() throws CSVException {
		CSVFile tmp;
		try {
			tmp = CSVFile.fromFile(filename);
		} catch (IOException ex) {
			throw new CSVException("CSV file got lost");
		}
		lines = tmp.lines;
		now = 0;
		columns = tmp.columns;
	}
	public void writeToFile() throws IOException {
		writeToFile(filename);
	}
	public void writeToFile(String filename) throws IOException {
		FileWriter file = new FileWriter(filename);
		StringBuilder sb;
		try {
			for (String[] line: lines) {
				sb = new StringBuilder();
				for (String str: line) {
					sb.append((sb.length() == 0 ? "" : ","));
					if (str.contains(" ")) {
						sb.append("\"");
						sb.append(str);
						sb.append("\"");
					} else
						sb.append(str);
				}
				sb.append("\n");
				file.write(sb.toString());
			}
		} finally {
			try {
				file.close();
			} catch (Exception ex) { }
		}
	}
	public void insert(String[] line) throws CSVException {
		if (line.length != columns)
			throw new CSVException("Inserting failed, number of columns doesen't match");
		lines.add(line);
	}
	public void update(String[] line) throws CSVException {
		if (line.length != columns)
			throw new CSVException("Inserting failed, number of columns doesen't match");
		String[] tmp = lines.get(now);
		for (int i = 0; i < tmp.length; ++i)
			tmp[i] = line[i];
	}
	public void delete() throws CSVException {
		if (lines.size() == 0 || now >= lines.size())
			throw new CSVException("Deleting is unavaliable");
		lines.remove(now);
		now--;
	}
	public int getColumns() {
		return columns;
	}
	public boolean hasNext() {
		return now < lines.size() - 1;
	}
	public boolean next() {
		if (now < lines.size())
			now++;
		return now < lines.size();
	}
	public void rewind() {
		now = -1;
	}
}
