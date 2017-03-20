package euler;
import org.junit.Test;
//import org.junit.Assert;
import static org.junit.Assert.assertEquals;

public class Main {
	public static void main(String[] args){
		int a=1;
		int b=2;
		//int evensum=0;
		//while(b<4000000){
		while(b<10){
			int temp=a+b;
			a=b;
			b=temp;
			System.out.print("a is "+a+" b is "+b+"\n");
		}
		String str= "Junit is working fine";
	    assertEquals("Junit is working fine",str);
		
		
	}
	
	@Test
	public void testStr(){
		String str= "Junit is working fine";
	    assertEquals("Junit is working fine",str);
	}
}
