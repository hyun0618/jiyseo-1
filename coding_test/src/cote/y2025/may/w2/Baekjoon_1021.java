package cote.y2025.may.w2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Baekjoon_1021 {

	public static void main(String[] args) throws IOException {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 

		String[] firstLine = br.readLine().split(" ");
		int n = Integer.parseInt(firstLine[0]); 
		int m = Integer.parseInt(firstLine[1]); 
		
		String[] targetStr = br.readLine().split(" ");
		List<Integer> targets = new ArrayList<>();
		for (String s : targetStr) {
			targets.add(Integer.parseInt(s));
		}
		
		Deque<Integer> deque = new LinkedList<>();
		for (int i = 1; i <= n; i++) {
			deque.add(i);
		}
		
		int totalMoves = 0;
		
		for (int target : targets) {
			int index = 0;
			
			for (int val : deque) {
				if (val == target) break;
				index++;
			}
			
			int left = index; 
			int right = deque.size() - index;

            // 더 가까운 방향으로 회전
            if (left <= right) {
                for (int i = 0; i < left; i++) {
                    deque.addLast(deque.pollFirst()); // 왼쪽 회전
                    totalMoves++;
                }
            } else {
                for (int i = 0; i < right; i++) {
                    deque.addFirst(deque.pollLast()); // 오른쪽 회전
                    totalMoves++;
                }
            }

            deque.pollFirst(); // target 제거
        }

        // 정답 출력
        System.out.println(totalMoves);
    }
}