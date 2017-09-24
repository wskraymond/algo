package com.mine.algo;

import java.util.HashMap;
import java.util.Map;

public class DPDiceGame {
	
	public int countDiceProb(int n){
		if(n<0)
		{
			throw new IllegalArgumentException();
		}
		
		switch(n){
			case 0:
				return 1;
			case 1:
				return countDiceProb(0);
			case 2:
				return countDiceProb(0) + countDiceProb(1);
			case 3:
				return countDiceProb(0) + countDiceProb(1) + countDiceProb(2);
			case 4:
				return countDiceProb(0) + countDiceProb(1) + countDiceProb(2) + countDiceProb(3);
			case 5:
				return countDiceProb(0) + countDiceProb(1) + countDiceProb(2) + countDiceProb(3) + countDiceProb(4);
			default:
				return countDiceProb(n-6) + countDiceProb(n-5) + countDiceProb(n-4) + countDiceProb(n-3) + countDiceProb(n-2) + countDiceProb(n-1);
		}
	}
	
	public int countDiceProbByDP(int n){
		return countDiceProbByDP(n, new HashMap<Integer,Integer>());
	}
	private int countDiceProbByDP(int n,Map<Integer,Integer> myCache){
		if(n<0)
		{
			throw new IllegalArgumentException(); 
		}
		
		if(n==0)
		{//base case
			return 1; 
		}
		
		int result = 0;
		for(int i=1, k = n-i; i <=6 && k >= 0; i++, k = n-i)
		{
			if(!myCache.containsKey(k))
			{
				myCache.put(k,countDiceProbByDP(k, myCache));
			}

			result+=myCache.get(k);

		}
		
		return result;
	}
}
