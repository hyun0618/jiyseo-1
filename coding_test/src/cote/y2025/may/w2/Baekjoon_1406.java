package cote.y2025.may.w2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Baekjoon_1406 {
	
	public static void main(String[] args) throws IOException {
        // 빠른 입출력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 초기 문자열 -> 커서 기준 왼쪽 스택에 넣기
        String init = br.readLine();
        int m = Integer.parseInt(br.readLine());

        LinkedList<Character> left = new LinkedList<>();
        LinkedList<Character> right = new LinkedList<>();

        for (char c : init.toCharArray()) {
            left.addLast(c);
        }

        // 명령 처리
        for (int i = 0; i < m; i++) {
            String line = br.readLine();
            char cmd = line.charAt(0);

            switch (cmd) {
                case 'L':
                    if (!left.isEmpty()) {
                        right.addFirst(left.removeLast());
                    }
                    break;
                case 'D':
                    if (!right.isEmpty()) {
                        left.addLast(right.removeFirst());
                    }
                    break;
                case 'B':
                    if (!left.isEmpty()) {
                        left.removeLast();
                    }
                    break;
                case 'P':
                    char ch = line.charAt(2); // P x
                    left.addLast(ch);
                    break;
            }
        }

        // 결과 조합
        for (char c : left) sb.append(c);
        for (char c : right) sb.append(c);

        System.out.println(sb);
    }
}



