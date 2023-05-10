package com.shinhan.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class DeptVO {
	 private int department_id;
	 private String department_name;
	 private int manager_id;
	 private int location_id;
	 
	 
}
