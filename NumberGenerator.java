import java.util.*;

public class NumberGenerator {
	int min;
	int max;
	ArrayList<Integer> primeNumbers = new ArrayList<Integer>();

	public NumberGenerator(int min, int max) {
		this.min = min;
		this.max = max;
	}

	public void generatePrimeNumber(int min, int max) {
		primeNumbers.clear();

		for (int i = min; i != max; i++) {
			int j;
			for (j = 2; j < i; j++) {
				if ((i % j) == 0) {
					break;
				}
			}

			if (i == j) {
				primeNumbers.add(i);
			}
		}
	}

	public int getLargePrimeNumber() {
		int largePrimeNumber = 0;
		generatePrimeNumber(min, max);
		// int index = getrandomNumber(0, primeNumbers.size());
		largePrimeNumber = primeNumbers.get(0);

		return largePrimeNumber;
	}
}



