package cote.y2025.june.w1;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_2751 {
	
	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
	
		int count = sc.nextInt();
		int[] numbers = new int[count];
		
		for (int i = 0; i < count; i++) {
			numbers[i] = sc.nextInt();
		}
		
		Arrays.sort(numbers);
		// System.out.println(Arrays.toString(numbers));
		
		StringBuilder sb = new StringBuilder();
		for (int num : numbers) {
			// System.out.println(num); //=> 시간초과
			
			sb.append(num).append('\n');
		}
		
		System.out.print(sb);
	
	}
}
