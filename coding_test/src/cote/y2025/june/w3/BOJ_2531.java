package cote.y2025.june.w3;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BOJ_2531 {
	// 시간 초과
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); // 접시 수 => 8
		int d = sc.nextInt(); // 초밥 가짓수
		int k = sc.nextInt(); // 연속 접시 수 => 4
		int c = sc.nextInt(); // 쿠폰 번호
	
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
		    arr[i] = sc.nextInt();
		}
		
		/* 1. 벨트의 임의의 한 위치부터 k개의 접시를 연속해서 먹을 경우 할인된 정액 가격으로 제공한다.
		 * 2. 각 고객에게 초밥의 종류 하나가 쓰인 쿠폰을 발행하고,
		 *    1번 행사에 참가할 경우 이 쿠폰에 적혀진 종류의 초밥 하나를 추가로 무료로 제공한다. 
		 */
		
		int max = 0;
		for (int i = 0; i < n; i++) { // n: 8 => 0 ~ 7 
			Set<Integer> set = new HashSet<>(); // 중복 없는 정수 집합
			
			for (int j = 0; j < k; j++) { // k: 4 => 0 ~ 3
			/*
				i =  0 :    0   1   2   3
				i =  1 :    1   2   3   4
				i =  2 :    2   3   4   5
				i =  3 :    3   4   5   6
				i =  4 :    4   5   6   7
				i =  5 :    5   6   7   0
				i =  6 :    6   7   0   1
				i =  7 :    7   0   1   2
			*/
				// curr : 현재 먹고 있는 초밥 번호
				int curr = arr[(i + j) % n];
				set.add(curr);

			}
			
			if (!set.contains(c)) {
				set.add(c);
			}
			max = Math.max(max, set.size());
		}
		System.out.println(max);
	}
}
