package com.cpsi.candidate.salary.runner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.cpsi.candidate.salary.Employee_Record;
import com.cpsi.candidate.salary.Salary_Calculator;

public class SalaryRunner 
{

	private static List <Employee_Record> EmployeeRecords = new ArrayList<Employee_Record>(); 
	
	public static void main(String[] args) throws IOException 
	{
		if(args.length != 1)
		{
			System.err.println("Please supply a single file for input");
			System.exit(1);
			
		}
		else
		{
			System.out.println("First argument in the input");
			System.out.println(args[0]);
			
			loadRecordsFromFile(args[0]);
			
			Salary_Calculator salaryCalculator = new Salary_Calculator(EmployeeRecords);
			
			System.out.println(salaryCalculator.getTotalSalary());
			System.out.println(salaryCalculator.getTotalSalaryByRole("Software Engineer"));
			System.out.println(salaryCalculator.getTotalSalaryByRole("Computer Programmer"));
			System.out.println(salaryCalculator.getTotalSalaryByRole("Software Design Engineer"));
			System.out.println(salaryCalculator.getTotalSalaryByTime("hours"));
			System.out.println(salaryCalculator.getTotalSalaryByTime("days"));
			System.out.println(salaryCalculator.getTotalSalaryByTime("weeks"));
			System.out.println(salaryCalculator.getTotalSalaryByTime("months"));
			System.out.println(salaryCalculator.getTotalSalaryByTimeAndRole("hours", "Software Engineer"));
			System.out.println(salaryCalculator.getTotalSalaryByTimeAndRole("days", "Software Engineer"));
			System.out.println(salaryCalculator.getTotalSalaryByTimeAndRole("weeks", "Software Design Engineer"));
			System.out.println(salaryCalculator.getTotalSalaryByTimeAndRole("months", "Software Design Engineer"));
			
			System.out.println("Possible combinations of salaries that is less than or equal to " + 150000 + " " +
					salaryCalculator.getCombinationsOfMeanSalariesByLimit(150000, EmployeeRecords).size());
		}

	}

	/**
	 * No reinventing the wheel, read in a file and parse it out
	 * @throws IOException
	 */
	private static void loadRecordsFromFile(String inputTestFile) throws IOException
	{
		
		String splitByComma = ",";
		String line = "";
		Employee_Record employeeRecord;
		
		BufferedReader br = new BufferedReader(new FileReader(inputTestFile));
		
		while ((line = br.readLine()) != null)
		{
		
			System.out.println(line);
			
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
