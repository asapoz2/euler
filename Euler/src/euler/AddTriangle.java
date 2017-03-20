package euler;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddTriangle {
	public static void main(String args[]) throws IOException{
		BufferedReader br2 = new BufferedReader(new FileReader("res/addtriangle"));
		//BufferedReader br2 = new BufferedReader(new FileReader("res/add2"));
		ArrayList<ArrayList<Integer>> matrix = createMatrix(br2);
		br2.close();
		printMatrix(matrix);
		Collections.reverse(matrix);
		/*ArrayList<ArrayList<Integer>> resultMatrix = getResultPath(matrix);
		printMatrix(resultMatrix);
		Collections.reverse(matrix);
		Collections.reverse(resultMatrix);
		
		finalLoop(matrix, resultMatrix);*/
		int sum = AddTriangle.getResult(matrix);
		System.out.println("sum is "+sum);
	}

	private static void finalLoop(ArrayList<ArrayList<Integer>> matrix,
			ArrayList<ArrayList<Integer>> resultMatrix) {
		long sum = 0;
		
		ArrayList<String> path = new ArrayList<>();
		sum+=matrix.get(0).get(0);
		path.add("*"+matrix.get(0).get(0)+"*");
		int currPathIdx=0; // was null
		for(int i=0;i<resultMatrix.size();i++){
			int idxToGet = resultMatrix.get(i).get(currPathIdx); //0
			sum+=matrix.get(i+1).get(idxToGet);
			String currLine="";
			for(int pi=0;pi<matrix.get(i+1).size();pi++){
				if(pi==idxToGet){
					currLine=currLine+"*"+matrix.get(i+1).get(pi)+"* ";
				}else{
					currLine=currLine+matrix.get(i+1).get(pi)+" ";
				}
			}
			path.add(currLine);
			currPathIdx=idxToGet;
			
		}
		for(String line:path){
			System.out.println(line);
		}
		System.out.println("total sum is "+sum);
	}

	private static ArrayList<ArrayList<Integer>> createMatrix(BufferedReader br2)
			throws IOException {
		String currLine;
		ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
		Pattern p = Pattern.compile("\\s*(\\d+)(\\s+|$)");
		while((currLine = br2.readLine())!=null){
			Matcher m = p.matcher(currLine);
			ArrayList<Integer> list = new ArrayList<>();
			while(m.find()){
				list.add(Integer.parseInt(m.group(1)));
			}
			matrix.add(list);
		}
		return matrix;
	}

	private static ArrayList<ArrayList<Integer>> getResultPath(
			ArrayList<ArrayList<Integer>> matrix) {
		ArrayList<ArrayList<Integer>> resultMatrix = new ArrayList<ArrayList<Integer>>();
		for(int li = 0; li<matrix.size()-1;li++){
			ArrayList<Integer> list = matrix.get(li);
			ArrayList<Integer> upList = matrix.get(li+1);
			ArrayList<Integer> upTransition = new ArrayList<Integer>(list.size()-1);
			
			for(int i = 0; i<upList.size(); i++){
				upTransition.add(list.get(i)>list.get(i+1)?i:i+1);
			}
			resultMatrix.add(upTransition);
		}
		return resultMatrix;
	}

	private static void printMatrix(ArrayList<ArrayList<Integer>> matrix) {
		for(List<Integer> list : matrix){
			for(Integer integer:list){
				System.out.print(integer+" ");
			}
			System.out.println();
		}
	}
	
	private static int getResult(
			ArrayList<ArrayList<Integer>> matrix) {
		ArrayList<Integer> bottomList = new ArrayList<Integer>(matrix.get(0));
		for(int li = 0; li<matrix.size()-1;li++){
			ArrayList<Integer> upList = matrix.get(li+1);
			ArrayList<Integer> newest = new ArrayList<Integer>();
			
			for(int i = 0; i<upList.size(); i++){
				newest.add(upList.get(i)+(bottomList.get(i)>bottomList.get(i+1)?bottomList.get(i):bottomList.get(i+1)));
			}
			bottomList = newest;
		}
		return bottomList.get(0);
	}
}
