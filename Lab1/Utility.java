
public class Utility {
    public static int GCD(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        int t;
        while (b != 0) {
            t = b;
            b = a % b;
            a = t;
        }
        return a;
	}
	
    public static int LCM(int a, int b){
        return a * b / GCD(a, b);
    }
}