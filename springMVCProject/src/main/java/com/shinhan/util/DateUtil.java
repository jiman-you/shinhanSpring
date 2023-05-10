package com.shinhan.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {

	public static Date convertToDate(String hdate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //SimpleDateFormat 객체에 날짜타입
		Date sqlDate = null;//sql 타입의 Date 사용 위에 임포트
		try {
			java.util.Date d = sdf.parse(hdate);//util타입의 Date 사용- 두개의 Date를 쓸 수없어 명시적으로 선언
												//sdf 타입으로 받은 문자열parse
			sqlDate = new Date(d.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return sqlDate;
	}
}
