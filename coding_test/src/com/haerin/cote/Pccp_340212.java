package com.haerin.cote;

public class Pccp_340212 {
	
	public int solution(int[] diffs, int[] times, long limit) {
		
		/* 완전탐색 -> 시간초과 */
//		for (int level = 1; ; level++) {
//			if (solve(diffs, times, limit, level)) {
//				return level;
//			}
//		}
		
		/* 이분탐색 */
		int left = 1; // 숙련도 최솟값 후보
		int right = 1_000_000_000; // 숙련도 최대값 후보
		int answer = right; // 정답을 담아둘 변수 (최솟값 갱신용)
		
		// left와 right의 중간값 mid를 level로 설정해서 
		// 퍼즐을 풀 수 있는지 검사
		while (left <= right) { 
			int mid = (left + right) / 2; 
			
			if (solve(diffs, times, limit, mid)) {
				answer = mid;
				right = mid - 1;
				
			} else {
				left = mid + 1;
			}
		}		
		return answer;		
    }
	
	private boolean solve(int[] diffs, int[] times, long limit, int level) {
		long totalTime = 0;
		
		for (int i = 0; i < diffs.length; i++) {
			int diff = diffs[i];
			int timeCur = times[i];
			int timePrev = (i == 0 ) ? 0 : times[i - 1];
			
			if (diff <= level) {
				totalTime += timeCur;
			} else {
				int failCount = diff - level;
				totalTime += (long) failCount * (timeCur + timePrev) + timeCur;
			}
			
			if (totalTime > limit) return false;
		}
		
		return true;
	}

	public static void main(String[] args) {
		
		Pccp_340212 sol = new Pccp_340212();
		
//		int[] diffs = {1, 5, 3};
//		int[] times = {2, 4, 7};
//		long limit = 30;
		
//		int[] diffs = {1, 4, 4, 2};
//		int[] times = {6, 3, 8, 2};
//		long limit = 59;
		
//		int[] diffs = {1, 328, 467, 209, 54};
//		int[] times = {2, 7, 1, 4, 3};
//		long limit = 1723;
		
		int[] diffs = {1, 99999, 100000, 99995};
		int[] times = {9999, 9001, 9999, 9001};
		long limit = 3456789012L;
		
		int result = sol.solution(diffs, times, limit);
		System.out.println("숙련도의 최솟값 = " + result);

	}

}
