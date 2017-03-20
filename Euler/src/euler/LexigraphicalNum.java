package euler;



public class LexigraphicalNum {
	//What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?
	public static void main(String[] args) {
		String startnum="0123456789";
		//num 2:0123456798
		//1 and 2
		
		//i=8;i>0;i++;
		//switch 2
		
		//start over
		//num 3:0123456879
		//3 and 2
		//
		//num 3:0123456897 
		//1 and 2
		//
		//num 3:0123456978
		// 3 and 2
		//
		//num 3:0123456987
		
		//num 4:0123457689
		//num 4:0123457698
		//num 4:0123457869
		//num 4:0123457896
		//num 4:0123457968
		//num 4:0123457986
		//num 4:0123458679
		//num 4:0123458769
		//num 4:0123458967
		//num 4:0123458976
		//num 4:0123459678
		//num 4:0123459768
		//num 4:0123459786
		//num 4:0123459867
		//num 4:0123459876
		
		String currnum="0123456789";
		System.out.println("swap last 2=:"+swap(currnum, currnum.length()-1, currnum.length()-2));
		/*int numctr=1;
		//iter for which dig, iter for which dig before dig
		for(int i=startnum.length()-2;i>0;i--){
			String currsub=currnum.substring(i, startnum.length());
			//89
			//for(int j=i;j<startnum.length();j++){
				if(numctr++ >100){
					break;
				}
				
				
			//}
			if(numctr >100){
				break;
			}
		}*/
	}
	private static String swap(String str,int i,int j){ //i to j
		char swap1=str.charAt(i);
		char swap2=str.charAt(j);
		char[] chararr=str.toCharArray();
		chararr[i]=swap2;
		chararr[j]=swap1;
		return String.copyValueOf(chararr);
	}

}
