package cote.y2025.may.w3;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Baekjoon_2178 {
	
	static int n, m;
    static String[] board;
    static int[][] dist;
    static int[] dx = {1, 0, -1, 0}; // 아래, 오른쪽, 위, 왼쪽
    static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        sc.nextLine(); // 줄바꿈 처리

        board = new String[n];
        dist = new int[n][m];

        for (int i = 0; i < n; i++) {
            board[i] = sc.nextLine();
        }

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], -1);
        }

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});
        dist[0][0] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];

            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (dist[nx][ny] != -1 || board[nx].charAt(ny) != '1') continue;

                dist[nx][ny] = dist[x][y] + 1;
                q.offer(new int[]{nx, ny});
            }
        }

        System.out.println(dist[n - 1][m - 1] + 1);
    }
}
	

/*
public class Baekjoon_2178 {
	
	static int n, m;
    static int[][] maze;
    static boolean[][] visited;
    static int[] dx = {1, 0, -1, 0}; // 하 우 상 좌
    static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        sc.nextLine(); 

        maze = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < m; j++) {
                maze[i][j] = line.charAt(j) - '0';
            }
        }

        System.out.println(bfs(0, 0));
	}
	
	static int bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int dir = 0; dir < 4; dir++) {
                int nx = cur[0] + dx[dir];
                int ny = cur[1] + dy[dir];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (visited[nx][ny] || maze[nx][ny] == 0) continue;

                visited[nx][ny] = true;
                maze[nx][ny] = maze[cur[0]][cur[1]] + 1;
                q.offer(new int[]{nx, ny});
            }
        }

        return maze[n - 1][m - 1];
    }
	
}
*/
