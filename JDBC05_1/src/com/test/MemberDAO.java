package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.util.DBConn;

public class MemberDAO
{
	// 주요 속성 구성
	private Connection conn; 
	
	
	// 데이터베이스 연결 메소드
	public Connection connection()
	{
		conn = DBConn.getConnection();
		
		return conn;
	}
	
	
	// 데이터 입력 담당 메소드
	public int add(MemberDTO dto) throws SQLException
	{
		int result = 0;
		Statement stmt = conn.createStatement();
		String sql = String.format("INSERT INTO TBL_EMP (EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_ID, TEL, BUSEO_ID, JIKWI_ID, BASICPAY, SUDANG)"
								+ " VALUES (EMPSEQ.NEXTVAL, '%s', '%s', '%s', %d, '%s', %d, %d, %d, %d)"
				                  , dto.getEmp_name(), dto.getSsn(), dto.getIbsadate(), dto.getCity_id(), dto.getTel()
				                  , dto.getBuseo_id(), dto.getJikwi_id(), dto.getBasicpay(), dto.getSudang());
		result = stmt.executeUpdate(sql);
		stmt.close();
		return result;
	}
	
	
	// 인원 수 확인 메소드
	public int count() throws SQLException 
	{
		int result = 0;
		Statement stmt = conn.createStatement();
		String sql = "SELECT COUNT(*) AS COUNT FROM TBL_EMP";
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			result = rs.getInt("COUNT");
		}
		
		rs.close();
		stmt.close();
		
		return result;
	}
	
	
	// 레코드 수 확인 메소드
	public int count(String tablename) throws SQLException 
	{
		int result = 0;
		Statement stmt = conn.createStatement();
		String sql = tablename;
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			result = rs.getInt("COUNT");
		}
		
		rs.close();
		stmt.close();
		
		return result;
	}
		
	
	// 리스트 출력 담당 메소드 - 사번, 이름, 부서, 직위 정렬
	public ArrayList<MemberDTO> lists(int kind) throws SQLException 
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		Statement stmt = conn.createStatement();
		String sql = String.format("SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_NAME, TEL, BUSEO_NAME, JIKWI_NAME, BASICPAY, SUDANG"
							   + ", NVL(BASICPAY, 0) + NVL(SUDANG, 0) AS SAL"
							   + " FROM TBL_EMP E JOIN TBL_CITY C ON E.CITY_ID = C.CITY_ID"
							   + " JOIN TBL_BUSEO B ON E.BUSEO_ID = B.BUSEO_ID"
							   + " JOIN TBL_JIKWI J ON E.JIKWI_ID = J.JIKWI_ID");
		
		switch (kind)
		{
			case 1: sql = sql + " ORDER BY EMP_ID ASC"; break;
			case 2: sql = sql + " ORDER BY EMP_NAME ASC"; break;
			case 3: sql = sql + " ORDER BY BUSEO_ID ASC"; break;
			case 4: sql = sql + " ORDER BY JIKWI_ID ASC"; break;
			case 5: sql = sql + " ORDER BY NVL(BASICPAY, 0) + NVL(SUDANG, 0) DESC"; break;
		}
		
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			
			dto.setEmp_id(rs.getInt("EMP_ID"));
			dto.setEmp_name(rs.getString("EMP_NAME"));
			dto.setSsn(rs.getString("SSN"));
			dto.setIbsadate(rs.getString("IBSADATE"));
			dto.setCity_name(rs.getString("CITY_NAME"));
			dto.setTel(rs.getString("TEL"));
			dto.setBuseo_name(rs.getString("BUSEO_NAME"));  
			dto.setJikwi_name(rs.getString("JIKWI_NAME"));
			dto.setBasicpay(rs.getInt("BASICPAY"));
			dto.setSudang(rs.getInt("SUDANG"));
			dto.setSal(rs.getInt("SAL"));
			
			result.add(dto);			
		}
		rs.close();
		stmt.close();
		
		return result;
	}
	
	
	// 리스트 출력 담당 메소드 - 검색 출력
	public ArrayList<MemberDTO> lists(int kind, String search) throws SQLException 
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		Statement stmt = conn.createStatement();
		String sql = String.format("SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_NAME, TEL, BUSEO_NAME, JIKWI_NAME, BASICPAY, SUDANG"
				+ ", NVL(BASICPAY, 0) + NVL(SUDANG, 0) AS SAL"
				+ " FROM TBL_EMP E JOIN TBL_CITY C ON E.CITY_ID = C.CITY_ID"
				+ " JOIN TBL_BUSEO B ON E.BUSEO_ID = B.BUSEO_ID"
				+ " JOIN TBL_JIKWI J ON E.JIKWI_ID = J.JIKWI_ID");
		
		switch (kind)
		{
			case 1: sql = sql + String.format(" WHERE E.EMP_ID = %s", search); break;
			case 2: sql = sql + String.format(" WHERE E.EMP_NAME = '%s'", search); break;
			case 3: sql = sql + String.format(" WHERE B.BUSEO_NAME = '%s'", search); break;
			case 4: sql = sql + String.format(" WHERE J.JIKWI_NAME = '%s'", search); break;
		}
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			
			dto.setEmp_id(rs.getInt("EMP_ID"));
			dto.setEmp_name(rs.getString("EMP_NAME"));
			dto.setSsn(rs.getString("SSN"));
			dto.setIbsadate(rs.getString("IBSADATE"));
			dto.setCity_name(rs.getString("CITY_NAME"));
			dto.setTel(rs.getString("TEL"));
			dto.setBuseo_name(rs.getString("BUSEO_NAME"));  
			dto.setJikwi_name(rs.getString("JIKWI_NAME"));
			dto.setBasicpay(rs.getInt("BASICPAY"));
			dto.setSudang(rs.getInt("SUDANG"));
			dto.setSal(rs.getInt("SAL"));
			
			result.add(dto);			
		}
		rs.close();
		stmt.close();
		
		return result;
	}
	
	
	// 레코드 목록 출력 메소드
	
	/*
	public ArrayList<MemberDTO> recordlists(String tablename, int i) throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		Statement stmt = conn.createStatement();
		
		String sql = String.format("SELECT CITY_NAME FROM '%s' WHERE CITY_ID = %d", tablename, i);
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			
			dto.setCity_name(rs.getString("CITY_NAME"));
			
			result.add(dto);
		}
		rs.close();
		stmt.close();
		
		return result;
	}
	
	
	public ArrayList<MemberDTO> recordlists(int i) throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		Statement stmt = conn.createStatement();
		
		String sql = String.format("SELECT CITY_NAME FROM TBL_CITY WHERE CITY_ID = %d", i);
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			
			dto.setCity_name(rs.getString("CITY_NAME"));
			
			result.add(dto);
		}
		rs.close();
		stmt.close();
		
		return result;
	}
	*/
	
	
	// 기본급 출력 메소드
	/*
	public void minbasicpay(String jikwi_name) throws SQLException
	{
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		
		System.out.print("(최소 ");
		String sql = String.format("SELECT * FROM TBL_JIKWI WHERE JIKWI_NAME = %s", jikwi_name);
		rs = stmt.executeQuery(sql);
		System.out.print(" 이상)");
		
		rs.close();
		stmt.close();
	}
	*/
	
	
	// 데이터 수정 메소드
	public int modify(MemberDTO dto) throws SQLException
	{
		int result = 0;
		Statement stmt = conn.createStatement();
		
		System.out.println(dto.getSsn());
		
		String sql = String.format("UPDATE TBL_EMP SET EMP_NAME='%s'"
				+ ", SSN='%s', IBSADATE=TO_DATE('%s', 'YYYY-MM-DD')"
				+ ", CITY_ID=(SELECT CITY_ID FROM TBL_CITY WHERE CITY_NAME ='%s')"
				+ ", TEL='%s'"
				+ ", BUSEO_ID=(SELECT BUSEO_ID FROM TBL_BUSEO WHERE BUSEO_NAME = '%s')"
				+ ", JIKWI_ID=(SELECT JIKWI_ID FROM TBL_JIKWI WHERE JIKWI_NAME = '%s')"
				+ ", BASICPAY=%d, SUDANG=%d WHERE EMP_ID = %d"
				, dto.getEmp_name(), dto.getSsn(), dto.getIbsadate()
				, dto.getCity_name(), dto.getTel(), dto.getBuseo_name()
				, dto.getJikwi_name(), dto.getBasicpay(), dto.getSudang()
				, dto.getEmp_id());
		result = stmt.executeUpdate(sql);
		stmt.close();
		
		return result;	
	}
	
	
	// 데이터 삭제 메소드
	public int remove(int emp_id) throws SQLException
	{
		int result = 0;
		
		Statement stmt = conn.createStatement();
		String sql = String.format("DELETE FROM TBL_EMP WHERE EMP_ID=%d", emp_id);
		result = stmt.executeUpdate(sql);
		stmt.close();
				
		return result;
	}
	
	// 데이터베이스 연결 종료 메소드
	public void close() 
	{
		DBConn.close();
	}
}
