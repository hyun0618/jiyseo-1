package com.haerin.cote;

public class Pccp_340213 {
	
	public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
			
		int videoLen = toSec(video_len);
		int curr = toSec(pos);
		int opStart = toSec(op_start);
		int opEnd = toSec(op_end);
		
		curr = skip(curr, opStart, opEnd);
		
		for (String cmd : commands) {
			switch (cmd) {
			case "next":
				curr = next(curr, videoLen);
				break;
			case "prev":
				curr = prev(curr);
				break;
			}
		}
		
		curr = skip(curr, opStart, opEnd);

		return toStr(curr);
	}
	
	private int prev(int curr) {
		return Math.max(0, curr - 10);
	}
	
	private int next(int curr, int videoLen) {
		return Math.min(curr + 10, videoLen);
	}
	
	private int skip(int curr, int opStart, int opEnd) {
		if (curr >= opStart && curr <= opEnd) {
			curr = opEnd;
		}
		return curr;
	}
	
	private int toSec(String time) {
		String[] parts = time.split(":"); // split하면 배열이 되므로
		int min = Integer.parseInt(parts[0]);
		int sec = Integer.parseInt(parts[1]);
		return min * 60 + sec;
	}
	
	private String toStr(int sec) {
		int min = sec / 60;
		int second = sec % 60;
		return String.format("%02d:%02d", min, second); // %02d : 2자리 정수로 표현, 빈자리는 0으로 채움
	}

	public static void main(String[] args) {
		
		Pccp_340213 sol = new Pccp_340213();
		
//		String video_len = "34:33";
//		String pos = "13:00";
//		String op_start = "00:55";
//		String op_end = "02:55";
//		String[] commands = {"next", "prev"};
		
		String video_len = "10:55";
		String pos = "00:05";
		String op_start = "00:15";
		String op_end = "06:55";
		String[] commands = {"prev", "next", "next"};
		
//		String video_len = "07:22";
//		String pos = "04:05";
//		String op_start = "00:15";
//		String op_end = "04:07";
//		String[] commands = {"next"};
		
		String result = sol.solution(video_len, pos, op_start, op_end, commands);
		System.out.println("최종 위치 = " + result);

	}

}
