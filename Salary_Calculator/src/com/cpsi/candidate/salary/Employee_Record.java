/**
 * 
 */
package com.cpsi.candidate.salary;

/**
 * @author Derek J. Short, CPSI Teammember Candidate
 * 
 * Object designed to encapsulate the values of an Employee Record
 *
 */
public class Employee_Record 
{
	
	private String Name;
	private String DOB;
	private int Annual_Salary;
	private String Role;
	
	/**
	 *  @return A Employee's full name.
	 */
	public String getName() 
	{
		return Name;
	}
	
	/**
	 * 
	 * @return Date Of Birth as java.util.Date object.
	 */
	public String getDOB() 
	{
		return DOB;
	}
	
	/**
	 * 
	 * @return Annual salary as a single value, IE, 100000 for $100,000.
	 */
	public int getAnnual_Salary() 
	{
		return Annual_Salary;
	}
	
	/**
	 * 
	 * @return Job title, role
	 */
	public String getRole() 
	{
		return Role;
	}
	
	public void setName(String name) 
	{
		Name = name;
	}
	
	public void setDOB(String dOB) 
	{
		DOB = dOB;
	}
	
	public void setAnnual_Salary(int annual_Salary) 
	{
		Annual_Salary = annual_Salary;
	}
	
	public void setRole(String role) 
	{
		Role = role;
	}

}
