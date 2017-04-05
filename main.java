import java.io.*;
import java.util.*;
/**
 * This is an implementation of the MergeSort Algorithm in Java programming Language.
 * In this programm there are 3 methods: main, mergeSort and merge
 * 
 * @author giorgos andreou AEM:2334
 * @exception IOException  to catch the input output file failure
 */
public class main {

	private static Scanner Scanner;
	private static Scanner s;
	static int cnt=0;
	static int swaps=0;
	
	/**
    *
    * @param args
    * @throws FileNotFoundException if the .txt file is not found
    */
	
	public static void main(String[] args) throws IOException {
		
		try {
		// User has to write the name of the .txt file located in the same project folder.
	    Scanner Scanner = new Scanner(System.in);
		System.out.print("Give the filename with the .txt suffix :");
        String filename = Scanner.nextLine();
		Scanner s = new Scanner(new File(filename));
		
		//Dynamic array which contains the numbers of the file
		ArrayList<Integer> list = new ArrayList<Integer>();
		while (s.hasNext()){
		    list.add(s.nextInt());
		}
		s.close();
		
		
		list=mergeSort(list);
		
		
		System.out.println("swaps:" +swaps);
		System.out.println("	");
		System.out.println("The total cost is :" +cnt);
		}
		
		catch (FileNotFoundException e) {
			System.out.println("Directory not found");
			System.out.println("//");
	        e.printStackTrace();
	        }
			
		
	}
	/**
     * The mergeSort method has taken some elements from a thread at StackOverFlow forum.
     * The method breaks at half each time the arrays until the arrays can not be divided no more 
     * and each one has one number
     * 
     * @param the Input array list filled with integers
     * @return ArrayList<Integer> Input where numbers are sorted
     */
	
      public static ArrayList<Integer> mergeSort( ArrayList<Integer> Input)
        {
            if (Input.size() ==1){
                return Input;
            }
            else {
            	
            	
            	int mid= (int) Math.floor(Input.size()/2.0);
                
              
            	
                ArrayList<Integer> left= new ArrayList<Integer>(mid);
                ArrayList<Integer> right=new ArrayList<Integer>(Input.size()-mid);
                

               //The numbers are copied into 2 same sized arrays or the right is by 1 number larger 
               for (int i = 0; i < mid; i++) {
                   left.add(Input.get(i));
                   }
               for (int i = mid; i < Input.size(); i++) {
                   right.add(Input.get(i));
               } 

               left=mergeSort(left); 
               right=mergeSort(right);
               merge(left,right,Input,mid);
            
               return Input;
        }
            }

    /**
     * The merge method compairs the left and the right arrays and if the left is bigger than the right then there is 
     * a swap of numbers
     * For the assignment's purposes there is also a counter that counts the cost of each swap
     * cost 2 for neighbor numbers (i.e. 6 and 7)
     * cost 3 for non-neighbors (i.e. 4 and 7)
     * 
     * @param left : array list with the first half of Input's list numbers.
     * @param right : array list with the second half of Input's list numbers.
     * @param Input 
     */
        public static void merge (ArrayList<Integer>left,ArrayList<Integer>right,ArrayList<Integer>Input,int mid)
        {
            int iLeft=0;// left pointer
            int iRight=0;// right pointer
            
            for (int m = 0; m < Input.size(); m++) {

                if(iLeft==left.size()){
                	Input.set(m, right.get(iRight));
                	iRight++;
                	//in case the left array is finished  then all the remaining elements of right array 
                	//are copied into the big Input array
                }
                else{
                    if (iRight==right.size()){
                    	Input.set(m,left.get(iLeft));
                    	iLeft++;
                    	//in case the right array is finished then all the remaining elements of right array 
                    	//are copied into the big Input array
                    }
                    else{
                        if (left.get(iLeft)<=right.get(iRight)) {
                        	Input.set(m,left.get(iLeft));
                        	iLeft++;
                        	//if element of the left array is smaller than the element of the right array at the current 
                        	//positions of both pointers, then the element of the left array is copied to the big Input array
                            }
                        else {
                        	if (left.get(iLeft)>=(right.get(iRight))) {
                        		//if the element of the left array is larger than the element of the right array 
                        		//at the current positions of both pointers, then the element of the right 
                        		//array is copied to the big Input array
                        			int i=iLeft;
                        			
                        	for(int l=0; l<(mid-iLeft); l++){
                        		    
                        		    if(left.get(i)==((right.get(iRight)+1))){
                        				cnt+=2;
                        				//if the element of the left array is by one larger than the the right one,
                        				//then according to the assignment's instructions, this swap costs 2 
                        				i++;
                        				swaps++;}
                        			else{
                        				cnt+=3;
                        				//if the element of the left array is more than one larger than the the right one,
                        				//then according to the assignment's instructions, this swap costs 3 
                        				i++;
                        				swaps++;
                        			}}
                        		    Input.set(m, right.get(iRight));
                        			iRight++;
                                    swaps++;
                                    
                        		
                        	}
                        }
                    }
                }
            }
        }
}
