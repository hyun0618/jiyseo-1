	package cote.y2025.may.w3;

import java.util.Scanner;
import java.util.Stack;

public class Baekjoon_10799 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        Stack<Character> stack = new Stack<>();
        int pieces = 0;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c == '(') {
                stack.push(c);
            } else { // c == ')'
                stack.pop(); // 일단 짝 맞추기 위해 pop

                if (str.charAt(i - 1) == '(') {
                    // 레이저일 경우 → 현재 열려 있는 막대기 수만큼 조각 추가
                    pieces += stack.size();
                } else {
                    // 막대기 끝남 → 조각 하나 추가
                    pieces += 1;
                }
            }
        }

        System.out.println(pieces);
    }
}
