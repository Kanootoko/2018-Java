package BusinessLogic;

public class BLException extends Exception {
	public BLException(String str) {
		super(str);
	}
	public BLException(Exception ex) {
		super(ex);
	}
}
