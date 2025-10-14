package cote.y2025.april.w4;

// https://school.programmers.co.kr/learn/courses/30/lessons/250135
public class Pccp_250135 {
	
	public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
		
		int start = h1 * 3600 + m1 * 60 + s1;
		int end = h2 * 3600 + m2 * 60 + s2;
		
		double periodHS = 360.0 / (6 - (1.0 / 120));
		double periodMS = 360.0 / (6 - (1.0 / 10));
		
		int count = 0;
		
		count += countOverlaps(start, end, periodHS);
		
		count += countOverlaps(start, end, periodMS);

        return count;
    }
	
	private int countOverlaps(int start, int end, double period) {
        int cnt = 0;
        double t = 0;

        // 주기가 float이기 때문에, 시작점까지 올림 처리
        if (t < start) {
            t = Math.ceil((start - t) / period) * period + t;
        }

        while (t <= end + 1e-6) { // 오차 방지
            cnt++;
            t += period;
        }

        return cnt;
    }
	
	public static void main(String[] args) {
		
		Pccp_250135 sol = new Pccp_250135();
		
		int[][] testCases = {
		        {0, 5, 30, 0, 7, 0, 2},
		        {12, 0, 0, 12, 0, 30, 1},
		        {0, 6, 1, 0, 6, 6, 0},
		        {11, 59, 30, 12, 0, 0, 1},
		        {11, 58, 59, 11, 59, 0, 1},
		        {1, 5, 5, 1, 5, 6, 2},
		        {0, 0, 0, 23, 59, 59, 2852},
		    };
		
		for (int[] testCase : testCases) {
	        int h1 = testCase[0], m1 = testCase[1], s1 = testCase[2];
	        int h2 = testCase[3], m2 = testCase[4], s2 = testCase[5];
	        int expected = testCase[6];

	        int result = sol.solution(h1, m1, s1, h2, m2, s2);
	        System.out.printf("입력: %02d:%02d:%02d ~ %02d:%02d:%02d => 결과: %d (기대값: %d)\n",
	            h1, m1, s1, h2, m2, s2, result, expected);
	    }
		

	}

}
