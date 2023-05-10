package com.shinhan.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.shinhan.model.DeptService;
import com.shinhan.vo.DeptVO;
import com.shinhan.vo.EmpVO;

@Controller
@RequestMapping("/dept")
public class DeptController {

	Logger logger = LoggerFactory.getLogger(DeptController.class);
	
	@ExceptionHandler(Exception.class)
	public String errorProcess(Exception ex) {
		ex.printStackTrace();
		logger.info(ex.getMessage());
		return "/error/error500";
	}

	@Autowired
	DeptService dService;

	// �μ���ȸ
	@RequestMapping("/deptList.do")
	public String deptList(Model model, HttpServletRequest request) {
		logger.info("---1.�μ���ȸ---");
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		if (flashMap != null) {
			Object message = flashMap.get("resultMessage");
			logger.info("(�Է�/����/������ ���� ���)Message:" + message);
		}
		List<DeptVO> deptlist = dService.DeptSelectAll();
		model.addAttribute("deptAll", deptlist);

		return "dept/deptList";
	}

	// �μ��Է�
	@RequestMapping(value = "/deptInsert.do", method = RequestMethod.GET)
	public String deptInsertGet() {
		logger.info("---2.�μ��Է� ��---");
		return "dept/deptInsert";
	}

	// �μ��Է�
	@RequestMapping(value = "/deptInsert.do", method = RequestMethod.POST)
	public String deptInsertPost(DeptVO dept, Model model,
					HttpServletRequest request, HttpSession session,
					RedirectAttributes redirectAttr) {
		logger.info("---2.�μ��Է� ����Ʈ---");
		String contextPaht=request.getContextPath();
		String method = request.getMethod();
		String remoteAddr = request.getRemoteAddr();
		
		String result = dService.deptInsert(dept);
		redirectAttr.addFlashAttribute("resultMessage",result);
		
		
		
		 return "redirect:/dept/deptList.do";
	}

	// �μ����� �󼼺��� 
	@RequestMapping(value="/deptDetail.do",method=RequestMethod.GET)
	  public String deptDetailMethod(int deptid,Model model){
		DeptVO dept = dService.deptById(deptid);
		logger.info("---3.�μ� �󼼺���---");		
		  model.addAttribute("dept",dept);
		  return "dept/deptDetail";
	  }
	
	
	//..����
	@RequestMapping(value="/deptUpdate.do",method=RequestMethod.POST)
	  public String deptDetailPost(DeptVO dept,RedirectAttributes attr){
		logger.info("---4.�μ� ����---");
		logger.info(dept.toString());
	
		  String result = dService.deptUpdate(dept);
		  attr.addFlashAttribute("resultMessage",result);
		  
		  return "redirect:/dept/deptList.do";
	  }
	
	// �μ�����
	  @RequestMapping("/deptDelete.do")
	  public String deptDelete(int deptid,RedirectAttributes attr) {
		  logger.info("---5.�μ�����---");
		  logger.info(deptid+"");
		  
		  String result = dService.deptDelete(deptid);
		  attr.addFlashAttribute("resultMessage",result);
		  
		  return "redirect:/dept/deptList.do";
	  }
}
