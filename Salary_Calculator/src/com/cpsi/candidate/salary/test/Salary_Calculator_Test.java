/**
 * 
 */
package com.cpsi.candidate.salary.test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.cpsi.candidate.salary.Employee_Record;
import com.cpsi.candidate.salary.Salary_Calculator;

/**
 * @author dmansupreme
 *
 */
public class Salary_Calculator_Test 
{

	private List <Employee_Record> EmployeeRecords;
	private Salary_Calculator salaryCalculator;
	private int expectedSalaryByHourResult = 168; // 1475000/8760 = $168 per hour
	private int expectedSalaryByDailyResult = 4041; // 1475000/365 = $4,041 per day
	private int expectedSalaryByWeekResult = 28365; // 1475000/52 = $28,365 per week
	private int expectedSalaryByMonthResult = 122917; // 1475000/12 = $122,917 per month;
	
	/**
	 * Use the same Employee Records for all of the following tests
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUpBefore() throws Exception 
	{
		EmployeeRecords  = new ArrayList<Employee_Record>();
		loadRecordsFromFile();
		salaryCalculator =  new Salary_Calculator(EmployeeRecords);
		
	}

	/**
	 * Test method for {@link com.cpsi.candidate.salary.Salary_Calculator#Salary_Calculator(java.util.List)}.
	 */
	@Test
	public void testSalary_Calculator() 
	{
		assertNotNull(salaryCalculator);
	}

	/**
	 * Test method for {@link com.cpsi.candidate.salary.Salary_Calculator#getTotalSalary()}.
	 */
	@Test
	public void testGetTotalSalary() 
	{
		int totalFromTestFile = 1475000;
		assertEquals("The total salary from the test file must equal 1475000", totalFromTestFile, salaryCalculator.getTotalSalary());
	}

	/**
	 * Test method for {@link com.cpsi.candidate.salary.Salary_Calculator#getTotalSalaryByRole(java.lang.String)}.
	 */
	@Test
	public void testGetTotalSalaryByRole() 
	{
		int totalForSoftwareEngineerRole = 265000;
		String softwareEngineerRole = "Software Engineer";
		
		assertEquals("The total salary from the test file for Software Engineers must equal 265000", 
				totalForSoftwareEngineerRole, salaryCalculator.getTotalSalaryByRole(softwareEngineerRole));
	}

	/**
	 * Test method for {@link com.cpsi.candidate.salary.Salary_Calculator#getTotalSalaryByTime(java.lang.String)}.
	 */
	@Test
	public void testGetTotalSalaryByTime() 
	{
				
		int remainderTolerance = 1; //  Since we are dividing numbers that do not always returns ints, add a tolerance of 1
				
		assertEquals("The expected value was 168", expectedSalaryByHourResult, salaryCalculator.getTotalSalaryByTime("hOuRs"), 
				remainderTolerance);
		assertEquals("The expected value was 4041", expectedSalaryByDailyResult, salaryCalculator.getTotalSalaryByTime("DAys"), 
				remainderTolerance);
		assertEquals("The expected value was 28365", expectedSalaryByWeekResult, salaryCalculator.getTotalSalaryByTime("WEEKS"), 
				remainderTolerance);
		assertEquals("The expected value was 122917", expectedSalaryByMonthResult, salaryCalculator.getTotalSalaryByTime("months"), 
				remainderTolerance);
		
	}

	/**
	 * Test method for {@link com.cpsi.candidate.salary.Salary_Calculator#getTotalSalaryByTimeAndRole(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testGetTotalSalaryByTimeAndRole() 
	{
		String softwareEngineerRole = "Software Engineer";
		int remainderTolerance = 1; //  Since we are dividing numbers that do not always returns ints, add a tolerance of 1
		
		// 265000/8760 = $1580 Rounded
		int expectedSoftwareEngineerByHours = 30;
		assertEquals("The expected value was 30", expectedSoftwareEngineerByHours, 
				salaryCalculator.getTotalSalaryByTimeAndRole("HouRS", softwareEngineerRole), 
				remainderTolerance);
	}

	
	@Test
	public void testGetCombinationsOfMeanSalariesByLimit() 
	{
		int arrayLength = 2;
		int salaryLimit = 120000; 
		
		List <Employee_Record> groupsOfEmployees = salaryCalculator.getCombinationsOfMeanSalariesByLimit(salaryLimit, EmployeeRecords);
		
		assertEquals(arrayLength, groupsOfEmployees.size());
	}
	
	/**
	 * No reinventing the wheel, read in a file and parse it out
	 * @throws IOException
	 */
	private void loadRecordsFromFile() throws IOException
	{
		String inputTestFile = "src/test/resources/EmployeeRecordCSV.csv";
		String splitByComma = ",";
		String line = "";
		Employee_Record employeeRecord;
		
		BufferedReader br = new BufferedReader(new FileReader(inputTestFile));
		
		while ((line = br.readLine()) != null)
		{
		
			String[] employees = line.split(splitByComma);
						
			employeeRecord = new Employee_Record();
			
			for(int i = 0; i < employees.length; i++)
			{
				if(i == 0) { employeeRecord.setName(employees[i]); }
				if(i == 1) { employeeRecord.setDOB(employees[i]);	 }
				if(i == 2) { employeeRecord.setAnnual_Salary(Integer.parseInt(employees[i])); 
				}
				if(i == 3) { employeeRecord.setRole(employees[i]); }
				
			}
			
			EmployeeRecords.add(employeeRecord);
			
		}
		
		/*
		 * Diagnostic code
		System.out.println("Employee Record length" + EmployeeRecords.size());
		
		for(Employee_Record employee_Record : EmployeeRecords)
		{
			System.out.println(employee_Record.getName());
			System.out.println(employee_Record.getDOB());
			System.out.println(employee_Record.getAnnual_Salary());
			System.out.println(employee_Record.getRole());
		}
		*/
		br.close();
	}

}
