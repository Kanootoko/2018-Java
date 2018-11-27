import java.io.*;

public class FractList {
	private Rational[] arr_;
	private Rational min_, max_;
	private int size_;
	public FractList() {
		arr_ = new Rational[2];
		size_ = 0;
	}
	private void ensureCapacity(int need) {
		Rational[] tmp;
		if (arr_.length < need) {
			tmp = new Rational[arr_.length * 2];
		} else if (arr_.length / 4 > need) {
			tmp = new Rational[arr_.length / 2];
		} else
			return;
		//System.arraycopy(tmp, 0, arr_, size_);
		for (int i = 0; i < size_; ++i)
			tmp[i] = arr_[i];
		arr_ = tmp;
	}
	public void push(Rational el) {
		ensureCapacity(size_ + 1);
		arr_[size_++] = el;
		if (size_ == 1 || Rational.isLarger(el, max_))
			max_ = el;
		if (size_ == 1 || Rational.isSmaller(el, min_))
			min_ = el;
	}
	public Rational top() {
		return arr_[size_ - 1];
	}
	public void pop() {
		if (size_ == 0)
			return;
		--size_;
		if (arr_[size_] == min_) {
			min_ = arr_[0];
			for (int i = 0; i < size_; ++i)
				if (Rational.isSmaller(arr_[i], min_))
					min_ = arr_[i];
		}
		if (arr_[size_] == max_) {
			max_ = arr_[0];
			for (int i = 0; i < size_; ++i)
				if (Rational.isLarger(arr_[i], max_))
					max_ = arr_[i];
		}
		ensureCapacity(size_);
	}
	public Rational max() {
		return max_;
	}
	public Rational min() {
		return min_;
	}
	public String toString() {
		String ans = "[";
		for (int i = 0; i < size_; i++) {
			ans += arr_[i];
			if (i < size_ - 1)
				ans += ", ";
		}	
		ans += "]";
		return ans;
	}
	public int largerThan(Rational x) {
		int ans = 0;
		for (int i = 0; i < size_; ++i)
			if (Rational.isLarger(arr_[i], x))
				++ans;
		return ans;
	}
	public int smallerThan(Rational x) {
		int ans = 0;
		for (int i = 0; i < size_; ++i)
			if (Rational.isSmaller(arr_[i], x))
				++ans;
		return ans;
	}
	public boolean write(String filename) {
		try {
			FileWriter fileWriter = new FileWriter(filename);
			BufferedWriter file = new BufferedWriter(fileWriter);
			file.write("" + size_ + "\n");
			for (int i = 0; i < size_; i++)
				file.write(arr_[i].numerator() + " " + arr_[i].denominator() + "\n");
			file.close();
			return true;
		} catch(Exception ex) {
			return false;
		}
	}
	public boolean read(String filename) {
		try {
			FileReader fileReader = new FileReader(filename);
			BufferedReader file = new BufferedReader(fileReader);
			size_ = new Integer(file.readLine());
			assert(size_ > 0);
			int alloc = 2;
			while (alloc < size_)
				alloc *= 2;
			arr_ = new Rational[alloc];
			for (int i = 0; i < size_; i++) {
				String[] tmp = file.readLine().split("\\s+");
				assert tmp.length == 2;
				arr_[i] = new Rational(new Integer(tmp[0]), new Integer(tmp[1]));
			}
			file.close();
			return true;
		} catch(Exception ex) {
			arr_ = new Rational[2];
			size_ = 0;
			return false;
		}
	}
}
