package cote.y2025.may.w4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BOJ_1941 {
	
	/*
	 * 1) 25개의 칸 중에서 7개를 선택 
	 * 2) 선택된 7개가 연결되어 있는지 확인
	 */
	static char[][] board = new char[5][5]; // S/Y 가 들어가므로 char로 생성
    static int result = 0; // 조건을 만족하는 7명의 조합의 수 
    static int[] dx = {-1, 1, 0, 0}; 
    static int[] dy = {0, 0, -1, 1}; 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
		// 5줄, 5글자씩 읽어서 board에 저장. 
        for (int i = 0; i < 5; i++) {
            String line = br.readLine();
            for (int j = 0; j < 5; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        // 25칸 중 7개 선택
        // 빈 ArrayList로 현재 선택한 칸. 
        // start는 0, count는 0
        comb(new ArrayList<>(), 0, 0);
        System.out.println(result);
    }

    // 조합 선택 -> 25개의 칸 중에 7개를 선택하는 조합을 만들기 위해
	// start : 현재 인덱스. +1씩 증가. count : 현재까지 선택된 개수
    static void comb(List<Integer> selected, int start, int count) {
    		
    		// 종료 조건 
        if (count == 7) {
            check(selected);
            return;
        }
        
        // 종료 조건 
        if (start >= 25) return;

        // 선택
        // 현재 인덱스를 selected에 넣는다. 
        selected.add(start);
        
        // 다음 인덱스로 넘어갔다가 
        comb(selected, start + 1, count + 1);

        // 선택 안 함
        // 현재 인덱스를 제거하고 
        selected.remove(selected.size() - 1);
        
        // 다시 다음 인덱스로 넘어간다. 
        comb(selected, start + 1, count);
    }

    // 선택된 조합 검증
    // S가 4개 이상인지, 7명이 연결되어 있는지 검증하는 함수
    static void check(List<Integer> selected) {
    		
    		
        int sCount = 0;
        boolean[][] selectedMap = new boolean[5][5];
        List<int[]> positions = new ArrayList<>();

        for (int idx : selected) {
            int x = idx / 5;
            int y = idx % 5;
            positions.add(new int[]{x, y});
            selectedMap[x][y] = true;
            if (board[x][y] == 'S') sCount++;
        }

        if (sCount < 4) return;

        // 연결성 체크 (BFS)
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[5][5];
        q.add(positions.get(0));
        visited[positions.get(0)[0]][positions.get(0)[1]] = true;

        int connected = 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5) continue;
                if (!selectedMap[nx][ny] || visited[nx][ny]) continue;

                visited[nx][ny] = true;
                q.add(new int[]{nx, ny});
                connected++;
            }
        }

        if (connected == 7) result++;
    }
}