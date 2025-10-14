package cote.y2025.june.w3;

import java.util.Scanner;

public class BOJ_1011 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        
        for (int i = 0; i < T; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int d = y - x;
            int count = 0;
            
            /* 
             * d:1 => count: 1
             * d:2 => count: 2 
             * d: 4 => 1 2 1 => count: 3 = 2n-1   
             *  
             * d: 6 => 1 2 2 1 => count: 4 = 2n
             * d: 7 => 1 2 2 1 1 
             * d: 8 => 1 2 2 2 1 
             * 
             * d: 9 => 1 2 3 2 1 => count: 5 = 2n-1
             * 
             * d: 10 => 1 2 3 2 1 1
             * d: 11 => 1 2 3 2 2 1
             * d: 12 => 1 2 3 3 2 1 => count: 6 = 2n
             * d: 13 => 1 2 3 3 2 1 1
             * d: 14 => 1 2 3 3 2 2 1 
             * d: 15 => 1 2 3 3 3 2 1 => count: 7 = 2n+1
             * 
             * d: 16 => 1 2 3 4 3 2 1 => count: 7 = 2n-1
             * 
             * d: 25 => 1 2 3 4 5 4 3 2 1 => count: 9 = 2n-1
             * ... 
             */
            
            int n = (int)(Math.sqrt(d));

            if (n * n == d) {
                count = 2 * n - 1;
            } else if (d <= n * n + n) {
                count = 2 * n;
            } else {
                count = 2 * n + 1;
            }
            
            System.out.println(count);
        }        

	}

}
