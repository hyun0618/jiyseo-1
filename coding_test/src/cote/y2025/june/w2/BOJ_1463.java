package cote.y2025.june.w2;

import java.util.Scanner;

public class BOJ_1463 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		int n = sc. nextInt();
		
		int[] dp = new int[n + 1];
		dp[1] = 0; // 연산 0번 
		
		for (int i = 2; i <= n; i++) {
			int count = dp[i - 1];
			
			if (i % 2 == 0) {
				count = Math.min(dp[i / 2], count);
			}
			
			if (i % 3 == 0) {
				count = Math.min(dp[i / 3], count);
			}
			
			dp[i] = count + 1;
		}
		
		/*
		for (int i = 2; i <= n; i++) {		
			dp[i] = dp[i - 1] + 1;			
			if (i % 2 == 0) {                
                dp[i] = Math.min(dp[i], dp[i / 2] + 1); 
            }
            if (i % 3 == 0) {               
                dp[i] = Math.min(dp[i], dp[i / 3] + 1);  
            }
		}
		*/		

        System.out.println(dp[n]);

	}
}
