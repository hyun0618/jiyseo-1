package com.haerin.cote;

public class DFS_43163 {

	public int answer = Integer.MAX_VALUE;
	
	public int solution(String begin, String target, String[] words) {
		
		boolean[] visited = new boolean[words.length];
		dfs(begin, target, words, visited, 0);
		
		System.out.println(answer);
		return answer == Integer.MAX_VALUE ? 0 : answer;
		
	}
	
	public void dfs(String current, String target, String[] words, boolean[] visited, int depth) {
		// depth는 몇 단계를 거쳐왔는지 세기 위한 것이다. 
		if (current.equals(target)) {
			// System.out.println("단계 수: " + depth);
			answer = Math.min(answer, depth);
			return;
			
		}
		
		for (int i = 0; i < words.length; i++) {
			if (!visited[i] && isOneCharDiff(current, words[i])) {
				visited[i] = true;
				// System.out.println(current + " -> " + words[i]);
				dfs(words[i], target, words, visited, depth + 1);
				visited[i] = false;
			}
		}
		
	}
	
	public boolean isOneCharDiff(String a, String b) {
		int diff = 0;
		
		for (int i = 0; i < a.length(); i++) {
	        if (a.charAt(i) != b.charAt(i)) diff++;
	    }
		
		return diff == 1;
	}
	
	// 테스트 케이스
	public static void main(String[] args) {
		DFS_43163 sol = new DFS_43163();
		
		String begin1 = "hit";
		String target1 = "cog";
		String[] words1 = {"hot", "dot", "dog", "lot", "log", "cog"};
		
		sol.solution(begin1, target1, words1);
	}
	

}
