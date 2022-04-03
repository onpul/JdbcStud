/*==================
 MemberDTO.java 
==================*/

package com.test;

public class MemberDTO
{
	// 주요 속성 구성
	private String sid, name, tel;
	
	// 마우스 오른쪽 - 소스 - 제너레이트 게터즈 앤 세터즈
	public String getSid()
	{
		return sid;
	}

	public void setSid(	String sid)
	{
		this.sid = sid;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getTel()
	{
		return tel;
	}

	public void setTel(String tel)
	{
		this.tel = tel;
	}
}


/* 내 풀이 
 
// 게터 세터...

package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.util.DBConn;

public class MemberDTO
{
	public MemberDTO() throws ClassNotFoundException, SQLException
	{
		Connection conn = DBConn.getConnection();
		
		if (conn != null)
		{
			try
			{
				// 작업 객체 생성
				Statement stmt = conn.createStatement();
				
				// 쿼리문 준비
				String sql = "SELECT SID, NAME, TEL FROM TBL_MEMBER ORDER BY SID";
				
				// 쿼리문 실행
				ResultSet rs = stmt.executeQuery(sql);
				
				// 출력 
				System.out.println("-------------------------------");
				
				// ResultSet에 대한 처리
				while (rs.next())
				{
					String sid = rs.getString("SID");
					String name = rs.getString("NAME");
					String tel = rs.getString("TEL");
					
					String str = String.format("%3s %8s %12s", sid, name, tel);
					
					System.out.println(str);
				}
				
				// ResultSet 리소스 반납
				rs.close();
				
				// Statement 리소스 반납
				stmt.close();
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
			
			DBConn.close();
		}
	}
}
*/
