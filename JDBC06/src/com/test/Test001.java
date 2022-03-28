/* =====================
Test001.java
- 쿼리문 전송 실습
======================= */
package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import com.util.DBConn;

public class Test001
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
					/* 
					<기존 statement 사용했던 방법>
					Statement stmt = conn.createStatement();
					
					String sql = "INSERT INTO TBL_MEMBER(SID,NAME,TEL)"
							+ " VALUES(MEMBERSEQ.NEXTVAL, '김충희','010-5555-5555')";
					
					int result = stmt.executeUpdate(sql);
					
					if (result > 0)
						System.out.println("데이터 입력 성공~!!!");
					
					stmt.close();
					DBConn.close();
					*/
					
					
					// 쿼리문 준비
					// 『?』으로 쿼리문 채우기
					// 『?』는 나중에 줄 테니까 자리 만들어 둬 ~ 하고 
					// 작업 객체에게 매개변수로 주기.
					String sql = "INSERT INTO TBL_MEMBER(SID,NAME,TEL)"
							+ " VALUES(MEMBERSEQ.NEXTVAL, ?,?)";
							//+ " VALUES(MEMBERSEQ.NEXTVAL, '?','?')";
							//※ 이거 절대 아님!. 포맷팅이랑 다름!!
							//   문자열인지 숫자인지 여기서는 다 물음표임
							//   그걸 정하는건 나중에 IN 매개변수 넘겨주기 에서!!
					
					
					
					// 작업 객체 생성
					// prepareStatement()의 매개변수 sql
					// 미리 sql 쿼리문 준비해야함.
					PreparedStatement pstmt = conn.prepareStatement(sql);
					
					
					// IN 매개변수 넘겨주기
					// 첫 번째로 나온 물음표에 변백현을 넣을거야.
					pstmt.setString(1, "변백현");
					// 두 번째로 나온 물음표에 010-6666-6666을 넣을거야.
					pstmt.setString(2, "010-6666-6666");
					
					
					
					// 쿼리문 실행
					// Statement 와 다르게 매개변수로 sql넘겨주지 않는다.
					// 이미 넘겨줌.
					int result = pstmt.executeUpdate();
					
					if (result > 0)
						System.out.println("데이터 입력 성공~!!!");
					
					pstmt.close();
					DBConn.close();
				}
				catch (Exception e)
				{
					System.out.println(e.toString());
				}
			}
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}

}
/*
데이터베이스 연결 성공~!!!
데이터 입력 성공~!!!
*/
