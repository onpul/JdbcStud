/* ====================
DBConn.java
- try ~ catch
==================== */
package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn
{
	static private Connection dbconn;
	
	public static  Connection getConnection()
	{
		if (dbconn == null)
		{
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "scott";
			String pw = "tiger";
			
			
			try
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				dbconn = DriverManager.getConnection(url, user, pw);
			}
			catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
		return dbconn;
	}
	
	
	
	
	
	
	
	public static Connection getConnection(String url, String user, String pw)
	{
		if (dbconn == null)
		{
			try
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				dbconn = DriverManager.getConnection(url, user, pw);
				 
			}
			catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
		return dbconn;
	}
	
	
	
	
	
	
	public static void close()
	{
		try
		{
			if (dbconn != null)
			{
				if (!dbconn.isClosed())
				{
					dbconn.close();
				}
			}
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		dbconn = null;
	}
}
