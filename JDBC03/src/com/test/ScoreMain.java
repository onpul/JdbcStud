/*=====================
	ScoreMain.java
=====================*/

/*
○ 성적 처리 프로그램 구현 → 데이터베이스 연동 → ScoreDAO, ScoreDTO 클래스 활용
   
   여러 병의 이름, 국어점수, 영어점수, 수학점수를 입력받아
   총점, 평균을 연산하여 내용을 출력하는 프로그램을 구현한다.
   출력 시 번호(이름, 총점 등) 오름차순 정렬하여 출력한다.
 
실행 예)

1번 학생 성적 입력(이름 국어 영어 수학) : 신시은 80 75 60
2번 학생 성적 입력(이름 국어 영어 수학) : 이호석 100 90 80
3번 학생 성적 입력(이름 국어 영어 수학) : 이연주 80 85 80
4번 학생 성적 입력(이름 국어 영어 수학) : .

-------------------------------------------------------------
번호	이름	국어	영어	수학	총점	평균
-------------------------------------------------------------
1		신시은	80		75		60		xxx		xx.x
2		이호석	100		90		80		xxx		xx.x
3		이연주	80		85		80		xxx		xx.x
-------------------------------------------------------------
*/

// 만드는 순서 : DBConn - DTO - DAO - Main

package com.test;

import java.util.Scanner;

import com.util.DBConn;

public class ScoreMain
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		try
		{
			ScoreDAO dao = new ScoreDAO();
			
			int count = dao.count();
			
			do
			{
				System.out.printf("%d번 학생 성적 입력(이름 국어 영어 수학) : ", ++count);
				String name = sc.next();
				
				// break 문 구성
				if (name.equals("."))
				{
					break;
				}
				
				int kor = sc.nextInt();
				int eng = sc.nextInt();
				int mat = sc.nextInt();
				
				// MemberDTO 객체 생성
				ScoreDTO dto = new ScoreDTO();
				
				// 속성값 구성
				dto.setName(name);
				dto.setKor(kor);
				dto.setEng(eng);
				dto.setMat(mat);
				
				// 데이터베이스 입력 메소드 호출
				int result = dao.add(dto);

			} while (true);
			
			System.out.println();
			System.out.println("-------------------------------------------------------------");
			System.out.println("번호	이름	  국어	  영어	  수학	  총점	  평균");
			System.out.println("-------------------------------------------------------------");
			
			// 리스트 가져와 출력
			for (ScoreDTO obj : dao.lists())
			{
				System.out.printf("%3s %7s %7d %7d %7d %7d %7.1f\n"
						        , obj.getSid(), obj.getName(), obj.getKor(), obj.getEng(), obj.getMat()
						        , obj.getKor() + obj.getEng() + obj.getMat()
						        , (obj.getKor() + obj.getEng() + obj.getMat()) / 3.0);
			}
		} catch (Exception e)
		{
			System.out.println(e.toString());
			//System.out.println("여기서 에러나는 거임1");
		}
		finally 
		{
			try
			{
				DBConn.close();
				System.out.println("데이터베이스 연결 닫힘~!!!");
				System.out.println("프로그램 종료됨~!!!");
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
	}
}
