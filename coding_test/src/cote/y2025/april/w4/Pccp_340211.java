package cote.y2025.april.w4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pccp_340211 {
	
	private class Point {
		int r;
		int c;
		
		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public int solution(int[][] points, int[][] routes) {
		
		/* 로봇을 이용한 자동 운송 시스템 
		 * 위험 상황 : 이동 중 같은 좌표에 로봇이 2대 이상 모이는 경우
		 * 
		 * 경로[4,2] : [1,4] -> [2,4] -> [3,4] -> [4,4] -> [5,4] -> [6,4] 
		 * 경로[1,3] : [3,2] -> [4,2] -> [4,3] -> [4,4] -> [4,5] -> [4,6] -> [4,7]
		 * 경로[2,4] : [6,4] -> [5,4] -> [4,4] -> [3,4] -> [2,4]
		 */
		
		int point = points.length;
		int robot = routes.length;
		
		List<List<Point>> paths = new ArrayList<>();
		
		for (int[] route : routes) { // routes가 2차원 배열이므로 
						
			List<Point> path = new ArrayList<>();
			
			int start = route[0];
            int[] starts = points[start - 1];
            int currR = starts[0];
            int currC = starts[1];
			
			path.add(new Point(currR, currC)); // 출발 지점 추가
			
			for (int i = 1; i < route.length; i++) { // 여러 포인트를 차례로 방문
				
				int end = route[i];
                int[] ends = points[end - 1];
                int targetR = ends[0];
                int targetC = ends[1];
				
				// r좌표 이동
				while (currR != targetR) {
					if (currR < targetR) currR++;
					else currR--;
					path.add(new Point(currR, currC));
				}
				
				// c좌표 이동
				while (currC != targetC) {
					if (currC < targetC) currC++;
					else currC--;
					path.add(new Point(currR, currC));
				}
			}
			
			paths.add(path); // 완성된 경로 저장
		}
			
        int answer = 0;
        int time = 0;
        boolean stillMoving = true;

        while (stillMoving) {
            stillMoving = false;
            Map<String, Integer> map = new HashMap<>();

            for (List<Point> path : paths) {
                if (time < path.size()) { // 아직 이동 중인 로봇만 체크
                    stillMoving = true;

                    Point p = path.get(time);
                    String key = p.r + "," + p.c;

                    map.put(key, map.getOrDefault(key, 0) + 1);
                }
            }

            for (int count : map.values()) {
                if (count >= 2) {
                    answer++;
                }
            }

            time++;
        }
        
        
        return answer;
        
    }

	public static void main(String[] args) {
		
		Pccp_340211 sol = new Pccp_340211();
		
		int[][] points = {{3,2}, {6,4}, {4,7}, {1,4}};
		int[][]	routes = {{4,2}, {1,3}, {2,4}};
		
//		int[][] points = {{3,2}, {6,4}, {4,7}, {1,4}};
//		int[][]	routes = {{4,2}, {1,3}, {4,2}, {4,3}};
		
//		int[][] points = {{2,2}, {2,3}, {2,7}, {6,6}, {5,2}};
//		int[][]	routes = {{2,3,4,5}, {1,3,4,5}};
		
		int result = sol.solution(points, routes);
		System.out.println("위험한 상황의 횟수 = " + result);

	}

}
