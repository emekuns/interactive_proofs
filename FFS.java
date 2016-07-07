import java.util.*;

public class FFS {
	public static void main(String[] args) {
		sim();
	}

	public static void sim() {
		/**** Setup ***/
		System.out.println("-----------------------------");
		NumberGenerator numGen = new NumberGenerator(18, 29);

		// Choose two large prime integers p and q
		int p = numGen.getLargePrimeNumber();
		int q = numGen.getLargePrimeNumber();

		// Compute the product n = pq
		int n = p * q;
		System.out.println("Center computes Modulus n = " + n);

		// Create a secret number s such that gcd(s, 1) = 1.
		// When the gcd of two numbers is 1, the two numbers are 
		// said to be coprime
		int s = getCoprime(n);
		System.out.println("Center generates secret numbers s = " + s);

		// Compute v = s^2 (mod n)
		int v = (s * s) % n;
		System.out.println("Center computes v = " + v);

		simProcedure(s, n, v);
	}

	public static void simProcedure(int s, int n, int v) {
		/**** Procedure ***/
		System.out.println("-----------------------------");

		// 1. Peggy chooses a random integer r
		int r = getRandomNumber(1, n);
		System.out.println("Peggy chooses a random integer r = " + r);

		// Peggy then chooses a random sign
		int S = getRandomSign();

		// Peggy computes x = S * r^2 (mod n) which she 
		// sends to Victor
		int x = (S * (r * r)) % n;
		System.out.println("Peggy computes x = " + x);

		// Victor chooses a number a, where a = 0 or 1
		// Victor sends the number to Peggy
		int a = getRandomNumber(0, 1);
		System.out.println("Victor chooses a number a = " + a);

		int yw = 0;
		int y = 0;

		// Peggy computes y = r * (s^a) (mod n)
		// Peggy sends this number to Victor
		yw = r * (int)Math.pow(s, a);
		y = yw % n;
		System.out.println("Peggy computes y = " + y);

		// Victor checks that y^2 = + or - (x * (v^a))
		int ySqModN = (y * y) % n;
		System.out.println("Victor computes y^2 mod n = " + ySqModN);

		int test = (x * (int)Math.pow(v, a)) % n;
		System.out.println("Victor computes x * v^a = " + test);

		System.out.println("Victor checks that (y^2 mod n) == (x * v^a)");
		if (ySqModN == Math.abs(test)) {
			System.out.println("Success!");
		} else {
			System.out.println("Failure!");
		}
	}

	public static int getRandomNumber(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	public static int getRandomSign() {
		int[] signArr = {-1, 1};

		Random rand = new Random();
		int index = rand.nextInt(signArr.length);
		int randomSign = signArr[index];

		return randomSign;
	}

	public static int getGCD(int a, int b) {
		int c;

		while (a != 0) {
			c = a;
			a = b % a;
			b = c;
		}

		return b;
	}

	public static int getCoprime(int n) {
		int coprime;

		do {
			coprime = getRandomNumber(1, n);
		} while (getGCD(n, coprime) != 1);

		return coprime;
	}
}




