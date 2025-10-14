package cote.y2025.may.w3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Baejoon_1012 {
	
	static int[][] field;
    static boolean[][] vis;
    static int n, m; // 세로, 가로
    static int[] dx = {1, 0, -1, 0}; 
    static int[] dy = {0, 1, 0, -1};
    /*
     *   dx  dy 
     *   +1  0  -> 아래 
     *   0  +1  -> 오른쪽
     *   -1  0  -> 위
     *   0  -1  -> 왼쪽  
     */

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        int T = sc.nextInt(); // 테스트 케이스 수

        while (T-- > 0) {
            m = sc.nextInt(); // 가로
            n = sc.nextInt(); // 세로
            int k = sc.nextInt(); // 배추 수

            field = new int[n][m];
            vis = new boolean[n][m];

            for (int i = 0; i < k; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                field[y][x] = 1;
            }

            int wormCount = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (field[i][j] == 1 && !vis[i][j]) {
                        bfs(i, j);
                        wormCount++;
                    }
                }
            }

            System.out.println(wormCount);
        }
    }

    static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        vis[x][y] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int dir = 0; dir < 4; dir++) {
                int nx = cur[0] + dx[dir];
                int ny = cur[1] + dy[dir];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (vis[nx][ny] || field[nx][ny] == 0) continue;

                vis[nx][ny] = true;
                q.offer(new int[]{nx, ny});
            }
        }
    }
}
