package cote.y2025.june.w3;

import java.util.*;

// [2025-06-17(화)] "수 찾기"
public class BOJ_1920 {
	
	// 이분 탐색
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ns = new int[n];
		for (int i = 0; i < n; i++) {
			ns[i] = sc.nextInt();
		}
		Arrays.sort(ns); 
		
		int m = sc.nextInt();
		for (int i = 0; i < m; i++) {
			int ms = sc.nextInt();
			// System.out.println(binarySearch(ns, ms) ? 1 : 0);
			System.out.println(binarySearch(ns, ms));
		}
	}
	
	private static int binarySearch(int[] arr, int target) {
		int lt = 0; int rt = arr.length - 1;
		
		while (lt <= rt) {
			int mid = (lt + rt) / 2;
			if (arr[mid] == target) {
				return 1;
			} else if (arr[mid] < target) {
				lt = mid + 1;
			} else {
				rt = mid - 1;
			}
		}
		
		return 0;
	}
}
	
/*
	// 해시
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		HashSet<Integer> ns = new HashSet<>();
		
		for (int i = 0; i < n; i++) {
			ns.add(sc.nextInt());
		}
		
		int m = sc.nextInt();
		for (int i = 0; i < m; i++) {
			int ms = sc.nextInt();
			System.out.println(ns.contains(ms) ? 1 : 0);
		}
	}
}
	
/*
	// 완전 탐색 => 시간 초과
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[] ns = new int[n];
		for (int i = 0; i < n; i++) {
			ns[i] = sc.nextInt();
		}
		
		int m = sc.nextInt();
		int[] ms = new int[m];
		for (int i = 0; i < m; i++) {
			ms[i] = sc.nextInt();
		}
		
		System.out.println("ns = " + Arrays.toString(ns));
		System.out.println("ms = " + Arrays.toString(ms));
		
		for (int i = 0; i < m; i++) {
			boolean found = false;
			
			for (int j = 0; j < n; j++) {
				if (ms[i] == ns[j]) {
					found = true;
					break;
				}
			}
			System.out.println(found ? 1 : 0);
		}		
	} 
} 
*/
