package cote.y2025.june.w2;

import java.util.Scanner;

// 소수찾기 [2025-06-13(금)]
public class BOJ_1978 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] numbers = new int[n];
		for (int i = 0; i < n; i++) {
			numbers[i] = sc.nextInt();
		}
		
		int count = 0;
		for (int num : numbers) {
			if (isPrime(num)) {
				count++;
			}
		}
		
		System.out.println(count);
	}
	
	private static boolean isPrime(int num) {
		if (num < 2) return false;
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0) return false;
		}
		return true;
	}

}
