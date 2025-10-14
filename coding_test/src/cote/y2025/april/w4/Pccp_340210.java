package cote.y2025.april.w4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Pccp_340210 {

	/*
	 * 2~9진법 2 <= expressions의 길이 <= 100 expressions의 원소는 "A + B = C" 또는 "A - B = C"
	 * 형태의 문자열. A, B는 음이 아닌 두 자릿수 이하의 정수. C는 X 혹은 음이 아닌 세 자릿수 이하의 정수.
	 * 
	 * (예시1) 14 + 3 = 17 13 - 6 = X -> X : 지워진 결과값 51 - 5 = 44 -> 8진법임을 알 수 있다. 따라서
	 * X = 5가 된다. expressions = {"14 + 3 = 17", "13 - 6 = X", "51 - 5 = 44"} result
	 * = {"13 - 6 = 5"}
	 * 
	 * (예시2) 1 + 1 = 2 1 + 3 = 4 1 + 5 = X -> 6진법일 때 10, 7~9진법일 때 6 -> 결괏값이 불확실할 경우
	 * '?'를 채워 넣는다. 1 + 2 = X -> 6 ~ 9진법에서 모두 3으로 같다. expressions = {"1 + 1 = 2",
	 * "1 + 3 = 4", "1 + 5 = X", "1 + 2 = X"} result = {"1 + 5 = ?", "1 + 2 = 3"}
	 */

	static List<String> incomplete;

	public String[] solution(String[] expressions) {
		
		incomplete = new ArrayList<>();
		
		// 수식 분리 -> incomplete 리스트에 "= X"로 끝나는 식만 따로 저장
		for (String expression : expressions) {
			if (expression.charAt(expression.length() - 1) == 'X') {
				incomplete.add(expression);
			}
		}
		
		// 유효한 진법 찾기 (2~9진법)
		List<Integer> radixList = new ArrayList<>();
		
		for (int radix = 2; radix < 10; radix++) {
			if (validate(radix, expressions)) {
				radixList.add(radix);
			}
		}
		
		String[] answer = new String[incomplete.size()];
		
		for (int i = 0; i < incomplete.size(); i++) {
			
			String expression = incomplete.get(i);
			String[] splits = expression.split("\\s*=\\s*");
			Set<String> resultSet = new HashSet<>();
			String result = "";
			
			for (int radix : radixList) {
				try {
					int value = calc(splits[0], radix);
					result = Integer.toString(value, radix);
					resultSet.add(result);
				} catch (NumberFormatException e) {
					continue;
				}
			}
			
			if (resultSet.size() == 1) {
				answer[i] = splits[0] + " = " + result;
			} else {
				answer[i] = splits[0] + " = ?";
			}
			
		}

		return answer;
	}

	private boolean validate(int radix, String[] expressions) {
		
		for (String expression : expressions) {
			try {
				String[] splits = expression.split("\\s*=\\s*");
				int result = calc(splits[0], radix);
				if (!splits[1].equals("X")) {
					int expected = Integer.parseInt(splits[1], radix);
					if (result != expected) {
						return false;
					}
				}
			} catch (NumberFormatException e) {
				return false;
			}
		}
		return true;
	}

	private int calc(String expression, int radix) {
		String[] splits = expression.split(" ");
		int operand1 = Integer.parseInt(splits[0], radix);
		String operator = splits[1];
		int operand2 = Integer.parseInt(splits[2], radix);

		if (operator.equals("+")) {
			return operand1 + operand2;
		} else {
			return operand1 - operand2;
		}
	}

	//
	public static void main(String[] args) {

		Pccp_340210 sol = new Pccp_340210();

		String[][] testCases = { 
				
				{ "14 + 3 = 17", "13 - 6 = X", "51 - 5 = 44" },
				{ "1 + 1 = 2", "1 + 3 = 4", "1 + 5 = X", "1 + 2 = X" },
				{ "10 - 2 = X", "30 + 31 = 101", "3 + 3 = X", "33 + 33 = X" },
				{ "2 - 1 = 1", "2 + 2 = X", "7 + 4 = X", "5 - 5 = X" },
				{ "2 - 1 = 1", "2 + 2 = X", "7 + 4 = X", "8 + 4 = X" }, 
				
		};

		for (int i = 0; i < testCases.length; i++) {
			System.out.println("Test Case " + (i + 1) + ": " + Arrays.toString(sol.solution(testCases[i])));
		}

//		String[] expressions = {"14 + 3 = 17", "13 - 6 = X", "51 - 5 = 44"};
//		String[] expressions = {"1 + 1 = 2", "1 + 3 = 4", "1 + 5 = X", "1 + 2 = X"};
//		String[] expressions = {"10 - 2 = X", "30 + 31 = 101", "3 + 3 = X", "33 + 33 = X"};
//		String[] expressions = { "2 - 1 = 1", "2 + 2 = X", "7 + 4 = X", "5 - 5 = X" };
//		String[] expressions = {"2 - 1 = 1", "2 + 2 = X", "7 + 4 = X", "8 + 4 = X"};

//		String[] result = sol.solution(expressions);
//		System.out.println(Arrays.toString(result));

	}
}