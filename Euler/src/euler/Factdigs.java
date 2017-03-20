package euler;

public class Factdigs {
	static int[] digfacts = new int[] { 0, 1, 2, 6, 24, 120, 720, 5040, 40320,
			362880 };

	public static void main(String[] args) {
		int result = 0;
		for (int i = 10; i < 2540161; i++) {
			int sumOfFacts = 0;
			int number = i;
			while (number > 0) {
				int d = number % 10;
				number /= 10;
				sumOfFacts += digfacts[d];
			}

			if (sumOfFacts == i) {
				System.out.println("found one " + i);
				result += i;
			}
		}
		System.out.println(result);
	}
}
