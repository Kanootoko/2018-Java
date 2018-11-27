
public class main {
	public static void main(String[] args) {
		FractList list = new FractList();
		int[] whatToPush = new int[]{1, 3, 1, 6, 6, 2, 3, 8, 8, 1, 7, -8};
		for (int i = 0; i + 1 < whatToPush.length; i += 2)
			list.push(new Rational(whatToPush[i], whatToPush[i + 1]));
		System.out.println("List: " + list);
		System.out.println("Maximum: " + list.max());
		System.out.println("Minimum: " + list.min());
		System.out.print("Input numerator of rational: ");
		Rational x = new Rational(1, 1);
		x.read();
		System.out.println("Larger than " + x + ": " + list.largerThan(x) + " items");
		System.out.println("Smaller than " + x + ": " + list.smallerThan(x) + " items");
		list.write("test.txt");
		list.pop();
		list.pop();
		list.pop();
		System.out.println("Reading was " + (list.read("test.txt") ? "successfull" : "an error"));
		System.out.println("List: " + list);
	}
}