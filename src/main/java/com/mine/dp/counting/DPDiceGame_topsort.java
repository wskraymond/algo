package com.mine.dp.counting;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class DPDiceGame_topsort {
	/**
	 * Imagine you are playing a board game.
	 * You roll a 6-faced dice and move forward the same number of spaces that you rolled.
	 * If the finishing point is “n” spaces away from the starting point,
	 * please implement a program that calculates how many possible ways there are to arrive exactly at the finishing point.
	 *
	 * @param n
	 * @return
	 */
	public int countDice(int n){
		if(n<2){
			return 1;
		}

		/**
		 * recurrence relation:
		 * 		f(k) = f(k-1) + f(k-2) + f(k-3) + f(k-4) + f(k-5) + f(k-6)
		 *
		 * base cases:
		 * 		f(0) = 1, f(1)=1
		 */

		//k-6,k-5........,k-1
		//max #no of dp variable = 6
		//Space complexity: O(1)
		Deque<Integer> dp = new LinkedList<Integer>();
		dp.addLast(1);
		dp.addLast(1);
		for(int i=2;i<=n;i++){
			int count = 0;
			for(int c:dp){
				count+=c;
			}
			if(dp.size()==6){
				dp.removeFirst();
			}

			dp.addLast(count);
		}

		return dp.getLast();
	}

	public int countDice_n(int n){
		if(n<2){
			return 1;
		}

		/**
		 * recurrence relation:
		 * 		f(k) = f(k-1) + f(k-2) + f(k-3) + f(k-4) + f(k-5) + f(k-6)
		 *
		 * base cases:
		 * 		f(0) = 1, f(1)=1
		 */

		int[] dp = new int[n+1];
		dp[0]=1;
		dp[1]=1;
		for(int i=2;i<=n;i++){
			for(int j=1; j<=6 && i-j>=0; j++){
				dp[i]+=dp[i-j];
			}
		}

		return dp[n];
	}


}
