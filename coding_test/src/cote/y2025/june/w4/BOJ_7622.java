package cote.y2025.june.w4;

/*	이중 우선순위 큐 
 * 
 *  ‘I n’은 정수 n을 Q에 삽입하는 연산을 의미한다. 
 *  동일한 정수가 삽입될 수 있음을 참고하기 바란다. 
 *  
 *  ‘D 1’는 Q에서 최댓값을 삭제하는 연산을 의미하며, 
 *  ‘D -1’는 Q 에서 최솟값을 삭제하는 연산을 의미한다. 
 *  
 *  최댓값(최솟값)을 삭제하는 연산에서 최댓값(최솟값)이 둘 이상인 경우, 하나만 삭제된다.
 */

/*
 * 최댓값과 최솟값을 삭제하는 연산이 있기 때문에 일반 큐가 아니라 
 * 우선순위 큐를 사용했습니다. 
 * 일반 큐는 먼저 들어온 게 먼저 나가지만, 
 * 우선순위 큐는 우선순위가 높은 값이 먼저 나가기 때문입니다. 
 */

import java.util.*;

public class BOJ_7622 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt(); // t: 테스트 데이터 수
		
		for (int i = 0; i < t; i++) {
			int k = sc.nextInt(); // k: 연산 수
			
			PriorityQueue<Integer> minQ = new PriorityQueue<>();
            PriorityQueue<Integer> maxQ = new PriorityQueue<>(Collections.reverseOrder());
            Map<Integer, Integer> map = new HashMap<>();
            
            for (int j = 0; j < k; j++) {
                String op = sc.next();
                int n = sc.nextInt();

                if (op.equals("I")) {
                    minQ.offer(n);
                    maxQ.offer(n);
                    map.put(n, map.getOrDefault(n, 0) + 1);
                } else {
                    if (map.isEmpty()) continue;

                    if (n == 1) {
                        remove(maxQ, map);
                    } else {
                        remove(minQ, map);
                    }
                }
            }
            
            clean(minQ, map);
            clean(maxQ, map);
            
            if (map.isEmpty()) {
                System.out.println("EMPTY");
            } else {
                System.out.println(maxQ.peek() + " " + minQ.peek());
            }
			
		}
	
	}
	
	private static void remove(PriorityQueue<Integer> pq, Map<Integer, Integer> map) {
        while (!pq.isEmpty()) {
            int num = pq.poll();
            if (map.containsKey(num)) {
                if (map.get(num) == 1) {
                    map.remove(num);
                } else {
                    map.put(num, map.get(num) - 1);
                }
                break;
            }
        }
    }

    private static void clean(PriorityQueue<Integer> pq, Map<Integer, Integer> map) {
        while (!pq.isEmpty() && !map.containsKey(pq.peek())) {
            pq.poll();
        }
    }

}
