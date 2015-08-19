/**
 * 
 */
package com.cpsi.candidate.salary;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Derek J. Short, CPSI Team member Candidate
 *
 * Object designed to do various salary calculations on a group of Employee Records
 * 
 */
public class Salary_Calculator 
{

	private int totalSalary = 0;
	private List <Employee_Record> EmployeeRecords;
	
	public Salary_Calculator(List <Employee_Record> EmployeeRecords)
	{
		//  I like to use the . notation that comes with using the keyword "this"
		this.EmployeeRecords = EmployeeRecords;
	}
	
	/**
	 * General method to calculate the total annual salary of all the employee records
	 * 
	 * @return The total salary of all the employees
	 */
	public int getTotalSalary()
	{
		System.out.println(totalSalary);
		
		for(Employee_Record employee_Record : EmployeeRecords)
		{			
			totalSalary += employee_Record.getAnnual_Salary();
		}
		
		return totalSalary;
	}
	
	/**
	 *  Method to get total annual salary based on job role
	 * @param Role the possible job roles listed in the employee records
	 * @return The total salary of all the employees who share the same role
	 */
	public int getTotalSalaryByRole(String Role)
	{
			
		System.out.println("Please make sure your role is present before searching.");
		
		for(Employee_Record employee_Record : EmployeeRecords)
		{
			if(employee_Record.getRole().equalsIgnoreCase(Role))
			{
				totalSalary += employee_Record.getAnnual_Salary();
			}
		}
		
		return totalSalary;
	}
	
	/**
	 * Method to get the total annual salary based on time unit 
	 * @param timeUnit - a string where the timeUnit is a variable of hour, week, month, or year
	 * @return the total salary of all employees divided by the time unit
	 */
	public int getTotalSalaryByTime(String timeUnit)
	{
		int hoursInANonLeapYear = 8760;
		int daysInANonLeapYear = 365;
		int weeksInAYear = 52;
		int monthsInAYear = 12;
		totalSalary = 0;
		totalSalary = this.getTotalSalary();
		
		timeUnit = timeUnit.toLowerCase();
		
		switch(timeUnit)
		{
			case "hours":
				System.err.println("Case is hours");
				totalSalary = totalSalary/hoursInANonLeapYear;
				break;
			case "days":
				System.err.println("Case is days");
				totalSalary = totalSalary/daysInANonLeapYear;
				break;
			case "weeks":
				System.err.println("Case is weeks");
				totalSalary = totalSalary/weeksInAYear;
				break;
			case "months":
				System.err.println("Case is months");
				totalSalary = totalSalary/monthsInAYear;
				break;
			default:
				System.err.println("timeUnit paramater must be a variable of hours, days, weeks, or months");
		}
		
		System.err.println(totalSalary);
		return totalSalary;
	}
	
	/**
	 * Method to get the total salary group by role and divided by the time unit
	 * @param timeUnit - a string where the timeUnit is a variable of hour, week, month, or year
	 * @param Role
	 * @return the total salary of all employees who share the same job role divided by the input timeUnit
	 */
	public int getTotalSalaryByTimeAndRole(String timeUnit, String Role)
	{
		
		totalSalary = 0;
		
		int hoursInANonLeapYear = 8760;
		int daysInANonLeapYear = 365;
		int weeksInAYear = 52;
		int monthsInAYear = 12;
		
		totalSalary = this.getTotalSalaryByRole(Role);
		
		timeUnit = timeUnit.toLowerCase();
		
		switch(timeUnit)
		{
			case "hours":
				System.err.println("Case is hours");
				totalSalary = totalSalary/hoursInANonLeapYear;
				break;
			case "days":
				System.err.println("Case is days");
				totalSalary = totalSalary/daysInANonLeapYear;
				break;
			case "weeks":
				System.err.println("Case is weeks");
				totalSalary = totalSalary/weeksInAYear;
				break;
			case "months":
				System.err.println("Case is months");
				totalSalary = totalSalary/monthsInAYear;
				break;
			default:
				System.err.println("timeUnit paramater must be a variable of hours, days, weeks, or months");
		}
		
		return totalSalary;
	}
	
	/**
	 	Find a group of individual salaries that come as close to the salary limit without going over it
	 	@param salaryLimit - The limit that the total salaries either have to be less than or be equal to
	 */
	public List <Employee_Record> getCombinationsOfMeanSalariesByLimit(int salaryLimit, List <Employee_Record> totalEmployees)			
	{
		/*
		 * This method was a best attempt given my remaining time.
		 * The intention was to find combinations of salaries that could equal up to the salary limit.
		 * 
		 *  If the first input happen to be equal to the salary limit, but the next ten together could equal the salary limit,
		 *  then a false positive would have been given.
		 *  
		 *  I had idea of creating a Comparator and sorting the salaries, but I simply ran out of time.
		 */
		List <Employee_Record> groupsOfEmployees = new ArrayList<Employee_Record>();
		
		int groupSalary = 0;
		
		for(int j = 0; j  < totalEmployees.size(); j++)
		{
			// System.err.println("Group Salary " + groupSalary);
			
			if(groupSalary < totalEmployees.get(j).getAnnual_Salary() && groupSalary < salaryLimit)
			{
					
				groupSalary += totalEmployees.get(j).getAnnual_Salary();
				
				if(groupSalary <= salaryLimit)
				{
					System.err.println(totalEmployees.get(j).getName() + " " +totalEmployees.get(j).getAnnual_Salary());
				
					groupsOfEmployees.add(totalEmployees.get(j));
					System.err.println("Element removed " + totalEmployees.remove(j).getName());
				}
				else
				{
					groupSalary -= totalEmployees.get(j).getAnnual_Salary();
				}
			}
		}
		
		if(totalEmployees.size() > 2)
		{
			System.err.println("total employees size " + totalEmployees.size());
			groupsOfEmployees.addAll(getCombinationsOfMeanSalariesByLimit(salaryLimit, totalEmployees));			
		}
			
		return groupsOfEmployees;
			
	}
		
}
