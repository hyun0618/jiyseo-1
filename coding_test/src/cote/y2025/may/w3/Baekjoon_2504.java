package cote.y2025.may.w3;

import java.util.Scanner;
import java.util.Stack;

public class Baekjoon_2504 {

	public static void main(String[] args) {
		
		/* 괄호의값
		 * 올바른 괄호열 :(), [], (()), [[]] ... 짝이 맞는 괄호열
		 * () : 2 
		 * [] : 3
		 * 
		 * (()[[]])([]) 의 괄호값
		 * 
		 * ()[[]] = 2 + 3 * 3 = 11
		 * (()[[]]) = 2 * 11 = 22 
		 * ([]) = 2 * 3 = 6 
		 * (()[[]])([]) = 22 + 6 = 28
		 */
		
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		Stack<Object> stack = new Stack<>();

        boolean valid = true;
        for (char c : str.toCharArray()) {
            if (c == '(' || c == '[') {
                stack.push(c);
            } else {
                int base = (c == ')') ? 2 : 3;
                char open = (c == ')') ? '(' : '[';

                int temp = 0;
                while (!stack.isEmpty()) {
                    Object top = stack.pop();
                    if (top instanceof Integer) {
                        temp += (int) top;
                    } else if ((char) top == open) {
                        if (temp == 0) {
                            stack.push(base);
                        } else {
                            stack.push(base * temp);
                        }
                        break;
                    } else {
                        valid = false;
                        break;
                    }
                }
                if (!valid || stack.isEmpty()) {
                    valid = false;
                    break;
                }
            }
        }

        if (!valid) {
            System.out.println(0);
            return;
        }

        int result = 0;
        for (Object o : stack) {
            if (o instanceof Integer) {
                result += (int) o;
            } else {
                // 아직 괄호가 남아있다면 올바르지 않은 괄호열
                System.out.println(0);
                return;
            }
        }
        System.out.println(result);
    }
}
