package cote.y2025.april.w4;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Pccp_250136 {

	int n, m;
	boolean[][] visited;
	int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	int[][] group;
	Map<Integer, Integer> groupOil = new HashMap<>(); // 그룹 번호별 석유량

	public int solution(int[][] land) {

		n = land.length;
		m = land[0].length;
		visited = new boolean[n][m];
		group = new int[n][m];

		int groupId = 1;

		// Step 1: 석유 덩어리 그룹핑
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (!visited[i][j] && land[i][j] == 1) {
					int size = bfs(i, j, land, groupId);
					groupOil.put(groupId, size);
					groupId++;
				}
			}
		}

		// Step 2: 각 열마다 석유량 계산
		int max = 0;
		for (int col = 0; col < m; col++) {
			Set<Integer> counted = new HashSet<>();
			int total = 0;
			for (int row = 0; row < n; row++) {
				int gId = group[row][col];
				if (gId > 0 && !counted.contains(gId)) {
					total += groupOil.get(gId);
					counted.add(gId);
				}
			}
			max = Math.max(max, total);
		}

		return max;
	}

	private int bfs(int r, int c, int[][] land, int id) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { r, c });
		visited[r][c] = true;
		group[r][c] = id;
		int count = 1;

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			for (int[] d : dirs) {
				int nr = cur[0] + d[0];
				int nc = cur[1] + d[1];
				if (0 <= nr && nr < n && 0 <= nc && nc < m && !visited[nr][nc] && land[nr][nc] == 1) {
					visited[nr][nc] = true;
					group[nr][nc] = id;
					count++;
					q.add(new int[] { nr, nc });
				}
			}
		}

		return count;
	}

	public static void main(String[] args) {

		Pccp_250136 solution = new Pccp_250136();

		int[][][] testCases = {

				{ { 0, 0, 0, 1, 1, 1, 0, 0 }, { 0, 0, 0, 0, 1, 1, 0, 0 }, { 1, 1, 0, 0, 0, 1, 1, 0 },
						{ 1, 1, 1, 0, 0, 0, 0, 0 }, { 1, 1, 1, 0, 0, 0, 1, 1 } },
				{ { 1, 0, 1, 0, 1, 1 }, { 1, 0, 1, 0, 0, 0 }, { 1, 0, 1, 0, 0, 1 }, { 1, 0, 0, 1, 0, 0 },
						{ 1, 0, 0, 1, 0, 1 }, { 1, 0, 0, 0, 0, 0 }, { 1, 1, 1, 1, 1, 1 } } };

		for (int i = 0; i < testCases.length; i++) {
			int[][] land = testCases[i];
			int result = solution.solution(land);
			System.out.println("테스트 " + (i + 1) + " 결과: " + result);
		}
	}
}
