package cote.y2025.may.w1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * 문제 개요:
 * 방향 그래프에서 '생성된 정점'을 찾아서,
 * 그 정점에서 연결된 각각의 그래프가 도넛형/막대형/8자형 중 어떤 구조인지 판단한다.
 * 각 구조의 개수를 구해서 결과로 반환하는 문제.
 * 
 * 해결 전략:
 * 1. 간선 정보를 바탕으로 인접 리스트(graph)를 구성한다.
 * 2. 진입 차수(in-degree)와 진출 차수(out-degree)를 활용해서 생성된 정점(root)을 찾는다.
 * 3. root에서 뻗어나간 각 컴포넌트를 DFS로 탐색한다.
 * 4. 싸이클 수 = 간선 수 - 정점 수 + 1 공식을 이용해 그래프 구조를 판별한다.
 */


public class Kakao_258711 {
	
	// 정점 간 연결 정보를 저장할 인접 리스트
    // 각 정점 번호를 key로, 연결된 이웃 정점들을 list로 저장.
    // List<Integer>는 그 정점과 연결된 이웃 정점들
	Map<Integer, List<Integer>> graph = new HashMap<>(); 
	
	// DFS에서 이미 방문한 정점을 체크하기 위한 집합.
	Set<Integer> visited = new HashSet<>();
	
	
	
	int nodeCount = 0; // DFS 돌릴 때마다 해당 컴포넌트의 정점 개수 
	int edgeCount = 0; // 간선 개수를 세기 위한 전역 변수.
	

	public int[] solution(int[][] edges) {
		
		// inDeg: 각 정점으로 들어오는 간선의 수 (in-degree)
		// outDeg: 각 정점에서 나가는 간선의 수 (out-degree)
		// -> 생성된 정점은 in-degree == 0 이고, out-degree >= 2 를 만족해야 함
		// → 생성된 정점은 "다른 정점에서 들어오는 간선이 없고", "두 개 이상으로 뻗어나가는" 구조
		// → 즉, in-degree == 0 && out-degree >= 2 인 정점이 문제의 '생성된 정점(root)'에 해당함
		
		// 각각의 정점이 가지는 진입 차수 / 진출 차수를 저장.
		// → 생성된 정점을 판별하기 위한 핵심 데이터.
		Map<Integer, Integer> inDeg = new HashMap<>();
		Map<Integer, Integer> outDeg= new HashMap<>();
		
		// 그래프 구성 + 차수 계산
		// edges 배열을 순회하며 그래프를 구성하고,
		// 각 정점의 진입 차수(in-degree), 진출 차수(out-degree)를 동시에 계산
		
		// 모든 간선을 하나씩 꺼내서 from → to 관계를 추출.
		for (int[] edge : edges) {
			
			int from = edge[0], to = edge[1];
			
			// from → to 방향의 간선을 인접 리스트에 추가
			// 인접 리스트에 from → to 간선을 추가.
			// from 노드가 처음 등장했을 경우 빈 리스트 생성 후 추가.
			graph.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
			
			// from 정점의 진출 차수 +1 (나가는 간선이 생겼으므로)	
			outDeg.put(from, outDeg.getOrDefault(from, 0) + 1);
			
			// to 정점의 진입 차수 +1 (들어오는 간선이 생겼으므로)
			inDeg.put(to, inDeg.getOrDefault(to, 0) + 1);
			
			// to 정점도 그래프 노드로 등록 (연결된 노드가 없더라도 그래프 key로 포함시켜야 함)
			graph.putIfAbsent(to, new ArrayList<>());
		}
		
		// 생성된 정점은: 진입 차수가 0 && 진출 차수가 2 이상
		// 생성된 정점을 찾는 과정
		// → 조건: in-degree == 0 && out-degree >= 2
		// → 하나의 생성된 정점(root)에서 여러 개의 컴포넌트(도넛, 막대, 8자 그래프)가 나뉘어 연결됨
		
		// 생성된 정점을 저장할 변수.
		// 아직 못 찾았으므로 초기값은 -1.
		int root = -1;
		
		// 등장한 모든 정점을 순회하면서 root 후보 찾기.
		for (int node : graph.keySet()) {
			
			
			if (inDeg.getOrDefault(node, 0) == 0 && outDeg.getOrDefault(node, 0) >= 2) {
                root = node;
                break;
            }
		}
		
		// 생성된 정점(root)에서 뻗어나가는 각 연결 컴포넌트를 순회하며,
		// 그래프 유형별 개수를 세기 위한 변수 초기화
		int donut = 0, bar = 0, eight = 0;
		
		// root에서 연결된 각 그래프를 탐색
		// 싸이클 수 = 간선 수 - 정점 수 + 1
		// → 이를 기반으로 그래프 유형을 판별
		// 1. 싸이클 없음(cycle == 0) && 트리 구조(edge == node - 1): 막대 그래프
		// 2. 싸이클 1개 && edge == node: 도넛 그래프
		// 3. 싸이클 2개 && edge == node + 1: 8자 그래프
		for (int next : graph.get(root)) {
            if (visited.contains(next)) continue;

            nodeCount = 0;
            edgeCount = 0;
            dfs(next);
            
            // 싸이클 수 계산
            int cycleCount = edgeCount - nodeCount + 1;
            
            // 그래프 유형 판별
            if (cycleCount == 0 && edgeCount == nodeCount - 1) bar++;
            else if (cycleCount == 1 && edgeCount == nodeCount) donut++;
            else if (cycleCount == 2 && edgeCount == nodeCount + 1) eight++;
        }
		
		return new int[]{root, donut, bar, eight};
    }
	
	// DFS로 연결된 그래프 순회하며 정점/간선 수 카운트
	private void dfs(int node) {
		visited.add(node);
		nodeCount++;
		for (int next : graph.getOrDefault(node, new ArrayList<>())) {
			edgeCount++;
			if (!visited.contains(next)) {
				dfs(next);
			}
		}
	}

	public static void main(String[] args) {
		
		int[][][] edgesCases = {
				{
					{2, 3}, {4, 3}, {1, 1}, {2, 1}
				},
				
				{
					{4, 11}, {1, 12}, {8, 3}, {12, 7}, {4, 2}, {7, 11}, {4, 8},
					{9, 6}, {10, 11}, {6, 10}, {3, 5}, {11, 1}, {5, 3}, {11, 9}, {3, 8}
				}
		};
		
		for (int i = 0; i < edgesCases.length; i++) {
	        Kakao_258711 sol = new Kakao_258711(); // 인스턴스 새로 생성 (visited, graph 초기화 필요)
	        int[] result = sol.solution(edgesCases[i]);
	        System.out.println("Test Case " + (i + 1) + " → " + Arrays.toString(result));
	    }

	}

}
