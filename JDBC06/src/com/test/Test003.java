/* =====================
Test003.java
- 쿼리문 전송 실습
======================= */
package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.util.DBConn;

public class Test003
{
	public static void main(String[] args)
	{
		try
		{
			Connection conn = DBConn.getConnection();
			
			if (conn != null)
			{
				System.out.println("데이터베이스 연결 성공~!!!");
			
				try
				{
					//쿼리문 준비
					String sql = "SELECT SID, NAME, TEL"
							+ " FROM TBL_MEMBER"
							+ " ORDER BY SID";
					
					//작업 객체 생성(쿼리문 전달)
					PreparedStatement pstmt = conn.prepareStatement(sql);
					
					
					//쿼리문 실행
					ResultSet rs = pstmt.executeQuery();
					

					while (rs.next())
					{
						int sid = rs.getInt("SID");
						String name = rs.getString("NAME");
						String tel = rs.getString("TEL");
						
						String str = String.format("%3d %7s %10s", sid,name,tel);
						
						System.out.println(str);
					}
					rs.close();
					pstmt.close();
					
				} catch (Exception e)
				{
					System.out.println(e.toString());
				}
			
			
			}
		} catch (Exception e)
		{
			System.out.println(e.toString());
			
		}
		DBConn.close();
		System.out.println("\n 데이터베이스 연결 닫힘 ~!!!");
		System.out.println("프로그램 종료됨~!!!");
	}
}
/*
데이터베이스 연결 성공~!!!
  1     이호석 010-1111-1111
  2     남주혁 010-1234-1234
  3     백이진 010-5555-2222
  4     김문어 010-4444-4444
  5     오이삭 010-2222-2222
  6     김태리 010-6666-6666
  7     시조새 010-4444-2222
  8      ..          .
  9       .          .
 10     신하리 010-4343-4343
 11     강태무 010-5555-5555
 12     김상기 010-4444-4444
 13     김충희 010-5555-5555
 14     변백현 010-6666-6666
 30     정금정 010-3030-3030
 31     오세훈 010-3131-3131

 데이터베이스 연결 닫힘 ~!!!
프로그램 종료됨~!!!
*/
 