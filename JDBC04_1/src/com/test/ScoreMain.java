package com.test;

import java.util.Scanner;

public class ScoreMain
{
	public static void main(String[] args)
	{
		ScoreProcess prc = new ScoreProcess();
		Scanner sc = new Scanner(System.in);
		
		do
		{
			System.out.println();
			System.out.println("====[ 성적 처리 ]===="); // 컨트롤 알트 방향키 -> 복사
			System.out.println("1. 성적 입력"); // 알트 쉬프트 a -> 토글
			System.out.println("2. 성적 전체 출력");
			System.out.println("3. 이름 검색 출력");
			System.out.println("4. 성적 수정");
			System.out.println("5. 성적 삭제");
			System.out.println("=====================");
			System.out.print(">> 선택(1~5, -1종료) : ");
			String menus = sc.next();
			
			try
			{
				int menu = Integer.parseInt(menus);
				
				if (menu == -1)
				{
					System.out.println();
					System.out.println("프로그램이 종료되었습니다.");
					return;
				}
				
				switch (menu)
				{
				case 1:
					// 성적 입력 기능 수행
					prc.sungjukInsert();
					break;
				case 2:
					// 성적 전체 출력 기능 수행
					prc.sungjukSelectAll();
					break;
				case 3:
					// 이름 검색 출력 기능 수행
					prc.sungjukSearchName();
					break;
				case 4:
					// 성적 수정 기능 수행
					prc.sungjukUpdate();
					break;
				case 5:
					// 성적 삭제 기능 수행
					prc.sungjukDelete();
					break;
				}
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
			
		} while (true);
	}
}