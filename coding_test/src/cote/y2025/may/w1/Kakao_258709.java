package cote.y2025.may.w1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Kakao_258709 {
	
	int maxWin = 0;
    int[] bestChoice;

    public int[] solution(int[][] dice) {
        int n = dice.length;
        List<int[]> combinations = new ArrayList<>();
        
        combination(0, 0, new int[n / 2], n, combinations);
        
        for (int[] aPick : combinations) {
            boolean[] selected = new boolean[n];
            for (int i : aPick) selected[i] = true;

            int[] bPick = new int[n / 2];
            int idx = 0;
            for (int i = 0; i < n; i++) {
                if (!selected[i]) bPick[idx++] = i;
            }

            int winCount = simulate(aPick, bPick, dice);
            if (winCount > maxWin) {
                maxWin = winCount;
                bestChoice = Arrays.copyOf(aPick, aPick.length);
            }
        }

        for (int i = 0; i < bestChoice.length; i++) {
            bestChoice[i]++;
        }

        return bestChoice;
    }

    private void combination(int depth, int start, int[] temp, int n, List<int[]> result) {
        if (depth == temp.length) {
            result.add(Arrays.copyOf(temp, temp.length));
            return;
        }

        for (int i = start; i < n; i++) {
            temp[depth] = i;
            combination(depth + 1, i + 1, temp, n, result);
        }
    }

    private int simulate(int[] aPick, int[] bPick, int[][] dice) {
        List<Integer> aSums = getAllSums(aPick, dice);
        List<Integer> bSums = getAllSums(bPick, dice);

        Collections.sort(bSums);
        int win = 0;

        for (int a : aSums) {
            win += lowerBound(bSums, a);
        }

        return win;
    }

    private List<Integer> getAllSums(int[] picks, int[][] dice) {
        List<Integer> result = new ArrayList<>();
        dfs(0, 0, picks, dice, result);
        return result;
    }

    private void dfs(int depth, int sum, int[] picks, int[][] dice, List<Integer> result) {
        if (depth == picks.length) {
            result.add(sum);
            return;
        }

        int[] d = dice[picks[depth]];
        for (int face : d) {
            dfs(depth + 1, sum + face, picks, dice, result);
        }
    }

    private int lowerBound(List<Integer> list, int target) {
        int left = 0, right = list.size();
        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid) < target) left = mid + 1;
            else right = mid;
        }
        return left;
    }

	public static void main(String[] args) {

		int[][][] testCases = {
		        {
		            {1, 2, 3, 4, 5, 6},
		            {3, 3, 3, 3, 4, 4},
		            {1, 3, 3, 4, 4, 4},
		            {1, 1, 4, 4, 5, 5}
		        },
		        {
		            {1, 2, 3, 4, 5, 6},
		            {2, 2, 4, 4, 6, 6}
		        },
		        {
		            {40, 41, 42, 43, 44, 45},
		            {43, 43, 42, 42, 41, 41},
		            {1, 1, 80, 80, 80, 80},
		            {70, 70, 1, 1, 70, 70}
		        }
		    };

		    int[][] expected = {
		        {1, 4},
		        {2},
		        {1, 3}
		    };

		    for (int i = 0; i < testCases.length; i++) {
		        Kakao_258709 sol = new Kakao_258709(); // 인스턴스를 새로 만들어야 maxWin 초기화됨
		        int[] result = sol.solution(testCases[i]);
		        System.out.printf("Test Case %d: Expected = %s, Got = %s\n",
		            i + 1,
		            Arrays.toString(expected[i]),
		            Arrays.toString(result)
		        );
		    }
		}
}