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

//DispatcherServlet-> ��û�� �޾Ƽ� Controlller�� ã�´�
@Controller
@RequestMapping("/first")//class-level �Ʒ� �޼��� level�� ���� ��û����
public class SampleController {
	
	Logger logger = LoggerFactory.getLogger(SampleController.class);
	
	@RequestMapping(value= {"/paramTest"},
					params = {"userid=hello","userpass","!email"})
	public String test5(Model model,@RequestParam String userid,@RequestParam String userpass,
			@RequestParam String email2,@RequestParam String address ,@RequestParam int age) {
		model.addAttribute("subject","�Ķ���Ϳ� ���� ��û--userid�� �ݵ�� hello userpass�� �����ؾ��ϰ� email�� �����ϸ� �ȵȴ�");
		logger.info(userid);
		logger.info(userpass);
		logger.info(email2);
		logger.info(address);
		logger.info("age:"+age);
		
		//�⺻�� ������
		return "sample/sampleView1";
	}

	@RequestMapping(value= {"/a.do","/b.go"},
					method=RequestMethod.GET)
	public String test4(Model model) {
		model.addAttribute("subject","��û �ּҰ� �������� ���");
		
		//�⺻�� ������
		return "sample/sampleView1";
	}

	@RequestMapping
	public String test3(Model model) {
		model.addAttribute("subject","class-level,method-level");

		//�⺻�� ������
		return "sample/sampleView1";
	}
	@RequestMapping("/sample1")//sample1�̶�� ��û�ϸ� �Ϸο´�
	public String test1(Model model) {
		logger.info("@@@@�˸�@@@@@@sample1��û�� ����@@@@@@@@@");
		logger.warn("@@@@���@@@@@@sample1��û�� ����@@@@@@@@@");
		model.addAttribute("subject","�����������ӿ�ũ");
		
		//�⺻�� ������
		return "sample/sampleView1";
	}
	
	@RequestMapping("/sample2")//sample1�̶�� ��û�ϸ� �Ϸο´�
	public ModelAndView test2() {
		logger.info("@@@@�˸�@@@@@@sample2��û�� ����@@@@@@@@@");
		logger.warn("@@@@���@@@@@@sample2��û�� ����@@@@@@@@@");
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("subject","��Ǫ��Ǫ���ӿ���-- modelandview�̿�");
		mv.setViewName("sample/sampleView1");
		
		//�⺻�� ������
		return mv;
	}
}
