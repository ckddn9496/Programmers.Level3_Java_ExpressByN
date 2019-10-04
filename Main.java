import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
//		int N = 5;
//		int number = 12; // return 4
		
		int N = 2;
		int number = 11; // return 3
		
		System.out.println(new Solution().solution(N, number));
	}

}
class Solution {
	ArrayList<Integer>[] numbersByUsedTimes = new ArrayList[9];
	
    public int solution(int N, int number) {
        
        int NN = N;
        for (int i = 1; i < 9; i++) {
        	if (NN == number) {
        		return i;
        	}
        	numbersByUsedTimes[i] = new ArrayList<>();
        	numbersByUsedTimes[i].add(NN);
        	NN = NN*10 + N;
        }
        
//        System.out.println(Arrays.toString(numbersByUsedTimes));
        
        for (int times = 2; times < 9; times++) {
        	for (int i = 1; i < times; i++) {
        		int j = times - i;
        		for (int left : numbersByUsedTimes[i]) {
        			for (int right : numbersByUsedTimes[j]) {
        				// add
        				numbersByUsedTimes[times].add(left+right);
        				// sub
        				numbersByUsedTimes[times].add(left-right);
        				// mul
        				numbersByUsedTimes[times].add(left*right);
        				// div
        				if (right != 0)
        					numbersByUsedTimes[times].add(left/right);
        				else
        					numbersByUsedTimes[times].add(0);
        			}
        		}
        		
        		if (numbersByUsedTimes[times].contains(number))
        			return times;
        		
        	}
        }
        
        return -1;
    }
}