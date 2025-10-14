package cote.y2025.june.w4;

import java.util.*;

public class BOJ_1202 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in); 
		int n = sc.nextInt(); // 보석 수
		int k = sc.nextInt(); // 가방 수
		
		int[][] jewels = new int[n][2]; // int[n][2]
		for (int i = 0; i < n; i++) {
			jewels[i][0] = sc.nextInt();
			jewels[i][1] = sc.nextInt();
		}
		
		int[] weights = new int[k];
		for (int j = 0; j < k; j++) {
			weights[j] = sc.nextInt();
		}
		
		// 보석은 무게 오름차순, 가격 내림차순 정렬
		Arrays.sort(jewels, (a, b) -> {
			if (a[0] == b[0]) return b[1] - a[1]; // 무게 같으면 가격 높은 순
			return a[0] - b[0];
		});
		
		// 가방 수용 무게 오름차순 정렬
		// 수용 무게가 클수록 담을 수 있는 보석의 경우의 수가 많아지므로,
		// 수용 무게가 적은 가방부터 최소한의 후보를 두고 선택.
		Arrays.sort(weights); 
		
		// 최대힙을 사용해서 가장 비싼 보석을 꺼내기 위해 우선순위 큐 사용
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        long total = 0; // 보석의 최대 가격
        int j = 0; // jewels 순회 인덱스
        
        // 가방에 보석을 하나씩 넣어야 하므로
        for (int i = 0; i < k; i++) {
            int bagWeight = weights[i];

            // 현재 가방 수용 무게보다 작거나 같은 보석을 모두 pq에 넣음
            while (j < n && jewels[j][0] <= bagWeight) {
                pq.offer(jewels[j][1]); // 가격
                j++;
            }

            if (!pq.isEmpty()) {
                total += pq.poll(); // 가장 비싼 보석
            }
        }

        System.out.println(total);
		
	}

}
