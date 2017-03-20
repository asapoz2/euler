package euler;

import java.util.HashMap;
import java.util.Map;

public class NumberLetterCounts {
	static Map<Integer,String> names = new HashMap<Integer,String>();
	static{
		names.put(0, "");
		names.put(1, "one");
		names.put(2, "two");
		names.put(3, "three");
		names.put(4, "four");
		names.put(5, "five");
		names.put(6,"six");
		names.put(7,"seven");
		names.put(8,"eight");
		names.put(9,"nine");
		names.put(10,"ten");
		names.put(11,"eleven");
		names.put(12,"twelve");
		names.put(13,"thirteen");
		names.put(14,"fourteen");
		names.put(15,"fifteen");
		names.put(16,"sixteen");
		names.put(17,"seventeen");
		names.put(18,"eighteen");
		names.put(19,"nineteen");
		names.put(20,"twenty");
		names.put(30,"thirty");
		names.put(40,"forty");
		names.put(50,"fifty");
		names.put(60,"sixty");
		names.put(70,"seventy");
		names.put(80,"eighty");
		names.put(90,"ninety");
	}
	
	public static void main(String args[]){
		long sum=0;
		for(int i=1;i<=1000;i++){
			sum+=getStringNameCount(i);
		}
		System.out.println("sum is "+sum);
	}
	
	public static long getStringNameCount(int num){
		int lasttwo = num%100;
		long sum = 0;
		num-=lasttwo;
		if(lasttwo<=20){
			sum +=names.get(lasttwo).length();
		}else{
			sum+=names.get((lasttwo/10)*10).length()+names.get(lasttwo%10).length();
		}
		if(num>0&&num<1000){
			num/=100;
			if(sum!=0){
				sum+=names.get(num).length()+"hundredand".length();
			}else{
				sum+=names.get(num).length()+"hundred".length();
			}
		}else if(num==1000){
			sum+="onethousand".length();
		}
		
		return sum;
	}
}
