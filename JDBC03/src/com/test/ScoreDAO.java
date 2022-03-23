/*=====================
	ScoreDTO.java
=====================*/


package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.util.DBConn;

public class ScoreDAO
{
	// 주요 속성 구성 -> DB 연결 객체
	private Connection conn;
	
	// 생성자 정의
	public ScoreDAO()
	{
		conn = DBConn.getConnection();
	}// end ScoreDAO()
	
	// 데이터 입력 메소드
	public int add(ScoreDTO dto) throws SQLException
	{
		int result = 0;
		
		// 작업 객체 생성
		Statement stmt = conn.createStatement();
		
		// 쿼리문 준비
		String sql = String.format("INSERT INTO TBL_SCORE(SID, NAME, KOR, ENG, MAT)"
				                 + " VALUES(MEMBERSEQ.NEXTVAL, '%s', '%d', '%d', '%d')"
				                   , dto.getName(), dto.getKor(), dto.getEng(), dto.getMat());
		
		// 쿼리문 실행
		result = stmt.executeUpdate(sql);
		
		// 사용한 리소스 반납
		stmt.close();
		
		// 최종 결과값 반환
		return result;
	}//end add()
	
	// 전체 인원수 확인 메소드
	public int count() throws SQLException 
	{
		int result = 0;
		
		// 작업 객체 생성
		Statement stmt = conn.createStatement();
		
		// 쿼리문 준비
		String sql = "SELECT COUNT(*) AS COUNT FROM TBL_SCORE";
		
		// 쿼리문 실행
		ResultSet rs = stmt.executeQuery(sql);
		
		// ResultSet 처리
		while (rs.next())
		{
			result = rs.getInt("COUNT");
		}
		
		// 리소스 반납
		rs.close();
		stmt.close();
		
		// 최종 결과값 반환
		return result;
	}// end count()
	
	// 전체 리스트 조회 메소드
	public ArrayList<ScoreDTO> lists() throws SQLException
	{
		// 변수 선언 및 초기화
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
		
		// 작업 객체 생성
		Statement stmt = conn.createStatement();
		
		// 쿼리문 준비
		String sql = "SELECT SID, NAME, KOR, ENG, MAT FROM TBL_SCORE ORDER BY SID";
		
		// 쿼리문 실행
		ResultSet rs = stmt.executeQuery(sql);
		
		// ResultSet 처리
		while (rs.next())
		{
			ScoreDTO dto = new ScoreDTO();
			
			dto.setSid(rs.getString("SID"));
			dto.setName(rs.getString("NAME"));
			dto.setKor(rs.getString("KOR"));
			dto.setEng(rs.getString("ENG"));
			dto.setMat(rs.getString("MAT"));
			
			result.add(dto);
		}
		
		// 리소스 반납
		rs.close();
		stmt.close();
		
		// 최종 결과값 반환
		return result;
	}// end lists()
}
