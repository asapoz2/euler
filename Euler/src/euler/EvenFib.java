package euler;

public class EvenFib {
	public static void main(String[] args){
		int a=1;
		int b=2;
		int evensum=2;
		while(b<4000000){
		//while(b<100){
			int temp=a+b;
			a=b;
			b=temp;
			if(b%2==0){
				evensum+=b;
			}
			System.out.println("a is "+a+" b is "+b);
			System.out.println("sum so far :"+evensum);
		}
		
		
		
	}
}
