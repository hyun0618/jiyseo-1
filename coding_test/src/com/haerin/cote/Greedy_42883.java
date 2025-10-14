package com.haerin.cote;

import java.util.Stack;

public class Greedy_42883 {

	 public String solution(String number, int k) {
	        Stack<Character> stack = new Stack<>();
	        
	        for (char c : number.toCharArray()) {
	            while (!stack.isEmpty() && k > 0 && stack.peek() < c) {
	                stack.pop();
	                k--;
	            }
	            stack.push(c);
	        }

	        // 아직 제거할 숫자가 남았다면 뒤에서부터 제거
	        while (k-- > 0) {
	            stack.pop();
	        }

	        // 스택에 있는 문자들을 이어서 결과 문자열 생성
	        StringBuilder sb = new StringBuilder();
	        for (char c : stack) {
	            sb.append(c);
	        }

	        return sb.toString();
	    }
	}

