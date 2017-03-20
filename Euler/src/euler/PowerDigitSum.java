package euler;

import java.math.BigInteger;

public class PowerDigitSum {

	public static void main(String[] args) {
		/*for(int i = 0; i<15; i++){
			long num = 2<<i;
			System.out.println("num is "+num);
			System.out.println("sum of digs is "+sumOfDigs(num));
		}*/
		BigInteger bignum = BigInteger.ONE.add(BigInteger.ONE);
		bignum=bignum.pow(1000);
		//bignum=bignum.pow(15);
		System.out.println("big num is "+bignum.toString());
		//System.out.println("big num is "+sumOfDigs(bignum));
		String string = bignum.toString();
		long sum=0;
		for(int i=0;i<string.length();i++){
			sum+=Character.getNumericValue(string.charAt(i)) ;
		}
		System.out.println("sum of digs "+sum);
	}
	
	public static int sumOfDigs(long num){
		int sum=0;
		while(num>0){
			sum += num%10;
			num/=10;
		}
		return sum;
	}
	
	public static BigInteger sumOfDigs(BigInteger num){
		BigInteger sum = BigInteger.ZERO;
		while(num.compareTo(BigInteger.ZERO)>0){
			sum=sum.add(num.mod(BigInteger.TEN));
			num = num.divide(BigInteger.TEN);
		}
		return sum;
	}

}
