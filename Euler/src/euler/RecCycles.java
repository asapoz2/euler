package euler;

import java.util.ArrayList;

public class RecCycles {

	public static void main(String[] args) {
		/*Fraction third = new Fraction(1,3);
		third.findIfHasCycle();*/
		for(int i=2; i<10; i++){
			new Fraction(1,i).findIfHasCycle();
		}
	}
	public static class Fraction{
		int num;
		int denom;
		final int originaldenom;
		
		public Fraction(int num, int denom){
			originaldenom=denom;
			this.num=num;
			this.denom=denom;
		}
		public int nextRemainder(){
			return this.num*10 % this.denom;
		}
		
		private int nextDivisor() {
			return this.num*10 / this.denom;
		}
		public void findIfHasCycle(){
			int max=500;
			ArrayList<Integer> numSeen = new ArrayList<Integer>();
			ArrayList<Integer> decSoFar = new ArrayList<Integer>();
			numSeen.add(1);
			while(max-->0){
				int next = this.nextRemainder();
				decSoFar.add(this.nextDivisor());
				System.out.println("next remainder is "+next);
				if(next==0){
					System.out.println("no repeating");
					break;
				}
				if(numSeen.contains(next)){
					System.out.println("found repeating");
					break;
				}
				numSeen.add(next);
				this.num=next;
			}
			if(max<=0) System.out.println("repeated too much");
			System.out.print("found repeating cycle for "+this.originaldenom+": ");
			for(Integer dig:decSoFar){
				System.out.print(dig+" ");
			}
			System.out.println();
		}

		
		
	}
}
