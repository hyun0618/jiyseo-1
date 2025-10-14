package cote.y2025.may.w2;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Baekjoon_3273 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

        // 1. 수열의 크기 입력
        int n = sc.nextInt();

        // 2. 수열 입력
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // 3. 목표 합 x 입력
        int x = sc.nextInt();

        // 4. 쌍의 개수 계산
        Set<Integer> set = new HashSet<>();
        int count = 0;

        for (int a : arr) {
            if (set.contains(x - a)) {
                count++;
            }
            set.add(a);
        }

        // 5. 결과 출력
        System.out.println(count);
    }

}
