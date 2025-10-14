package com.haerin.cote;

public class Joystick_42860 {
	
	public int solution(String name) {
        int answer = 0;
        int len = name.length();

    
        for (int i = 0; i < len; i++) {
            char c = name.charAt(i);
            answer += Math.min(c - 'A', 26 - (c - 'A'));
        }

    
        int minMove = len - 1;
        for (int i = 0; i < len; i++) {
            int next = i + 1;
            while (next < len && name.charAt(next) == 'A') {
                next++;
            }
            int move = Math.min(i * 2 + len - next, (len - next) * 2 + i);
            minMove = Math.min(minMove, move);
        }

        answer += minMove;
        return answer;
    }

	public static void main(String[] args) {
		String name = "JEROEN"; 

        Joystick_42860 joystick = new Joystick_42860();
        int result = joystick.solution(name);

        System.out.println("입력: " + name);
        System.out.println("최소 조작 횟수: " + result);
	}

}
