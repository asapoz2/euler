package euler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class NameScores {
	public static void main(String args[]) throws IOException{
		BufferedReader br2 = new BufferedReader(new FileReader("res/p022_names.txt"));
		String currLine = br2.readLine();
		br2.close();
		Pattern p = Pattern.compile("\"(\\w+)\"");
		Matcher m = p.matcher(currLine);
		ArrayList<String> list = new ArrayList<>();
		while(m.find()){
			list.add(m.group(1));
		}
		Collections.sort(list);
		/*for(String name:list){
			System.out.println(name);
		}*/
		//System.out.println("COLIN: "+letterScore("COLIN"));
		BigInteger totalScore = BigInteger.ZERO;
		for(int i=0;i<list.size();i++){
			totalScore = totalScore.add(BigInteger.valueOf((i+1)*letterScore(list.get(i))));
		}
		System.out.println("Total Score: "+totalScore.toString());
		
	}
	public static int letterScore(String name){
		IntStream stream = name.chars();
		Iterator<Integer> iterator =stream.iterator();
		int sum = 0;
		int aval = Character.valueOf('A')-1;
		while(iterator.hasNext()){
			sum += iterator.next()-aval;
		}
		return sum;
	}
}
