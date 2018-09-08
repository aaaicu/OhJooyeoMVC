package kr.co.ohjooyeo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.ohjooyeo.service.WorshipService;
import kr.co.ohjooyeo.vo.WorshipOrderVO;
import kr.co.ohjooyeo.vo.WorshipVO;

@Controller

public class WorshipController {
	@Autowired
	WorshipService worshipService;
	
	@RequestMapping(value = "/worship", method = RequestMethod.GET)
	public String worshipForm() {
		return "worship-form";
	}

	@RequestMapping(value = "/add-worship", method = RequestMethod.POST)
	public String addWorship(@ModelAttribute WorshipVO vo) {
		// 디폴트 버전 
		vo.setVersion("aaa");
		//하드코딩 (유저생성시 변경)
		vo.setUserId("admin");
		
		//확인 
		System.out.println(vo);
		worshipService.addWorship(vo);
		
		return "redirect:worship";
	}
	
	@RequestMapping(value = "/worship-order", method = RequestMethod.GET)
	public String worshipOrderForm() {
		return "worship-order-form";
	}
	
	@RequestMapping(value = "/add-worship-order", method = RequestMethod.POST)
	public String addWorshipOrder(@ModelAttribute WorshipOrderVO vo) {
		//확인 
		System.out.println(vo);
		return "redirect:worship-order";
	}
	
	
}
