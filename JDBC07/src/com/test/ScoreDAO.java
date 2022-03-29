/*==================
	ScoreDAO.java
==================*/
package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.util.DBConn;

public class ScoreDAO
{
	//주요 변수 선언
	private Connection conn;
	
	
	//데이터베이스 연결하기
	/*
	public void connection()
	{
		// 사실 이 구문도 DBConn 에서 처리한 구문이기 때문에 필요없음~!!!
		if (conn == null)
			conn = DBConn.getConnection();
		//System.out.println("데이터베이스 연동성공");
		//System.out.println();
	}
	*/
	
	// 데이터베이스 연결하는 메소드 → 커넥션을 넘겨줄 수 있도록 처리~!!!
	public Connection connection()
	{
		conn = DBConn.getConnection();
		return conn;
	}
		
	
	//데이터베이스 연결해제하기
	public void unconnection()
	{
		DBConn.close();
		//System.out.println("데이터베이스 연동해제 성공");
		//System.out.println();
	}
	
	
	
	//성적 입력
	public int insert(ScoreDTO dto) throws SQLException
	{
		//주요 변수 선언
		int result = 0;
		
		// 쿼리문 준비
		String sql = "INSERT INTO TBL_SCORE (SID,NAME,KOR,ENG,MAT)"
				+ " VALUES(SCORESEQ.NEXTVAL,?,?,?,?)";
		
		//작업 객체 생성
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		//IN 매개변수 넘겨주기
		pstmt.setString(1, dto.getName());
		pstmt.setInt(2, dto.getKor());
		pstmt.setInt(3, dto.getEng());
		pstmt.setInt(4, dto.getMat());
		
		//쿼리문 실행
		result = pstmt.executeUpdate();
		
		//리소스 반납
		pstmt.close();
		
		//값 반환
		return result;
	}//end insert(ScoreDTO dto)
	
	
	
	
	
	
	//인원 수 세기
	public int count() throws SQLException
	{
		int result = 0;
		
		String sql = "SELECT COUNT(*) AS COUNT FROM TBL_SCORE";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.next())
			result = rs.getInt("COUNT");
		
		rs.close();
		pstmt.close();
		
		
		return result;
	}//end count()
	
	
	
	
	
	
	//성적 전체 출력
	public ArrayList<ScoreDTO> selectAll() throws SQLException
	{
		//주요 변수 선언
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
		
		//쿼리문 준비
		String sql ="SELECT SID, NAME, KOR, ENG, MAT"
				+ " ,(KOR + ENG + MAT) AS TOT"
				+ " ,(KOR + ENG + MAT) / 3 AS AVG"
				+ " ,RANK() OVER(ORDER BY(KOR + ENG + MAT) DESC) AS RANK"
				+ " FROM TBL_SCORE";
		
		//작업 객체 생성
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		
		//쿼리문 실행
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next())
		{
			ScoreDTO dto = new ScoreDTO();
			dto.setSid(rs.getInt("SID"));
			dto.setName(rs.getString("NAME"));
			dto.setKor(rs.getInt("KOR"));
			dto.setEng(rs.getInt("ENG"));
			dto.setMat(rs.getInt("MAT"));
			dto.setTot(rs.getInt("TOT"));
			dto.setAvg(rs.getInt("AVG"));
			dto.setRank(rs.getInt("RANK"));
			
			result.add(dto);
		}
		
		//리소스 반환
		rs.close();
		pstmt.close();
		
		//값 반환
		return result;
	
	}//end selectAll(ScoreDTO dto)
	
	
	
	
	
	//이름 검색 출력
	public ArrayList<ScoreDTO> search(String name) throws SQLException
	{
		
		// 주요 변수 선언
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
		
		//쿼리문 준비
		String sql = "SELECT SID, NAME, KOR, ENG, MAT"
				+ " ,(KOR + ENG + MAT) AS TOT"
				+ " ,(KOR + ENG + MAT) / 3.0 AS AVG"
				+ " ,RANK() OVER(ORDER BY(KOR + ENG + MAT) DESC) AS RANK"
				+ " FROM TBL_SCORE"
				+ " WHERE NAME = ?";
		//작업 객체 생성
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		//IN 매개변수
		pstmt.setString(1, name);
		
		//쿼리문 실행
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next())
		{
			ScoreDTO dto = new ScoreDTO();
			dto.setSid(rs.getInt("SID"));
			dto.setName(rs.getString("NAME"));
			dto.setKor(rs.getInt("KOR"));
			dto.setEng(rs.getInt("ENG"));
			dto.setMat(rs.getInt("MAT"));
			dto.setTot(rs.getInt("TOT"));
			dto.setAvg(rs.getInt("AVG"));
			dto.setRank(rs.getInt("RANK"));
			
			result.add(dto);
		}
		
		//리소스 반환
		rs.close();
		pstmt.close();
		
		
		// 값 반환
		return result;
		
	}//end searchName(String name)
	
	
	
	
	
	//학번 검색 출력
	public ArrayList<ScoreDTO> search(int sid) throws SQLException
	{
		// 주요 변수 선언
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
		
		//쿼리문 준비
		String sql = "SELECT SID, NAME, KOR, ENG, MAT"
				+ " ,(KOR + ENG + MAT) AS TOT"
				+ " ,(KOR + ENG + MAT) / 3 AS AVG"
				+ " FROM TBL_SCORE"
				+ " WHERE SID = ?";
		//작업 객체 생성
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		//IN 매개변수
		pstmt.setInt(1, sid);
		
		//쿼리문 실행
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next())
		{
			ScoreDTO dto = new ScoreDTO();
			dto.setSid(rs.getInt("SID"));
			dto.setName(rs.getString("NAME"));
			dto.setKor(rs.getInt("KOR"));
			dto.setEng(rs.getInt("ENG"));
			dto.setMat(rs.getInt("MAT"));
			dto.setTot(rs.getInt("TOT"));
			dto.setAvg(rs.getInt("AVG"));
			
			result.add(dto);
		}
		
		//리소스 반환
		rs.close();
		pstmt.close();
		
		
		// 값 반환
		return result;
		
	}//end search(int sid)
	

	// 성적 수정
	public int update(ScoreDTO dto) throws SQLException
	{
		int result = 0;
		String sql = "UPDATE TBL_SCORE SET NAME = ?, KOR = ? , ENG = ?, MAT = ? WHERE SID = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, dto.getName());
		pstmt.setInt(2, dto.getKor());
		pstmt.setInt(3, dto.getEng());
		pstmt.setInt(4, dto.getMat());
		pstmt.setInt(5, dto.getSid());
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
		
		return result;
	}//end update(int sid)
	
	
	
	
	//성적 삭제
	public int delete(int sid) throws SQLException
	{
		int result = 0;
		String sql = "DELETE FROM TBL_SCORE WHERE SID = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, sid);
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
		return result;
				
	}//end delete(int sid) 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
