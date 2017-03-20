
package euler;

public class PalindromeSum {

	public static void main(String[] args) {
		int currDrome=0;
		int currA=0;
		int currB=0;
		for(int a=100;a<1000;a++){
			for(int b=100;b<1000;b++){
		/*for(int a=10;a<100;a++){
			for(int b=10;b<100;b++){*/
				int prod=a*b;
				if(isPalindrome(prod)&&prod>currDrome){
						currDrome=prod;
						currA=a;
						currB=b;
				}
			}
		}
		System.out.println("curr drome = "+currDrome+ " factors = "+currA+","+currB);

	}
	private static boolean isPalindrome(int prod){
		String checkstr=""+prod;
		String revStr="";
		for(int i=checkstr.length()-1;i>=0;i--){
			revStr=revStr+checkstr.charAt(i);
		}
		return checkstr.equals(revStr);
	}

}
