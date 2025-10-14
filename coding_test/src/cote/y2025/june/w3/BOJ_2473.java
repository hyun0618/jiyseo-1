package cote.y2025.june.w3;

import java.util.*;

// [2025-06-18(수)] "세 용액"
public class BOJ_2473 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 산성 용액의 특성값 : 1 ~ 1_000_000_000
		// 알칼리성 용액의 특성값 : -1_000_000_000 ~ -1 
		
		/*
		 * 같은 양의 세 가지 용액을 혼합한 용액의 특성값은 혼합에 사용된 각 용액의 특성값의 합으로 정의한다. 
		 * 이 연구소에서는 같은 양의 세 가지 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만들려고 한다. 
		 * 
		 */
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int[n];
		
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		// System.out.println(Arrays.toString(arr));
		
		long min = Long.MAX_VALUE; // 최솟값을 찾아야 하므로 최대값으로 초기화한다. 
		int x = 0, y = 0, z = 0;
		
		// [-97, -6, -2, 6, 98]
		for (int i = 0; i < n - 2; i++) {
			int lt = i + 1;
			int rt = n - 1;
			
			while (lt < rt) {
				long total = (long) arr[i] + arr[lt] + arr[rt];
				if (Math.abs(total) < min) {
					min = Math.abs(total);
					x = arr[i];
					y = arr[lt];
					z = arr[rt];
				}
				
				if (total < 0) {
					lt++;
				} else {
					rt--;
				}
			}
		}
		
		int[] answer = new int[]{x, y, z};
		Arrays.sort(answer);
		for (int num : answer) {
		    System.out.print(num + " ");
		}

	}

}
