
public class Rational {
	private int m_, n_;
	public Rational(int numerator, int denominator) {
		m_ = numerator;
		n_ = denominator;
		int tmp;
		if ((tmp = Utility.GCD(m_, n_)) != 1) {
			m_ /= tmp;
			n_ /= tmp;
		}
	}
	public Rational(Rational other) {
		m_ = other.m_;
		n_ = other.n_;
	}
	public int numerator() {
		return m_;
	}
	public int denominator() {
		return n_;
	}
	static public Double toDouble(Rational r) {
		return new Double(r.m_) / r.n_;
	}
	public Double toDouble() {
		return Rational.toDouble(this);
	}
	public String toString() {
		return m_ + "/" + n_ + " [" + (double) m_ / n_ + "]";
	}
	public static boolean isLarger(Rational left, Rational right) {
		return toDouble(left) > toDouble(right);
	}
	public static boolean isSmaller(Rational left, Rational right) {
		return toDouble(left) < toDouble(right);
	}
	public static boolean isEqual(Rational left, Rational right) {
		double EXP = 1e-6;
		return (toDouble(left) - toDouble(right)) < EXP;
	}
	public boolean isLarger(Rational other) {
		return Rational.isLarger(this, other);
	}
	public boolean isSmaller(Rational other) {
		return Rational.isSmaller(this, other);
	}
	public boolean isEqual(Rational other) {
		return Rational.isEqual(this, other);
	}
	public void read() {
		String[] line = System.console().readLine().split("\\s+");
		String[] mn = new String[2];
		if (line.length == 1) {
			System.out.print("Input denominator of rational: ");
			mn[0] = line[0];
			mn[1] = System.console().readLine().split("\\s+")[0];
		} else if (line.length > 2) {
			System.out.println("Error with number of parameters: there should be either pair of " +
			                   "numerator and denominator on one line, or both on individual lines");
		} else {
			mn = line;
		}
		line = null;
		try {
			m_ = new Integer(mn[0]);
			n_ = new Integer(mn[1]);
		} catch(NumberFormatException ex) {
			throw ex;
		}
	}
	
	public Rational inverse() {
		return new Rational(this.n_, this.m_);
	}

	public Rational simplify() {
        int gcd = Utility.GCD(m_, n_);
        m_ /= gcd;
        n_ /= gcd;
        return this;
    }
}
