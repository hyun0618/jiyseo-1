package com.haerin.cote;

import java.util.Arrays;

public class Greedy_42885 {

	public int solution(int[] people, int limit) {
        Arrays.sort(people);
        
        int left = 0;
        int right = people.length - 1;
        int boats = 0;
        
        while (left <= right) {
            if (people[left] + people[right] <= limit) {
                left++;  // 가벼운 사람 태움
            }
            right--;  // 무거운 사람은 항상 태움
            boats++;  // 보트 하나 사용
        }
        
        return boats;
    }
}

