package cote.y2025.june.w4;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_2230 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[] arr = new int[n];
		
		for (int i = 0; i < n; i++) {
		    arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		
		int start = 0;
		int end = 0;
		int answer = Integer.MAX_VALUE;
		
		while (end < n) {
		    int diff = arr[end] - arr[start];
		    if (diff < m) {
		        end++;
		    } else {
		        answer = Math.min(answer, diff);
		        start++;
		        if (start > end) end = start;
		    }
		}
		System.out.print(answer);

	}

}
