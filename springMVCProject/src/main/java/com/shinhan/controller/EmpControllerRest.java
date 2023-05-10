package com.shinhan.controller;

import java.sql.Date;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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

@RestController //@controller + @ResponseBody
@RequestMapping("/restemp")
public class EmpControllerRest {

	Logger logger = LoggerFactory.getLogger(EmpControllerRest.class);
	
	@Autowired
	EmpService eService;
	@Autowired
	CompanyService comService;
	
	@RequestMapping(value = "/emplist.do",
					produces = "application/json;charset=utf-8")
	public Map<String, Object> empList() {
		Map<String, Object> returnMap = new HashMap<String, Object>();   
		
		List<EmpVO> emplist = eService.selectByCondition(new Integer[]{0},"",0.0,null);
		returnMap.put("emplist", emplist);
		
		return returnMap;
	}
	
	

	@RequestMapping(value = "/empinsert.do", method = RequestMethod.GET)
	public String registerget(HttpServletRequest reques,Model model) {
		  model.addAttribute("deptList",comService.deptSelectALl()); 
		  model.addAttribute("jobList",comService.jobsSelectALl()); 
		  model.addAttribute("managerList",comService.managerSelectALl()); 
		return "emp/empInsert";
	}

	
	

	  //HTML ���� �ڹٺ� ��ü //request�� parameter�� �о vO�� new �ؼ� setter�����Ѵ�.
	  //@ModelAttribute view���� ������ ����
	  @RequestMapping(value="/empinsert.do",
			  			consumes = MediaType.APPLICATION_JSON_VALUE,
			  			produces = "text/plain;charset=utf-8",
			  			method= RequestMethod.POST) 
	  public String registerpost(@RequestBody EmpVO emp) {
		 logger.info("emp:"+emp.toString());
		String result = eService.empInsert(emp);
		
	  return "�����->"+result;
	  }
	 
	  //�󼼺���
	  //Jackson�� json���� �����Ͽ� return 
	  @RequestMapping(value="/empDetail.do/{empid}",
			  		produces = MediaType.APPLICATION_JSON_VALUE,//������ �����Ͱ� ���̽� Ÿ���̾�.
			  		method=RequestMethod.GET)
	  public EmpVO empDetailMethod(@PathVariable int empid){
		  EmpVO emp = eService.selectById(empid);
		  //model.addAttribute("deptList",comService.deptSelectALl()); 
		  //model.addAttribute("jobList",comService.jobsSelectALl()); 
		  //model.addAttribute("managerList",comService.managerSelectALl()); 
		 // model.addAttribute("emp",emp);
		  return emp;
	  }
	  
	  //�󼼺��� ���� PUT�� �����̶�� �� --POST�� �Է�
	  //@REquestBody: ��û������ body�� data�� ���´�
	  @RequestMapping(value="/empDetail.do",
			  		consumes = "application/json",
			  		produces = "text/plain;charset=utf-8",
			  		method=RequestMethod.PUT)
	  public String empDetailPut(@RequestBody EmpVO emp,RedirectAttributes attr){
		  logger.info(emp.toString());
	
		  String result = eService.empUpdate(emp);
		  attr.addFlashAttribute("resultMessage",result);
		  
		  return "�����->"+result;
	  }
	  
	  //����
	  @RequestMapping(value="/empDelete.do/{empid}",
			  			produces = "text/plain;charset=utf-8",
			  			method=RequestMethod.DELETE)
	  public String empDelete(@PathVariable int empid) {

		  String result = eService.empDelete(empid);
		  
		  return "�����->"+result;
	  }
	  
	  
	  //@RequestMapping(value= "/empCondition.do",method = RequestMethod.GET)
	  //Ajax��û�� �迭�� ���� @RequestParam("deptid[]")Integer[] deptid�� ���
	  //�ϴ� ��û�� �迭�̸� @RequestParam("deptid")Integer[] deptid
	  //�ϴ� ��û�� �迭�̸� Integer[] deptid
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
	// MAP�̿�--@RequestParam �ʼ�
	/*
	 * @RequestMapping(value="/insert.do",method=RequestMethod.POST) public String
	 * registerpost(@RequestParam Map<String,String> map) {
	 * 
	 * for(String key:map.keySet()) { logger.info(key);
	 * logger.info(map.get(key).toString()); logger.info("------------"); }
	 * 
	 * return "emp/empInsert"; }
	 */
	//�Լ��� returnŸ��
		//1)String : ������ �̸��� �����Ҷ�
		//2)ModelAndView : ��+��
		@RequestMapping("/one")
		public ModelAndView test1(@RequestParam(name="my",required = false) String myname) {
			ModelAndView mv = new ModelAndView("sample/sampleView1");//�̰͵���
			mv.addObject("subject","model and view ����..."+myname);
			//mv.setViewName("sample/sampleView1");
			
			return mv;
		}
		
		//���٤���Ʈ
		@RequestMapping("/two")
		public String test2(RedirectAttributes redirectAttr) {
			logger.info("���û(redirect)��");
			redirectAttr.addFlashAttribute("resultMessage","��� �����̷�Ʈ �޼����Դϴ�");
			
			return "redirect:/emp/insert.do";
		}
}
