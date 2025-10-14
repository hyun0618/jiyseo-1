package com.haerin.cote;

import java.util.Arrays;

public class Greedy_42884 {
	
public int solution(int[][] routes) {
        
        /* Greedy : 매 순간 최선의 선택을 하면 전체에서도 최선이 된다는 전략.
         * 
         * - 모든 차량은 이동 구간 동안 최소 한 번은 단속 카메라를 만나야 한다.
         * - 가능한 한 적은 수의 카메라를 설치하되, 모든 차량을 커버해야 한다.
         * 
         * 1. 차량의 진출 시점 기준으로 오름차순 정렬한다.
         *    → 먼저 나가는 차량부터 카메라를 설치하면 더 많은 차량을 커버할 수 있다.
         * 2. 처음에는 카메라가 "가장 왼쪽(존재하지 않는 위치)"에 있다고 가정한다.
         * 3. 차량의 진입 시점보다 카메라가 앞에 있으면 → 새 카메라 설치 필요.
         * 4. 새 카메라는 현재 차량의 진출 지점에 설치한다.
         * 
         * routes : [[-20,-15], [-14,-5], [-18,-13], [-5,-3]]
         * => -20, _, -18, _, _, -15, -14, -13, _, _, _, _, _, _, _, -5, _ -3
         * => 설치 시점 : -15, -5 
         * => 2개 
         */
		
		// 먼저 나가는 차가 나가는 시점에 카메라를 설치해도 되므로,
		// 차가 나가는 시점을 기준으로 오름차순 정렬
        Arrays.sort(routes, (a, b) -> Integer.compare(a[1], b[1]));
        
        for (int[] route : routes) {
            System.out.println(Arrays.toString(route));
        }
        
        
        int cameraCount = 0;
        int cameraPosition = Integer.MIN_VALUE;

        for (int[] route : routes) {
        	
        		// 처음에는 카메라가 제일 왼쪽에 있다 생각하고, 
        		// 첫 차량이 나가는 시점에 카메라 설치한다.
        		// routes : [[-20,-15], [-14,-5], [-18,-13], [-5,-3]]
            // -20, _, -18, _, _, -15, -14, -13, _, _, _, _, _, _, _, -5, _ -3
        		// 설치한 카메라가 그 다음 차량의 진입 시점보다 뒤에 있다면,
        		// 카메라를 설치할 필요가 없고, 
        		// 진입 시점보다 앞에 있을 경우에 설치가 필요하다. 
        	
            if (cameraPosition < route[0]) {
                cameraPosition = route[1]; // 현재 차량의 나가는 시점에 설치
                cameraCount++;
            }
        }

        return cameraCount;
    }

	public static void main(String[] args) {
		Greedy_42884 sol = new Greedy_42884();
		
		int[][] routes = {{-20,-15}, {-14,-5}, {-18,-13}, {-5,-3}};
		
		int result = sol.solution(routes);
		System.out.println(result);
		
	}

}
