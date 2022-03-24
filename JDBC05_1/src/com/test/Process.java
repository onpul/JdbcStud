package com.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.text.html.parser.DTD;

public class Process
{
	// 주요 속성 구성
	private MemberDAO dao;
	
	// 생성자 정의
	public Process()
	{
		dao = new MemberDAO();
	}
	
	// 직원 정보 입력 기능
	public void memberInsert()
	{
		try
		{
			// 데이터베이스 연결
			dao.connection();
			
			// 레코드 수 확인
			int count = dao.count();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			
			System.out.println();
			System.out.println("직원 정보 입력 -------------------------------------------------------------------");
			System.out.print("이름 : ");
			String emp_name = br.readLine();
			System.out.print("주민등록번호(yymmdd-nnnnnnn) : ");
			String ssn = br.readLine();
			System.out.print("입사일(yyyy-mm-dd) : ");
			String ibsadate = br.readLine();
			
			System.out.print("지역 : ");
			String city_name = br.readLine();
			
			System.out.print("전화번호 : ");
			String tel = br.readLine();
			
			System.out.print("부서 : ");
			String buseo_name = br.readLine();
			
			System.out.print("직위 : ");
			String jikwi_name = br.readLine();
			
			System.out.print("기본급 : ");
			int basicpay = Integer.parseInt(br.readLine());
			
			System.out.print("수당 : ");
			int sudang = Integer.parseInt(br.readLine());
				
			
			// MemberDTO 객체 구성
			MemberDTO dto = new MemberDTO();
			dto.setEmp_name(emp_name);
			dto.setSsn(ssn);
			dto.setIbsadate(ibsadate);
			
			// 지역 입력 정수로 set
			switch (city_name)
			{
				case "강원" : dto.setCity_id(1); break;
				case "경기" : dto.setCity_id(2); break;
				case "경남" : dto.setCity_id(3); break;
				case "경북" : dto.setCity_id(4); break;
				case "부산" : dto.setCity_id(5); break;
				case "서울" : dto.setCity_id(6); break;
				case "인천" : dto.setCity_id(7); break;
				case "전남" : dto.setCity_id(8); break;
				case "전북" : dto.setCity_id(9); break;
				case "제주" : dto.setCity_id(10); break;
				case "충남" : dto.setCity_id(11); break;
				case "충북" : dto.setCity_id(12); break;
			}
				
			dto.setTel(tel);
			
			// 부서 이름 정수로 set
			switch (buseo_name)
			{
				case "개발부" : dto.setBuseo_id(1); break;
				case "기획부" : dto.setBuseo_id(2); break;
				case "영업부" : dto.setBuseo_id(3); break;
				case "인사부" : dto.setBuseo_id(4); break;
				case "자재부" : dto.setBuseo_id(5); break;
				case "총무부" : dto.setBuseo_id(6); break;
				case "홍보부" : dto.setBuseo_id(7); break;
			}
			
			// 직위 이름 정수로 set
			switch (jikwi_name)
			{
				case "사장" : dto.setJikwi_id(1); break;
				case "전무" : dto.setJikwi_id(2); break;
				case "상무" : dto.setJikwi_id(3); break;
				case "이사" : dto.setJikwi_id(4); break;
				case "부장" : dto.setJikwi_id(5); break;
				case "차장" : dto.setJikwi_id(6); break;
				case "과장" : dto.setJikwi_id(7); break;
				case "대리" : dto.setJikwi_id(8); break;
				case "사원" : dto.setJikwi_id(9); break;
			}
			
			dto.setBasicpay(basicpay);
			dto.setSudang(sudang);
			
			// dao add() 호출
			int result = dao.add(dto);
			
			if (result > 0 )
			{
				System.out.println("직원 정보 입력 완료~!!!");
				System.out.println("------------------------------------------------------------------- 직원 정보 입력");
			}
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
			System.out.println("직원 정보 입력 오류");
		}
	} // end memberInsert()
	
	// 멤버 전체 출력 기능
	public void memberSelectAll()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.println("1. 사번 정렬");
		System.out.println("2. 이름 정렬");
		System.out.println("3. 부서 정렬");
		System.out.println("4. 직위 정렬");
		System.out.println("5. 급여 내림차순 정렬");
		System.out.print(">> 선택(1~5, -1종료) : ");
		int num = sc.nextInt();
		
		try
		{
			dao.connection();
			
			ArrayList<MemberDTO> arrayList = dao.lists(num); 

			int count = dao.count();
			
			System.out.println();
			System.out.printf("전체 인원 : %d명\n", count);
			System.out.println("사번   이름     주민번호         입사일       지역   전화번호        부서     직위   기본급    수당     급여");
			
			for (MemberDTO dto : arrayList)
			{
				System.out.printf("%3s  %4s  %15s  %11s  %3s  %14s  %4s  %3s  %8d  %7d  %8d\n"
						        , dto.getEmp_id(), dto.getEmp_name(), dto.getSsn(), ((dto.getIbsadate()).substring(0, 10)) // ★ Q.왜 세션 설정 적용 안 됨?
						        , dto.getCity_name(), dto.getTel(), dto.getBuseo_name(), dto.getJikwi_name()
						        , dto.getBasicpay(), dto.getSudang(), dto.getSal());
			}
			
			dao.close();

		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	} // end memberSelectAll()
	
	public void memberSearch()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.println("1. 사번 검색");
		System.out.println("2. 이름 검색");
		System.out.println("3. 부서 검색");
		System.out.println("4. 직위 검색");
		System.out.print(">> 선택(1~4, -1종료) : ");
		int num = sc.nextInt();
		
		String kind = null;
		
		switch (num)
		{
			case 1: kind = "사번"; break;
			case 2: kind = "이름"; break;
			case 3: kind = "부서"; break;
			case 4: kind = "직위"; break;
		}
		
		try
		{	
			System.out.printf("검색할 %s 입력 : ", kind);
			String search = sc.next();
			
			// 데이터베이스 연결
			dao.connection();
			
			// 매개변수로 입력할 검색 내용 문자열 형태로 넘겨주기
			ArrayList<MemberDTO> arrayList = dao.lists(num, search); 
			
			if (arrayList.size() > 0)
			{	
				System.out.println();
				System.out.println("사번   이름     주민번호         입사일       지역   전화번호        부서     직위   기본급    수당     급여");
				
				for (MemberDTO dto : arrayList)
				{
					System.out.printf("%3s  %4s  %15s  %11s  %3s  %14s  %4s  %3s  %8d  %7d  %8d\n"
							        , dto.getEmp_id(), dto.getEmp_name(), dto.getSsn(), ((dto.getIbsadate()).substring(0, 10)) // ★ Q.왜 세션 설정 적용 안 됨?
							        , dto.getCity_name(), dto.getTel(), dto.getBuseo_name(), dto.getJikwi_name()
							        , dto.getBasicpay(), dto.getSudang(), dto.getSal());
				}
			}
			else
				System.out.println("검색 결과가 존재하지 않습니다.");
			
			dao.close();
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	} // end memberSearch()
	
	public void memberUpdate()
	{
		try
		{
			Scanner sc = new Scanner(System.in);
			System.out.print("수정할 직원의 번호를 입력하세요 : ");
			String emp_id = sc.next(); 
			
			// 데이터베이스 연결
			dao.connection();
			
			// 수정할 대상 수신
			ArrayList<MemberDTO> arrayList = dao.lists(1, emp_id);
			
			if (arrayList.size() > 0)
			{
				// 수신된 내용 출력
				System.out.println();
				System.out.println("사번   이름     주민번호         입사일       지역   전화번호        부서     직위   기본급    수당     급여");
				
				for (MemberDTO dto : arrayList)
				{
					System.out.printf("%3s  %4s  %15s  %11s  %3s  %14s  %4s  %3s  %8d  %7d  %8d\n"
							        , dto.getEmp_id(), dto.getEmp_name(), dto.getSsn(), ((dto.getIbsadate()).substring(0, 10)) // ★ Q.왜 세션 설정 적용 안 됨?
							        , dto.getCity_name(), dto.getTel(), dto.getBuseo_name(), dto.getJikwi_name()
							        , dto.getBasicpay(), dto.getSudang(), dto.getSal());
				}
				
				System.out.println();
				System.out.println("수정 데이터 입력(이름 주민번호 입사일 지역 전화번호 부서 직위 기본급 수당) : ");
				String emp_name = sc.next();
				String ssn = sc.next();
				String ibsadate = sc.next();
				String city_name = sc.next();
				String tel = sc.next();
				String buseo_name = sc.next();
				String jikwi_name = sc.next();
				int basicpay = sc.nextInt();
				int sudang = sc.nextInt();
				
				// dto 구성
				MemberDTO dto = new MemberDTO();
				dto.setEmp_name(emp_name);
				dto.setSsn(ssn);
				dto.setIbsadate(ibsadate);
				dto.setCity_name(city_name);
				dto.setTel(tel);
				dto.setBuseo_name(buseo_name);
				dto.setJikwi_name(jikwi_name);
				dto.setBasicpay(basicpay);
				dto.setSudang(sudang);
				
				// 구성된 dto 를 넘겨주며 dao의 modify() 메소드 호출
				int result = dao.modify(dto);
				if (result > 0)
				{
					System.out.println("수정이 완료되었습니다.");
				}
			} else
			{
				System.out.println("수정 대상이 존재하지 않습니다.");
			}
			
			dao.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
}
