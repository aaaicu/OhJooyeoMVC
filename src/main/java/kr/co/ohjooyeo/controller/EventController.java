package kr.co.ohjooyeo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EventController {
	@RequestMapping( value="/miniLotto", method=RequestMethod.GET )
	public String MiniLotto() {
		return "miniLotto";
	}

}
