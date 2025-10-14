package com.haerin.cote;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BFS_67259 {
	
	public int solution(int[][] board) {
		
		int n = board.length;
		int[][][] cost = new int[n][n][4];
		
		for (int[][] layer : cost) 
			for (int[] row : layer)
                Arrays.fill(row, Integer.MAX_VALUE);

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        Queue<Node> queue = new LinkedList<>();
        for (int d = 0; d < 4; d++) {
            cost[0][0][d] = 0;
            queue.add(new Node(0, 0, 0, d));
        }
		
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n && board[nx][ny] == 0) {
                    int newCost = cur.cost + (cur.dir == d ? 100 : 600);
                    if (cost[nx][ny][d] > newCost) {
                        cost[nx][ny][d] = newCost;
                        queue.add(new Node(nx, ny, newCost, d));
                    }
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int d = 0; d < 4; d++) {
            min = Math.min(min, cost[n - 1][n - 1][d]);
        }

        return min;
	
    }
	
	private static class Node {
		int x;
		int y;
		int cost;
		int dir;
		
		Node(int x, int y, int cost, int dir) {
			this.x = x;
			this.y = y;
			this.cost = cost;
			this.dir = dir;
		}
		
	}
	


	public static void main(String[] args) {
		
		int[][] board = {
				{0, 0, 0}, 
				{0, 0, 0}, 
				{0, 0, 0}
		};
		
		BFS_67259 sol = new BFS_67259();
		int result = sol.solution(board);
		System.out.println("최소 비용 : " + result);
		
		/* Queue는 인터페이스. LinkedList는 그걸 구현한 클래스이다.
		 * Queue는 "나는 줄을 쓸 거야"라고 말하는 것뿐이지 데이터를 저장할 수는 없다.
		 * 그래서 진짜 데이터를 담을 수 있는 구현체(클래스)가 필요하다.
		 * LinkedList는 실제로 데이터를 저장하고, Queue처럼 동작할 수 있도록 
		 * Queue 인터페이스를 구현한 클래스이다. 
		 */
		
		/* BFS, 줄 서기 -> Queue = new LinkedList<>() 
		 * 순서만 필요한 배열 -> ArrayList
		 * 우선순위가 필요 -> PriorityQueue
		 */ 
		
		// Queue 타입으로 선언했지만, LinkedList가 진짜로 그 기능을 실행해준다.
		// 자바에는 Queue 구현체 클래스에는 LinkedList, ArrayDeque, PriorityQueue가 있다. 
		

	}

}
