package cote.y2025.may.w3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Baekjoon_2206 {
	
	static int N, M;
	static int[][] map;
	static boolean[][][] visited;
	
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};
	
	/*
	 * BFS 큐에 들어가는 탐색 상태를 하나의 객체로 표현
	 * wallBroken 값이 0이면 아직 벽 안 부숨, 1이면 이미 부쉈음
	 */
	static class Node {
		int x, y;
		int dist;
		int wallBroken;
		
		Node(int x, int y, int dist, int wallBroken) {
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.wallBroken = wallBroken;
		}
	}

	public static void main(String[] args) {
		/*
		 * 1. 시작하는 칸을 큐에 넣고 방문했다는 표시를 남김
		 * 2. 큐에서 원소를 꺼내어 그 칸에 상하좌우로 인접한 칸에 대해 3번을 진행
		 * 3. 해당 칸을 이전에 방문했다면 아무 것도 하지 않고, 
		 * 	  처음으로 방문했다면 방문했다는 표시를 남기고 해당 칸을 큐에 삽입
		 * 4. 큐의 빌 때까지 2번을 반복 
		 * 
		 * 모든 칸이 큐에 1번씩 들어가므로 시간복잡도는 칸이 N개 일 때 O(N).
		 */
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		sc.nextLine();
		
		map = new int[N][M];
		
		/*
		 * 일반 BFS라면 visited[x][y]로 충분하지만, 이 문제는 벽을 부쉈는지 여부도 상태임
		 * visited[x][y][0]: 벽 안 부수고 온 경우
		 * visited[x][y][1]: 벽 1번 부수고 온 경우
		 */
		visited = new boolean[N][M][2];
		
		for (int i = 0; i < N; i++) {
			String line = sc.nextLine();
			for (int j = 0; j < M; j++) {
				map[i][j] =line.charAt(j) - '0';
			}
		}
		
		System.out.println(bfs());
	}
	
	
	static int bfs() {
		Queue<Node> q = new LinkedList<>();
		
		
		/*
		 * 출발점은 (0,0)이고, 아직 벽을 안 부쉈으므로 wallBroken = 0
		 * 시작 거리는 1
		 */
		q.offer(new Node(0, 0, 1, 0));
		visited[0][0][0] = true;
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			
			// 도착 지점 (N-1, M-1)에 도달한 순간 최단 거리 리턴
			if (cur.x == N -1 && cur.y == M -1) {
				return cur.dist;
			}
			
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
				
				// 빈 칸이면 그대로 이동
				// 단, 현재 벽 부순 상태로 아직 방문한 적 없어야 함
				if (map[nx][ny] == 0 && !visited[nx][ny][cur.wallBroken]) {
					visited[nx][ny][cur.wallBroken] = true;
					q.offer(new Node(nx, ny, cur.dist + 1, cur.wallBroken));
				}
				
				// 벽을 만났는데 아직 안 부쉈다면 → 지금 부수고 진행
				// 이때부터는 wallBroken = 1 상태로 간주
				if (map[nx][ny] == 1 && cur.wallBroken == 0 && !visited[nx][ny][1]) {
					visited[nx][ny][1] = true;
					q.offer(new Node(nx, ny, cur.dist + 1, 1));
				}
			}
		}
		
		//
		return -1;
	}

}
