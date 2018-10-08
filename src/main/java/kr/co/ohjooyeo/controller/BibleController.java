package kr.co.ohjooyeo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.ohjooyeo.service.OrderService;
import kr.co.ohjooyeo.service.VersionService;
import kr.co.ohjooyeo.service.WorshipService;

@Controller

public class BibleController {
	@Autowired
	WorshipService worshipService;

	@Autowired
	OrderService orderService;
	
	@Autowired
	VersionService versionService;
	
	@RequestMapping(value = "/search-bible", method = RequestMethod.GET)
	public String searchBible(){
		
		return "search-bible";
	}
}
