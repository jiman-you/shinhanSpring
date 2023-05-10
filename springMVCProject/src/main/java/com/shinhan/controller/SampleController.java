package com.shinhan.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

//DispatcherServlet-> 요청을 받아서 Controlller를 찾는다
@Controller
@RequestMapping("/first")//class-level 아래 메서드 level의 공통 요청사함
public class SampleController {
	
	Logger logger = LoggerFactory.getLogger(SampleController.class);
	
	@RequestMapping(value= {"/paramTest"},
					params = {"userid=hello","userpass","!email"})
	public String test5(Model model,@RequestParam String userid,@RequestParam String userpass,
			@RequestParam String email2,@RequestParam String address ,@RequestParam int age) {
		model.addAttribute("subject","파라메터에 의한 요청--userid는 반드시 hello userpass는 존재해야하고 email은 존재하면 안된다");
		logger.info(userid);
		logger.info(userpass);
		logger.info(email2);
		logger.info(address);
		logger.info("age:"+age);
		
		//기본이 포워드
		return "sample/sampleView1";
	}

	@RequestMapping(value= {"/a.do","/b.go"},
					method=RequestMethod.GET)
	public String test4(Model model) {
		model.addAttribute("subject","요청 주소가 여러개인 경우");
		
		//기본이 포워드
		return "sample/sampleView1";
	}

	@RequestMapping
	public String test3(Model model) {
		model.addAttribute("subject","class-level,method-level");

		//기본이 포워드
		return "sample/sampleView1";
	}
	@RequestMapping("/sample1")//sample1이라고 요청하면 일로온다
	public String test1(Model model) {
		logger.info("@@@@알림@@@@@@sample1요청을 받음@@@@@@@@@");
		logger.warn("@@@@경고@@@@@@sample1요청을 받음@@@@@@@@@");
		model.addAttribute("subject","스프링프레임워크");
		
		//기본이 포워드
		return "sample/sampleView1";
	}
	
	@RequestMapping("/sample2")//sample1이라고 요청하면 일로온다
	public ModelAndView test2() {
		logger.info("@@@@알림@@@@@@sample2요청을 받음@@@@@@@@@");
		logger.warn("@@@@경고@@@@@@sample2요청을 받음@@@@@@@@@");
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("subject","스푸링푸레임워쿠-- modelandview이용");
		mv.setViewName("sample/sampleView1");
		
		//기본이 포워드
		return mv;
	}
}
