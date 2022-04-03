/*==================
 MemberDAO.java 
==================*/

// 데이터베이스에 액세스 하는 기능
// → DBConn 활용(전담 계층)

// 데이터를 입력하는 기능 → INSERT

// 인원 수 확인하는 기능 
// 즉, 대상 테이블(TBL_MEMBER)의 레코드 카운팅 기능 → SELECT

// 전체 리스트를 조회하는 기능
// 즉, 대상 테이블(TBL_MEMBER)의 데이터를 조회하는 기능 → SELECT

package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.util.DBConn;

public class MemberDAO
{
	// 주요 속성 구성 → DB 연결 객체
	private Connection conn;

	// 생성자 정의(사용자 정의 생성자)
	public MemberDAO() throws ClassNotFoundException, SQLException
	{
		conn = DBConn.getConnection();
	}
	
	// 메소드 정의 → 데이터를 입력하는 기능 → INSERT
	public int add(MemberDTO dto) throws SQLException
	{
		// 반환할 결과값을 담아낼 변수(적용된 행의 갯수)
		int result = 0;
		
		// 작업 객체 생성
		Statement stmt = conn.createStatement();
		
		// 쿼리문 준비(insert)
		String sql = String.format("INSERT INTO TBL_MEMBER(SID, NAME, TEL)"
								+ " VALUES(MEMBERSEQ.NEXTVAL, '%s', '%s')", dto.getName(), dto.getTel());
		
		// 작업 객체를 활용하여 쿼리문 실행(전달)
		result = stmt.executeUpdate(sql);
		
		// 사용한 리소스 반납
		stmt.close();
		
		// 최종 결과값 반환
		return result;
		
	}//end add()
	
	
	// 메소드 정의 → 전체 인원 수 확인하는 기능 → SELECT
	public int count() throws SQLException
	{
		// 결과값으로 반환하게 될 변수 선언 및 초기화
		int result = 0;
		
		// 작업 객체 생성
		Statement stmt = conn.createStatement();
		
		// 쿼리문 준비
		String sql = "SELECT COUNT(*) AS COUNT FROM TBL_MEMBER";
		
		// 생성된 작업 객체를 활용하여 쿼리문 실행 → select → executeQuery() → ResultSet 반환 → 일반적으로 반복문 구성을 통한 ResultSet 처리
		ResultSet rs = stmt.executeQuery(sql);
		
		// ResultSet 처리 → 반복문 구성 → 결과값 수신
		while (rs.next())					// if (rs.next)
		{
			result = rs.getInt("COUNT");    // rs.getInt(1);		// ※ 컬럼 인덱스는 1 부터...
		}
		
		// 리소스 반납 -> 반납 순서: 바깥쪽부터(오픈이 abc -> 클로즈는 cba)
		rs.close();
		stmt.close();
		
		// 최종 결과값 반환
		return result;
		
	}//end count()
	
	// 메소드 정의 → 전체 리스트를 조회하는 기능 → select
	public ArrayList<MemberDTO> lists() throws SQLException
	{
		// 결과값으로 변환할 변수 선언 및 초기화
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		// 작업 객체 생성
		Statement stmt = conn.createStatement();
		
		// 쿼리문 준비 → select
		String sql = "SELECT SID, NAME, TEL FROM TBL_MEMBER ORDER BY SID";
		
		// 생성된 작업 객체를 활용하여 쿼리문 실행 → select → executeQuery() → ResultSet 반환 → 일반적으로 반복 처리
		ResultSet rs = stmt.executeQuery(sql);
		
		// ResultSet 처리 → 일반적 반복문 활용
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			
			dto.setSid(rs.getString("SID"));
			dto.setName(rs.getString("NAME"));
			dto.setTel(rs.getString("TEL"));
			
			result.add(dto);
		}
		
		// 리소스 반납
		rs.close();
		stmt.close();
		
		// 최종 결과값 반환
		return result;
		
	}//end lists()
	
	
	public void close() throws SQLException 
	{
		// conn.close(); // 이거 아님
		
		DBConn.close();
	}
	
	
}//end class MemberDAO


/* 내 풀이 

//데이터 입력, 데이터 조회, 인원수 확인 (액션 처리)

package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.util.DBConn;

public class MemberDAO
{
	public MemberDAO() throws ClassNotFoundException, SQLException
	{	
		Connection conn = DBConn.getConnection();
		
		Scanner sc = new Scanner(System.in);
		
		if (conn != null)
		{
			System.out.println("데이터베이스 연결 성공~!!!");
			
			try
			{
				// 작업 객체 생성
				Statement stmt = conn.createStatement();
				
				// 쿼리문 준비(select)
				String sql = "SELECT COUNT(*) AS COUNT FROM TBL_MEMBER";
				
				// 쿼리문 실행
				ResultSet rs = stmt.executeQuery(sql);
				
				int count = 0;
				
				while (rs.next())
				{
					count = rs.getInt("COUNT");
				}

				// ResultSet 리소스 반납
				rs.close();
				
				// Statement 리소스 반납
				stmt.close();
				
				do
				{
					System.out.printf("이름 전화번호 입력(%s) : ", count+1);
					String name = sc.next();
					String tel = sc.next();
					
					if (name.equals(".") || tel.equals("."))
			        {
			            break;
			        }
					
					try
					{
						// 작업 객체 준비
						Statement stmt2 = conn.createStatement();
						
						// 쿼리문 준비
			            String sql2 = String.format("INSERT INTO TBL_MEMBER(SID, NAME, TEL) VALUES(MEMBERSEQ.NEXTVAL, '%s', '%s')", name, tel);
						int result = stmt2.executeUpdate(sql2);
			            
			            if (result > 0)
		                {
			            	System.out.println("회원 정보 입력 완료~!!!");
		                }
			            else
			            {
			            	System.out.println("회원 정보 입력 실패");
			            }
						// Statement 리소스 반납
						stmt2.close();
					} catch (Exception e)
					{
						System.out.println(e.toString());
					}
				}
				while (true);
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
			
		}
		else
		{
			System.out.println("데이터베이스 연결 실패~!!!");
		}
		
		sc.close();
		
		DBConn.close();
		
		System.out.println("데이터베이스 연결 닫힘~!!!");
		System.out.println("프로그램 종료됨~!!!");
	}
}
*/