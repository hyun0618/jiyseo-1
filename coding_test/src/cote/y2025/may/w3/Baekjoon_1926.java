package cote.y2025.may.w3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Baekjoon_1926 {
	
	static int n, m;
    static int[][] board;
    static boolean[][] vis;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};


	public static void main(String[] args) {
		/*
		 * [문제]
		 * 어떤 큰 도화지에 그림이 그려져 있을 때, 
		 * 그 그림의 개수와, 그 그림 중 넓이가 가장 넓은 것의 넓이를 출력하여라. 
		 * 단, 그림이라는 것은 1로 연결된 것을 한 그림이라고 정의하자. 
		 * 가로나 세로로 연결된 것은 연결이 된 것이고 대각선으로 연결이 된 것은 떨어진 그림이다. 
		 * 그림의 넓이란 그림에 포함된 1의 개수이다.
		 * 
		 * [입력]
		 * 첫째 줄에 도화지의 세로 크기 n (1 <= n <= 500)과 가로 크기 m (1 <= m <= 500)이 차례로 주어진다.
		 * 
		 * [출력]
		 * 첫째 줄에는 그림의 개수, 둘째 줄에는 그 중 가장 넓은 그림의 넓이를 출력하여라.
		 *
		 * 1. 상하좌우로 연결된 그림의 크기를 알아내기 
		 * 2. 도화지에 있는 모든 그림을 찾아내기 
		 */
		
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt(); m = sc.nextInt();
        board = new int[n][m];
        vis = new boolean[n][m];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                board[i][j] = sc.nextInt();

        int count = 0;
        int maxArea = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 1 && !vis[i][j]) {
                    count++;
                    int area = bfs(i, j);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }

        System.out.println(count);
        System.out.println(maxArea);
    }

    static int bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        vis[x][y] = true;
        q.offer(new int[]{x, y});
        int area = 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int dir = 0; dir < 4; dir++) {
                int nx = cur[0] + dx[dir];
                int ny = cur[1] + dy[dir];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (vis[nx][ny] || board[nx][ny] == 0) continue;

                vis[nx][ny] = true;
                q.offer(new int[]{nx, ny});
                area++;
            }
        }

        return area;
    }
}
		

