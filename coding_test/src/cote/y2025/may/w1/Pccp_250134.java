package cote.y2025.may.w1;

public class Pccp_250134 {

	    static int n,m;
	    static int dx[] = {1,0,-1,0};
	    static int dy[] = {0,1,0,-1};
	    static int start[][];
	    static int maze[][];
	    static boolean been[][][];
	    static int answer;

	    public int solution(int[][] maze0) {

	        maze = maze0;

	        answer = Integer.MAX_VALUE;

	        n = maze.length;
	        m = maze[0].length;
	        start = new int[2][2];
	        been = new boolean[2][n][m];

	        for(int i = 0; i < n; i++){
	            for(int j = 0; j < m; j++){
	                if(maze[i][j] == 1){
	                    start[0][0] = j;
	                    start[0][1] = i;
	                }

	                if(maze[i][j] == 2){
	                    start[1][0] = j;
	                    start[1][1] = i;
	                }  
	            }
	        }

	        been[0][start[0][1]][start[0][0]] = true;
	        been[1][start[1][1]][start[1][0]] = true;
	        dfs(start, 0);

	        return answer == Integer.MAX_VALUE ? 0 : answer;
	    }

	    static void dfs(int waggons[][], int cost){
	        int rx = waggons[0][0];
	        int ry = waggons[0][1];
	        int bx = waggons[1][0];
	        int by = waggons[1][1];

	        if(cost + 1 >= answer){
	            return;
	        }

	        for(int i = 0; i < 4; i++){
	            int nrx = maze[ry][rx] == 3 ? rx : rx + dx[i];
	            int nry = maze[ry][rx] == 3 ? ry : ry + dy[i];

	            if(maze[ry][rx] != 3 && !isOkay(nrx,nry,0)){
	                continue;
	            }

	            for(int j = 0; j < 4; j++){
	                int nbx = maze[by][bx] == 4 ? bx : bx + dx[j];
	                int nby = maze[by][bx] == 4 ? by : by + dy[j];

	                if(maze[by][bx] != 4 && !isOkay(nbx,nby,1)){
	                    continue;
	                }
	                if(nrx == nbx && nry == nby){
	                    continue;
	                }
	                if((nrx == bx && nry == by) && (rx == nbx && ry == nby)){
	                    continue;
	                }

	                if(maze[nry][nrx] == 3 && maze[nby][nbx] == 4){
	                    answer = Math.min(answer, cost + 1);
	                    return;
	                }

	                been[0][nry][nrx] = true;
	                been[1][nby][nbx] = true;

	                dfs(new int[][]{{nrx, nry},{nbx,nby}}, cost + 1);

	                been[0][nry][nrx] = false;
	                been[1][nby][nbx] = false;
	            }
	        }
	    }

	    static boolean isOkay(int x, int y, int idx){
	        if(x < 0 || x >= m || y < 0 || y >= n || maze[y][x] == 5 || been[idx][y][x]){
	            return false;
	        }

	        return true;
	    }


	public static void main(String[] args) {
		
		Pccp_250134 sol = new Pccp_250134();

	    int[][][] testCases = {
	        {
	            {1, 4},
	            {0, 0},
	            {2, 3}
	        },
	        {
	            {1, 0, 2},
	            {0, 0, 0},
	            {5, 0, 5},
	            {4, 0, 3}
	        },
	        {
	            {1, 5},
	            {2, 5},
	            {4, 5},
	            {3, 5}
	        },
	        {
	            {4, 1, 2, 3}
	        }
	    };

	    int[] expected = {3, 7, 0, 0};

	    for (int i = 0; i < testCases.length; i++) {
	        int result = sol.solution(testCases[i]);
	        System.out.println("예제 " + (i + 1) + " (기댓값 " + expected[i] + "): " + result);
	    }
		

	}

}
