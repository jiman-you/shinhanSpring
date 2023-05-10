package com.shinhan.controller;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.shinhan.model.CompanyService;
import com.shinhan.model.EmpService;
import com.shinhan.vo.EmpVO;

@Controller 
@RequestMapping("/emp")
public class EmpController {

	Logger logger = LoggerFactory.getLogger(EmpController.class);
	
	@Autowired
	EmpService eService;
	@Autowired
	CompanyService comService;
	
	@RequestMapping("/emplist.do")
	public String empList(Model model,HttpServletRequest request) {
		Map<String, ?> flashMap =   
				RequestContextUtils.getInputFlashMap(request);
		if(flashMap !=null ) {
			Object message = flashMap.get("resultMessage");
			logger.info("(입력/삭제/수정에 대한 결과)Message:"+message);
		}
		
		List<EmpVO> emplist = eService.selectByCondition(new Integer[]{0},"",0.0,null);
		model.addAttribute("empAll",emplist);
		
		
		  model.addAttribute("deptList",comService.deptSelectALl()); 
		  model.addAttribute("jobList",comService.jobsSelectALl()); 
		  model.addAttribute("managerList",comService.managerSelectALl()); 
		return "emp/empSelect";
	}
	
	

	@RequestMapping(value = "/empinsert.do", method = RequestMethod.GET)
	public String registerget(HttpServletRequest reques,Model model) {
		  model.addAttribute("deptList",comService.deptSelectALl()); 
		  model.addAttribute("jobList",comService.jobsSelectALl()); 
		  model.addAttribute("managerList",comService.managerSelectALl()); 
		return "emp/empInsert";
	}

	
	

	  //HTML 폼과 자바빈 객체 //request의 parameter를 읽어서 vO를 new 해서 setter수행한다.
	  //@ModelAttribute view에게 데이터 전달
	  @RequestMapping(value="/empinsert.do",method=RequestMethod.POST) 
	  public String registerpost(@ModelAttribute("emp") EmpVO emp,String address,Model model
			  					,HttpServletRequest request,HttpSession session,
			  					RedirectAttributes redirectAttr) {
		 logger.info("emp:"+emp);
		 logger.info("address:"+address);
		
		String contextPaht=request.getContextPath();
		String method = request.getMethod();
		String remoteAddr = request.getRemoteAddr();
		
		session.setAttribute("userInfo", "세션에 사용자 정보 저장");
		
		logger.info("contextPaht: "+contextPaht);		
		logger.info("method: "+method);		
		logger.info("remoteAddr: "+remoteAddr);
		
		String result = eService.empInsert(emp);
		redirectAttr.addFlashAttribute("resultMessage",result);
		
		model.addAttribute("emp2",emp);
		model.addAttribute("address",address);
		
		
	  return "redirect:/emp/emplist.do";
	  }
	 
	  //상세보기
	  @RequestMapping(value="/empDetail.do",method=RequestMethod.GET)
	  public String empDetailMethod(int empid,Model model){
		  EmpVO emp = eService.selectById(empid);
		  model.addAttribute("deptList",comService.deptSelectALl()); 
		  model.addAttribute("jobList",comService.jobsSelectALl()); 
		  model.addAttribute("managerList",comService.managerSelectALl()); 
		  model.addAttribute("emp",emp);
		  return "emp/empDetail";
	  }
	  
	  //상세보기 수정
	  @RequestMapping(value="/empDetail.do",method=RequestMethod.POST)
	  public String empDetailPost(EmpVO emp,RedirectAttributes attr){
		  logger.info(emp.toString());
	
		  String result = eService.empUpdate(emp);
		  attr.addFlashAttribute("resultMessage",result);
		  
		  return "redirect:/emp/emplist.do";
	  }
	  
	  //삭제
	  @RequestMapping("/empDelete.do")
	  public String empDelete(int empid,RedirectAttributes attr) {
		  logger.info(empid+"");
		  
		  String result = eService.empDelete(empid);
		  attr.addFlashAttribute("resultMessage",result);
		  
		  return "redirect:/emp/emplist.do";
	  }
	  
	  
	  //@RequestMapping(value= "/empCondition.do",method = RequestMethod.GET)
	  //Ajax요청시 배열이 오면 @RequestParam("deptid[]")Integer[] deptid을 사용
	  //일단 요청시 배열이면 @RequestParam("deptid")Integer[] deptid
	  //일단 요청시 배열이면 Integer[] deptid
	  @GetMapping("/empCondition.do")
	  public String selectByCondition(@RequestParam("deptid[]")Integer[] deptid,String jobid,Double salary,Date hiredate, Model model){
		  logger.info(""+Arrays.toString(deptid));
		  logger.info(""+jobid);
		  logger.info(""+salary);
		  logger.info(""+hiredate);
		  List<EmpVO> emplist =  eService.selectByCondition(deptid, jobid, salary, hiredate);
		  model.addAttribute("empAll",emplist);
		  model.addAttribute("deptList",comService.deptSelectALl()); 
		  model.addAttribute("jobList",comService.jobsSelectALl()); 
	
		  
		  return "emp/empRetrieve";
	  }
	  
	  
	  /*
		 * @RequestMapping(value="/insert.do",method=RequestMethod.POST) public String
		 * registerpost(int employee_id,String first_name,String last_name,String email,
		 * String phone_number,int salary,int department_id,int manager_id, double
		 * commission_pct,Date hire_date,String job_id) {
		 * logger.info("employee_id"+employee_id); logger.info("first_name"+first_name);
		 * logger.info("last_name"+last_name); logger.info("email"+email);
		 * logger.info("phone_number"+phone_number); logger.info("salary"+salary);
		 * logger.info("department_id"+department_id);
		 * logger.info("manager_id"+manager_id);
		 * logger.info("commission_pct"+commission_pct);
		 * logger.info("hire_date"+hire_date); logger.info("job_id"+job_id);
		 * 
		 * return "emp/empInsert"; }
		 */
	// MAP이용--@RequestParam 필수
	/*
	 * @RequestMapping(value="/insert.do",method=RequestMethod.POST) public String
	 * registerpost(@RequestParam Map<String,String> map) {
	 * 
	 * for(String key:map.keySet()) { logger.info(key);
	 * logger.info(map.get(key).toString()); logger.info("------------"); }
	 * 
	 * return "emp/empInsert"; }
	 */
	//함수의 return타입
		//1)String : 페이지 이름을 리턴할때
		//2)ModelAndView : 모델+뷰
		@RequestMapping("/one")
		public ModelAndView test1(@RequestParam(name="my",required = false) String myname) {
			ModelAndView mv = new ModelAndView("sample/sampleView1");//이것도됨
			mv.addObject("subject","model and view 연습..."+myname);
			//mv.setViewName("sample/sampleView1");
			
			return mv;
		}
		
		//리다ㅇ렉트
		@RequestMapping("/two")
		public String test2(RedirectAttributes redirectAttr) {
			logger.info("재요청(redirect)함");
			redirectAttr.addFlashAttribute("resultMessage","얘는 리다이렉트 메세지입니다");
			
			return "redirect:/emp/insert.do";
		}
}
