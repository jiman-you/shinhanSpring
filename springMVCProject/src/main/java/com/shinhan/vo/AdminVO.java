package com.shinhan.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor@Getter
@Setter @ToString
//DTO,VO,JAVABeans : data를 저장해서 전송하기 위해
public class AdminVO {
	private String email;
	private String manager_name;
	private String pass;
	private String pic;
	public AdminVO(String email, String manager_name, String pass) {
		super();
		this.email = email;
		this.manager_name = manager_name;
		this.pass = pass;
	}
	
	
	
}
