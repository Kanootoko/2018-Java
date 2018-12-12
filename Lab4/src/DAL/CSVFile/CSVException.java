package DAL.CSVFile;

public class CSVException extends Exception {
	public CSVException(String msg) {
		super(msg);
	}
	public CSVException(Exception ex) {
		super(ex);
	}
	public CSVException(String msg, Exception ex) {
		super(msg, ex);
	}
}
