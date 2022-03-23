/*=====================
	ScoreDTO.java
=====================*/


package com.test;

public class ScoreDTO
{
	// 주요 속성 구성
	private String sid, name, kor, eng, mat;

	// 게터 세터 구성
	public String getSid()
	{
		return sid;
	}

	public void setSid(String sid)
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

	public String getKor()
	{
		return kor;
	}

	public void setKor(String kor)
	{
		this.kor = kor;
	}

	public String getEng()
	{
		return eng;
	}

	public void setEng(String eng)
	{
		this.eng = eng;
	}

	public String getMat()
	{
		return mat;
	}

	public void setMat(String mat)
	{
		this.mat = mat;
	}
}
