/*=====================
	DBConn.java
=====================*/


package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn
{
	// 변수 선언
	private static Connection dbConn; // 추상적인 연결
	
	// 메소드 정의 -> 연결
	public static Connection getConnection() // 구체적인 연결
	{
		if (dbConn == null) // 연결되지 않은 경우에만 연결(싱글톤)
		{
			try
			{
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				String user = "scott";
				String pwd = "tiger";
				
				Class.forName("oracle.jdbc.driver.OracleDriver");
				dbConn = DriverManager.getConnection(url, user, pwd);// 오라클 서버 실제 연결
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
				// 오라클 연결 실패 시 오류 출력
			}
		}
		
		return dbConn; // 구성된 연결 객체 반환
	}// end getConnection()
	
	public static Connection getConnection(String url, String user, String pwd)
	{
		if (dbConn == null)
		{
			try
			{
				Class.forName("oraclel.jdbc.driver.OracleDriver");
				dbConn = DriverManager.getConnection(url, user, pwd);
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
		
		return dbConn;
		
	}// end getConnection(String url, String user, String pwd)
	
	public static void close() // 연결 종료 
	{
		if (dbConn != null)
		{
			try
			{
				if (!dbConn.isClosed())
				{
					dbConn.close();
				}
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
		
		dbConn = null; // 연결 객체 초기화
	}// end close()
}// end class DBConn
