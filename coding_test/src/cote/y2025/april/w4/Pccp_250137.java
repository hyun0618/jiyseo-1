package cote.y2025.april.w4;

import java.util.ArrayList;
import java.util.List;

public class Pccp_250137 {
	
	public int solution(int[] bandage, int health, int[][] attacks) {
		
		/* 붕대감기: 
		 * t초 동안 붕대를 감으면서 1초마다 x 만큼의 체력을 회복.
		 * t초 연속으로 붕대를 감는 데 성공한다면 y 만큼 체력을 추가로 회복.
		 * 게임 캐릭터에는 최대 체력이 존재해 현재 체력이 최대 체력보다 커지는 것은 불가능.
		 * 
		 * 기술을 쓰는 도중 몬스터에게 공격을 당하면 기술이 취소됨.
		 * 공격을 당하는 순간에는 체력을 회복할 수 없음.
		 * 몬스터에게 공격당해 기술이 취소되거나 기술이 끝나면 그 즉시 붕대 감기를 다시 사용.
		 * 연속 성공 시간이 0으로 초기화. 
		 *  
		 * bandage = {시전 시간, 초당 회복량, 추가 회복량}
		 * health = 최대 체력(정수)
		 * attacks = {몬스터의 공격 시간, 피해량}
		 * 모든 공격이 끝난 직후 남은 체력 리턴.
		 * 만약 몬스터의 공격을 받고 캐릭터의 체력이 0 이하가 되어 죽는다면 -1 리턴.
		 */
		
		int castTime = bandage[0];  // 시전시간, t초
        int healPerSec = bandage[1]; // 초당 회복량, x
        int bonusHeal = bandage[2]; // 추가 회복량, y

        int maxHealth = health; // 최대 체력
        int currentHealth = health; 
        int time = 0; 
        int successTime = 0;

        int attackIdx = 0;
        int lastAttackTime = attacks[attacks.length - 1][0];

		
        for (int t = 1; t <= lastAttackTime; t++) {
            if (attackIdx < attacks.length && attacks[attackIdx][0] == t) {
            	
                // 공격 받음
                currentHealth -= attacks[attackIdx][1];
                if (currentHealth <= 0) return -1;

                successTime = 0; // 기술 실패
                attackIdx++;
                
            } else {
            	
                // 회복
                currentHealth += healPerSec;
                successTime++;

                if (successTime == castTime) {
                    currentHealth += bonusHeal;
                    successTime = 0;
                }

                if (currentHealth > maxHealth) currentHealth = maxHealth;
            }
        }

        return currentHealth;
    }
	
	public static void main(String[] args) {
		Pccp_250137 sol = new Pccp_250137();

		int[][] testBandages = {
                {5, 1, 5},
                {3, 2, 7},
                {4, 2, 7},
                {1, 1, 1}
        };

		int[] testHealths = {30, 20, 20, 5};
		
        int[][][] testAttacks = {
                {{2, 10}, {9, 15}, {10, 5}, {11, 5}},
                {{1, 15}, {5, 16}, {8, 6}},
                {{1, 15}, {5, 16}, {8, 6}},
                {{1, 2}, {3, 2}}
        };
        
        int[] expectedResults = {5, -1, -1, 3};

        for (int i = 0; i < testBandages.length; i++) {
            int result = sol.solution(testBandages[i], testHealths[i], testAttacks[i]);
            System.out.printf("Test Case %d: %d (expected: %d)\n", i + 1, result, expectedResults[i]);
        }
    }

}
