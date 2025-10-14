class Solution {
    public int solution(int n, int w, int num) {
        
        // n: 총 상자 개수
        // w: 한 줄에 놓이는 상자 개수
        // num: 꺼내려는 상자 번호
        
        int targetRow = (num - 1) / w; 
        
        int column;
        if (targetRow % 2 == 0) { // 왼 -> 오
            column = (num - 1) % w;
        } else {
            column = w - 1 - ((num - 1) % w);
        }
        
        // 최대 row 
        int maxRow = (n - 1) / w; 
        
        // 해당 column의 최대 row 
        int lastBoxInColumn;
        for (int row = maxRow; row >= 0; row--) {
            if (row % 2 == 0) { // 왼 -> 오
                lastBoxInColumn = row * w + column + 1;
            } else { // 오 -> 왼
                lastBoxInColumn = (row + 1) * w - column;
            }
            if (lastBoxInColumn <= n) {
                maxRow = row;
                break;
            }
        }
        
        // count 계산 (targetRow부터 maxRow까지)
        int count = 0;
        for (int row = targetRow; row <= maxRow; row++) {
            int boxNumber;
            if (row % 2 == 0) { // 왼 -> 오
                boxNumber = row * w + column + 1;
            } else { // 오 -> 왼
                boxNumber = (row + 1) * w - column;
            }
            
            if (boxNumber > n) continue; // 창고에 있는 총 상자 수를 넘으면 무시
            count++;
        }
        
        return count;
    }
}
