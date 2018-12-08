package BusinessLogic;

public class BLException extends Exception {
	public BLException(String msg) {
		super(msg);
	}
	public BLException(Exception ex) {
		super(ex);
	}
	public BLException(String msg, Exception ex) {
		super(msg, ex);
	}
}
