package cote.y2025.may.w1;

import java.util.HashMap;
import java.util.Map;

public class Kakao_258712 {

	public int solution(String[] friends, String[] gifts) {
		
		int n = friends.length;

        // 친구 이름을 인덱스로 매핑
        Map<String, Integer> nameToIndex = new HashMap<>();
        for (int i = 0; i < n; i++) {
            nameToIndex.put(friends[i], i);
        }

        int[][] giftCount = new int[n][n]; // giftCount[i][j] = i가 j에게 준 선물 수
        int[] giveTotal = new int[n];      // i가 이번 달까지 준 총 선물 수
        int[] receiveTotal = new int[n];   // i가 받은 총 선물 수
        
        // 선물 기록 파싱
        for (String gift : gifts) {
            String[] parts = gift.split(" ");
            int from = nameToIndex.get(parts[0]);
            int to = nameToIndex.get(parts[1]);

            giftCount[from][to]++;
            giveTotal[from]++;
            receiveTotal[to]++;
        }
        
        // 선물 지수 계산
        int[] giftScore = new int[n];
        for (int i = 0; i < n; i++) {
            giftScore[i] = giveTotal[i] - receiveTotal[i];
        }
        
        // 다음 달 받는 선물 수 계산
        int[] nextGifts = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int aToB = giftCount[i][j];
                int bToA = giftCount[j][i];

                if (aToB > bToA) {
                    nextGifts[i]++;
                } else if (aToB < bToA) {
                    nextGifts[j]++;
                } else {
                    // 선물 주고받은 횟수가 같을 때, 선물 지수 비교
                    if (giftScore[i] > giftScore[j]) {
                        nextGifts[i]++;
                    } else if (giftScore[i] < giftScore[j]) {
                        nextGifts[j]++;
                    }
                    // 같으면 아무 일도 없음
                }
            }
        }
        
        // 최댓값 리턴
        int max = 0;
        for (int cnt : nextGifts) {
            max = Math.max(max, cnt);
        }
        return max;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Kakao_258712 sol = new Kakao_258712();

		String[][] friendsCases = { 
				{"muzi", "ryan", "frodo", "neo"},
				{"joy", "brad", "alessandro", "conan", "david"},
				{"a", "b", "c"}
		};

		String[][] giftsCases = { 
				{
					"muzi frodo", "muzi frodo", 
					"ryan muzi", "ryan muzi", "ryan muzi", 
					"frodo muzi", "frodo ryan", 
					"neo muzi"
				},
				
				{
					"alessandro brad", "alessandro joy", "alessandro conan", 
					"david alessandro", 
					"alessandro david"
				},
				
				{
					"a b", "b a", "c a", "a c", "a c", "c a"
				}
				
		};
		
		int[] expected = {2, 4, 0};
		
		for (int i = 0; i < friendsCases.length; i++) {
	        String[] friends = friendsCases[i];
	        String[] gift = giftsCases[i];
	        int result = sol.solution(friends, gift);
	        System.out.println("예제 " + (i + 1) + " (기댓값 " + expected[i] + "): " + result);
	    }

	}

}
