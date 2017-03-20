package euler;

import java.util.ArrayList;

public class Fifthpowers {
	static int[] arr = {
			0,
			1,
			(int)(Math.pow(2.0, 5.0)),
			(int)(Math.pow(3.0, 5.0)),
			(int)(Math.pow(4.0, 5.0)),
			(int)(Math.pow(5.0, 5.0)),
			(int)(Math.pow(6.0, 5.0)),
			(int)(Math.pow(7.0, 5.0)),
			(int)(Math.pow(8.0, 5.0)),
			(int)(Math.pow(9.0, 5.0)),
	};
	static int[] arr4 = {
		0,
		1,
		(int)(Math.pow(2.0, 4.0)),
		(int)(Math.pow(3.0, 4.0)),
		(int)(Math.pow(4.0, 4.0)),
		(int)(Math.pow(5.0, 4.0)),
		(int)(Math.pow(6.0, 4.0)),
		(int)(Math.pow(7.0, 4.0)),
		(int)(Math.pow(8.0, 4.0)),
		(int)(Math.pow(9.0, 4.0)),
};
	public static void main(String args[]){
		ArrayList<Integer> results = new ArrayList<Integer>();
		for(int i=1000;i<299999;i++){
			if(Fifthpowers.isSumOf5s(i)){
				//System.out.println("found one:"+i);
				Fifthpowers.printEval(i);
				results.add(i);
			}
		}
		int sum=0;
		for(Integer result:results){
			System.out.print(result+",");
			sum+=result;
		}
		System.out.println("final sum:"+sum);
		//System.out.println("max? "+arr[5]*5);
		
	}
	public static boolean isSumOf5s(int number){
		int curr=number;
		int sum=0;
		while(curr>0){
			int dig = curr%10;
			curr/=10;
			sum+=arr[dig];
		}
		return sum==number;
	}
	public static boolean isSumOf4s(int number){
		int curr=number;
		int sum=0;
		while(curr>0){
			int dig = curr%10;
			curr/=10;
			sum+=arr4[dig];
		}
		return sum==number;
	}
	
	public static void printEval(int number){
		int curr=number;
		int sum=0;
		System.out.println("full number:"+number);
		while(curr>0){
			int dig = curr%10;
			curr/=10;
			StringBuilder output = new StringBuilder("+digit:").append(dig).append(" exponentiated:")
					.append(arr[dig]);
			sum+=arr[dig];
			output.append(" sum so far:").append(sum);
			System.out.println(output.toString());
		}
	}
}
