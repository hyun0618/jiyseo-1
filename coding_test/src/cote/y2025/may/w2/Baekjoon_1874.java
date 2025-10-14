package cote.y2025.may.w2;

import java.util.Scanner;
import java.util.Stack;

public class Baekjoon_1874 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder(); 

        int n = sc.nextInt();
        int[] sequence = new int[n];

        for (int i = 0; i < n; i++) {
            sequence[i] = sc.nextInt();
        }

        Stack<Integer> stack = new Stack<>();
        int num = 1;
        boolean possible = true;

        for (int i = 0; i < n; i++) { // 오름차순
            int target = sequence[i];

            while (num <= target) {
                stack.push(num++);
                sb.append("+\n");
            }

            if (!stack.isEmpty() && stack.peek() == target) {
                stack.pop();
                sb.append("-\n");
            } else {
                possible = false;
                break;
            }
        }

        if (possible) {
            System.out.print(sb);
        } else {
            System.out.println("NO");
        }
    }
}
	