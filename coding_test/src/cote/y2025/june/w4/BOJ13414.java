package cote.y2025.june.w4;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class BOJ13414 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		int k = sc.nextInt(); // 수강 가능 인원
		int l = sc.nextInt(); // 대기목록의 길이
		sc.nextLine(); // 버퍼 비우기 (nextInt 이후 줄바꿈 제거)
		
		Set<String> set = new LinkedHashSet<>(); // 클릭 순서 유지 
		 
		for (int i = 0; i < l; i++) {
		    String student = sc.nextLine();
		    if (set.contains(student)) {
		        set.remove(student);
		    } 
		    set.add(student);
		}
		//System.out.print(set);
		int count = 0;
		for (String id : set) {
		    if (count == k) break;
		    System.out.println(id);
		    count++;
		}

	}

}
