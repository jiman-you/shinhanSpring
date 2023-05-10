package com.shinhan.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//JavaBeans
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmpVO {//value object
	private int employee_id;
	private String first_name;
	private String last_name;
	private String email;
	private String phone_number;
	private Date hire_date;
	private String job_id;
	private Double salary;
	private double commission_pct;
	private int manager_id;
	private Integer department_id;
}
