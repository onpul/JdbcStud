package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.util.DBConn;

public class ScoreDAO
{
	// 주요 속성 구성
	private Connection conn;
	
	
	// 데이터베이스 연결 담당 메소드
	public Connection connection() throws ClassNotFoundException, SQLException
	{
		conn = DBConn.getConnection();
		return conn;
	}
	
	
	// 데이터 입력 담당 메소드
	public int add(ScoreDTO dto) throws SQLException
	{
		// 변수 선언
		int result = 0;
		
		// 작업 객체 생성
		Statement stmt = conn.createStatement();
		
		// 쿼리문 준비
		String sql = String.format("INSERT INTO TBL_SCORE(SID, NAME, KOR, ENG, MAT) VALUES(SCORESEQ.NEXTVAL, '%s', %d, %d, %d)"
                , dto.getName(), dto.getKor(), dto.getEng(), dto.getMat());
		
		// 쿼리문 실행
		result = stmt.executeUpdate(sql);
		
		// 리소스 반납
		stmt.close();
		
		// 리턴
		return result;
	}
	
	
	// 전체 리스트 출력 담당 메소드
	public ArrayList<ScoreDTO> lists() throws SQLException
	{
		// 변수 선언
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
		
		// 작업 객체 생성
		Statement stmt = conn.createStatement();
		
		// 쿼리문 준비
		String sql = "SELECT SID, NAME, KOR, ENG, MAT"
				+ ", (KOR + ENG + MAT) AS TOT"
				+ ", (KOR + ENG + MAT)/3 AS AVG"
				+ ", RANK() OVER(ORDER BY (KOR+ENG+MAT) DESC) AS RANK"
				+ " FROM TBL_SCORE"
				+ " ORDER BY SID ASC";
		
		// 쿼리문 실행
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			ScoreDTO dto = new ScoreDTO();
			
			dto.setSid(rs.getString("SID"));
			dto.setName(rs.getString("NAME"));
			dto.setKor(rs.getInt("KOR"));
			dto.setEng(rs.getInt("ENG"));
			dto.setMat(rs.getInt("MAT"));
			dto.setTot(rs.getInt("TOT"));
			dto.setAvg(rs.getDouble("AVG"));
			dto.setRank(rs.getInt("RANK"));
			
			result.add(dto);
		}
		
		// 리소스 반납
		rs.close();
		stmt.close();
		
		// 리턴
		return result;
	}
	
	
	// 이름 검색 담당 메소드(오버로딩)
	public ArrayList<ScoreDTO> lists(String name) throws SQLException
	{
		// 변수 선언
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
		
		// 작업 객체 생성
		Statement stmt = conn.createStatement();
		
		// 쿼리문 준비
		String sql = String.format("SELECT SID, NAME, KOR, ENG, MAT, TOT, AVG, RANK"
				+ " FROM"
				+ " (SELECT SID, NAME, KOR, ENG, MAT"
				+ ", (KOR + ENG + MAT) AS TOT"
				+ ", (KOR + ENG + MAT)/3 AS AVG"
				+ ", RANK() OVER(ORDER BY (KOR+ENG+MAT) DESC) AS RANK"
				+ " FROM TBL_SCORE)"
				+ " WHERE NAME = '%s'", name);
		
		// 쿼리문 실행
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			ScoreDTO dto = new ScoreDTO();
			
			dto.setSid(rs.getString("SID"));
			dto.setName(rs.getString("NAME"));
			dto.setKor(rs.getInt("KOR"));
			dto.setEng(rs.getInt("ENG"));
			dto.setMat(rs.getInt("MAT"));
			dto.setTot(rs.getInt("TOT"));
			dto.setAvg(rs.getDouble("AVG"));
			dto.setRank(rs.getInt("RANK"));
			
			result.add(dto);
		}
		
		// 리소스 반납
		rs.close();
		stmt.close();
		
		// 리턴
		return result;
	}
	
	
	// 번호 검색 담당 메소드(오버로딩)
	public ArrayList<ScoreDTO> lists(int sid) throws SQLException
	{
		// 변수 선언
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
		
		// 작업 객체 생성
		Statement stmt = conn.createStatement();
		
		// 쿼리문 준비
		String sql = String.format("SELECT SID, NAME, KOR, ENG, MAT, TOT, AVG, RANK"
				+ " FROM"
				+ " (SELECT SID, NAME, KOR, ENG, MAT"
				+ ", (KOR + ENG + MAT) AS TOT"
				+ ", (KOR + ENG + MAT)/3 AS AVG"
				+ ", RANK() OVER(ORDER BY (KOR+ENG+MAT) DESC) AS RANK"
				+ " FROM TBL_SCORE)"
				+ " WHERE SID =%d", sid);
		
		// 쿼리문 실행
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			ScoreDTO dto = new ScoreDTO();
			
			dto.setSid(rs.getString("SID"));
			dto.setName(rs.getString("NAME"));
			dto.setKor(rs.getInt("KOR"));
			dto.setEng(rs.getInt("ENG"));
			dto.setMat(rs.getInt("MAT"));
			dto.setTot(rs.getInt("TOT"));
			dto.setAvg(rs.getDouble("AVG"));
			dto.setRank(rs.getInt("RANK"));
			
			result.add(dto);
		}
		
		// 리소스 반납
		rs.close();
		stmt.close();
		
		// 리턴
		return result;
	}
	
	
	// 인원 수 확인 담당 메소드
	public int count() throws SQLException
	{
		// 변수 선언
		int result = 0;
		
		// 작업 객체 생성
		Statement stmt = conn.createStatement();
		
		// 쿼리문 준비
		String sql = "SELECT COUNT(*) AS COUNT FROM TBL_SCORE";
		
		// 쿼리문 실행
		ResultSet rs = stmt.executeQuery(sql);
		
		if (rs.next())
		{
			result = rs.getInt(1);
		}
		
		// 리소스 반납
		rs.close();
		stmt.close();
		
		// 리턴
		return result;
	}
	
	
	// 데이터 수정 담당 메소드 
	// -> 레코드의 값을 바꾸는 메소드 => 업뎃 쿼리문의 set할 애들 다 줘야 함
	public int modify(ScoreDTO dto) throws SQLException
	{
		// 변수 선언
		int result = 0;
		
		// 작업 객체 생성
		Statement stmt = conn.createStatement();
		
		// 쿼리문 준비
		String sql = String.format("UPDATE TBL_SCORE SET NAME='%s', KOR=%d, ENG=%d, MAT=%d WHERE SID=%s"
				, dto.getName(), dto.getKor(), dto.getEng(), dto.getMat(), dto.getSid());
		
		// 쿼리문 실행
		result = stmt.executeUpdate(sql);
		
		// 리소스 반납
		stmt.close();
		
		// 리턴
		return result;
	}
	
	
	// 데이터 삭제 담당 메소드
	public int remove(int sid) throws SQLException
	{
		// 변수 선언
		int result = 0;
		
		// 작업 객체 생성
		Statement stmt = conn.createStatement();
		
		// 쿼리문 준비
		String sql = String.format("DELETE FROM TBL_SCORE WHERE SID=%d", sid);
		
		// 쿼리문 실행
		result = stmt.executeUpdate(sql);
		
		// 리소스 반납
		stmt.close();
		
		// 리턴
		return result;
	}
	
	
	// 데이터베이스 연결 종료 담당 메소드
	public void close() throws SQLException
	{
		DBConn.close();
	}
}
