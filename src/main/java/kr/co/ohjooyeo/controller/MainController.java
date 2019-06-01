package kr.co.ohjooyeo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.ohjooyeo.service.UserService;

@Controller
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	
	@Autowired
	UserService userService;

	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		logger.debug("로그인페이지 페이지");		
		return "login";
	}
	
	@RequestMapping(value = "/loginCheck", method =RequestMethod.POST)
	public String loginCheck(
			@RequestParam String id,
			@RequestParam String pw,
			HttpSession session) {
		logger.debug("로그인페이지 확인");

		
		Map<String,String> loginMap = new HashMap<>();
		loginMap.put("id", id);
		loginMap.put("pw", pw);
		
	
		if(userService.loginCheck(loginMap)) {
			logger.debug("페이지 이동");
			return "redirect:/testCSS";
		} else {
			logger.debug("로그인 실패");
			return "redirect:/login?result=fail";
		}
	}
	
	//어플리케이션용 login API
	@RequestMapping(value = "/signin", method =RequestMethod.POST)
	public @ResponseBody boolean loginCheck(
			@RequestBody Map<String,String> loginMap ) {
		return userService.loginCheck(loginMap);
	}
}
