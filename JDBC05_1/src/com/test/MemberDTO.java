package com.test;

public class MemberDTO
{
	// 주요 속성 구성
	
	// ID
	private int emp_id, city_id, buseo_id, jikwi_id;
	
	// TBL_EMP
	private String emp_name, ssn, ibsadate, tel;
	private int basicpay, sudang, sal;
	
	// TBL_CITY
	private String city_name;
	
	// TBL_BUSEO
	private String buseo_name;
	
	// TBL_JIKWI
	private String jikwi_name;
	private int min_basicpay;
	
	// getter / setter
	public int getEmp_id()
	{
		return emp_id;
	}
	public void setEmp_id(int emp_id)
	{
		this.emp_id = emp_id;
	}
	public int getCity_id()
	{
		return city_id;
	}
	public void setCity_id(int city_id)
	{
		this.city_id = city_id;
	}
	public int getBuseo_id()
	{
		return buseo_id;
	}
	public void setBuseo_id(int buseo_id)
	{
		this.buseo_id = buseo_id;
	}
	public int getJikwi_id()
	{
		return jikwi_id;
	}
	public void setJikwi_id(int jikwi_id)
	{
		this.jikwi_id = jikwi_id;
	}
	public String getEmp_name()
	{
		return emp_name;
	}
	public void setEmp_name(String emp_name)
	{
		this.emp_name = emp_name;
	}
	public String getSsn()
	{
		return ssn;
	}
	public void setSsn(String ssn)
	{
		this.ssn = ssn;
	}
	public String getIbsadate()
	{
		return ibsadate;
	}
	public void setIbsadate(String ibsadate)
	{
		this.ibsadate = ibsadate;
	}
	public String getTel()
	{
		return tel;
	}
	public void setTel(String tel)
	{
		this.tel = tel;
	}
	public int getBasicpay()
	{
		return basicpay;
	}
	public void setBasicpay(int basicpay)
	{
		this.basicpay = basicpay;
	}
	public int getSudang()
	{
		return sudang;
	}
	public void setSudang(int sudang)
	{
		this.sudang = sudang;
	}
	public int getSal()
	{
		return sal;
	}
	public void setSal(int sal)
	{
		this.sal = sal;
	}
	public String getCity_name()
	{
		return city_name;
	}
	public void setCity_name(String city_name)
	{
		this.city_name = city_name;
	}
	public String getBuseo_name()
	{
		return buseo_name;
	}
	public void setBuseo_name(String buseo_name)
	{
		this.buseo_name = buseo_name;
	}
	public String getJikwi_name()
	{
		return jikwi_name;
	}
	public void setJikwi_name(String jikwi_name)
	{
		this.jikwi_name = jikwi_name;
	}
	public int getMin_basicpay()
	{
		return min_basicpay;
	}
	public void setMin_basicpay(int min_basicpay)
	{
		this.min_basicpay = min_basicpay;
	}
}
