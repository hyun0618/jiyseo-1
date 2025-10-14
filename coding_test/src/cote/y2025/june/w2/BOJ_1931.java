package cote.y2025.june.w2;

import java.util.*;

// 회의실 배정 [2025-06-12(목)]
public class BOJ_1931 {
	
	public static void main(String[] args) {
		
		// 회의실 : 1개
		// 회의 : N개
		// 각 회의에 대해 시작시간과 끝나는 시간이 주어져 있고, 
		// 각 회의가 겹치지 않게 하면서, 
		// 회의실을 사용할 수 있는 회의의 최대 개수
		
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		// (1, 4) = 3, (3, 5) = 2, (0, 6) = 6, (5, 7) = 2, 
		// (3, 8) = 5, (5, 9) = 4, (6, 10) = 4, (8, 11) = 3
		// (8, 12) = 4, (2, 13) = 11, (12, 14) = 2
		
		// => (1, 4) + (5, 7) + (8, 11) + (12, 14)
		
int[][] meetings = new int[n][2]; // [시작시간, 종료시간]
        
        for (int i = 0; i < n; i++) {
            meetings[i][0] = sc.nextInt(); // 시작시간
            meetings[i][1] = sc.nextInt(); // 종료시간
        }

        // 종료시간 오름차순 → 종료시간이 같다면 시작시간 오름차순
        Arrays.sort(meetings, (a, b) -> {
            if (a[1] == b[1]) return a[0] - b[0];
            return a[1] - b[1];
        });

        int count = 0;     // 회의 개수
        int endTime = 0;   // 마지막 회의 종료시간

        for (int i = 0; i < n; i++) {
            if (meetings[i][0] >= endTime) {
                count++;
                endTime = meetings[i][1];
            }
        }

        System.out.println(count);
	}

}
