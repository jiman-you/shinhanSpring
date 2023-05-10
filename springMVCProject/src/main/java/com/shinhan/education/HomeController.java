package com.shinhan.education;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	//logger를 사용하면 나중에 보이지않게 설정가능
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		//model: data 를 저장하기 위한 객체 -request.setAttribute()와 동일
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("myname","지만-jiman");
		model.addAttribute("myage",20);
		model.addAttribute("mycar",new Car("BMW",123456));
		
		
		//viewResolver한테 준다
		//접두사+"home"+접미사
		//view가 결정 =>/WEB-INF/views/home/.jsp
		//view에서 modle을 이용해서 코드.
		return "home";
	}
	
}
