class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        
        
        // n: 직원 수
        // schedules:  출근 희망 시각
        // timelogs: 직원들의 일주일 동안 출근한 시각
        // startday: 이벤트를 시작한 요일(정수)
        
        int answer = 0;
        int n = schedules.length;
        
        boolean[] isWeekday = new boolean[7];
        for (int i = 0; i < 5; i++) {
            isWeekday[(startday + i) % 7] = true;
        }
        
        for (int i = 0; i < n; i++) {
            int targetTime = schedules[i];
            int latestTime = targetTime + 10;
            boolean allDaysOnTime = true;
            
            for (int day = 0; day < 7; day++) {
                if (!isWeekday[day]) continue;
                
                boolean attendedOnTime = false;
                for (int time : timelogs[i]) {
                    if (time >= targetTime && time <= latestTime) {
                        attendedOnTime = true;
                        break;
                    }
                }
                if (!attendedOnTime) {
                    allDaysOnTime = false;
                    break;
                }
                
            }
            if (allDaysOnTime) {
                answer++;
            }
        }
        return answer;
        
    }
}
